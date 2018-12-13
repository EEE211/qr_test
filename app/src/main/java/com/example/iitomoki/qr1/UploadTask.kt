package com.example.iitomoki.qr1

import android.os.AsyncTask
import android.os.Build
import android.support.annotation.RequiresApi
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


@RequiresApi(Build.VERSION_CODES.CUPCAKE)
class UploadTask(callback: (String?) -> Unit) : AsyncTask<String, Unit, String>() {

    var callback = callback

    override fun doInBackground(vararg params: String): String? {
        val urlSt = params[1]   //引数のURLを代入
        var httpConn: HttpURLConnection? = null

        try {
            var url = URL(urlSt)
            httpConn = url.openConnection() as HttpURLConnection
            //httpConn.requestMethod = "POST"
            httpConn.instanceFollowRedirects = false
            httpConn.requestMethod = params[0]
            httpConn.doOutput = true
            httpConn.doInput = true
            httpConn.readTimeout = 10000
            httpConn.connectTimeout = 20000
            httpConn.connect()  //通信開始

            val status = httpConn.responseCode

            //通信成功ならデータ取得
            if (status == HttpURLConnection.HTTP_OK) {
                val br = BufferedReader(InputStreamReader(httpConn.inputStream))

                var line: String? = null
                val sb = StringBuilder()

                for (line in br.readLines()) {
                    line?.let { sb.append(line) }
                }

                br.close()
                var responseData = sb.toString()

                return responseData     //取得したデータを返す

            } else {
                println("ERROR ${status}")
            }

        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            httpConn?.disconnect()
        }
        return null
    }

    //取得したデータを使うクラスに返す用
    override fun onPostExecute(result: String) {
        super.onPostExecute(result)

        callback(result)

    }

}