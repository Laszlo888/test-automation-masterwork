package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage {

  public ProductPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//div[@class='add']/button")
  WebElement addToCart;

  @FindBy(xpath = "//div[@class='cart-content-btn']/a")
  WebElement afterAddToCartPopUpWindowProceedButton;

  public WebElement getAddToCart() {
    return addToCart;
  }

  public WebElement getAfterAddToCartPopUpWindowProceedButton() {
    return afterAddToCartPopUpWindowProceedButton;
  }
}
