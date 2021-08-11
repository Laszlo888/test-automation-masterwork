package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends BasePage {

  public HomePage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//h2/a")
  List<WebElement> productsNames;

  @FindBy(xpath = "//nav[@class='pagination']//a[contains(text(),'Next')]")
  WebElement nextPageLink;

  @FindBy(partialLinkText = "Ecommerce")
  WebElement footerEcommerceLink;

  public WebElement getFooterEcommerceLink() {
    return footerEcommerceLink;
  }

  public List<WebElement> getProductsNames() {
    return productsNames;
  }

  public WebElement getNextPageLink() {
    return nextPageLink;
  }

  // get products name on current page
  public ArrayList<String> productNames() {
    List<WebElement> onCurrentPage = getProductsNames();
    ArrayList<String> pn = new ArrayList<>();
    for (int i = 0; i < onCurrentPage.size(); i++) {
      pn.add(onCurrentPage.get(i).getText());
    }

    return pn;
  }
}
