package base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import utilities.driverutils.DriverFactory;

//@Listeners(ExtentListener.class)
public class TestSetup {

  /**
   * Using access modifiers effectively as protected
   */
  protected WebDriver driver;

  @BeforeEach
  public void setupDriver() {
    driver = DriverFactory.getDriver();
  }

  @AfterEach
  public void cleanUp() {
    DriverFactory.quitDriver();
  }

}
