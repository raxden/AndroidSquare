package com.raxdenstudios.square.interceptor.zxing;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.raxdenstudios.square.interceptor.FragmentInterceptor;
import com.raxdenstudios.square.interceptor.webview.WebViewInterceptor;

import java.util.ArrayList;
import java.util.List;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by agomez on 24/12/2015.
 */
public class ZXingScannerFragmentInterceptorImpl extends FragmentInterceptor<ZXingScannerInterceptorCallback> implements WebViewInterceptor, ZXingScannerView.ResultHandler {

    private ZXingScannerView mScannerView;
    private boolean mFlash;
    private boolean mAutoFocus;
    private ArrayList<Integer> mSelectedIndices;
    private int mCameraId = -1;

    public ZXingScannerFragmentInterceptorImpl(@NonNull Fragment fragment) {
        super(fragment);
    }

    public ZXingScannerFragmentInterceptorImpl(@NonNull Fragment fragment, @NonNull ZXingScannerInterceptorCallback callback) {
        super(fragment, callback);
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
