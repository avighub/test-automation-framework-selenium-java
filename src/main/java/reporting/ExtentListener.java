package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.SeleniumUtils;
import webdriver.DriverFactory;

@Slf4j
public class ExtentListener implements ITestListener {

  ExtentReports extentReports;
  ExtentManager extentManager;

  @Override
  public void onStart(ITestContext context) {
    log.debug("onStart()");
    extentManager = new ExtentManager();
//    extentReports = extentManager.initialize();
  }

  @Override
  public void onTestStart(ITestResult result) {
    log.debug("onTestStart()");
    ExtentFactory.setExtentTest(extentReports.createTest(result.getMethod().getMethodName()));
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    log.debug("onTestSuccess()");
    ExtentFactory.getExtentTest().pass("Test is PASS");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    log.debug("onTestFailure()");
    String base64Screenshot = SeleniumUtils.takeScreenShotAsBase64(DriverFactory.getDriver());
//      ExtentFactory.getExtentTest().addScreenCaptureFromBase64String(base64Screenshot);
    ExtentFactory.getExtentTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
    ExtentFactory.getExtentTest().fail("Test is FAIL");
    ExtentFactory.getExtentTest().fail(result.getThrowable());
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    log.debug("onTestSkipped()");
    ExtentFactory.getExtentTest().skip("Test is SKIPPED");
  }

  @Override
  public void onFinish(ITestContext context) {
    log.debug("onFinish()");
    extentReports.flush();
    ExtentFactory.removeTest();
  }
}
