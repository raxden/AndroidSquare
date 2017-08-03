package com.raxdenstudios.square.interceptor.commons.autoinflatelayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.utils.ResourceUtils;
import com.raxdenstudios.square.utils.StringUtils;

import java.util.Locale;

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
public class AutoInflateLayoutActivityInterceptor extends ActivityInterceptor<AutoInflateLayoutInterceptorCallback> implements AutoInflateLayoutInterceptor {

    private int mLayoutId;
    private View mInflateLayout;

    public AutoInflateLayoutActivityInterceptor(@NonNull Activity activity) {
        super(activity);
    }

    public AutoInflateLayoutActivityInterceptor(@NonNull Activity activity, @NonNull AutoInflateLayoutInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInflateLayout = onCreateView(mActivity.getLayoutInflater());
        if (mInflateLayout != null) {
            mActivity.setContentView(mInflateLayout);
        }
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mCallback != null) {
            mCallback.onContentViewCreated(mInflateLayout, savedInstanceState);
        }
    }

    private View onCreateView(LayoutInflater inflater) {
        return inflateLayout(inflater);
    }

    private View inflateLayout(LayoutInflater inflater) {
        if (mLayoutId != 0) {
            return inflater.inflate(mLayoutId, null);
        } else {
            int layoutId = ResourceUtils.getLayoutId(mActivity, getLayoutName());
            if (layoutId > 0) {
                return inflater.inflate(layoutId, null);
            }
        }
        return null;
    }

    private String getLayoutName() {
        String className = StringUtils.uncapitalize(mActivity.getClass().getSimpleName());
        return StringUtils.join(className.split("(?=\\p{Upper})"), "_").toLowerCase(Locale.getDefault());
    }

    @Override
    public void setLayoutId(int layoutId) {
        mLayoutId = layoutId;
    }

}

