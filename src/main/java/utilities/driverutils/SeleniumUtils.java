package utilities.driverutils;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.SessionStorage;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.remote.Augmenter;

public final class SeleniumUtils {

  private WebDriver driver;

  public SeleniumUtils(WebDriver driverParam)
    {
      this.driver=driverParam;
    }


  public void setCookie(String key, String value)
    {
      Cookie cookie = new Cookie(key, value);
      driver.manage().addCookie(cookie);
      driver.navigate().refresh();
    }

  public static ChromeOptions getHeadlessBrowserOptions(String browserName){
    ChromeOptions options ;
    if(browserName.equalsIgnoreCase("chrome")){
      options = new ChromeOptions();
      options.addArguments("--headless");
      options.addArguments("--disable-gpu");
      options.addArguments("--disable-logging");
      options.addArguments("--log-level=3");
      options.addArguments("window-size=1920,1080");
      options.setPageLoadStrategy(PageLoadStrategy.NORMAL);

    }else {
      throw new RuntimeException("Invalid Browsername.");
    }
    return options;
  }

  public static String takeScreenShotAsBase64(WebDriver driver){
    //JAVA Typecasting concept, we are downcasting WebDriver class with TakeScreenshot Interface
    TakesScreenshot screenshotShot = ((TakesScreenshot) driver);
    return screenshotShot.getScreenshotAs(OutputType.BASE64);
  }

  public static void setLocalStorage(String key,String value,WebDriver driver){
    WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
    LocalStorage localStorage = webStorage.getLocalStorage();
    localStorage.setItem(key,value);
    driver.navigate().refresh();
  }

  public static void setSessionStorage(String key,String value,WebDriver driver){
    WebStorage webStorage = (WebStorage) new Augmenter().augment(driver);
    SessionStorage sessionStorage = webStorage.getSessionStorage();
    sessionStorage.setItem(key,value);
    driver.navigate().refresh();
  }

}
