package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AccessoriesPage extends BasePage {

  public AccessoriesPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//button[contains(text(),'Relevance')]")
  WebElement chooseSortingMethodMenu;

  @FindBy(xpath = "//a[contains(text(),'Price, low to high')]")
  WebElement pricesLowToHighLink;

  @FindBy(xpath = "//button[contains(text(),'Price, low to high')]")
  WebElement pricesLowToHighAfterClickingIt;

  @FindBy(xpath = "//span[@class='price']")
  List<WebElement> prices;

  @FindBy(xpath = "//h2/a")
  List<WebElement> nameOfAccessoriesOnPage;

  public WebElement getChooseSortingMethodMenu() {
    return chooseSortingMethodMenu;
  }

  public WebElement getPricesLowToHighLink() {
    return pricesLowToHighLink;
  }

  public WebElement getPricesLowToHighAfterClickingIt() {
    return pricesLowToHighAfterClickingIt;
  }

  public List<WebElement> getPrices() {
    return prices;
  }

  public List<WebElement> getNameOfAccessoriesOnPage() {
    return nameOfAccessoriesOnPage;
  }

  // collect prices
  public boolean dataListing() {
    List<WebElement> prices = getPrices();
    boolean result = false;

    // check prices are in good order
    for (int i = 0; i < prices.size() - 1; i++) {
      if (Double.parseDouble(prices.get(i).getText().substring(1))
          > Double.parseDouble(prices.get(i + 1).getText().substring(1))) {
        result = true;
        break;
      }
    }
    return result;
  }
}
