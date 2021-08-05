import io.qameta.allure.Feature;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Random;

public class MWPR_01_RegSuccess extends BaseTest {

  @DisplayName("Successful registration")
  @Feature("Registration")
  @Description("Do a successful registration")
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

    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));

    // if email is already registered
    try {
      wait.until(
          ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//li[@class='alert alert-danger']")));

      // if email is registered, we generating new email address with random chars
      Random r = new Random();
      String ch = "123456789abcdefghijklmnopqrstuvwxyz";
      int randomInt1 = r.nextInt(ch.length());
      char randomChar1 = ch.charAt(randomInt1);
      String r1 = Character.toString(randomChar1);

      int randomInt2 = r.nextInt(ch.length());
      char randomChar2 = ch.charAt(randomInt2);
      String r2 = Character.toString(randomChar2);

      int randomInt3 = r.nextInt(ch.length());
      char randomChar3 = ch.charAt(randomInt3);
      String r3 = Character.toString(randomChar3);

      driver.findElement(By.xpath("//input[@name='email']")).clear();
      driver.findElement(By.xpath("//input[@name='email']")).sendKeys(r1 + r2 + r3 + email);
      driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
      driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
    } catch (Exception ignored) {
    }

    // check logged in name is presence
    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//a[@title='View my customer account']")));
  }
}
