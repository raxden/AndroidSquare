package com.raxdenstudios.square;

/**
 * Created by Raxden on 15/07/2016.
 */
public interface Interceptor<T extends InterceptorCallback> {

    void onInterceptorCreated(T callback);

}
