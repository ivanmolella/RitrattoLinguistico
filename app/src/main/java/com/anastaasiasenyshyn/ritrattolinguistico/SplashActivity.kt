package com.anastaasiasenyshyn.ritrattolinguistico

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.anastaasiasenyshyn.ritrattolinguistico.ritratto.RitrattoLinguisticoFragment
import com.anastaasiasenyshyn.ritrattolinguistico.util.AnimationUtil
import com.anastaasiasenyshyn.ritrattolinguistico.util.Util

val handler : Handler = Handler(Looper.getMainLooper())

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        handler.postDelayed(object : Runnable {
            override fun run() {
                //startActivity(Intent(this@SplashActivity, DemoActivity::class.java))
                var intent : Intent? = null
                val isSlideRequired = checkSlideRequired()
                if (isSlideRequired){
                    intent = Intent(this@SplashActivity, DemoActivity::class.java)
                }else{
                    intent = Intent(this@SplashActivity, MainActivity::class.java)
                }
                AnimationUtil.startIntentWithSlideInRightAnimation(this@SplashActivity,intent,null)
                finish()
            }
        },1500)
    }

    private fun checkSlideRequired(): Boolean {
        val isRequired =
            Util.readBooleanSharedPreference(Constants.SHAR_SLIDE_MAIN_DONE, this)
        Log.i(RitrattoLinguisticoFragment.TAG, "checkMainSlideRequired: $isRequired")
        return isRequired ?: true
    }
}