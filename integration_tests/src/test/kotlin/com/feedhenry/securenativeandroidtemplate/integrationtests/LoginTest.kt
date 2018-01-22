package com.feedhenry.securenativeandroidtemplate.integrationtests

import com.feedhenry.secureandroidtemplate.integrationtests.BuildConfig.BROWSER_PACKAGE
import org.junit.Before
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * E2E Testing of login and logout functionality.
 */
class LoginTest : BaseTest() {

    companion object {
        const val KEYCLOAK_LOGIN_ID = "com.feedhenry.securenativeandroidtemplate:id/keycloakLogin"
        const val KEYCLOAK_LOGOUT_ID = "com.feedhenry.securenativeandroidtemplate:id/keycloakLogout"
    }

    /**
     * Required to prevent reauthentication with remembered session.
     */
    @Before
    fun killBrowserBeforehand() {
        clearPackage(BROWSER_PACKAGE)
    }

    @Test
    fun loginLogoutTest() {
        val el1 = driver.findElementByAccessibilityId("Open navigation drawer")
        el1.click()
        val el2 = driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[2]/android.widget.CheckedTextView")
        el2.click()
        val el3 = driver.findElementById(KEYCLOAK_LOGIN_ID)
        el3.click()
        val el4 = driver.findElementById("username")
        el4.click()
        el4.sendKeys("user1")
        val el5 = driver.findElementById("password")
        el5.click()
        el5.sendKeys("123")
        val el6 = driver.findElementByAccessibilityId("Log in")
        el6.click()

        val el7 = driver.findElementById(KEYCLOAK_LOGOUT_ID)
        assertEquals("LOGOUT", el7.text)
        val username = driver.findElementById("com.feedhenry.securenativeandroidtemplate:id/user_name")
        assertEquals("User 1", username.text)
        el7.click()
        el7.click() //currently issue here, that it won't do the click sometimes

        driver.findElementByAccessibilityId("Open navigation drawer").click()
        driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android" +
                ".support.v4.widget.DrawerLayout/android.widget.FrameLayout/android.support.v7.widget.RecyclerView/android.support.v7.widget.LinearLayoutCompat[2]/android.widget.CheckedTextView").click()

        val loginButton = driver.findElementById(KEYCLOAK_LOGIN_ID)
        assertEquals("AUTHENTICATE", loginButton.text)

    }
}
