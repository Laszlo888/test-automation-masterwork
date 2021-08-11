import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_01_RegSuccess extends BaseTest {

  String email = "ceja@gmail.com";
  String password = "Asdfghj1";
  String firstname = "János";
  String lastname = "Cecei";

  @DisplayName("Successful registration")
  @Feature("Registration")
  @Description("Do a successful registration")
  @Test
  public void successfulRegistration() {

    startPage.startPageOpen();

    startPage.clickSignIn();

    wait.until(ExpectedConditions.visibilityOf(signInPage.getRegistrationFormLink())).click();

    wait.until(ExpectedConditions.elementToBeClickable(registrationPage.getRegSaveButton()));

    registrationPage.registrationFormFill(firstname, lastname, password, email);

    wait.until(ExpectedConditions.visibilityOf(registrationPage.getFooterEcommerceLink()));

    // if email is already registered
    try {
      wait.until(ExpectedConditions.visibilityOf(registrationPage.getErrorMessageEmailIsUsed()));

      // if email is registered, we generating new email address with random chars
      registrationPage.newEmailIfEmailIsAlreadyRegistered(email, password);

    } catch (Exception ignored) {
    }

    // check logged in name is presence
    wait.until(ExpectedConditions.elementToBeClickable(myAccountPage.getMyAccountLink()));
  }
}
