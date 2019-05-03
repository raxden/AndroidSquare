package com.raxdenstudios.square.interceptor.commons.fragmentbottomnavigation

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.view.View
import com.raxdenstudios.square.interceptor.HasInterceptor

/**
 * Created by Ángel Gómez on 29/12/2016.
 *
    <android.support.constraint.ConstraintLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/activity_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@android:color/white">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar_view"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="@color/colorPrimary"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toTopOf="parent">

            <android.support.v7.widget.AppCompatTextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_gravity="center"
                android:text="Toolbar" />

        </android.support.v7.widget.Toolbar>

        <FrameLayout
            android:id="@+id/container_view"
            android:layout_width="0dp"
            android:layout_height="0dp"
            app:layout_constraintBottom_toTopOf="@+id/bottom_navigation_view"
            app:layout_constraintEnd_toEndOf="parent"
            app:layout_constraintStart_toStartOf="parent"
            app:layout_constraintTop_toBottomOf="@+id/toolbar_view" />

        <android.support.design.widget.BottomNavigationView
            android:id="@+id/bottom_navigation_view"
            android:layout_width="0dp"
            android:layout_height="wrap_content"
            android:layout_marginEnd="0dp"
            android:layout_marginStart="0dp"
            android:background="?android:attr/windowBackground"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintRight_toRightOf="parent"
            app:menu="@menu/navigation"/>

    </android.support.constraint.ConstraintLayout>
 */
interface HasFragmentBottomNavigationInterceptor<T : Fragment> : HasInterceptor {

    fun onCreateBottomNavigationView(): BottomNavigationView

    fun onBottomNavigationViewCreated(bottomNavigationView: BottomNavigationView)

    fun onLoadFragmentContainer(): View

    fun onCreateFragment(itemId: Int): T

    fun onFragmentLoaded(itemId: Int, fragment: T)

    fun onBottomNavigationItemSelected(itemId: Int)
}
