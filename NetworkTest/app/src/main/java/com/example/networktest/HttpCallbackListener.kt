package com.example.networktest

import java.lang.Exception

interface HttpCallbackListener {

    fun onFinish(response: String) {
        // 得到服务返回的具体内容
    }

    fun onError(e: Exception) {
        // 在这里对异常情况进行处理
    }

}