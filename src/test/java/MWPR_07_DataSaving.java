import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_07_DataSaving extends BaseTest {

  @DisplayName("Data saving")
  @Feature("Data saving")
  @Description("Put a product into cart and check it is saved to account")
  @Test
  public void saveProductInCart() {

    login();

    assertThat(addProductToCart().isDisplayed())
            .as("Hummingbird printed sweater should be in shopping cart")
            .isTrue();
  }
}
