package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddressPage extends BasePage{

  public AddressPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//span[contains(text(),'Update')]")
  WebElement updateLink;

  @FindBy(xpath = "//input[@name='address1']")
  WebElement addressInput;

  @FindBy(xpath = "//input[@name='city']")
  WebElement cityInput;

  @FindBy(xpath = "//input[@name='postcode']")
  WebElement postCodeInput;

  @FindBy(name = "id_state")
  WebElement chooseState;

  @FindBy(xpath = "//button[contains(text(),'Save')]")
  WebElement saveButton;

  @FindBy(
      xpath =
          "//article[@data-alert='success']/ul/li[contains(text(),'Address successfully updated!')]")
  WebElement successUpdatedAddressMessage;

  @FindBy(
      xpath =
          "//article[@data-alert='success']/ul/li[contains(text(),'Address successfully added!')]")
  WebElement successAddedAddressMessage;

  public WebElement getUpdateLink() {
    return updateLink;
  }

  public WebElement getAddressInput() {
    return addressInput;
  }

  public WebElement getCityInput() {
    return cityInput;
  }

  public WebElement getPostCodeInput() {
    return postCodeInput;
  }

  public WebElement getChooseState() {
    return chooseState;
  }

  public WebElement getSaveButton() {
    return saveButton;
  }

  public WebElement getSuccessUpdatedAddressMessage() {
    return successUpdatedAddressMessage;
  }

  public WebElement getSuccessAddedAddressMessage() {
    return successAddedAddressMessage;
  }

  // fill from csv
  public void fillForm(String address, String city, String postalCode) {
    getAddressInput().clear();
    getAddressInput().sendKeys(address);
    getCityInput().clear();
    getCityInput().sendKeys(city);
    getPostCodeInput().clear();
    getPostCodeInput().sendKeys(postalCode);

    Select state = new Select(getChooseState());
    state.selectByVisibleText("Alaska");

    getSaveButton().click();
  }
}
