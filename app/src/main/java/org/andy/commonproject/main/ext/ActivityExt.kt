package org.andy.commonproject.main.ext

import android.app.Activity
import android.graphics.Color
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

private var originalStatusBarColor: Int? = null
private var originalNavigationBarColor: Int? = null

fun Activity.enterEdgeToEdge(systemBarColor:Int) {
	originalStatusBarColor = originalStatusBarColor ?: window.statusBarColor
	originalNavigationBarColor = originalNavigationBarColor ?: window.navigationBarColor

	WindowCompat.setDecorFitsSystemWindows(window, false)
	window.statusBarColor = Color.TRANSPARENT
	window.navigationBarColor = Color.TRANSPARENT
}

fun Activity.exitEdgeToEdge() {
	WindowCompat.setDecorFitsSystemWindows(window, true)
	window.statusBarColor = originalStatusBarColor ?: 0
	window.navigationBarColor = originalNavigationBarColor ?: 0
}

fun Activity.enableFullScreen(){
	WindowInsetsControllerCompat(window, window.decorView).let { controller ->
		controller.hide(WindowInsetsCompat.Type.systemBars())
		controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
	}
}

fun Activity.disableFullScreen(){
	WindowInsetsControllerCompat(window, window.decorView).let { controller ->
		controller.show(WindowInsetsCompat.Type.systemBars())
		controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_TOUCH
	}
}