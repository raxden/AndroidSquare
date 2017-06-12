package com.raxdenstudios.square.interceptor.icepick;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import icepick.Icepick;

/**
 * Created by Ángel Gómez on 11/02/2017.
 */

public class IcepickActivityInterceptor
        extends ActivityInterceptor<IcepickInteractor, IcepickInterceptorCallback>
        implements IcepickInteractor {

    public IcepickActivityInterceptor(@NonNull Activity activity) {
        super(activity);
    }

    public IcepickActivityInterceptor(@NonNull Activity activity, @NonNull IcepickInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState(this, savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

}
