package com.raxdenstudios.square.interceptor.type.fragment.impl;

import android.app.Fragment;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.type.FragmentInterceptor;
import com.raxdenstudios.square.interceptor.callback.BundleArgumentsInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.BundleArgumentsInterceptorConfig;

/**
 * Created by agomez on 22/05/2015.
 */
public class BundleArgumentsInterceptorImpl
        extends FragmentInterceptor<BundleArgumentsInterceptorConfig, BundleArgumentsInterceptorCallback>
        implements BundleArgumentsInterceptorConfig {

    public BundleArgumentsInterceptorImpl(Fragment fragment) {
        super(fragment);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCallback.onHandleArguments(savedInstanceState, mFragment.getArguments());
    }

}
