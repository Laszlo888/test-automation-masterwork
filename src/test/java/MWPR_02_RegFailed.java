import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_02_RegFailed extends BaseTest {

  String alreadyUsedEmail = "ceceij@gmail.com";
  String password = "Asdfghj1";
  String firstname = "János";
  String lastname = "Cecei";

  @DisplayName("Failed registration")
  @Feature("Registration")
  @Description("Do a failed registration")
  @Test
  public void failedRegistration() {

    startPage.startPageOpen();
    startPage.clickSignIn();
    wait.until(ExpectedConditions.visibilityOf(signInPage.getRegistrationFormLink())).click();

    wait.until(ExpectedConditions.elementToBeClickable(registrationPage.getRegSaveButton()));

    registrationPage.registrationFormFill(firstname, lastname, password, alreadyUsedEmail);

    wait.until(ExpectedConditions.visibilityOf(registrationPage.getErrorMessageEmailIsUsed()));
  }
}
