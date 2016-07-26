package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;

import com.raxdenstudios.commons.util.Utils;
import com.raxdenstudios.square.activity.interceptor.CheckPlayServicesInterceptor;
import com.raxdenstudios.square.activity.interceptor.callback.CheckPlayServicesInterceptorCallback;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 06/05/2015.
 */
public class CheckPlayServicesInterceptorImpl extends InterceptorActivityImpl<CheckPlayServicesInterceptor> implements CheckPlayServicesInterceptorCallback {

    private static final String TAG = CheckPlayServicesInterceptorImpl.class.getSimpleName();

    public CheckPlayServicesInterceptorImpl(Activity activity) {
        super(activity);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        checkGooglePlayServices();
    }

    private void checkGooglePlayServices() {
        if (Utils.checkPlayServices(getContext())) {
            mCallbacks.onGooglePlayServicesSupported();
        } else {
            mCallbacks.onGooglePlayServicesNotSupported();
        }
    }

}
