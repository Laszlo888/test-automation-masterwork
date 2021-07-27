import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MWPR_02_LoginFailed extends BaseTest {

    @Test
    public void failedLogin() {

        String email = "cecej@gmail.com";
        String password = "Asdfghj2";

        driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//input[@name='email' and @class='form-control']")))
                .sendKeys(email);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.id("submit-login")).click();
        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        By.xpath("//li[contains(text(),'Authentication failed.')]")));
    }
}
