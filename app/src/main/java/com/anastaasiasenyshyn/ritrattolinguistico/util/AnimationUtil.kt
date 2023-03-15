package com.anastaasiasenyshyn.ritrattolinguistico.util

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.anastaasiasenyshyn.ritrattolinguistico.R

object AnimationUtil {

    fun startIntentWithSlideInRightAnimation(activity: Activity, intent: Intent, extras: Bundle?) {

        if (extras != null) {
            intent.putExtras(extras)
        }
        activity.startActivity(intent)
        activity.overridePendingTransition(
            R.anim.move_right_in_activity,
            R.anim.move_left_out_activity
        )
    }
}