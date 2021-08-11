import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    startPage.startPageOpen();
    startPage.getClothesMenu().click();

    // add something to cart
    wait.until(ExpectedConditions.elementToBeClickable(clothesPage.getFirstClothProduct())).click();
    wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddToCart())).click();

    wait.until(
            ExpectedConditions.elementToBeClickable(
                productPage.getAfterAddToCartPopUpWindowProceedButton()))
        .click();

    // we have something in cart
    wait.until(ExpectedConditions.visibilityOf(cartPage.getFirstCartItem()));
    List<WebElement> somethingInCart = cartPage.getProductsNameInCart();
    int numberOfProductsInCart = somethingInCart.size();

    // delete a product
    cartPage.getDeleteProductFromCart().click();
    wait.until(ExpectedConditions.visibilityOf(cartPage.getFooterEcommerceLink()));
    driver.navigate().refresh();
    wait.until(ExpectedConditions.visibilityOf(cartPage.getFooterEcommerceLink()));

    // after deleting
    List<WebElement> fewerItemInCart = cartPage.getProductsNameInCart();

    assertTrue(numberOfProductsInCart > fewerItemInCart.size());
  }
}
