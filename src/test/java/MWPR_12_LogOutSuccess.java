import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_12_LogOutSuccess extends BaseTest {

  @DisplayName("Log out")
  @Feature("Log out")
  @Description("Log out")
  @Test
  public void logOutSuccessful() {

    login();

    driver
        .findElement(
            By.xpath("//a[@href='http://test-automation-shop1.greenfox.academy/?mylogout=']"))
        .click();

    wait.until(
        ExpectedConditions.invisibilityOfElementLocated(
            By.xpath("//a[@title='View my customer account']")));

  }
}
