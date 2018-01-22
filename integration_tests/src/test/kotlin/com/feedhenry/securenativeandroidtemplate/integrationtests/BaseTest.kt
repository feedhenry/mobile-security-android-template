package com.feedhenry.securenativeandroidtemplate.integrationtests

import com.feedhenry.secureandroidtemplate.integrationtests.BuildConfig
import com.feedhenry.secureandroidtemplate.integrationtests.BuildConfig.*
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.service.local.AppiumDriverLocalService
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException
import io.appium.java_client.service.local.AppiumServiceBuilder
import org.junit.After
import org.junit.Before
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File
import java.io.FileNotFoundException

abstract class BaseTest {

    lateinit var service: AppiumDriverLocalService
    lateinit var driver: AndroidDriver<MobileElement>

    companion object {
        val NODE_JS_BIN = File("${BuildConfig.NODEJS_DIRECTORY}/bin/node")
        val APPIUM_JS = File("${BuildConfig.NODEJS_DIRECTORY}/lib/node_modules/appium/build/lib/appium.js")
    }


    @Before
    fun setUp() {
        val serviceBuilder = AppiumServiceBuilder().withIPAddress(APPIUM_IP_ADDRESS).usingPort(APPIUM_IP_PORT).usingDriverExecutable(NODE_JS_BIN).withAppiumJS(APPIUM_JS)
        if (START_LOCAL_SERVER) {
            NODE_JS_BIN.isFile || throw FileNotFoundException("Node.js not found")
            APPIUM_JS.isFile || throw FileNotFoundException("appium.js not found")
            service = AppiumDriverLocalService.buildService(serviceBuilder)
            service.start()

            if (!service.isRunning) {
                throw AppiumServerHasNotBeenStartedLocallyException(
                        "An appium server node is not started!")
            }
        }
        val app = File(BuildConfig.TEST_APK)
        val capabilities = DesiredCapabilities()
        capabilities.setCapability("platformName", "Android")
        capabilities.setCapability("deviceName", "Android Emulator")
        capabilities.setCapability("app", app.absolutePath)
        capabilities.setCapability("appPackage", BuildConfig.TESTED_PACKAGE)

        driver = AndroidDriver(serviceBuilder.build().url, capabilities)
    }

    @After
    fun tearDown() {
        driver.quit()
        if (START_LOCAL_SERVER) service.stop()
    }


}