package login;

import base.TestSetup;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utilities.driverutils.SeleniumUtils;

import static reporting.ExtentFactory.getExtentTest;

public class LoginPageTest extends TestSetup {
  private LoginPageTest() {
  }

  private LoginPage loginPage;

  @BeforeMethod
  void setupState() {
    loginPage = new LoginPage(driver);
//      loginPage.get();
    loginPage.navigateToLoginPge();
  }

  @DataProvider
  private Object[][] getValidLoginCredentials() {
    return new Object[][] {
      {"standard_user", "secret_sauce"},
      {"performance_glitch_user", "secret_sauce"},
    };
  }

  @DataProvider
  public static Object[][] getInvalidLoginCredentials() {
    return new Object[][] {
      {"standard_user", "secret_sauce_invalid1"},
      {"performance_glitch_user", "secret_sauce_invalid2"},
      {"performance_glitch_user2", "secret_sauce_invalid3"},
    };
  }

  @Test(dataProvider = "getValidLoginCredentials")
  public void verifyUserIsAbleToLoginSuccessfullyUsingValidCredentials(String username, String password) {
    //Assert
    getExtentTest().log(Status.INFO, "Checking for Products page displayed?");
    boolean productTitleVisible = loginPage
      .loginAsUser(username, password)
      .isProductTitleVisible();

    SeleniumUtils.setLocalStorage("key1", "value1", driver);
    SeleniumUtils.setSessionStorage("key1", "value1", driver);
    getExtentTest().log(Status.INFO, "Session storage created");
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
    String expectedValidationErrorMessage = "Epic sadface: Username and password do not match any user in this service";
    String actualValidationErrorMessage = loginPage.getValidationErrorMessage();
    getExtentTest().log(Status.INFO, "Checking for error message displayed correctly??");
    Assert.assertEquals(actualValidationErrorMessage, expectedValidationErrorMessage);
  }

}
