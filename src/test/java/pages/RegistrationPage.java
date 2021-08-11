package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class RegistrationPage extends BasePage {

  public RegistrationPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//input[@name='firstname']")
  WebElement regFirstName;

  @FindBy(xpath = "//input[@name='lastname']")
  WebElement regLastName;

  @FindBy(xpath = "//input[@name='email']")
  WebElement regEmail;

  @FindBy(xpath = "//input[@name='password']")
  WebElement regPassword;

  @FindBy(xpath = "//input[@name='customer_privacy']")
  WebElement regCustomerPrivacy_checkbox;

  @FindBy(xpath = "//input[@name='psgdpr']")
  WebElement regGDPR_checkbox;

  @FindBy(xpath = "//button[contains(text(),'Save')]")
  WebElement regSaveButton;

  @FindBy(xpath = "//li[contains(text(),'email is already used')]")
  WebElement errorMessageEmailIsUsed;

  @FindBy(partialLinkText = "Ecommerce")
  WebElement footerEcommerceLink;

  public WebElement getRegFirstName() {
    return regFirstName;
  }

  public WebElement getRegLastName() {
    return regLastName;
  }

  public WebElement getRegEmail() {
    return regEmail;
  }

  public WebElement getRegPassword() {
    return regPassword;
  }

  public WebElement getRegCustomerPrivacy_checkbox() {
    return regCustomerPrivacy_checkbox;
  }

  public WebElement getRegGDPR_checkbox() {
    return regGDPR_checkbox;
  }

  public WebElement getFooterEcommerceLink() {
    return footerEcommerceLink;
  }

  public WebElement getRegSaveButton() {
    return regSaveButton;
  }

    public WebElement getErrorMessageEmailIsUsed() {
    return errorMessageEmailIsUsed;
  }

  public void registrationFormFill(
      String firstname, String lastname, String password, String email) {
    getRegFirstName().sendKeys(firstname);
    getRegLastName().sendKeys(lastname);
    getRegPassword().sendKeys(password);
    getRegEmail().sendKeys(email);
    getRegCustomerPrivacy_checkbox().click();
    getRegGDPR_checkbox().click();
    getRegSaveButton().click();
  }

  public void newEmailIfEmailIsAlreadyRegistered(String email, String password) {

    // if email is registered, we generating new email address with random chars
    Random r = new Random();
    String ch = "123456789abcdefghijklmnopqrstuvwxyz";
    int randomInt1 = r.nextInt(ch.length());
    char randomChar1 = ch.charAt(randomInt1);
    String r1 = Character.toString(randomChar1);

    int randomInt2 = r.nextInt(ch.length());
    char randomChar2 = ch.charAt(randomInt2);
    String r2 = Character.toString(randomChar2);

    int randomInt3 = r.nextInt(ch.length());
    char randomChar3 = ch.charAt(randomInt3);
    String r3 = Character.toString(randomChar3);

    email = r1 + r2 + r3 + email;

    getRegEmail().clear();
    getRegEmail().sendKeys(email);
    getRegPassword().sendKeys(password);
    getRegSaveButton().click();
  }
}
