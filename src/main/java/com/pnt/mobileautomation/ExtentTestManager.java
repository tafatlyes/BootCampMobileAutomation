package com.pnt.mobileautomation;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.Reporter;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    private static final Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    private static ExtentReports extent;
    private static ITestContext context;

    //screenshot
    public static String captureScreenshot(WebDriver driver, String screenshotName) {
        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH_mma)");
        Date date = new Date();
        df.format(date);
        String destination = null;
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + " " + df.format(date) + ".png";
            FileUtils.copyFile(file, new File(destination));
        } catch (Exception ignored) {
        }
        return destination;
    }

    //logging to report
    public static void log(String message, Class clas) {
        Logger logger = Logger.getLogger(clas);
        logger.info(message + " : " + clas);
        Reporter.log(message + " : " + clas, true);
        ExtentTestManager.getTest().log(LogStatus.INFO, message + " : " + clas + "<br>");
    }

    //logging to report
    public static void log(String message) {
        Reporter.log(message + "<br>", true);
        ExtentTestManager.getTest().log(LogStatus.INFO, message + "<br>");
    }

    protected static synchronized ExtentTest getTest() {
        return extentTestMap.get((int) Thread.currentThread().getId());
    }

    protected static synchronized void endTest() {
        extent.endTest(extentTestMap.get((int) Thread.currentThread().getId()));
    }

    protected static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    protected static synchronized ExtentTest startTest(String testName, String desc) {
        ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) Thread.currentThread().getId(), test);
        return test;
    }

    protected synchronized static ExtentReports getInstance() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH_mm_ss");
        String date = sdf.format(cal.getTime());

        if (extent == null) {
            File outputDirectory = new File(context.getOutputDirectory());
            File resultDirectory = new File(outputDirectory.getParentFile(), "html");
            extent = new ExtentReports(System.getProperty("user.dir") + "/Execution_Report/Report_" + date + ".html", true);
            Reporter.log("Extent Report Directory" + resultDirectory, true);
            extent.addSystemInfo("Host Name", "My_Host_Name").addSystemInfo("Environment",
                    "QA").addSystemInfo("User Name", "My_User_Name");
            extent.loadConfig(new File(System.getProperty("user.dir") + "/src/main/resources/report-config.xml"));
        }
        return extent;
    }

    protected static void setOutputDirectory(ITestContext context) {
        ExtentTestManager.context = context;
    }

    //get execution time
    protected static Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }

    //print stacktrace
    protected static String getStackTrace(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        return sw.toString();
    }
}