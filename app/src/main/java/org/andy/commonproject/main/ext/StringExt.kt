package org.andy.commonproject.main.ext

import android.app.AlertDialog
import android.content.Context
import okhttp3.*
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

const val  CONNECT_TIMEOUT = 15L
const val  READ_TIMEOUT = 15L

@Throws(IOException::class)
fun String.get(): Response {
	val request = Request.Builder()
		.url(this)
		.get()
		.build()
	return  OkHttpClient().newBuilder()
		.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
		.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
		.build()
		.newCall(request)
		.execute()
}

@Throws(IOException::class)
fun String.post(body: RequestBody? = null, headers: Headers? = null):Response{
	val request = Request.Builder().apply {
		this.url(this@post)
		if(body != null) {
			this.post(body)
		}
		if(headers != null){
			this.headers(headers)
		}
	}.build()

	return OkHttpClient().newBuilder()
		.connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
		.readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
		.build()
		.newCall(request)
		.execute()
}

@Throws(JSONException::class)
fun String.toJsonObject(): JSONObject {
	return JSONObject(this)
}

@Throws(JSONException::class)
fun String.toJsonArray(): JSONArray {
	return JSONArray(this)
}

fun String.toAlert(context: Context, title:String? = null, okText:String, block:(() -> Unit)? = null): AlertDialog {
	return  AlertDialog.Builder(context).apply {
		title?.also {  this.setTitle(it) }
		this.setMessage(this@toAlert)
			.setPositiveButton(okText){_,_ ->
				block?.invoke()
			}
	}.create()
}