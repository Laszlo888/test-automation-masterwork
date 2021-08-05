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

  String email;
  String password;
  String firstName;
  String lastName;
  String badPassword;
  String alreadyUsedEmail;

  @BeforeAll
  public void setup() throws IOException {

    Properties properties = new Properties();
    InputStream propertiesStream = this.getClass().getResourceAsStream("/test.properties");
    properties.load(propertiesStream);
    String browser = properties.getProperty("browser");
    email = properties.getProperty("email");
    password = properties.getProperty("password");
    firstName = properties.getProperty("firstname");
    lastName = properties.getProperty("lastname");
    badPassword = properties.getProperty("badpassword");
    alreadyUsedEmail = properties.getProperty("alreadyusedemail");

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

    driver.findElement(By.xpath("//span[contains(text(),'Sign in')]")).click();
    wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//input[@name='email' and @class='form-control']"))).sendKeys(email);
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    driver.findElement(By.id("submit-login")).click();

    // if registration fails, using already registered email
    try{ wait.until(
            ExpectedConditions.presenceOfElementLocated(
                By.xpath(
                    "//section[@class='login-form']//li[contains(text(),'Authentication failed')]")))
        .isDisplayed();
      driver.findElement(By.xpath("//input[@name='email' and @class='form-control']")).clear();
      driver
          .findElement(By.xpath("//input[@name='email' and @class='form-control']")).sendKeys(alreadyUsedEmail);
      driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
      driver.findElement(By.id("submit-login")).click();
    } catch (Exception ignored){}
    // waiting for presence of first name, last name (my account)
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

    return productInCart;
  }

  @AfterEach
  public void tearDown() {
    driver.quit();
  }
}
