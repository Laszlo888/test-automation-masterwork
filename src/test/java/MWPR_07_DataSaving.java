import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_07_DataSaving extends BaseTest {

  @DisplayName("Data saving")
  @Feature("Data saving")
  @Description("Put a product into cart and check it is saved")
  @Test
  public void saveProductInCart() {

    startPage.startPageOpen();
    startPage.getClothesMenu().click();

    wait.until(ExpectedConditions.elementToBeClickable(clothesPage.getFirstClothProduct())).click();
    wait.until(ExpectedConditions.elementToBeClickable(productPage.getAddToCart())).click();

    wait.until(
            ExpectedConditions.elementToBeClickable(
                productPage.getAfterAddToCartPopUpWindowProceedButton()))
        .click();

    assertThat(cartPage.getFirstCartItem().isDisplayed())
        .as("Should be one product in cart")
        .isTrue();
  }
}
