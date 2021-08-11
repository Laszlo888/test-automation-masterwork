import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_03_LoginFailed extends BaseTest {

  String email = "ceja@gmail.com";
  String badPassword = "Asdfghj2";

  @DisplayName("Failed login")
  @Feature("Login")
  @Description("Do a failed login")
  @Test
  public void failedLogin() {

    startPage.startPageOpen();
    startPage.clickSignIn();

    signInPage.login(email, badPassword);

    wait.until(ExpectedConditions.visibilityOf(signInPage.getLogInFailedErrorMessage()));
  }
}
