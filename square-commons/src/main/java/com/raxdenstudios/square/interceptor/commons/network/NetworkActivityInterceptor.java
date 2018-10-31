package com.raxdenstudios.square.interceptor.commons.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;
import com.raxdenstudios.square.utils.NetworkUtils;

/**
 * This interceptor requires the following permissions:
 * + android.permission.ACCESS_NETWORK_STATE
 *
 * Created by agomez on 08/05/2015.
 */
public class NetworkActivityInterceptor extends ActivityInterceptor<NetworkInterceptorCallback> implements NetworkInterceptor {

    private BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            if (getCallback() != null) {
                getCallback().onNetworkAvailable(NetworkUtils.isNetworkAvailable(context));
                getCallback().onWifiAvailable(NetworkUtils.isWifiAvailable(context));
            }
        }
    };

    public NetworkActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public NetworkActivityInterceptor(@NonNull FragmentActivity activity, @NonNull NetworkInterceptorCallback callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getActivity().registerReceiver(mNetworkReceiver, intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getActivity().unregisterReceiver(mNetworkReceiver);
    }

}
