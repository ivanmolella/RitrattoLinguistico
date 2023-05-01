package com.anastaasiasenyshyn.ritrattolinguistico

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.anastaasiasenyshyn.ritrattolinguistico.util.AnimationUtil

val handler : Handler = Handler(Looper.getMainLooper())

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler.postDelayed(object : Runnable {
            override fun run() {
                //startActivity(Intent(this@SplashActivity, DemoActivity::class.java))
                AnimationUtil.startIntentWithSlideInRightAnimation(this@SplashActivity,Intent(this@SplashActivity, DemoActivity::class.java),null)
                finish()
            }
        },1500)
    }
}