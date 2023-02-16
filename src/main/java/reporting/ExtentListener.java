package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utilities.driverutils.DriverFactory;
import utilities.driverutils.SeleniumUtils;

public class ExtentListener implements ITestListener {

  ExtentReports extentReports;

  @Override
  public void onStart(ITestContext context)
    {
      System.out.println("====onStart====");
      ExtentManager extentManager= new ExtentManager();
      extentReports=extentManager.initReport();
    }

  @Override
  public void onTestStart(ITestResult result)
    {
      System.out.println("====onTestStart====");
      ExtentFactory.setExtentTest(extentReports.createTest(result.getMethod().getMethodName()));
    }

  @Override
  public void onTestSuccess(ITestResult result)
    {
      System.out.println("====onTestSuccess====");
      ExtentFactory.getExtentTest().pass("Test is PASS");

    }

  @Override
  public void onTestFailure(ITestResult result)
    {
      System.out.println("====onTestFailure====");
      String base64Screenshot = SeleniumUtils.takeScreenShotAsBase64(DriverFactory.getDriver());
//      ExtentFactory.getExtentTest().addScreenCaptureFromBase64String(base64Screenshot);
      ExtentFactory.getExtentTest().fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
      ExtentFactory.getExtentTest().fail("Test is FAIL");
      ExtentFactory.getExtentTest().fail(result.getThrowable());



    }

  @Override
  public void onTestSkipped(ITestResult result)
    {
      System.out.println("====onTestSkipped====");
      ExtentFactory.getExtentTest().skip("Test is SKIPPED");
    }

  @Override
  public void onFinish(ITestContext context)
    {
      System.out.println("====onFinish====");
      extentReports.flush();
      ExtentFactory.removeTest();
    }
}
