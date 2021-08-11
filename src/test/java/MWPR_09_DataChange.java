import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_09_DataChange extends BaseTest {

  String email = "ceja@gmail.com";
  String password = "Asdfghj1";
  String alreadyUsedEmail = "ceceij@gmail.com";

  @DisplayName("Data changing")
  @Feature("Updating data")
  @Description("Changing last name")
  @Test
  public void changingLastName() {

    startPage.startPageOpen();
    startPage.clickSignIn();
    signInPage.login(email, password);
    signInPage.checkLogin(alreadyUsedEmail, password);

    // click 'information'
    wait.until(ExpectedConditions.elementToBeClickable(myAccountPage.getLinkToInformationForm()))
        .click();
    wait.until(ExpectedConditions.visibilityOf(informationPage.getSaveButton()));

    // store currentName
    String currentName = informationPage.getMyAccountLink().getText();

    // store current lastname
    String currentLastName = informationPage.getLastName().getAttribute("Value");

    // change lastname
    informationPage.changeLastName(currentLastName, password);

    wait.until(ExpectedConditions.visibilityOf(informationPage.getSuccessUpdateMessage()));

    // store changed name
    String changedName = informationPage.getMyAccountLink().getText();

    // store message
    String successMessage = informationPage.getSuccessUpdateMessage().getText();

    assertThat(successMessage)
        .as("Message should be: Information successfully updated.")
        .isEqualTo("Information successfully updated.");
    assertThat(changedName).as("Last name should be changed.").isNotEqualTo(currentName);
  }
}
