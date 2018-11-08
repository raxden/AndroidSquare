package com.raxdenstudios.square.interceptor.commons.handlearguments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.raxdenstudios.square.interceptor.FragmentInterceptor;

/**
 * Created by agomez on 22/05/2015.
 */
public class HandleArgumentsFragmentInterceptor extends FragmentInterceptor<HandleArgumentsInterceptorCallback> implements HandleArgumentsInterceptor {

    public HandleArgumentsFragmentInterceptor(@NonNull Fragment fragment) {
        super(fragment, null);
    }

    public HandleArgumentsFragmentInterceptor(@NonNull Fragment fragment, @NonNull HandleArgumentsInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getCallback().onHandleArguments(savedInstanceState, getFragment().getArguments());
    }

}
