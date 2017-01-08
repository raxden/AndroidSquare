package com.raxdenstudios.square;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import com.raxdenstudios.square.interceptor.type.ActivityInterceptor;
import com.raxdenstudios.square.interceptor.manager.ActivityInterceptorManager;
import com.raxdenstudios.square.interceptor.manager.InterceptorManagerFactory;

import java.util.List;

/**
 * Created by Ángel Gómez
 *
 * SquareActivity is an abstract class that adds interceptor functionality to the activity.
 */
public abstract class SquareActivity extends AppCompatActivity {

    private ActivityInterceptorManager mInterceptorManager;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getInterceptorManager().onSaveInstanceState(outState);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        getInterceptorManager().attachBaseContext(newBase);
        super.attachBaseContext(newBase);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getInterceptorManager().onCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        getInterceptorManager().onPostCreate(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getInterceptorManager().onCreateOptionsMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override /* Called whenever we call ActivityCompat.invalidateOptionsMenu(); */
    public boolean onPrepareOptionsMenu(Menu menu) {
        getInterceptorManager().onPrepareOptionsMenu(menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getInterceptorManager().onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getInterceptorManager().onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getInterceptorManager().onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        getInterceptorManager().onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getInterceptorManager().onDestroy();
    }

    /* Callbacks */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getInterceptorManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        getInterceptorManager().onConfigurationChanged(newConfig);
    }

    @Override
    public void onBackPressed() {
        if (getInterceptorManager().onBackPressed()) {
            return;
        } else {
            super.onBackPressed();
        }
    }

    /* Support methods */

    private ActivityInterceptorManager getInterceptorManager() {
        if (mInterceptorManager == null) {
            mInterceptorManager = (ActivityInterceptorManager) InterceptorManagerFactory.buildManager(this);
            addCustomInterceptorToStack(mInterceptorManager.getInterceptors());
        }
        return mInterceptorManager;
    }

    protected void addCustomInterceptorToStack(List<ActivityInterceptor> interceptors) {

    }

}
