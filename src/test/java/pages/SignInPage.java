package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SignInPage extends BasePage {

  public SignInPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//input[@name='email' and @class='form-control']")
  WebElement logInEmailField;

  @FindBy(xpath = "//input[@type='password']")
  WebElement logInPasswordField;

  @FindBy(id = "submit-login")
  WebElement logInSubmitButton;

  @FindBy(xpath = "//section[@class='login-form']//li[contains(text(),'Authentication failed')]")
  WebElement logInFailedErrorMessage;

  @FindBy(linkText = "No account? Create one here")
  WebElement registrationFormLink;

  public WebElement getLogInEmailField() {
    return logInEmailField;
  }

  public WebElement getLogInPasswordField() {
    return logInPasswordField;
  }

  public WebElement getLogInSubmitButton() {
    return logInSubmitButton;
  }

  public WebElement getLogInFailedErrorMessage() {
    return logInFailedErrorMessage;
  }

  public WebElement getRegistrationFormLink() {
    return registrationFormLink;
  }

  public void login(String email, String password) {

    wait.until(ExpectedConditions.elementToBeClickable(getLogInEmailField())).sendKeys(email);
    getLogInPasswordField().sendKeys(password);
    getLogInSubmitButton().click();
  }
  // if registration fails, using already registered email
  public void checkLogin(String alreadyUsedEmail, String password) {
    try {
      wait.until(ExpectedConditions.visibilityOf(getLogInFailedErrorMessage()));
      getLogInEmailField().clear();
      getLogInEmailField().sendKeys(alreadyUsedEmail);
      getLogInPasswordField().sendKeys(password);
      getLogInSubmitButton().click();
    } catch (Exception ignored) {
    }
    // waiting for presence of first name, last name (my account)

  }
}
