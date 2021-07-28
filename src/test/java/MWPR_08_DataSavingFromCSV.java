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

    // click 'add first address'
    wait.until(ExpectedConditions.elementToBeClickable(By.id("addresses-link"))).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));

    // decide we already have registered address or not
    if (driver.findElement(By.xpath("//span[contains(text(),'Update')]")).isDisplayed()) {
      driver.findElement(By.xpath("//span[contains(text(),'Update')]")).click();
      wait.until(
          ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='postcode']")));
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

      wait.until(
          ExpectedConditions.presenceOfElementLocated(
              By.xpath(
                  "//article[@data-alert='success']/ul/li[contains(text(),'Address successfully updated!')]")));
    } else {
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

      wait.until(
          ExpectedConditions.presenceOfElementLocated(
              By.xpath(
                  "//article[@data-alert='success']/ul/li[contains(text(),'Address successfully added!')]")));
    }
  }
}
