package webdriver;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 1- Keeping this class only for one responsibility , that is to manage browser
 * 2- This will be called only from DriverFactory.java class and hence createDriver()
 * is made as package-private , that is can only be accessed from DriverFactory.java class
 * 3- Thus we are following Single Responsibility Principle and making use of access modifiers
 * effectively
 */
@Slf4j
public final class BrowserManager {
  private final BrowserName browserName;

  BrowserManager(BrowserName browserName) {
    this.browserName = browserName;
  }

  WebDriver createDriver() {
    WebDriver driver;

    switch (browserName) {
      case CHROME:
        ChromeOptions chromeOptions = new ChromeOptions();
        driver = new ChromeDriver(chromeOptions);
        driver.manage().window().maximize();
        log.debug("Chrome driver created");
        break;
      case FIREFOX:
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().window().maximize();
        log.debug("Firefox driver created");
        break;
      case SAFARI:
        driver = new SafariDriver();
        driver.manage().window().maximize();
        log.debug("Safari driver created");
        break;
      case EDGE:
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        log.debug("Edge driver created");
        break;
      default:
        throw new IllegalArgumentException("Invalid browser name: " + browserName);
    }
    return driver;
  }
}
