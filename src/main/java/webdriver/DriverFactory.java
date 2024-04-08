package webdriver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import utilities.ConfigurationManager;

/**
 * 1- This class deals with only one thing, that is to manage driver object
 * 2- This uses threadLocal concept to ensure returning unique driver
 * instance for each test
 * 3- This also checks environment variable for browserName if not present
 * takes it from properties file as fall back mechanism
 * 4- In production we will be passing the browserName from CLI in CI tool
 */
@Slf4j
public final class DriverFactory {

  private DriverFactory() {
  }

  private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

  public static WebDriver getDriver() {
    if (threadLocalDriver.get() == null) {
      BrowserManager browserManager = new BrowserManager(getBrowserName());
      threadLocalDriver.set(browserManager.createDriver());
    }

    return threadLocalDriver.get();
  }

  public static void quitDriver() {
    if (threadLocalDriver.get() != null) {
      threadLocalDriver.get().quit();
      threadLocalDriver.remove();
      log.info("Driver is quit");
    }
  }

  private static BrowserName getBrowserName() {
    String browserNameString = System.getProperty("BROWSER_NAME");
    BrowserName browserName;

    if (browserNameString != null && !browserNameString.isEmpty()) {
      browserName = BrowserName.valueOf(browserNameString.toUpperCase());
    } else {
      browserName = BrowserName.valueOf(ConfigurationManager.BROWSER_NAME.toUpperCase());
    }
    log.info("Browser name is set as {}", browserName);
    return browserName;
  }
}
