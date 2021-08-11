package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage{

  public StartPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//a[@href='http://test-automation-shop1.greenfox.academy/6-accessories']")
  WebElement accessoriesMenu;

  @FindBy(xpath = "//a[@href='http://test-automation-shop1.greenfox.academy/3-clothes']")
  WebElement clothesMenu;

  @FindBy(xpath = "//a[contains(text(),'All products')]")
  WebElement allProducts;

  @FindBy(xpath = "//span[contains(text(),'Sign in')]")
  WebElement signInLink;

  @FindBy(partialLinkText = "Ecommerce")
  WebElement footerEcommerceLink;

  public WebElement getAccessoriesMenu() {
    return accessoriesMenu;
  }

  public WebElement getClothesMenu() {
    return clothesMenu;
  }

  public WebElement getAllProducts() {
    return allProducts;
  }

  public WebElement getSignInLink() {
    return signInLink;
  }

  public WebElement getFooterEcommerceLink() {
    return footerEcommerceLink;
  }

  public void clickSignIn() {
    getSignInLink().click();
  }

  public void startPageOpen() {
    driver.get("http://test-automation-shop1.greenfox.academy/");
    driver.manage().window().maximize();
    wait.until(ExpectedConditions.visibilityOf(getFooterEcommerceLink()));
  }



}
