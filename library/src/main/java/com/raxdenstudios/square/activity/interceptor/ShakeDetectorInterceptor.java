package com.raxdenstudios.square.activity.interceptor;

/**
 * Created by agomez on 06/05/2015.
 */
public interface ShakeDetectorInterceptor {

    void onInterceptorLoaded(ShakeDetectorInterceptorCallback callback);

    void shakeDetected();

    interface ShakeDetectorInterceptorCallback {

    }
}
