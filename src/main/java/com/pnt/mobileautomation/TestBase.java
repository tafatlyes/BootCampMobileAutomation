package com.pnt.mobileautomation;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static com.pnt.mobileautomation.ExtentTestManager.getTime;

public class TestBase {

    private static AppiumDriver driver = null;
    private static ExtentReports extent;

    public static AppiumDriver getDriver() {
        return driver;
    }

    @Parameters({"platform", "platformVersion", "udid", "deviceName", "automationName", "appPackage", "appActivity", "bundleId"})
    @BeforeMethod
    public static void setAppiumDriver(String platform, String platformVersion, String udid, String deviceName, String automationName, String appPackage, String appActivity, String bundleId) throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
        desiredCapabilities.setCapability(MobileCapabilityType.UDID, udid);
        if (platform.equalsIgnoreCase("ios")) {
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, automationName);
            desiredCapabilities.setCapability("bundleId", bundleId);
            driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        } else {
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            desiredCapabilities.setCapability(MobileCapabilityType.APP_PACKAGE, appPackage);
            desiredCapabilities.setCapability(MobileCapabilityType.APP_ACTIVITY, appActivity);
            driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), desiredCapabilities);
        }
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    /**
     * This method will swipe either up, Down, left or Right according to the
     * direction specified. This method takes the size of the screen and uses
     * the swipe function present in the Appium driver to swipe on the screen
     * with a particular timeout. There is one more method to implement swipe
     * using touch actions, which is not put up here.
     *
     * @param Direction The direction we need to swipe in.
     * @param swipeTime The swipe time, ie the time for which the driver is supposed
     *                  to swipe.
     * @param Offset    The offset for the driver, eg. If you want to swipe 'up', then
     *                  the offset is the number of pixels you want to leave from the
     *                  bottom of the screen t start the swipe.
     * @Author -
     * @Modified By -
     */

    public static void functionSwipe(String Direction, int swipeTime, int Offset) {
        Dimension size;
        size = driver.manage().window().getSize();
        int starty = (int) (size.height * 0.80);
        int endy = (int) (size.height * 0.20);
        int startx = size.width / 2;
        if (Direction.equalsIgnoreCase("Up")) {
            ((AppiumDriver<WebElement>) (driver)).swipe(startx / 2, starty - Offset, startx / 2, endy, swipeTime);
        } else if (Direction.equalsIgnoreCase("Down")) {
            ((AppiumDriver<WebElement>) (driver)).swipe(startx / 2, endy + Offset, startx / 2, starty, swipeTime);
        } else if (Direction.equalsIgnoreCase("Right")) {
            starty = size.height / 2;
            endy = size.height / 2;
            startx = (int) (size.width * 0.10);
            int endx = (int) (size.width * 0.90);
            ((AppiumDriver<WebElement>) (driver)).swipe(startx + Offset, starty, endx, endy, swipeTime);
        } else if (Direction.equalsIgnoreCase("Left")) {
            starty = size.height / 2;
            endy = size.height / 2;
            startx = (int) (size.width * 0.90);
            int endx = (int) (size.width * 0.10);
            ((AppiumDriver<WebElement>) (driver)).swipe(startx - Offset, starty, endx, endy, swipeTime);
        }
    }

    //Extent Report Setup
    @BeforeSuite
    public void extentSetup(ITestContext context) {
        ExtentTestManager.setOutputDirectory(context);
        extent = ExtentTestManager.getInstance();
    }


    // Extent report ends

    @BeforeMethod
    public void startExtent(Method method) {
        String className = method.getDeclaringClass().getSimpleName();
        ExtentTestManager.startTest(method.getName());
        ExtentTestManager.getTest().assignCategory(className);
    }

    //Extent Report
    @AfterMethod
    public void afterEachTestMethod(ITestResult result) {
        ExtentTestManager.getTest().getTest().setStartedTime(getTime(result.getStartMillis()));
        ExtentTestManager.getTest().getTest().setEndedTime(getTime(result.getEndMillis()));
        for (String group : result.getMethod().getGroups()) {
            ExtentTestManager.getTest().assignCategory(group);
        }

        if (result.getStatus() == 1) {
            ExtentTestManager.getTest().log(LogStatus.PASS, "TEST CASE PASSED : " + result.getName());
        } else if (result.getStatus() == 2) {
            ExtentTestManager.getTest().log(LogStatus.FAIL, "TEST CASE FAILED : " + result.getName() + " :: " + ExtentTestManager.getStackTrace(result.getThrowable()));
        } else if (result.getStatus() == 3) {
            ExtentTestManager.getTest().log(LogStatus.SKIP, "TEST CASE SKIPPED : " + result.getName());
        }
        ExtentTestManager.endTest();
        extent.flush();
        if (result.getStatus() == ITestResult.FAILURE) {
            ExtentTestManager.captureScreenshot(driver, result.getName());
        }
    }

    @AfterSuite
    public void generateReport() {
        extent.close();
    }

    @AfterMethod
    public void cleanUp() {
        driver.quit();
    }

    public void sleepFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException ignored) {
        }
    }
}
