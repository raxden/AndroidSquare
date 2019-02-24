package com.raxdenstudios.square.interceptor.commons.floatingactionbutton

import android.app.Activity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import com.raxdenstudios.square.interceptor.ActivityInterceptor
import com.raxdenstudios.square.interceptor.commons.R

/**
 * Created by Ángel Gómez on 22/05/2015.
 */
class FloatingActionButtonFragmentActivityInterceptor<TMasterFragment : Fragment, TDetailFragment : Fragment>(
        callback: HasFloatingActionButtonFragmentInterceptor<TMasterFragment, TDetailFragment>
) : ActivityInterceptor<FloatingActionButtonFragmentInterceptor, HasFloatingActionButtonFragmentInterceptor<TMasterFragment, TDetailFragment>>(callback),
        FloatingActionButtonFragmentInterceptor {

    private var mNavigationIcon: Int = R.drawable.square__ic_close_white_24dp
    private lateinit var mFloatingActionButton: FloatingActionButton
    private lateinit var mToolbar: Toolbar
    private lateinit var mContainerView: View
    private var mHasSavedInstanceState: Boolean = false
    private var mMasterFragment: TMasterFragment? = null
    private var mDetailFragment: TDetailFragment? = null

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
        super.onActivityCreated(activity, savedInstanceState)

        (activity as? AppCompatActivity)?.also {
            mHasSavedInstanceState = savedInstanceState != null
            mFloatingActionButton = initFloatingActionButton(activity)
            mToolbar = initToolbar(activity)
            mContainerView = mCallback.onLoadFragmentContainer()
            mMasterFragment = initMasterFragment(activity)
            mDetailFragment = initDetailFragment(activity)
        }
    }


    override fun onActivityStarted(activity: Activity?) {
        super.onActivityStarted(activity)

        (activity as? AppCompatActivity)?.also {
            mMasterFragment = initMasterFragment(activity)
            mDetailFragment = initDetailFragment(activity)
        }
    }

    override fun onActivityDestroyed(activity: Activity?) {
        super.onActivityDestroyed(activity)

        mMasterFragment = null
        mDetailFragment = null
    }

    private fun initFloatingActionButton(activity: AppCompatActivity): FloatingActionButton {
        return mCallback.onLoadFloatingActionButton().also {
            it.setOnClickListener{
                mDetailFragment?.also { fragment ->
                    activity.supportFragmentManager.beginTransaction().addToBackStack("detail").replace(mContainerView.id, fragment).commit()
                }
            }
        }
    }

    private fun initToolbar(activity: AppCompatActivity): Toolbar = mCallback.onCreateToolbarView().also {
        activity.setSupportActionBar(it)
        activity.supportActionBar?.setDisplayShowTitleEnabled(false)
        it.setOnMenuItemClickListener { item -> activity.onOptionsItemSelected(item) }
        it.setNavigationOnClickListener {
            if (activity.supportFragmentManager.backStackEntryCount > 0)
                activity.supportFragmentManager.popBackStack()
        }
        activity.supportFragmentManager.addOnBackStackChangedListener {
            if (activity.supportFragmentManager.backStackEntryCount > 0) {
                it.setNavigationIcon(mNavigationIcon)
                mFloatingActionButton.hide()
            } else {
                it.navigationIcon = null
                mFloatingActionButton.show()
            }
        }
        mCallback.onToolbarViewCreated(it)
    }

    private fun initMasterFragment(activity: AppCompatActivity): TMasterFragment? {
        return if (!mHasSavedInstanceState) {
            mCallback.onCreateMasterFragment().also {
                activity.supportFragmentManager.beginTransaction().replace(mContainerView.id, it, it.javaClass.simpleName).commit()
                mCallback.onFragmentMasterLoaded(it)
            }
        } else if (mMasterFragment == null) {
            (activity.supportFragmentManager.findFragmentById(mContainerView.id) as? TMasterFragment)?.also {
                mCallback.onFragmentMasterLoaded(it)
            }
        } else {
            mMasterFragment
        }
    }

    private fun initDetailFragment(activity: AppCompatActivity): TDetailFragment? {
        return if (!mHasSavedInstanceState) {
            mCallback.onCreateDetailFragment().also {
                activity.supportFragmentManager.beginTransaction().replace(mContainerView.id, it, it.javaClass.simpleName).commit()
                mCallback.onFragmentDetailLoaded(it)
            }
        } else if (mDetailFragment == null) {
            (activity.supportFragmentManager.findFragmentById(mContainerView.id) as? TDetailFragment)?.also {
                mCallback.onFragmentDetailLoaded(it)
            }
        } else {
            mDetailFragment
        }
    }
}

