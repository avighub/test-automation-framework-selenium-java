package utilities.driverutils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import utilities.ConfigurationManager;

@Slf4j
public final class DriverManager {

  private DriverManager() {

  }

  private static final String BROWSER_NAME = ConfigurationManager.BROWSER_NAME;

  public static WebDriver getDriver() {
    WebDriver driver = null;
    if (BROWSER_NAME.equalsIgnoreCase(BrowserName.CHROME.toString())) {
      driver = new ChromeDriver();
      driver.manage().window().maximize();
      log.debug("Chrome driver created");
    } else if (BROWSER_NAME.equalsIgnoreCase(BrowserName.SAFARI.toString())) {
      driver = new SafariDriver();
      driver.manage().window().maximize();
      log.debug("Safari driver created");
    } else if (BROWSER_NAME.equalsIgnoreCase(BrowserName.EDGE.toString())) {
      driver = new EdgeDriver();
      driver.manage().window().maximize();
      log.debug("Edge driver created");
    } else {
      // TODO: Implement custom exception
      throw new RuntimeException("Invalid browser name selected. Please select one of these: chrome, firefox, safari, edge");
    }
    return driver;
  }
}
