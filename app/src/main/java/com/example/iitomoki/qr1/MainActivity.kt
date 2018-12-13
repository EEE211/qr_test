package com.example.iitomoki.qr1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Build
import android.support.annotation.RequiresApi
import android.widget.Button
import android.content.Intent


class MainActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //buttonの定義
        val generate_button = findViewById(R.id.generate) as Button
        val read_button = findViewById(R.id.read) as Button

        //QR生成の画面へ遷移
        generate_button.setOnClickListener {
            val intent = Intent(this, generateActivity::class.java)
            startActivity(intent)
        }

        //QR読取の画面へ遷移
        read_button.setOnClickListener {
            val intent = Intent(this, readActivity::class.java)
            startActivity(intent)
        }

    }
}
