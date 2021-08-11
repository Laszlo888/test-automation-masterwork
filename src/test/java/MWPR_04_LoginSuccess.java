import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_04_LoginSuccess extends BaseTest {

  String email = "ceja@gmail.com";
  String password = "Asdfghj1";
  String alreadyUsedEmail = "ceceij@gmail.com";

  @DisplayName("Successful login")
  @Feature("Login")
  @Description("Do a successful login")
  @Test
  public void successfulLogin() {

    startPage.startPageOpen();
    startPage.clickSignIn();

    signInPage.login(email, password);

    signInPage.checkLogin(alreadyUsedEmail, password);

    wait.until(ExpectedConditions.visibilityOf(myAccountPage.getMyAccountLink()));
  }
}
