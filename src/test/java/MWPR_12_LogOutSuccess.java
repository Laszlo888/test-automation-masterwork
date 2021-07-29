import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_12_LogOutSuccess extends BaseTest {

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
