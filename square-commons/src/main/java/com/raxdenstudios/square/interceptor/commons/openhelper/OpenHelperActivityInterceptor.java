package com.raxdenstudios.square.interceptor.commons.openhelper;

import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

/**
 * Created by agomez on 08/05/2015.
 */
public class OpenHelperActivityInterceptor<T extends SQLiteOpenHelper> extends ActivityInterceptor<OpenHelperInterceptorCallback<T>> implements OpenHelperInterceptor {

    private SQLiteOpenHelper mOpenHelper;

    public OpenHelperActivityInterceptor(@NonNull FragmentActivity activity) {
        super(activity);
    }

    public OpenHelperActivityInterceptor(@NonNull FragmentActivity activity, @NonNull OpenHelperInterceptorCallback<T> callback) {
        super(activity, callback);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mOpenHelper = mCallback.onCreateOpenHelper(mActivity, savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mOpenHelper != null) {
            mOpenHelper.close();
            mOpenHelper = null;
        }
    }
}
