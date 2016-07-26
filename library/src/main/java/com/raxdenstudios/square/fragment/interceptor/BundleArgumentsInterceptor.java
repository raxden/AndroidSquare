package com.raxdenstudios.square.fragment.interceptor;

import android.os.Bundle;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.fragment.interceptor.delegate.BundleArgumentsInterceptorDelegate;

/**
 * Created by agomez on 22/05/2015.
 */
public interface BundleArgumentsInterceptor extends Interceptor {

    void onInterceptorCreated(BundleArgumentsInterceptorDelegate callback);

    void onHandleArguments(Bundle savedInstanceState, Bundle arguments);

}
