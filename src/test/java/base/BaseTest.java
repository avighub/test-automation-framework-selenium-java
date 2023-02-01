package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import reporting.ExtentListener;
import utilities.driverutils.DriverFactory;
import utilities.driverutils.DriverManager;

@Listeners(ExtentListener.class)
public class BaseTest {

  @BeforeMethod(alwaysRun = true)
  public void setupDriver(){
    DriverFactory.setDriver(DriverManager.getDriver());
  }

  @AfterMethod(alwaysRun = true)
  public void cleanUp(){
    DriverFactory.removeDriver();
  }

}
