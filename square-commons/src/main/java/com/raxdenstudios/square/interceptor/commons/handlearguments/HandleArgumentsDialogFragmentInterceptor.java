package com.raxdenstudios.square.interceptor.commons.handlearguments;

import android.os.Bundle;

import com.raxdenstudios.square.interceptor.DialogFragmentInterceptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

/**
 * Created by agomez on 22/05/2015.
 */
public class HandleArgumentsDialogFragmentInterceptor extends DialogFragmentInterceptor<HandleArgumentsInterceptorCallback> implements HandleArgumentsInterceptor {

    public HandleArgumentsDialogFragmentInterceptor(@NonNull DialogFragment fragment) {
        super(fragment);
    }

    public HandleArgumentsDialogFragmentInterceptor(@NonNull DialogFragment fragment, @NonNull HandleArgumentsInterceptorCallback callback) {
        super(fragment, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mCallback.onHandleArguments(savedInstanceState, mDialogFragment.getArguments());
    }

}
