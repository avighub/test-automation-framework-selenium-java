package utilities.driverutils;

import org.openqa.selenium.WebDriver;

import java.util.Objects;

public final class DriverFactory {

  private DriverFactory(){}
  private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

  public static void setDriver(WebDriver driver){
    if(Objects.nonNull(driver)){
      threadLocalDriver.set(driver);
      System.out.println("== Driver is Set== ");
    }else {
      throw new RuntimeException("Null Driver");
    }
  }

  public static WebDriver getDriver(){
    return threadLocalDriver.get();
  }

  public static void removeDriver(){
    if(Objects.nonNull(DriverManager.getDriver())){
      threadLocalDriver.get().quit();
      threadLocalDriver.remove();
    }
  }
}
