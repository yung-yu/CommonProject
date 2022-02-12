package org.andy.commonproject.main.ext;

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment


fun Fragment.back(isRoot:Boolean = false){
	if(isRoot) {
		activity?.finish()
	} else {
		if(!parentFragmentManager.isStateSaved
			&& !parentFragmentManager.isDestroyed) {
			parentFragmentManager.popBackStackImmediate()
		}
	}
}
