import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_04_RegSuccess extends BaseTest {

  String firstName = "Jozsef";
  String lastName = "Cece";
  String email = "cecej@gmail.com";
  String password = "Asdfghj1";


  @Test
  public void successfulRegistration() {

    driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.linkText("No account? Create one here")))
        .click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='firstname']")))
        .sendKeys(firstName);
    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
    driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
    driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
    driver.findElement(By.xpath("//input[@name='customer_privacy']")).click();
    driver.findElement(By.xpath("//input[@name='psgdpr']")).click();
    driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[contains(text(),'" + firstName + " " + lastName + "')]")));
  }
}
