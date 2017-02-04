package com.raxdenstudios.square.interceptor.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

import com.raxdenstudios.square.interceptor.ActivityInterceptor;

/**
 * Created by agomez on 11/05/2105.
 */
public class TimerActivityInterceptor
        extends ActivityInterceptor<TimerInteractor, TimerInterceptorCallback>
        implements TimerInteractor {

    private final static int DEFAULT_TIME_MS = 3000;

    private Handler mHandler = new Handler();
    private long time;
    private boolean isTimerEnded;
    private Object o = new Object();

    private Runnable runnableTimer = new Runnable() {
        @Override
        public void run() {
            synchronized (o) {
                isTimerEnded = true;
                if (mCallback != null) {
                    mCallback.onTimerEnd();
                }
            }
            removeRunnableIfExists();
        }
    };

    public TimerActivityInterceptor(Activity activity, TimerInterceptorCallback callback) {
        super(activity, callback);

        if (time == 0) {
            time = DEFAULT_TIME_MS;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        removeRunnableIfExists();
        postRunnable();
    }

    @Override
    public boolean onBackPressed() {
        removeRunnableIfExists();
        if (mCallback != null) mCallback.onTimerCancelled();
        return super.onBackPressed();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        synchronized (o) {
            if (!isTimerEnded) {
                if (mCallback != null) mCallback.onTimerCancelled();
            }
            removeRunnableIfExists();
        }
    }

    @Override
    public void setTime(int seconds) {
        time = seconds * 1000;
    }

    @Override
    public void setTime(long milliseconds) {
        time = milliseconds;
    }

    private void removeRunnableIfExists() {
        if (mHandler != null) mHandler.removeCallbacks(runnableTimer);
    }

    private void postRunnable() {
        if (mHandler != null) mHandler.postDelayed(runnableTimer, time);
    }

}
