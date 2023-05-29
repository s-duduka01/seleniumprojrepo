package listeners;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import resources.Base;

public class Listeners extends Base implements ITestListener {
    private ExtentReports extentReport;
    private ExtentTest extentTest;
    private ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
    private WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport.html";
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setReportName("Test Execution Report");
        sparkReporter.config().setDocumentTitle("Test Results");

        extentReport = new ExtentReports();
        extentReport.attachReporter(sparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getTestName();
        extentTest = extentReport.createTest(testName + " execution started");
        extentTestThread.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getName();
        extentTestThread.get().log(Status.PASS, testName + " got passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testMethodName = result.getName();
        extentTestThread.get().fail(result.getThrowable());

        try {
            driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String screenshotFilePath = takeSreenShot(testMethodName, driver);
            extentTestThread.get().addScreenCaptureFromPath(screenshotFilePath, testMethodName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        extentReport.flush();
    }

    // Other methods

    // ...
}
