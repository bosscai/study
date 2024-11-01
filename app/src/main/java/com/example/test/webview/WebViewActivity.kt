package com.example.test.webview

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.JsResult
import android.webkit.ValueCallback
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        settings.javaScriptEnabled = true
        webView.loadUrl("file:///android_asset/javascript.html");
        Log.e(TAG, "btnWeb onCreate: " + btnWeb.hashCode() )
        //JS向native发送消息
        btnWeb.setOnClickListener {
            webView.evaluateJavascript("javascript:callJS()", ValueCallback {
                Log.e(TAG, "onCreate: $it")
                Toast.makeText(baseContext, it, Toast.LENGTH_SHORT).show()
            })
        }

        webView.webChromeClient = object : WebChromeClient(){
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                val builder = AlertDialog.Builder(baseContext)
                builder.setTitle("Alert")
                builder.setMessage(message)
                builder.setPositiveButton("OK", object : DialogInterface.OnClickListener{
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        result?.confirm()
                    }
                })
                builder.setCancelable(false)
//                builder.create().show()
                return false
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