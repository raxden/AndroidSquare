package com.raxdenstudios.square.activity.interceptor.impl;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.raxdenstudios.commons.util.ResourceUtils;
import com.raxdenstudios.commons.util.StringUtils;
import com.raxdenstudios.square.activity.interceptor.AutoInflateLayoutInterceptor;
import com.raxdenstudios.square.activity.interceptor.delegate.AutoInflateLayoutInterceptorDelegate;
import com.raxdenstudios.square.activity.interceptor.manager.InterceptorActivityImpl;

import java.util.Locale;

/**
 * Created by agomez on 22/05/2015.
 */
public class AutoInflateLayoutInterceptorImpl extends InterceptorActivityImpl
        implements AutoInflateLayoutInterceptorDelegate {

    private static final String TAG = AutoInflateLayoutInterceptorImpl.class.getSimpleName();

    private AutoInflateLayoutInterceptor mCallbacks;
    private View mInflateLayout;

    public AutoInflateLayoutInterceptorImpl(Activity activity) {
        super(activity);
        mCallbacks = (AutoInflateLayoutInterceptor)activity;
        mCallbacks.onInterceptorCreated(this);
    }

    @Override
    public void onInterceptorCreate(Bundle savedInstanceState) {
        super.onInterceptorCreate(savedInstanceState);

        mInflateLayout = onCreateView(getActivity().getLayoutInflater());
        if (mInflateLayout != null) {
            getActivity().setContentView(mInflateLayout);
        }
    }

    @Override
    public void onInterceptorPostCreate(Bundle savedInstanceState) {
        super.onInterceptorPostCreate(savedInstanceState);
        mCallbacks.onViewCreated(mInflateLayout, savedInstanceState);
    }

    private View onCreateView(LayoutInflater inflater) {
        return inflateLayout(inflater);
    }

    private View inflateLayout(LayoutInflater inflater) {
        int layoutId = ResourceUtils.getLayoutId(getContext(), getLayoutName());
        if (layoutId > 0) {
            return inflater.inflate(layoutId, null);
        }
        return null;
    }

    public String getLayoutName() {
        return StringUtils
                .join(StringUtils
                        .uncapitalize(getActivity().getClass().getSimpleName())
                        .split("(?=\\p{Upper})"), "_")
                .toLowerCase(Locale.getDefault());
    }

}

