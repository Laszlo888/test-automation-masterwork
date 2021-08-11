package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

  public MyAccountPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//section[@id='content']//div[@class='links']/a[2]")
  WebElement linkToAddressForm;

  @FindBy(xpath = "//a[@title='View my customer account']")
  WebElement myAccountLink;

  @FindBy(id = "identity-link")
  WebElement linkToInformationForm;

  @FindBy(xpath = "//a[@href='http://test-automation-shop1.greenfox.academy/?mylogout=']")
  WebElement logOutLink;

  @FindBy(xpath = "//span[contains(text(),'Sign in')]")
  WebElement signInLink;

  public WebElement getSignInLink() {
    return signInLink;
  }

  public WebElement getLogOutLink() {
    return logOutLink;
  }

  public WebElement getMyAccountLink() {
    return myAccountLink;
  }

  public WebElement getLinkToAddressForm() {
    return linkToAddressForm;
  }

  public WebElement getLinkToInformationForm() {
    return linkToInformationForm;
  }
}
