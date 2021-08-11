import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_08_DataSavingFromCSV extends BaseTest {

  String email = "ceja@gmail.com";
  String password = "Asdfghj1";
  String alreadyUsedEmail = "ceceij@gmail.com";

  @DisplayName("Data input from csv file")
  @Feature("Data saving")
  @Description("Filling address form from csv file")
  @ParameterizedTest
  @CsvFileSource(resources = "/inputAddress.csv", numLinesToSkip = 1)
  public void saveAddress(String address, String city, String postalCode) {

    startPage.startPageOpen();
    startPage.clickSignIn();
    signInPage.login(email, password);
    signInPage.checkLogin(alreadyUsedEmail, password);

    // click address menu
    wait.until(ExpectedConditions.elementToBeClickable(myAccountPage.getLinkToAddressForm()))
        .click();

    // decide we already have registered address or not
    try {
      wait.until(ExpectedConditions.elementToBeClickable(addressPage.getUpdateLink()));
      addressPage.getUpdateLink().click();
      wait.until(ExpectedConditions.elementToBeClickable(addressPage.getSaveButton()));

      addressPage.fillForm(address, city, postalCode);

      wait.until(ExpectedConditions.visibilityOf(addressPage.getSuccessUpdatedAddressMessage()));
    } catch (Exception ignored) {

      addressPage.fillForm(address, city, postalCode);

      wait.until(ExpectedConditions.visibilityOf(addressPage.getSuccessAddedAddressMessage()));
    }
  }
}
