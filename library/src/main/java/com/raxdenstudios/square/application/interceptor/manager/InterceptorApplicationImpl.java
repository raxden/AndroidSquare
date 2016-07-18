package com.raxdenstudios.square.application.interceptor.manager;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.raxdenstudios.square.application.InterceptorApplication;

/**
 * Created by agomez on 13/07/2015.
 */
public class InterceptorApplicationImpl implements IInterceptorApplication {

    private static final String TAG = InterceptorApplicationImpl.class.getSimpleName();

    protected Application mApplication;

    public InterceptorApplicationImpl(Application application) {
        mApplication = application;
        checkContextIfInterceptorApplicationInstance(application);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

    }

    @Override
    public void onCreate() {

    }

    public Context getContext() {
        return mApplication.getApplicationContext();
    }

    private void checkContextIfInterceptorApplicationInstance(Application application) {
        if (!(application instanceof InterceptorApplication)) {
            throw new IllegalStateException(this.getClass().getSimpleName()+" interceptor must be used just on IInterceptorApplication");
        }
    }

}
