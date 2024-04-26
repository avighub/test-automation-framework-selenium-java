package tests.saucedemo;

import base.TestSetup;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;


public class LoginPageTest extends TestSetup {
  private LoginPageTest() {
  }

  private LoginPage loginPage;

  @BeforeMethod
  void setupState() {
    loginPage = new LoginPage(driver).get();
  }

  @DataProvider
  private Object[][] getValidLoginCredentials() {
    return new Object[][]{
            {"standard_user", "secret_sauce"},
            {"performance_glitch_user", "secret_sauce"},
    };
  }

  @DataProvider
  public static Object[][] getInvalidLoginCredentials() {
    return new Object[][]{
            {"standard_user1", "secret_sauce_invalid1"},
            {"performance_glitch_user", "secret_sauce_invalid2"},
    };
  }

  @Test(dataProvider = "getValidLoginCredentials")
  public void verifyUserIsAbleToLoginSuccessfullyUsingValidCredentials(String username, String password) {
    //Assert
    boolean productTitleVisible = loginPage
            .loginAsUser(username, password)
            .isProductTitleVisible();

    Assert.assertTrue(productTitleVisible);
  }

  @Test(dataProvider = "getInvalidLoginCredentials")
  public void verifyValidationErrorMessageIsDisplayedIfUserEntersInvalidCredentials(String username, String password)
          throws InterruptedException {
    //Act
    loginPage.loginAsUser(username, password);
    // This is intentional to make the thread wait for this scenario
    Thread.sleep(3000);

    //Assert
    String expectedValidationErrorText = "Epic sadface: Username and password do not match any user in this service";
    String actualValidationErrorText = loginPage.getValidationErrorMessage();
    Assert.assertEquals(actualValidationErrorText, expectedValidationErrorText);
  }

}
