import io.qameta.allure.Feature;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_03_Loginfailed extends BaseTest {

    @DisplayName("Failed login")
    @Feature("Login")
    @Description("Do a failed login")
    @Test
    public void failedLogin() {

        driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@name='email' and @class='form-control']")))
                .sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(badPassword);
        driver.findElement(By.id("submit-login")).click();
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//li[contains(text(),'Authentication failed.')]")));
    }
}
