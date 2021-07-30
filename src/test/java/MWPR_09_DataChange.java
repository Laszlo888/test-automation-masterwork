import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_09_DataChange extends BaseTest {

  @Test
  public void changingLastName() {

    login();

    // store current lastname
    String currentLastName =
        driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();

    // click logged in name (my account)
    driver.findElement(By.xpath("//a[@title='View my customer account']")).click();

    // click 'information'
    wait.until(ExpectedConditions.elementToBeClickable(By.id("identity-link"))).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));

    // change lastname, fill form
    String currentName =
        driver.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value");
    String changedName;
    if (currentName.indexOf("-") > 0) {
      changedName = currentName.substring(0, currentName.indexOf("-"));
    } else {
      changedName = currentName + "-MwalkrT";
    }

    driver.findElement(By.xpath("//input[@name='lastname']")).clear();
    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(changedName);
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath("//input[@name='customer_privacy']")).click();
    driver.findElement(By.xpath("//input[@name='psgdpr']")).click();
    driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//a[@title='View my customer account']")));

    // store changed lastname
    String changedLastName =
        driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();

    // store message
    String successMessage =
        driver.findElement(By.xpath("//article[@data-alert='success']/ul/li")).getText();

    assertThat(successMessage)
        .as("Message should be: Information successfully updated.")
        .isEqualTo("Information successfully updated.");
    assertThat(changedLastName).as("Last name should be changed.").isNotEqualTo(currentLastName);
  }
}
