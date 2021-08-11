import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_12_LogOutSuccess extends BaseTest {

  String email = "ceja@gmail.com";
  String password = "Asdfghj1";
  String alreadyUsedEmail = "ceceij@gmail.com";

  @DisplayName("Log out")
  @Feature("Log out")
  @Description("Log out")
  @Test
  public void logOutSuccessful() {

    startPage.startPageOpen();
    startPage.clickSignIn();

    signInPage.login(email, password);

    signInPage.checkLogin(alreadyUsedEmail, password);

    wait.until(ExpectedConditions.visibilityOf(myAccountPage.getMyAccountLink()));

    myAccountPage.getLogOutLink().click();

    wait.until(ExpectedConditions.visibilityOf(myAccountPage.getSignInLink()));
  }
}
