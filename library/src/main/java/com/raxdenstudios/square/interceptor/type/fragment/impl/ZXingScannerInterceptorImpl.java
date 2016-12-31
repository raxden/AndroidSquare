package com.raxdenstudios.square.interceptor.type.fragment.impl;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.raxdenstudios.square.interceptor.type.FragmentInterceptor;
import com.raxdenstudios.square.interceptor.callback.ZXingScannerInterceptorCallback;
import com.raxdenstudios.square.interceptor.config.WebViewInterceptorConfig;
import com.raxdenstudios.square.interceptor.config.ZXingScannerInterceptorConfig;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public class ZXingScannerInterceptorImpl
        extends FragmentInterceptor<ZXingScannerInterceptorConfig, ZXingScannerInterceptorCallback>
        implements WebViewInterceptorConfig, ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private int mCameraId = -1;

    public ZXingScannerInterceptorImpl(Fragment fragment) {
        super(fragment);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mScannerView = mCallback != null ? mCallback.onLoadZXingScannerView() : null;
        if (mScannerView != null) {
            mFlash = false;
            mAutoFocus = true;
            mSelectedIndices = null;
            mCameraId = -1;
            setupFormats();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mScannerView != null) {
            mScannerView.setResultHandler(this);
            mScannerView.startCamera(mCameraId);
            mScannerView.setFlash(mFlash);
            mScannerView.setAutoFocus(mAutoFocus);
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (mScannerView != null) {
            mScannerView.stopCamera();
        }
    }

    @Override
    public void handleResult(Result result) {
        if (mCallback != null) {
            mCallback.handleZXingScannerResult(result);
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
