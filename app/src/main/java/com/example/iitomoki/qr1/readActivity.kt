package com.example.iitomoki.qr1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class readActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)


        val integrator = IntentIntegrator(this@readActivity)
        integrator.setOrientationLocked(false)
        integrator.initiateScan()


        //IntentIntegrator(this@readActivity).initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(intentResult != null) {
            Log.d("readQR", intentResult.getContents());
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }

        val code_text = findViewById(R.id.code) as TextView
        code_text.text = intentResult.getContents()

    }
}
