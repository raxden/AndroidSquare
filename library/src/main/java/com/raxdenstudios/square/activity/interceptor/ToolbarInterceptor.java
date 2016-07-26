package com.raxdenstudios.square.activity.interceptor;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.raxdenstudios.square.Interceptor;
import com.raxdenstudios.square.activity.interceptor.callback.ToolbarInterceptorCallback;

/**
 * Created by agomez on 21/05/2015.
 */
public interface ToolbarInterceptor extends Interceptor<ToolbarInterceptorCallback> {

    Toolbar onCreateToolbarView(Bundle savedInstanceState);

    void onToolbarViewCreated(Toolbar toolbar, Bundle savedInstanceState);

}
