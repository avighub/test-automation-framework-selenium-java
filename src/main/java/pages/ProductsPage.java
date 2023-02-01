package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
  private final WebDriver DRIVER;
  public ProductsPage(WebDriver driverParam)
    {
      this.DRIVER = driverParam;
    }

  //Locators
  private final By logoImage = By.xpath("//div[@class='app_logo']");
  private final By productsHeaderText = By.xpath("//span[@class='title']");
  private final By menuButton = By.xpath("//*[@id='react-burger-menu-btn']");
  private final By robotImage = By.xpath("//div[@class='peek']");
  private final By cartButton = By.cssSelector(".shopping_cart_link");
  private final By sortingDropdown = By.xpath("//select[@class='product_sort_container']");
  private final By addToCartButtonBackpack = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
  private final By productPriceBackpack = By.xpath("//div[normalize-space()='$29.99']");



  //User Actions
  public ProductsPage navigateToProductsPge()
    {
      System.out.println("Navigate to Product page");
      String productPageUrl = "https://www.saucedemo.com/inventory.html";
      DRIVER.get(productPageUrl);
      return this;
    }

  public boolean isProductTitleVisible()
    {
      System.out.println("Check for product title visibility");
      return DRIVER.findElement(productsHeaderText).isDisplayed();
    }

  public boolean isLogoVisible()
    {
      System.out.println("Check for logo visibility");
      return DRIVER.findElement(logoImage).isDisplayed();
    }
  public boolean isRobotImageVisible()
    {
      System.out.println("Check for robot image visibility");
      return DRIVER.findElement(robotImage).isDisplayed();
    }

  public boolean isMenuButtonVisible()
    {
      System.out.println("Check for menu button  visibility");
      return DRIVER.findElement(menuButton).isDisplayed();
    }

  public boolean isSortingDropDownVisible()
    {
      System.out.println("Check for sort dropdown  visibility");
      return DRIVER.findElement(sortingDropdown).isDisplayed();
    }

  public boolean isCartButtonVisible()
    {
      System.out.println("Check for CartButton  visibility");
      return DRIVER.findElement(cartButton).isDisplayed();
    }


}
