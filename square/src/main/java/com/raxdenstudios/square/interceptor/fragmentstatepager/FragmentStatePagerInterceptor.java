package com.raxdenstudios.square.interceptor.fragmentstatepager;

import android.app.Fragment;

import com.raxdenstudios.square.interceptor.Interceptor;

/**
 * Created by Ángel Gómez on 29/12/2016.
 */

public interface FragmentStatePagerInterceptor<TFragment extends Fragment> extends Interceptor {

    boolean isFirstPage();

    boolean isLastPage();

    int getNumPages();

    int getCurrentPosition();

    void setCurrentPage(int page);

    void setCurrentPage(int page, boolean smoothScroll);

    TFragment getCurrentPage();

    TFragment nextPage();

    TFragment previousPage();

}