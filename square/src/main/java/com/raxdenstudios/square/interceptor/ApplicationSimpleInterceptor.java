package com.raxdenstudios.square.interceptor;

import android.app.Application;

/**
 * Created by Ángel Gómez
 *
 * This abstract class defines the basis of an application interceptor.
 */
public abstract class ApplicationSimpleInterceptor
        extends ApplicationInterceptor<InterceptorCallback> {

    public ApplicationSimpleInterceptor(Application application) {
        super(application);
    }

}
