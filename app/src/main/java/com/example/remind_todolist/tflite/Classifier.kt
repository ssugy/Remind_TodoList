package com.example.remind_todolist.tflite

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.util.Pair
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

// 실제 분류 모델 클래스
class Classifier(
    val context: Context
) {
    // 에셋폴더 파일이름 저장
    companion object{
        val MODEL_NAME = "keras_model_cnn.tflite"
    }


    // 1. 우선 Assets폴더에 있는 파일 접근해야된다. 그러기위해선 Context가 필요하다.(생성자로 넣음)
    // 2. 에셋폴더에있는, tflite파일을 읽어오는 함수 구현 -> ByteBuffer 클래스모델 반환
    // 여기 각각 분석해야됨
    fun loadModelFile(tfliteModelName : String): ByteBuffer{
        val assetManager : AssetManager = context.assets        // 에셋 매니저 객체
        val assetFileDescriptor : AssetFileDescriptor = assetManager.openFd(tfliteModelName)
        val fileInputStream = FileInputStream(assetFileDescriptor.fileDescriptor)
        val fileChannel = fileInputStream.channel
        val startOffset = assetFileDescriptor.startOffset
        val declareLength = assetFileDescriptor.declaredLength

        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declareLength)
    }

    // Init() - 초기화
    var interpreter: Interpreter? = null

    fun init(){
        val model = loadModelFile(MODEL_NAME)
        model.order(ByteOrder.nativeOrder())
        interpreter = Interpreter(model)
        
        // 이미지 입출력 크기 계산
        initModelShape()
    }

    // 모델의 출력 클래스 수를 담을 멤버 변수 선언
    var modelOutputClasses : Int = 0

    var modelInputChannel : Int = 0
    var modelInputWidth : Int = 0
    var modelInputHeight : Int = 0

    fun initModelShape(){
        val inputTensor = interpreter?.getInputTensor(0)
        val inputShape = inputTensor?.shape()

        // 널관련 체크 어느시점에 해야될지 정확히 몰라서 우선 이렇게 처리함
        modelInputChannel = inputShape?.get(0) ?: 0
        modelInputWidth = inputShape?.get(1) ?: 0
        modelInputHeight = inputShape?.get(2) ?: 0

        val outputTensor = interpreter!!.getOutputTensor(0)
        val outputShape = outputTensor.shape()
        modelOutputClasses = outputShape[1]
    }

    // 이미지 보간 처리
    private fun resizeBitmap(bitmap: Bitmap) : Bitmap{
        return Bitmap.createScaledBitmap(bitmap, modelInputWidth, modelInputHeight, false)
    }

    // ARGB를 GrayScale로 변환하면서 Bitmap을 ByteBuffer 포맷으로 변환
    private fun convertBitmapToGrayByteBuffer(bitmap: Bitmap): ByteBuffer{
        val byteBuffer = ByteBuffer.allocateDirect(bitmap.byteCount)
        byteBuffer.order(ByteOrder.nativeOrder())

        val pixels = IntArray(bitmap.width * bitmap.height, {0})
        bitmap.getPixels(pixels, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        // 코틀린 비트연산자 shr(오른쪽이동 >>)  (8 >> 1 = 4이다)
        for (pixel in pixels){
            val r = (pixel shr (16 and 0xFF))
            val g = (pixel shr (8 and 0xFF))
            val b = (pixel and 0xFF)

            val avgPixelValue = (r + g + b)/3.0f
            val normalizedPixelValue = avgPixelValue / 255.0f

            byteBuffer.putFloat(normalizedPixelValue)
        }

        return  byteBuffer
    }

    
    ///////////////////////// 여기서부터는 제대로 확인하지 못함
    private fun argmax(array: FloatArray): Pair<Int, Float>? {
        var argmax = 0
        var max = array[0]
        for (i in 1 until array.size) {
            val f = array[i]
            if (f > max) {
                argmax = i
                max = f
            }
        }
        return Pair(argmax, max)
    }

    fun classify(image: Bitmap?): Pair<Int, Float>? {
        val buffer = convertBitmapToGrayByteBuffer(resizeBitmap(image!!))
        val result = Array(1) {
            FloatArray(
                modelOutputClasses
            )
        }
        interpreter!!.run(buffer, result)
        return argmax(result[0])
    }

    fun finish() {
        if (interpreter != null) interpreter!!.close()
    }
}