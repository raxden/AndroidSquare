package com.raxdenstudios.square.activity.interceptor;

import com.raxdenstudios.square.Interceptor;

/**
 * Created by agomez on 06/05/2015.
 */
public interface CheckPlayServicesInterceptor extends Interceptor {

    void onGooglePlayServicesSupported();

    void onGooglePlayServicesNotSupported();

    interface CheckPlayServicesInterceptorCallback {

    }
}
