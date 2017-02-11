package com.raxdenstudios.square.interceptor.butterknife;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import butterknife.ButterKnife;

/**
 * Created by Ángel Gómez on 23/07/2016.
 */
public class ButterKnifeActivityInterceptor
        extends ActivityInterceptor<ButterKnifeInterceptorInteractor, ButterKnifeInterceptorCallback>
        implements ButterKnifeInterceptorInteractor {

    public ButterKnifeActivityInterceptor(@NonNull Activity activity) {
        super(activity, null);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(mActivity);
    }

}
