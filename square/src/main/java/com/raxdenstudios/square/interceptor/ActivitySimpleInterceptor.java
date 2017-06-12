package com.raxdenstudios.square.interceptor;

import android.app.Activity;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an activity interceptor.
 */
public abstract class ActivitySimpleInterceptor
        extends ActivityInterceptor<Interactor, InterceptorCallback<Interactor>> {

    public ActivitySimpleInterceptor(Activity activity) {
        super(activity);
    }

}
