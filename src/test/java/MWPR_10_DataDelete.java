import io.qameta.allure.Feature;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MWPR_10_DataDelete extends BaseTest {

  @DisplayName("Data deleting")
  @Feature("Data delete")
  @Description("Delete a product from cart")
  @Test
  public void deleteProductFromCart() {

    addProductToCart();

    // we have something in cart
    List<WebElement> somethingInCart = driver.findElements(By.xpath("//li[@class='cart-item']"));

    // delete a product
    driver.findElement(By.xpath("//a[@data-link-action='delete-from-cart']/i")).click();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));
    driver.navigate().refresh();
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));

    // after deleting
    List<WebElement> fewerItemInCart = driver.findElements(By.xpath("//li[@class='cart-item']"));

    assertTrue(somethingInCart.size() > fewerItemInCart.size());
  }
}
