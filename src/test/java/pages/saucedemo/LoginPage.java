package pages.saucedemo;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utilities.ConfigurationManager;
import utilities.driverutils.SeleniumUtils;

import java.time.Duration;

/**
 * Making page object more concise by using LoadableComponent
 * override load(),isLoaded() methods
 * https://github.com/SeleniumHQ/selenium/wiki/LoadableComponent
 */

@Slf4j
public class LoginPage extends LoadableComponent<LoginPage> {
  private final WebDriver driver;

  public LoginPage(WebDriver driver) {
    this.driver = driver;
  }

  private final String loginUrl = ConfigurationManager.ENVIRONMENT_PROPERTIES.getProperty("LOGIN_URL");


  @Override
  protected void load() {
    driver.get(loginUrl);
  }

  /**
   * Note: isLoaded() method has assertion, which is generally anti pattern but
   * this is an exception to ensure right page is loaded.
   * For more info: https://github.com/SeleniumHQ/selenium/wiki/LoadableComponent
   */
  @Override
  protected void isLoaded() throws Error {
    String currentUrl = driver.getCurrentUrl();
    log.debug("Current URL: {}", currentUrl);

    if (!currentUrl.equals(loginUrl)) {
      Assert.assertEquals(currentUrl, loginUrl, "Login Page is not loaded");
    }
  }

  public LoginPage enterUsername(String username) {
    By usernameInput = By.id("user-name");
    new WebDriverWait(driver, Duration.ofSeconds(30))
            .until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    driver.findElement(usernameInput).sendKeys(username);

    log.debug("Entered username as : {}", username);
    return this;
  }

  public LoginPage enterPassword(String password) {
    final By passwordInput = By.id("password");
    driver.findElement(passwordInput).sendKeys(password);

    log.debug("Entered password as : {}", password);
    return this;
  }

  public ProductsPage clickLoginButton() {
    final By LOGIN_BUTTON = By.id("login-button");
    driver.findElement(LOGIN_BUTTON).click();

    log.debug("Clicked Login button");
    return new ProductsPage(driver);
  }

  // Using method composition
  public ProductsPage loginAsUser(String username, String password) {
    enterUsername(username);
    enterPassword(password);
    clickLoginButton();
    return new ProductsPage(driver);
  }

  public ProductsPage loginAsUser(String userRole) {
    SeleniumUtils.setCookie(driver, "session-username", userRole);
    return new ProductsPage(driver);
  }

  public ProductsPage loginAsStandardUser() {
    loginAsUser("standard_user");
    return new ProductsPage(driver);
  }

  public String getValidationErrorMessage() {
    final By validationErrorMessage = By.xpath("//h3[@data-test='error']");
    return driver.findElement(validationErrorMessage).getText();
  }


}
