package com.raxdenstudios.square;

import android.content.res.Configuration;
import android.support.multidex.MultiDexApplication;

import com.raxdenstudios.square.interceptor.ApplicationInterceptor;
import com.raxdenstudios.square.manager.ApplicationMultiDexInterceptorManager;
import com.raxdenstudios.square.manager.InterceptorManagerFactory;

import java.util.List;

/**
 * Created by Ángel Gómez
 *
 * SquareMultiDexApplication is an abstract class that adds interceptor functionality to the
 * MultiDexApplication.
 */
public abstract class SquareMultiDexApplication extends MultiDexApplication {

    private ApplicationMultiDexInterceptorManager mInterceptorManager;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getInterceptorManager().onCreate();
    }

    /* Support methods */

    private ApplicationMultiDexInterceptorManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (ApplicationMultiDexInterceptorManager) InterceptorManagerFactory
                    .buildManager(this);
            addInterceptor(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected abstract void addInterceptor(List<ApplicationInterceptor> interceptors);

}
