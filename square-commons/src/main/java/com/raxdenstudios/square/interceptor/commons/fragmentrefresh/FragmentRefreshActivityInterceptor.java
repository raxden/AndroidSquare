package com.raxdenstudios.square.interceptor.commons.fragmentrefresh;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ángel Gómez on 17/06/2017.
 */

public class FragmentRefreshActivityInterceptor extends ActivityInterceptor<FragmentRefreshInterceptorCallback> implements FragmentRefreshInterceptor {

    private List<OnFragmentRefreshListener> mOnRefreshListeners;

    public FragmentRefreshActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity, null);
    }

    public FragmentRefreshActivityInterceptor(@NonNull FragmentActivity activity, @NonNull FragmentRefreshInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void addOnFragmentRefreshListener(OnFragmentRefreshListener listener) {
        if (mOnRefreshListeners == null) {
            mOnRefreshListeners = new ArrayList<>();
        }
        mOnRefreshListeners.add(listener);
    }

    @Override
    public void removeOnFragmentRefreshListener(OnFragmentRefreshListener listener) {
        if (mOnRefreshListeners != null) {
            mOnRefreshListeners.remove(listener);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mOnRefreshListeners != null) {
            mOnRefreshListeners.clear();
            mOnRefreshListeners = null;
        }
    }

    @Override
    public void refresh() {
        if (mOnRefreshListeners != null) {
            for (OnFragmentRefreshListener listener : mOnRefreshListeners) {
                listener.onRefresh();
            }
        }
    }

}
