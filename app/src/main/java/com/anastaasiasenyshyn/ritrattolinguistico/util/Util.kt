package com.anastaasiasenyshyn.ritrattolinguistico.util

import android.app.Activity
import android.content.Context
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping.ImageMapping
import com.anastaasiasenyshyn.ritrattolinguistico.model.imagemapping.Mapping

class Util {

    companion object {

        const val TAG = "Util"
        fun commitIfActivityAlive(activity: Activity, fragmentTransaction: FragmentTransaction) {
            if (isActivityAlive(activity)) {
                try {
                    fragmentTransaction.commit()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
        }

        fun findMappedObject(
            currentImageMapping: ImageMapping?,
            pixelRGBCode: String,
            xPerc: Int,
            yPerc: Int
        ): Mapping? {
            Log.i(TAG,"<findMappedObject> pixelRGBCode: $pixelRGBCode")
            val imageMappingList = currentImageMapping?.filter { it.color == pixelRGBCode  }
            return if (!imageMappingList.isNullOrEmpty()){
                val imageMapping=imageMappingList[0]
                val objMapping = imageMapping.mapping?.filter { xPerc >= it?.xInit!! && xPerc <= it?.xEnd!! && yPerc >= it?.yInit!! && yPerc <= it?.yEnd!!}
                if (objMapping != null) objMapping[0] else null
            } else null
        }

        private fun isActivityAlive(activity: Activity?): Boolean {
            return activity != null && !activity.isFinishing
        }

        fun readBooleanSharedPreference(sharedKey: String, ctx: Context): Boolean? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            var value: Boolean? = null
            if (preferences.contains(sharedKey)) {
                value = preferences.getBoolean(sharedKey, false)
            }

            return value
        }

        fun readStringSharedPreference(sharedKey: String, ctx: Context): String? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            return preferences.getString(sharedKey, null)
        }

        fun readIntSharedPreference(sharedKey: String, ctx: Context): Int? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            return preferences.getInt(sharedKey, 0)
        }

        fun readLongSharedPreference(sharedKey: String, ctx: Context): Long? {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            return preferences.getLong(sharedKey, 0)
        }

        fun writeBooleanSharedPreference(sharedKey: String, value: Boolean?, ctx: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            val editor = preferences.edit()
            editor.putBoolean(sharedKey, value!!)
            editor.commit()
        }

        fun writeStringSharedPreference(sharedKey: String, value: String, ctx: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            val editor = preferences.edit()
            editor.putString(sharedKey, value)
            editor.commit()
        }

        fun writeIntSharedPreference(sharedKey: String, value: Int?, ctx: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            val editor = preferences.edit()
            editor.putInt(sharedKey, value!!)
            editor.commit()
        }

        fun writeLongSharedPreference(sharedKey: String, value: Long?, ctx: Context) {
            val preferences = PreferenceManager.getDefaultSharedPreferences(ctx)
            val editor = preferences.edit()
            editor.putLong(sharedKey, value!!)
            editor.commit()
        }
    }
}