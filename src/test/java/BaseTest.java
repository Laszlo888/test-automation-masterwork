import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
  WebDriver driver;
  WebDriverWait wait;

  @BeforeAll
  public void setup() throws IOException {

    Properties properties = new Properties();
    InputStream propertiesStream = this.getClass().getResourceAsStream("/test.properties");
    properties.load(propertiesStream);
    String browser = properties.getProperty("browser");

    if (browser.equals("chrome")) {
      WebDriverManager.chromedriver().setup();
      this.driver = new ChromeDriver();
    } else if (browser.equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      this.driver = new FirefoxDriver();
    } else {
      WebDriverManager.edgedriver().setup();
      this.driver = new EdgeDriver();
    }

    driver.get("http://test-automation-shop1.greenfox.academy/");
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, 20);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));
  }

  public void login() {
    String email = "cecej@gmail.com";
    String password = "Asdfghj1";

    driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
    wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='email' and @class='form-control']")))
        .sendKeys(email);
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    driver.findElement(By.id("submit-login")).click();
    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//a[@title='View my customer account']")));
  }

  public WebElement addProductToCart() {
    driver
        .findElement(
            By.xpath("//a[@href='http://test-automation-shop1.greenfox.academy/3-clothes']"))
        .click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));

    WebElement productImage =
        driver.findElement(
            By.xpath(
                "//img[@src='http://test-automation-shop1.greenfox.academy/21-home_default/brown-bear-printed-sweater.jpg']"));

    // hovering over image
    Actions action = new Actions(driver);
    action.moveToElement(productImage).perform();

    wait.until(ExpectedConditions.elementToBeClickable(productImage)).click();

    wait.until(
        (ExpectedConditions.presenceOfElementLocated(
            By.xpath("//h1[contains(text(),'Hummingbird printed sweater')]"))));

    // click 'add to cart'
    driver.findElement(By.xpath("//div[@class='add']/button")).click();
    // click 'proceed'
    wait.until(
            ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='cart-content-btn']/a")))
        .click();

    wait.until(
        ExpectedConditions.presenceOfElementLocated(
            By.xpath("//h1[contains(text(),'Shopping Cart')]")));

    WebElement productInCart =
        driver.findElement(By.xpath("//a[contains(text(),'Hummingbird printed sweater')]"));

    return  productInCart;


  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }
}
