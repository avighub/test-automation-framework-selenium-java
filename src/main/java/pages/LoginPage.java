package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigurationManager;
import utilities.driverutils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class LoginPage {
  private final WebDriver DRIVER;

  public LoginPage(WebDriver driverParam){
    this.DRIVER =driverParam;
  }

  //Locators
  private final By USERNAME_TEXTBOX = By.id("user-name");
  private final By PASSWORD_TEXTBOX = By.id("password");
  private final By LOGIN_BUTTON = By.id("login-button");
  private final By LOGO_IMAGE = By.xpath("//*[@id='root']/div/div[1]");
  private final By ROBOT_IMAGE = By.xpath("//*[@id='root']/div/div[1]");
  private final By VALIDATION_ERROR_MSG = By.xpath("//h3[@data-test='error']");


  //User Actions
  public LoginPage navigateToLoginPge()
    {
    String loginUrl = ConfigurationManager.ENVIRONMENT_PROPERTIES.getProperty("LOGIN_URL");
    System.out.println("Navigate to : " + loginUrl);
    DRIVER.get(loginUrl);
    return this;
  }

  public LoginPage enterUsername(String username){
     new WebDriverWait(DRIVER, Duration.ofSeconds(30))
            .until(ExpectedConditions.visibilityOfElementLocated(USERNAME_TEXTBOX));

    System.out.println("Enter Username: " + username);
    DRIVER.findElement(USERNAME_TEXTBOX).sendKeys(username);
    return this;
  }

  public LoginPage enterPassword(String password){
    System.out.println("Enter password: " + password);
    DRIVER.findElement(PASSWORD_TEXTBOX).sendKeys(password);
    return this;
  }

  public ProductsPage clickLoginButton(){
    System.out.println("Click Login button");
    DRIVER.findElement(LOGIN_BUTTON).click();
    return new ProductsPage(DRIVER);
  }

  //Using method composition
  public ProductsPage loginAsUser(String username, String password){
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
    return new ProductsPage(DRIVER);
  }

  public ProductsPage loginAsUser(String userRole){
    SeleniumUtils seleniumUtils = new SeleniumUtils(DRIVER);
    seleniumUtils.setCookie("session-username",userRole);
    return new ProductsPage(DRIVER);
  }

  public ProductsPage loginAsStandardUser(){
    loginAsUser("standard_user");
    return  new ProductsPage(DRIVER);
  }

  public boolean isLogoVisible(){
    System.out.println("Check for logo visibility");
    return DRIVER.findElement(LOGO_IMAGE).isDisplayed();
  }
  public boolean isRobotImageVisible(){
    System.out.println("Check for Robot Image visibility");
    return DRIVER.findElement(ROBOT_IMAGE).isDisplayed();
  }

  public String getValidationErrorMessage(){
    System.out.println("Check for Validation error msg");
    return DRIVER.findElement(VALIDATION_ERROR_MSG).getText();
  }

}
