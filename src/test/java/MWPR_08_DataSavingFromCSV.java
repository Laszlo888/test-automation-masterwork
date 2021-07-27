import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MWPR_08_DataSavingFromCSV {

  WebDriver driver;
  WebDriverWait wait;

  @BeforeEach
  void setup() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.get("http://test-automation-shop1.greenfox.academy/");
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, 20);
  }

  @ParameterizedTest
  @CsvFileSource(resources = "/inputAddress.csv", numLinesToSkip = 1)
  public void saveAddress(String address, String city, String postalCode) {

    // login
    String email = "cecej@gmail.com";
    String password = "Asdfghj1";

    driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
    wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='email' and @class='form-control']")))
        .sendKeys(email);
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    driver.findElement(By.id("submit-login")).click();

    // click logged in name
    wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath("//a[@title='View my customer account']")))
        .click();

    // click 'add first address'
    wait.until(ExpectedConditions.elementToBeClickable(By.id("address-link"))).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));

    // fill from csv
    driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(address);
    driver.findElement(By.xpath("//input[@name='city']")).sendKeys(city);
    driver.findElement(By.xpath("//input[@name='postcode']")).sendKeys(postalCode);

    Select state = new Select(driver.findElement(By.name("id_state")));
    state.selectByVisibleText("Alaska");

    driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath(
                "//article[@data-alert='success']/ul/li[contains(text(),'Address successfully added!')]")));
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }
}
