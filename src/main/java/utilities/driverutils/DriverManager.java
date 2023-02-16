package utilities.driverutils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;
import   utilities.ConfigurationManager;

import static utilities.ConfigurationManager.*;


public class DriverManager {

  private DriverManager(){

  }

  private static final String BROWSER_NAME = ConfigurationManager.BROWSER_NAME;
                                //FRAMEWORK_PROPERTIES.getProperty("BROWSER_NAME");
  public static WebDriver getDriver()
    {
      WebDriver driver=null;
      if(BROWSER_NAME.equalsIgnoreCase(BrowserName.CHROME.toString())){
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.manage().window().maximize();
      } else if (BROWSER_NAME.equalsIgnoreCase(BrowserName.SAFARI.toString())) {
        WebDriverManager.safaridriver().setup();
        driver= new SafariDriver();
        driver.manage().window().maximize();
      } else if (BROWSER_NAME.equalsIgnoreCase(BrowserName.EDGE.toString())) {
        WebDriverManager.edgedriver().setup();
        driver= new EdgeDriver();
        driver.manage().window().maximize();
      }else {
        System.out.println("Invalid browser name selected. Please select one of these: chrome, firefox, safari, edge");
      }
      return driver;
    }
}
