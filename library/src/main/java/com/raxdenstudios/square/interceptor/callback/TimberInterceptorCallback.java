package com.raxdenstudios.square.interceptor.callback;

import com.raxdenstudios.square.interceptor.InterceptorCallback;
import com.raxdenstudios.square.interceptor.interactor.TimberInterceptorInteractor;

import timber.log.Timber;

/**
 * Created by Ángel Gómez on 24/07/2016.
 */
public interface TimberInterceptorCallback
        extends InterceptorCallback<TimberInterceptorInteractor> {

    /**
     * Create your custom Timber.Tree like this:
     *
     * ========================================
     *  Timber.Tree tree;
     *  if (BuildConfig.DEBUG) {
     *      tree = new Timber.DebugTree();
     *  } else {
     *      tree = new CrashReportingTree();
     *  }
     *  Timber.plant(tree);
     *  ========================================
     *
     * @return {@link Timber.Tree}
     */
    Timber.Tree onCreateTimberTree();

}
