package com.raxdenstudios.square.interceptor.commons.toolbar;

import android.os.Bundle;
import android.view.MenuItem;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by agomez on 21/05/2015.
 */
public class ToolbarActivityInterceptor extends ActivityInterceptor<ToolbarInterceptorCallback> implements ToolbarInterceptor {

    public ToolbarActivityInterceptor(@NonNull AppCompatActivity activity) {
        super(activity);
    }

    public ToolbarActivityInterceptor(@NonNull AppCompatActivity activity, @NonNull ToolbarInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = mCallback.onCreateToolbarView(savedInstanceState);
        if (toolbar != null) {
            if (mActivity instanceof AppCompatActivity) {
                AppCompatActivity compatActivity = ((AppCompatActivity) mActivity);
                compatActivity.setSupportActionBar(toolbar);
                ActionBar actionBar = compatActivity.getSupportActionBar();
                if (actionBar != null) {
                    actionBar.setDisplayShowTitleEnabled(false);
                }
            }
            toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return mActivity.onOptionsItemSelected(item);
                }
            });
            mCallback.onToolbarViewCreated(toolbar);
        }
    }

}
