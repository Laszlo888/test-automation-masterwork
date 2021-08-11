package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClothesPage extends BasePage {

  public ClothesPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//article//img")
  WebElement firstClothProduct;

  public WebElement getFirstClothProduct() {
    return firstClothProduct;
  }
}
