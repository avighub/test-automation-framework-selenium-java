package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import reporting.ExtentListener;
import webdriver.DriverFactory;

@Listeners(ExtentListener.class)
public class TestSetup {

  /**
   * Using access modifiers effectively as protected
   */
  protected WebDriver driver;

  @BeforeMethod(alwaysRun = true)
  public void setupDriver() {
    driver = DriverFactory.getDriver();
  }

  @AfterMethod(alwaysRun = true)
  public void cleanUp() {
    DriverFactory.quitDriver();
  }

}
