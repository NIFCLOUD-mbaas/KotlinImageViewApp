package mbaas.com.nifcloud.androidimageviewapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert
import org.hamcrest.Matchers.*

import mbaas.com.nifcloud.androidimageviewapp.ImageViewHasDrawableMatcher.hasDrawable
import mbaas.com.nifcloud.androidimageviewapp.Utils.waitFor


@RunWith(AndroidJUnit4ClassRunner::class)
class DisplayImageSuccessTest {
    @get:Rule
    var mActivityRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun init() {
        // Specify a valid string.
    }

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("mbaas.com.nifcloud.androidimageviewapp", appContext.packageName)
    }

    @Test
    fun validateDisplayImageSuccess() {
        onView(withText("ImageViewSample")).check(matches(isDisplayed()))
        onView(withId(R.id.imgShow)).check(matches(not(hasDrawable())))
        onView(withId(R.id.btnShow)).check(matches(isDisplayed())).perform(click())
        onView(isRoot()).perform(waitFor(800))
        onView(withId(R.id.imgShow)).check(matches(hasDrawable()))
    }
}
