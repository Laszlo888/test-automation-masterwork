import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;

public class BaseTest {
  protected static WebDriver driver;
  protected static WebDriverWait wait;

  HomePage homePage = PageFactory.initElements(driver, HomePage.class);
  AccessoriesPage accessoriesPage = PageFactory.initElements(driver, AccessoriesPage.class);
  AddressPage addressPage = PageFactory.initElements(driver, AddressPage.class);
  CartPage cartPage = PageFactory.initElements(driver, CartPage.class);
  MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
  ClothesPage clothesPage = PageFactory.initElements(driver, ClothesPage.class);
  InformationPage informationPage = PageFactory.initElements(driver, InformationPage.class);
  ProductPage productPage = PageFactory.initElements(driver, ProductPage.class);
  RegistrationPage registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
  SignInPage signInPage = PageFactory.initElements(driver, SignInPage.class);
  StartPage startPage = PageFactory.initElements(driver, StartPage.class);

  @BeforeAll
  public static void setup() {
    WebDriverManager.firefoxdriver().setup();
    driver = new FirefoxDriver();
    driver.manage().window().maximize();
    driver.manage().deleteAllCookies();
    wait = new WebDriverWait(driver, 20);
  }

  @AfterAll
  public static void tearDown() {
    driver.quit();
  }
}
