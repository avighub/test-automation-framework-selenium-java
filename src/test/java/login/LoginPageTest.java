package login;

import base.BaseTest;
import com.aventstack.extentreports.Status;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import utilities.driverutils.SeleniumUtils;
import static reporting.ExtentFactory.*;
import static utilities.driverutils.DriverFactory.*;

public class LoginPageTest extends BaseTest {
  private LoginPageTest(){
  }

  private LoginPage loginPage;

  @BeforeMethod
  void preValidation()
    {
      loginPage = new LoginPage(getDriver());
      loginPage.navigateToLoginPge();
      boolean isDisplayed = loginPage.isRobotImageVisible();
      Assert.assertTrue(isDisplayed);
    }

  @Test
  void verifyForLogoAndRobotImageVisibility()
    {
      getExtentTest().log(Status.INFO,"Checking for Logo visibility");
      boolean isLogoVisible = loginPage.isLogoVisible();
      boolean isRobotImageVisible = loginPage.isRobotImageVisible();

      SoftAssert softAssert = new SoftAssert();
      softAssert.assertTrue(isLogoVisible);
      softAssert.assertTrue(isRobotImageVisible);
      softAssert.assertAll();
    }

  @Test( dataProvider = "getValidLoginCredentials", dataProviderClass = LoginDataProviders.class)
  public void verifyUserIsAbleToLoginSuccessfullyUsingValidCredentials(String username, String password)
    {
      //Assert
      getExtentTest().log(Status.INFO,"Checking for Products page displayed?");
      boolean productTitleVisible = loginPage
                      .loginAsUser(username, password)
                      .isProductTitleVisible();

      SeleniumUtils.setLocalStorage("key1","value1",getDriver());
      SeleniumUtils.setSessionStorage("key1","value1",getDriver());
      getExtentTest().log(Status.INFO,"Session storage created");
      Assert.assertTrue(productTitleVisible);
    }

  @Test(dataProvider = "getInvalidLoginCredentials", dataProviderClass = LoginDataProviders.class)
  public void verifyValidationErrorMessageIsDisplayedIfUserEntersInvalidCredentials(String username, String password) throws InterruptedException
    {
      //Act
      loginPage.loginAsUser(username,password);
      Thread.sleep(3000);
      //Assert
      String expectedValidationErrorMessage = "Epic sadface: Username and password do not match any user in this service";
      String actualValidationErrorMessage = loginPage.getValidationErrorMessage();
      getExtentTest().log(Status.INFO,"Checking for error message displayed correctly??");
      Assert.assertEquals(actualValidationErrorMessage, expectedValidationErrorMessage);
    }

}
