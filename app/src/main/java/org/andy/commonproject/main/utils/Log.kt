package org.andy.commonproject.main.utils

import android.util.Log
import androidx.viewbinding.BuildConfig
import org.json.JSONObject


object Log {

	private val LINE_SEPARATOR = System.getProperty("line.separator")
	private const val EXCEPTION_START = "****************EXCEPTION START****************"
	private const val EXCEPTION_END = "****************EXCEPTION END****************"
	private const val TAG = "customlog"

	const val IS_DEBUG = BuildConfig.DEBUG

	private val process: String
		get() {
			val elements = Thread.currentThread().stackTrace
			var className = elements[4].className
			className = className.substring(className.lastIndexOf(".") + 1)
			val methodName = elements[4].methodName

			return "[" + Thread.currentThread().id + "]" + "[" + className + "]" + "[" + methodName + "]" + "[" + elements[4].lineNumber + "]"
		}

	fun d(tag: String, msg: String) {
		var msg = msg
		if (!IS_DEBUG) {
			return
		}
		try {
			msg = process + msg
			Log.d(tag, msg)
		} catch (e: Exception) {
			Log.e(TAG, e.toString())
		}

	}

	fun exception(tag: String, exc: Exception) {
		if (!IS_DEBUG) {
			return
		}
		try {
			val process = process
			val msg = process + exc.toString()
			Log.e(tag, msg)
		} catch (e: Exception) {
			android.util.Log.e(TAG, e.toString())
		}

	}

	fun e(tag: String, msg: String) {
		var msg = msg
		if (!IS_DEBUG) {
			return
		}
		try {
			msg = process + msg
			Log.e(tag, msg)
		} catch (e: Exception) {
			Log.e(TAG, e.toString())
		}

	}

	fun i(tag: String, msg: String) {
		var msg = msg
		if (!IS_DEBUG) {
			return
		}
		try {
			msg = process + msg
			Log.i(tag, msg)
		} catch (e: Exception) {
			Log.e(TAG, e.toString())
		}

	}

	fun v(tag: String, msg: String) {
		var msg = msg
		if (!IS_DEBUG) {
			return
		}
		try {
			msg = process + msg
			Log.v(tag, msg)
		} catch (e: Exception) {
			Log.e(TAG, e.toString())
		}

	}

	fun w(tag: String, msg: String) {
		var msg = msg
		if (!IS_DEBUG) {
			return
		}
		try {
			msg = process + msg
			Log.w(TAG, msg)
		} catch (e: Exception) {
			Log.e(TAG, e.toString())
		}

	}

	fun e(e: Exception) {
		if (!IS_DEBUG) {
			return
		}
		try {
			val msg = process
			Log.e(TAG, msg + EXCEPTION_START)
			Log.e(TAG, msg + e.toString())
			Log.e(TAG, msg + EXCEPTION_END)
		} catch (e1: Exception) {
			Log.e(TAG, e1.toString())
		}

	}
	private fun printLine( tag:String,  isTop:Boolean) {
		if (isTop) {
			Log.d(tag, "$process╔═══════════════════════════════════════════════════════════════════════════════════════");
		} else {
			Log.d(tag, "$process╚═══════════════════════════════════════════════════════════════════════════════════════");
		}
	}

	fun json(tag:String, json:JSONObject){

		if(LINE_SEPARATOR != null){
			val msg = json.toString(4)
			msg.split(LINE_SEPARATOR).apply {
				if(this.isNotEmpty()) {
					printLine(tag, true)
					for (line in this) {
						Log.d(tag, "$process║$line")
					}
					printLine(tag, false)
				}
			}
		}



	}
}