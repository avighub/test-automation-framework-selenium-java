package pages.saucedemo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * This class is just an example of Page Factory
 * DO NOT USE Page Factory as it leads to stale element for dynamically loading pages.
 */
public class ProductsPageWithPageFactory {
  private final WebDriver driver;

  public ProductsPageWithPageFactory(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, ProductsPageWithPageFactory.class); // It is discouraged to use Page Factory
  }

  //Locators
  @FindBy(xpath = "//div[@class='app_logo']")
  private WebElement logoImage;

  @FindBy(xpath = "//span[@class='title']")
  private WebElement productsHeaderText;

  //User Actions
  public ProductsPageWithPageFactory navigateToProductsPge() {
    System.out.println("Navigate to Product page");
    String productPageUrl = "https://www.saucedemo.com/inventory.html";
    this.driver.get(productPageUrl);
    return this;
  }

  public boolean isProductTitleVisible() {
    System.out.println("Check for product title visibility");
    return productsHeaderText.isDisplayed();
  }

  public boolean isLogoVisible() {
    System.out.println("Check for logo visibility");
    return logoImage.isDisplayed();
  }

}
