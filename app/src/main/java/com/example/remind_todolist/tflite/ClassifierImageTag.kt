package com.example.remind_todolist.tflite

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.util.Log
import android.util.Pair
import android.widget.Toast
import org.tensorflow.lite.Interpreter
import org.tensorflow.lite.Tensor
import org.tensorflow.lite.support.common.FileUtil
import org.tensorflow.lite.support.common.ops.NormalizeOp
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.TensorImage
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.label.TensorLabel
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

// 실제 분류 모델 클래스
class ClassifierImageTag(
    val context: Context
) {
    // 에셋폴더 파일이름 저장
    companion object{
        val MODEL_NAME_IMAGE = "mobilenet_imagenet_model.tflite"
        val LABEL_FILE = "labels.txt"
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
    lateinit var labels : List<String>
    fun initImageClassify(){
        val model = FileUtil.loadMappedFile(context, MODEL_NAME_IMAGE)
        model.order(ByteOrder.nativeOrder())
        interpreter = Interpreter(model)

        // 이미지 입출력 크기 계산
        initModelShape()

        // 라벨 추가 - 클래스 최종 라벨 필요함 1000개 구분
        labels = FileUtil.loadLabels(context, LABEL_FILE)
    }


    // 모델의 출력 클래스 수를 담을 멤버 변수 선언
    var modelOutputClasses : Int = 0

    var modelInputChannel : Int = 0
    var modelInputWidth : Int = 0
    var modelInputHeight : Int = 0

    var inputImage : TensorImage = TensorImage()
    lateinit var outputBuffer : TensorBuffer

    fun initModelShape(){
        // inputtensor부터 null처리하기
        val inputTensor = interpreter?.getInputTensor(0)
        if (inputTensor == null){
            Log.d("tensor", "InputTensor는 null이다.")
            return
        }

        val inputShape = inputTensor!!.shape()

        // 널처리 완료됨
        modelInputChannel = inputShape.get(0)
        modelInputWidth = inputShape.get(1)
        modelInputHeight = inputShape.get(2)

        inputImage = TensorImage(inputTensor.dataType())

        val outputTensor = interpreter!!.getOutputTensor(0)
        outputBuffer = TensorBuffer.createFixedSize(
            outputTensor.shape(),
            outputTensor.dataType()
        )
    }

    // 이미지 처리 1번 =  ARGB_8888타입으로 변경
    private fun convertBitmapToARGB8888(bitmap: Bitmap): Bitmap {
        return bitmap.copy(Bitmap.Config.ARGB_8888, true)
    }

    private fun loadImage(bitmap: Bitmap): TensorImage {
        if (bitmap.config != Bitmap.Config.ARGB_8888) {
            inputImage.load(convertBitmapToARGB8888(bitmap))
        } else {
            inputImage.load(bitmap)
        }
        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(modelInputWidth, modelInputHeight, ResizeOp.ResizeMethod.NEAREST_NEIGHBOR))
            .add(NormalizeOp(0.0f, 255.0f))
            .build()
        return imageProcessor.process(inputImage)
    }

// 비트맵 이미지를 받아서, Pair형식으로 반환. 실질적인 분류작업 이루어짐
    fun classify(image: Bitmap): Pair<String, Float> {
        inputImage = loadImage(image)
        interpreter!!.run(inputImage.buffer, outputBuffer.buffer.rewind())
        val output = TensorLabel(labels, outputBuffer).mapWithFloatValue
        return argmax(output)
    }


    /////////////////////////map 형식으로 데이터를 받아서, Pair형식으로 반환
    private fun argmax(map: Map<String, Float>): Pair<String, Float> {
        var maxKey = ""
        var maxVal = -1f
        for ((key, f) in map){
            if (f > maxVal) {
                maxKey = key
                maxVal = f
            }
        }
        return Pair(maxKey, maxVal)
    }
    
    // 마지막에는 인터프리터 종료
    fun finish() {
        if (interpreter != null) interpreter!!.close()
    }
}