package products;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.LoginPage;
import pages.ProductsPage;

import static utilities.driverutils.DriverFactory.getDriver;

public class ProductsPageTest extends BaseTest {

  private LoginPage loginPage;
  private ProductsPage productsPage;

  @BeforeMethod
  void preValidation()
    {
      loginPage = new LoginPage(getDriver());
      loginPage.navigateToLoginPge(); // navigate to login
      productsPage=loginPage.loginAsStandardUser(); //injecting cookie
      productsPage.navigateToProductsPge(); //Jumping to product page
      boolean isDisplayed = productsPage.isRobotImageVisible();
      Assert.assertTrue(isDisplayed);
    }

  @Test
  void verifyIfAllTheElementsArePresentInPrimaryHeader()
    {
      //Act
      boolean logoVisible = productsPage.isLogoVisible();
      boolean menuButtonVisible = productsPage.isMenuButtonVisible();
      boolean cartButtonVisible = productsPage.isCartButtonVisible();

      //Assert
      SoftAssert softAssert = new SoftAssert();
      softAssert.assertTrue(logoVisible);
      softAssert.assertTrue(menuButtonVisible);
      softAssert.assertTrue(cartButtonVisible);
      softAssert.assertAll();

    }

  @Test
  void verifyIfAllTheElementsArePresentInSecondaryHeader()
    {
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


}
