package utilities.driverutils;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

@Slf4j
public final class DriverFactory {

  private DriverFactory() {
  }

  private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

  public static void setDriver(WebDriver driver) {
    if (Objects.nonNull(driver)) {
      threadLocalDriver.set(driver);
      log.debug("Driver is set");
    } else {
      throw new RuntimeException("Null Driver");
    }
  }

  public static WebDriver getDriver() {
    log.debug("Getting the driver instance");
    return threadLocalDriver.get();
  }

  public static void removeDriver() {
    if (Objects.nonNull(DriverFactory.getDriver())) {
      threadLocalDriver.get().quit();
      threadLocalDriver.remove();
      log.debug("Driver terminated");
    }
  }
}
