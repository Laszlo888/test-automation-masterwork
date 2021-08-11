package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class InformationPage extends BasePage {

  public InformationPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//input[@name='lastname']")
  WebElement lastName;

  @FindBy(xpath = "//input[@name='password']")
  WebElement password;

  @FindBy(xpath = "//input[@name='psgdpr']")
  WebElement gdprCheckbox;

  @FindBy(xpath = "//input[@name='customer_privacy']")
  WebElement customerPrivacyCheckbox;

  @FindBy(xpath = "//button[contains(text(),'Save')]")
  WebElement saveButton;

  @FindBy(xpath = "//a[@title='View my customer account']")
  WebElement myAccountLink;

  @FindBy(xpath = "//article[@data-alert='success']/ul/li")
  WebElement successUpdateMessage;

  public WebElement getLastName() {
    return lastName;
  }

  public WebElement getPassword() {
    return password;
  }

  public WebElement getGdprCheckbox() {
    return gdprCheckbox;
  }

  public WebElement getCustomerPrivacyCheckbox() {
    return customerPrivacyCheckbox;
  }

  public WebElement getSaveButton() {
    return saveButton;
  }

  public WebElement getMyAccountLink() {
    return myAccountLink;
  }

  public WebElement getSuccessUpdateMessage() {
    return successUpdateMessage;
  }

  // change lastname, fill form
  public void changeLastName(String currentLastName, String password) {
    String changedName;
    if (currentLastName.indexOf("-") > 0) {
      changedName = currentLastName.substring(0, currentLastName.indexOf("-"));
    } else {
      changedName = currentLastName + "-MwalkrT";
    }

    getLastName().clear();
    getLastName().sendKeys(changedName);
    getPassword().sendKeys(password);
    getCustomerPrivacyCheckbox().click();
    getGdprCheckbox().click();
    getSaveButton().click();
  }
}
