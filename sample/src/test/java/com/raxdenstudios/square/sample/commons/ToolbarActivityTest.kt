package com.raxdenstudios.square.sample.commons

import com.raxdenstudios.square.sample.BaseTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.robolectric.Robolectric

class ToolbarActivityTest : BaseTest() {

    lateinit var activity: ToolbarActivity

    @Before
    fun setup() {
        activity = Robolectric.buildActivity(ToolbarActivity::class.java).apply {
            create().start().resume()
        }.get()
    }

    @Test
    fun checkIfLayoutIsLoaded() {
        assertNotNull(activity.mContentView)
    }

    @Test
    fun checkIfToolbarIsLoaded() {
        assertNotNull(activity.mToolbarView)
    }

}
