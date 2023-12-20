package com.example.test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.test.pool.HandlerThreadPool
import kotlinx.android.synthetic.main.activity_thread_pool.*
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class ThreadPoolActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread_pool)

        btn_create_thread.setOnClickListener {
            for (i in 0 until 9){
                HandlerThreadPool.getHandlerThread(i.toString()).start()
            }
        }
        btn_get_thread.setOnClickListener {
            println(HandlerThreadPool.getPoolSize())
            val threadPoolExecutor = ThreadPoolExecutor(
                10,
                10,
                0L,
                TimeUnit.MILLISECONDS,
                ArrayBlockingQueue<Runnable>(10)
            )
            threadPoolExecutor.execute { println("Hi 线程池") }
        }
    }
}