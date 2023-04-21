package com.anastaasiasenyshyn.ritrattolinguistico.util

import android.app.Activity
import android.content.Context
import android.preference.PreferenceManager
import androidx.fragment.app.FragmentTransaction

class Util {

    companion object {
        fun commitIfActivityAlive(activity: Activity, fragmentTransaction: FragmentTransaction) {
            if (isActivityAlive(activity)) {
                try {
                    fragmentTransaction.commit()
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }
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