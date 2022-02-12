package org.andy.commonproject.main.frgment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.google.android.material.transition.MaterialSharedAxis

abstract class BaseFragment<T:ViewBinding>(layoutId:Int):Fragment() {
	private var _vb:T? = null
	val vb:T get() = _vb!!

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enterTransition = MaterialSharedAxis(MaterialSharedAxis.Z, false)
		exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true)
		val callback = object : OnBackPressedCallback(true) {
			override fun handleOnBackPressed() {
				backPressed()
			}
		}
		requireActivity().onBackPressedDispatcher.addCallback(this, callback)
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		_vb = createViewBinding(inflater)
		return vb.root
	}

	override fun onDestroyView() {
		super.onDestroyView()
		_vb = null
	}

	fun isViewExist():Boolean{
		return _vb != null
	}

	abstract fun createViewBinding(inflater: LayoutInflater):T
	abstract fun backPressed()
}