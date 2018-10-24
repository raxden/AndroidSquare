package com.raxdenstudios.square.manager;

import android.app.Application;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.raxdenstudios.square.utils.LibraryHelper;

/**
 * Created by Ángel Gómez on 18/12/2016.
 */

public class InterceptorManagerFactory {

    public static <T> InterceptorManager buildManager(T type) {
        InterceptorManager interceptorManager = null;
        if (LibraryHelper.getInstance().isMultiDexLibraryAvailable() && type instanceof MultiDexApplication) {
            interceptorManager = new ApplicationMultiDexInterceptorManager((MultiDexApplication) type);
        } else if (type instanceof Application) {
            interceptorManager = new ApplicationInterceptorManager((Application) type);
        } else if (type instanceof FragmentActivity) {
            interceptorManager = new ActivityInterceptorManager((FragmentActivity) type);
        } else if (type instanceof DialogFragment) {
            interceptorManager = new DialogFragmentInterceptorManager((DialogFragment) type);
        } else if (type instanceof Fragment) {
            interceptorManager = new FragmentInterceptorManager((Fragment) type);
        }
        return interceptorManager;
    }


}
