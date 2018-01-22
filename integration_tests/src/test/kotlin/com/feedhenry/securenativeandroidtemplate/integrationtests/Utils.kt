package com.feedhenry.securenativeandroidtemplate.integrationtests

import com.feedhenry.secureandroidtemplate.integrationtests.BuildConfig
import io.appium.java_client.MobileElement
import io.appium.java_client.android.Activity
import io.appium.java_client.android.AndroidDriver
import java.io.BufferedReader

/**
 * Created on 1/19/18.
 */

/**
 * Kills android process on connected ADB device.
 * @param packageName package name, like <em>com.android.browser</em>
 */
fun killAndroidProcess(packageName: String) {
    execWithoutOutput(BuildConfig.ADB_EXECUTABLE, "shell", "am", "force-stop", packageName)
}

/**
 * Clears android package data on connected ADB device.
 * @param packageName package name, like <em>com.android.browser</em>
 */
fun clearPackage(packageName: String) {
    execWithoutOutput(BuildConfig.ADB_EXECUTABLE, "shell", "pm", "clear", packageName)
}

/**
 * Executes shell command and ignores output.
 * @param cmd shell command
 * @param args command arguments
 */
private fun execWithoutOutput(vararg args: String) {
    val p = Runtime.getRuntime().exec(args)
    val out = p.inputStream.bufferedReader().use(BufferedReader::readText) //there is actually no output expected, so read it all and ignore to prevent hanging
    println(out)
}

fun AndroidDriver<MobileElement>.restoreActivity(packageName: String, oldActivityName: String) {
    val activity = Activity(packageName, oldActivityName)
    activity.intentFlags = "0x00020000"
    startActivity(activity)
}

//fun AndroidDriver<MobileElement>.waitForElementById(id: String,duration:Long, func: (MobileElement) -> Unit) {
//    findElementsById()
//    WebDriverWait(this,duration).
//}

