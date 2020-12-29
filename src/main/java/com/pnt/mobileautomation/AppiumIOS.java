package com.pnt.mobileautomation;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppiumIOS {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13.4");
        cap.setCapability(MobileCapabilityType.UDID, "");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
        cap.setCapability("bundleId", "");

        AppiumDriver driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), cap);

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        Thread.sleep(10000);

        driver.findElement(By.xpath("")).click();

        Thread.sleep(10000);
        driver.quit();
    }
}
