package org.andy.commonproject.main.ext

import android.os.Bundle
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.maps.MapView

/**
 * 綁定生命週期必須在onCreate階段執行
 */
fun MapView.bind(lifecycleOwner: LifecycleOwner, savedInstanceState: Bundle?){
	this.onCreate(savedInstanceState)
	lifecycleOwner.lifecycle.addObserver(object: DefaultLifecycleObserver{
		override fun onStart(owner: LifecycleOwner) {
			super.onStart(owner)
			this@bind.onStart()
		}

		override fun onResume(owner: LifecycleOwner) {
			super.onResume(owner)
			this@bind.onResume()
		}

		override fun onPause(owner: LifecycleOwner) {
			super.onPause(owner)
			this@bind.onPause()
		}

		override fun onStop(owner: LifecycleOwner) {
			super.onStop(owner)
			this@bind.onStop()
		}

		override fun onDestroy(owner: LifecycleOwner) {
			super.onDestroy(owner)
			this@bind.onDestroy()
			lifecycleOwner.lifecycle.removeObserver(this)
		}
	})

}