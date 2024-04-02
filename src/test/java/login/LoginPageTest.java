package login;

import base.TestSetup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.LoginPage;
import utilities.SeleniumUtils;

public class LoginPageTest extends TestSetup {
  private LoginPageTest() {
  }

  private LoginPage loginPage;

  @BeforeEach
  void setupState() {
    loginPage = new LoginPage(driver);
//      loginPage.get();
    loginPage.navigateToLoginPge();
  }

  @ParameterizedTest
  @CsvSource({"standard_user,secret_sauce", "performance_glitch_user,secret_sauce"})
  void verifyUserIsAbleToLoginSuccessfullyUsingValidCredentials(String username, String password) {
    //Assert
    boolean productTitleVisible = loginPage
            .loginAsUser(username, password)
            .isProductTitleVisible();

    SeleniumUtils.setLocalStorage("key1", "value1", driver);
    SeleniumUtils.setSessionStorage("key1", "value1", driver);
    Assertions.assertTrue(productTitleVisible);
  }

  @ParameterizedTest
  @CsvSource({"standard_user1,secret_sauce_invalid1", "performance_glitch_user2,secret_sauce_invalid2"})
  void verifyValidationErrorMessageIsDisplayedIfUserEntersInvalidCredentials(String username, String password)
          throws InterruptedException {
    //Act
    loginPage.loginAsUser(username, password);
    // This is intentional to make the thread wait for this scenario
    Thread.sleep(3000);
    //Assert
    String expectedValidationErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    String actualValidationErrorMessage = loginPage.getValidationErrorMessage();
    Assertions.assertEquals(expectedValidationErrorMessage, actualValidationErrorMessage);

  }

}
