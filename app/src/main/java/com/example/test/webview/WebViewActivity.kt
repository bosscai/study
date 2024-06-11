package com.example.test.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.ValueCallback
import com.example.test.R
//import com.squareup.leakcanary.LeakCanary
import kotlinx.android.synthetic.main.activity_web_view.*
//import okhttp3.*
//import org.greenrobot.eventbus.EventBus
//import java.io.IOException

class WebViewActivity : AppCompatActivity() {

    private val TAG = "WebViewActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        val settings = webView.settings
        settings.allowContentAccess = true
        webView.loadUrl("file:///android_asset/javascript.html");
        btnWeb.setOnClickListener {
            webView.post {
                webView.evaluateJavascript("javascript:callJS()", ValueCallback {
                    Log.e(TAG, "onCreate: $it")
                })
            }
        }

//        btnHttp.setOnClickListener {
//            val client = OkHttpClient()
//            client.interceptors()
//            client.newCall(Request.Builder().get().url("https://www.baidu.com").build())
//                .enqueue(object :
//                    Callback {
//                    override fun onFailure(call: Call, e: IOException) {
//                        Log.e(TAG, "onFailure OkHttpClient: $call")
//                    }
//
//                    override fun onResponse(call: Call, response: Response) {
//                        Log.e(TAG, "onResponse OkHttpClient: $call")
//                    }
//
//                })
//        }
    }
}