package com.example.test

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_component.*

class ComponentActivity : AppCompatActivity() {

    private var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_component)
        tv_content.text = "CCCC"

        btn_save.setOnClickListener {
            spSave()
        }
        btn_load.setOnClickListener {
            loadFromSp()
        }

    }
    private fun spSave(){
        val edit = getSharedPreferences("userId", Context.MODE_PRIVATE).edit()
        val commit = edit.putString("name", "ccx $index").commit()
        index++
        if (commit){
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "failure", Toast.LENGTH_SHORT).show()
        }
    }

    private fun loadFromSp(){
        val sharedPreferences = getSharedPreferences("userId", Context.MODE_PRIVATE)
        val str = sharedPreferences.getString("name", "failure")
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
}
