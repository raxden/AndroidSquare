package com.raxdenstudios.square.interceptor.type.application.impl;

import android.app.Application;

import com.raxdenstudios.square.interceptor.type.ApplicationInterceptor;
import com.raxdenstudios.square.interceptor.callback.JodaTimeInterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.JodaTimeInterceptorInteractor;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by Ángel Gómez on 28/07/2016.
 */
public class JodaTimeInterceptorImpl
        extends ApplicationInterceptor<JodaTimeInterceptorInteractor, JodaTimeInterceptorCallback>
        implements JodaTimeInterceptorInteractor {

    public JodaTimeInterceptorImpl(Application application) {
        super(application);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(mApplication);
    }

}