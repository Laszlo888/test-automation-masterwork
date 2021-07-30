import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class MWPR_08_DataSavingFromCSV extends BaseTest {

  @ParameterizedTest
  @CsvFileSource(resources = "/inputAddress.csv", numLinesToSkip = 1)
  public void saveAddress(String address, String city, String postalCode) {

    login();

    // click address menu
    driver.findElement(By.xpath("//section[@id='content']//div[@class='links']/a[2]")).click();

    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));

    // decide we already have registered address or not
    try {
      wait.until(
          ExpectedConditions.visibilityOfElementLocated(
              By.xpath("//span[contains(text(),'Update')]")));
      driver.findElement(By.xpath("//span[contains(text(),'Update')]")).click();
      wait.until(
          ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='address1']")));

      fillForm(address, city, postalCode);

      wait.until(
          ExpectedConditions.presenceOfElementLocated(
              By.xpath(
                  "//article[@data-alert='success']/ul/li[contains(text(),'Address successfully updated!')]")));
    } catch (Exception ignored) {

      fillForm(address, city, postalCode);

      wait.until(
          ExpectedConditions.presenceOfElementLocated(
              By.xpath(
                  "//article[@data-alert='success']/ul/li[contains(text(),'Address successfully added!')]")));
    }
  }

  public void fillForm(String address, String city, String postalCode) {
    // fill from csv
    driver.findElement(By.xpath("//input[@name='address1']")).clear();
    driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
    driver.findElement(By.xpath("//input[@name='city']")).clear();
    driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
    driver.findElement(By.xpath("//input[@name='postcode']")).clear();
    driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(postalCode);

    Select state = new Select(driver.findElement(By.name("id_state")));
    state.selectByVisibleText("Alaska");

    driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
  }
}
