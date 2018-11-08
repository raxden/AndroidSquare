package com.raxdenstudios.square.sample.commons

import com.raxdenstudios.square.sample.BaseTest
import junit.framework.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric

class InjectFragmentListActivityTest : BaseTest() {

    lateinit var activity: InjectFragmentListActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(InjectFragmentListActivity::class.java).apply {
            create().start().resume()
        }.get()
    }

    @Test
    fun checkIfLayoutIsLoaded() {
        assertNotNull(activity.mContentView)
    }

    @Test
    fun checkIfFragmentListIsLoaded() {
        assertNotNull(activity.mFirstFragment)
        assertNotNull(activity.mSecondFragment)
        assertNotNull(activity.mThirdFragment)
    }

}
