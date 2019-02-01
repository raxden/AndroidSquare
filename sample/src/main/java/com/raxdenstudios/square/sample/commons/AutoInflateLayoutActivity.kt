package com.raxdenstudios.square.sample.commons

import android.os.Bundle
import android.view.View
import com.raxdenstudios.square.SquareActivity
import com.raxdenstudios.square.interceptor.Interceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.autoinflatelayout.AutoInflateLayoutInterceptorCallback

class AutoInflateLayoutActivity
    : SquareActivity(),
        AutoInflateLayoutInterceptorCallback {

    var mContentView: View? = null

    // ======== InflateLayoutInterceptorCallback ===============================================

    override fun onContentViewCreated(view: View, savedInstanceState: Bundle?) {
        mContentView = view
    }

    // ======== SUPPORT METHODS ====================================================================

    override fun setupInterceptors(interceptorList: MutableList<Interceptor>) {
        interceptorList.add(AutoInflateLayoutActivityInterceptor(this, this))
    }

}