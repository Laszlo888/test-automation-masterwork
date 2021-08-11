package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

  public CartPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//li[@class='cart-item']")
  List<WebElement> productsNameInCart;

  @FindBy(xpath = "//a[@data-link-action='delete-from-cart']/i")
  WebElement deleteProductFromCart;

  @FindBy(xpath = "//li[@class='cart-item']")
  WebElement firstCartItem;

  @FindBy(partialLinkText = "Ecommerce")
  WebElement footerEcommerceLink;

  public WebElement getFooterEcommerceLink() {
    return footerEcommerceLink;
  }

  public WebElement getFirstCartItem() {
    return firstCartItem;
  }

  public List<WebElement> getProductsNameInCart() {
    return productsNameInCart;
  }

  public WebElement getDeleteProductFromCart() {
    return deleteProductFromCart;
  }
}
