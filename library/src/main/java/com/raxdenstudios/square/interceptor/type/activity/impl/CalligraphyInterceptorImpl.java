package com.raxdenstudios.square.interceptor.type.activity.impl;

import android.app.Activity;
import android.content.Context;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.InterceptorConfig;
import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class CalligraphyInterceptorImpl
        extends ActivityInterceptor<InterceptorConfig, InterceptorCallback<InterceptorConfig>>
        implements InterceptorConfig {

    public CalligraphyInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

        newBase = CalligraphyContextWrapper.wrap(newBase);
    }

}

