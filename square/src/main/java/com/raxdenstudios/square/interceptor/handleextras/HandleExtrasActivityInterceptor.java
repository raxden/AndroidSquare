package com.raxdenstudios.square.interceptor.handleextras;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class HandleExtrasActivityInterceptor
        extends ActivityInterceptor<HandleExtrasInteractor, HandleExtrasInterceptorCallback>
        implements HandleExtrasInteractor {

    public HandleExtrasActivityInterceptor(@NonNull Activity activity, @NonNull HandleExtrasInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = mActivity.getIntent() != null ? mActivity.getIntent().getExtras() : new Bundle();
        if (mCallback != null) {
            mCallback.onHandleExtras(savedInstanceState, extras);
        }
    }

}
