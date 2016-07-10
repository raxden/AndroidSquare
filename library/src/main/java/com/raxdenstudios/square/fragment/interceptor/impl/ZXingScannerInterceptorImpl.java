package com.raxdenstudios.square.fragment.interceptor.impl;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.raxdenstudios.square.fragment.interceptor.ZXingScannerInterceptor;
import com.raxdenstudios.square.fragment.interceptor.manager.InterceptorFragmentImpl;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public class ZXingScannerInterceptorImpl extends InterceptorFragmentImpl implements ZXingScannerInterceptor.ZXingScannerInterceptorListener, ZXingScannerView.ResultHandler {

    private static final String TAG = AutoInflateViewInterceptorImpl.class.getSimpleName();

    private ZXingScannerInterceptor mCallbacks;
    private ZXingScannerView mScannerView;

    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private int mCameraId = -1;

    public ZXingScannerInterceptorImpl(Fragment fragment) {
        if (!(fragment instanceof ZXingScannerInterceptor)) {
            throw new IllegalStateException("Fragment must implement ZXingScannerInterceptor.");
        }
        mCallbacks = (ZXingScannerInterceptor)fragment;
    }

    @Override
    public void onInterceptorViewCreated(Context context, View view, Bundle savedInstanceState) {
        super.onInterceptorViewCreated(context, view, savedInstanceState);

        mScannerView = mCallbacks != null ? mCallbacks.onLoadZXingScannerView() : null;
        if (mScannerView != null) {
            mFlash = false;
            mAutoFocus = true;
            mSelectedIndices = null;
            mCameraId = -1;
            setupFormats();
        }
        if (mCallbacks != null) {
            mCallbacks.onInterceptorLoaded(this);
        }
    }

    @Override
    public void onInterceptorResume(Context context) {
        super.onInterceptorResume(context);

        if (mScannerView != null) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera(mCameraId);
            mScannerView.setFlash(mFlash);
            mScannerView.setAutoFocus(mAutoFocus);
        }
    }

    @Override
    public void onInterceptorPause(Context context) {
        super.onInterceptorPause(context);

        if (mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        if (mCallbacks != null) {
            mCallbacks.handleZXingScannerResult(result);
        }
    }

    private void setupFormats() {
        List<BarcodeFormat> formats = new ArrayList<>();
        if (mSelectedIndices == null || mSelectedIndices.isEmpty()) {
            mSelectedIndices = new ArrayList<>();
            for(int i = 0; i < ZXingScannerView.ALL_FORMATS.size(); i++) {
                mSelectedIndices.add(i);
            }
        }

        for (int index : mSelectedIndices) {
            formats.add(ZXingScannerView.ALL_FORMATS.get(index));
        }

        if (mScannerView != null) {
            mScannerView.setFormats(formats);
        }
    }


}
