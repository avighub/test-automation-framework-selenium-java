package tests.saucedemo;

import base.TestSetup;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.saucedemo.LoginPage;
import pages.saucedemo.ProductsPage;

import static webdriver.DriverFactory.getDriver;

public class ProductsPageTest extends TestSetup {

  private LoginPage loginPage;
  private ProductsPage productsPage;

  @BeforeMethod
  void preValidation() {
    loginPage = new LoginPage(getDriver());
    loginPage.navigateToLoginPge(); // navigate to login
    productsPage = loginPage.loginAsStandardUser(); //injecting cookie
    productsPage.navigateToProductsPge(); //Jumping to product page
    boolean isDisplayed = productsPage.isRobotImageVisible();
    Assert.assertTrue(isDisplayed);
  }

  @Test
  void verifyIfAllTheElementsArePresentInPrimaryHeader() {
    //Act
    boolean logoVisible = productsPage.isLogoVisible();
    boolean menuButtonVisible = productsPage.isMenuButtonVisible();
    boolean cartButtonVisible = productsPage.isCartButtonVisible();

    //Assert
    try {
      SoftAssert softAssert = new SoftAssert();
      softAssert.assertFalse(logoVisible);
      softAssert.assertTrue(menuButtonVisible);
      softAssert.assertTrue(cartButtonVisible);

      softAssert.assertAll();
    } catch (AssertionError e) {
      e.printStackTrace();
      System.out.println("one assertion got failed");
    }

  }

  @Test(enabled = false)
  void verifyIfAllTheElementsArePresentInSecondaryHeader() {
    //Act
    boolean productTitleVisible = productsPage.isProductTitleVisible();
    boolean robotImageVisible = productsPage.isRobotImageVisible();
    boolean sortingDropDownVisible = productsPage.isSortingDropDownVisible();

    //Assert
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(productTitleVisible);
    softAssert.assertTrue(robotImageVisible);
    softAssert.assertTrue(sortingDropDownVisible);
    softAssert.assertAll();
  }

  @Test
  public void skippedTest() {
    throw new SkipException("the test is skipped");
  }

}
