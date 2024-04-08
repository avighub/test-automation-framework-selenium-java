package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ConfigurationManager;
import utilities.SeleniumUtils;

import java.time.Duration;

public class LoginPage extends LoadableComponent<LoginPage> {
  private final WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  private final String loginUrl = ConfigurationManager.ENVIRONMENT_PROPERTIES.getProperty("LOGIN_URL");

  //Locators
  private final By USERNAME_TEXTBOX = By.id("user-name");
  private final By PASSWORD_TEXTBOX = By.id("password");
  private final By LOGIN_BUTTON = By.id("login-button");
  private final By LOGO_IMAGE = By.xpath("//*[@id='root']/div/div[1]");
  private final By VALIDATION_ERROR_MSG = By.xpath("//h3[@data-test='error']");


  //User Actions

  @Override
  protected void load() {
    driver.get(loginUrl);
  }

  @Override
  protected void isLoaded() throws Error {
    String currentUrl = driver.getCurrentUrl();
    if (!currentUrl.equals(loginUrl)) {
      throw new IllegalStateException("Login Page is not loaded");
    }
  }

  public LoginPage navigateToLoginPge() {
    String loginUrl = ConfigurationManager.ENVIRONMENT_PROPERTIES.getProperty("LOGIN_URL");
    driver.get(loginUrl);
    return this;
  }

  public LoginPage enterUsername(String username) {
    new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.visibilityOfElementLocated(USERNAME_TEXTBOX));

    System.out.println("Enter Username: " + username);
    driver.findElement(USERNAME_TEXTBOX).sendKeys(username);
    return this;
  }

  public LoginPage enterPassword(String password) {
    System.out.println("Enter password: " + password);
    driver.findElement(PASSWORD_TEXTBOX).sendKeys(password);
    return this;
  }

  public ProductsPage clickLoginButton() {
    System.out.println("Click Login button");
    driver.findElement(LOGIN_BUTTON).click();
    return new ProductsPage(driver);
  }

  //Using method composition
  public ProductsPage loginAsUser(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
    return new ProductsPage(driver);
  }

  public ProductsPage loginAsUser(String userRole) {
    SeleniumUtils seleniumUtils = new SeleniumUtils(driver);
    seleniumUtils.setCookie("session-username", userRole);
    return new ProductsPage(driver);
  }

  public ProductsPage loginAsStandardUser() {
    loginAsUser("standard_user");
    return new ProductsPage(driver);
  }

  public boolean isLogoVisible() {
    System.out.println("Check for logo visibility");
    return driver.findElement(LOGO_IMAGE).isDisplayed();
  }

  public String getValidationErrorMessage() {
    System.out.println("Check for Validation error msg");
    return driver.findElement(VALIDATION_ERROR_MSG).getText();
  }


}
