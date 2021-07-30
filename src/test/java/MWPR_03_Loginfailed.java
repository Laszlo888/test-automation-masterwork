import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_03_Loginfailed extends BaseTest {

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
