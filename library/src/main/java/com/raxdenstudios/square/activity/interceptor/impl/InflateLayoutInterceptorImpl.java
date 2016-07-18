package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.raxdenstudios.square.activity.InterceptorActivity;
import com.raxdenstudios.square.activity.interceptor.InflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

/**
 * Created by agomez on 25/05/2015.
 */
public class InflateLayoutInterceptorImpl extends InterceptorActivityImpl implements InflateLayoutInterceptor.InflateLayoutInterceptorCallback {

    private static final String TAG = InflateLayoutInterceptorImpl.class.getSimpleName();

    private InflateLayoutInterceptor mCallbacks;
    private View mInflateLayout;

    public InflateLayoutInterceptorImpl(Activity activity) {
        super(activity);
        if (!(activity instanceof InflateLayoutInterceptor)) {
            throw new IllegalStateException("Activity must implement InflateLayoutInterceptor.");
        }
        mCallbacks = (InflateLayoutInterceptor)activity;
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);
        if (mCallbacks != null) {
            mInflateLayout = onCreateView(((InterceptorActivity)mCallbacks).getLayoutInflater(), savedInstanceState);
            if (mInflateLayout != null) {
                ((InterceptorActivity)mCallbacks).setContentView(mInflateLayout);
            }
        }
    }

    private View onCreateView(LayoutInflater inflater, Bundle savedInstanceState) {
        View view = null;
        if (mCallbacks != null) {
            view = mCallbacks.onCreateLayout(inflater, (ViewGroup) view, savedInstanceState);
        }
        return view;
    }

    @Override
    public View getLayout() {
        return mInflateLayout;
    }
}
