import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_07_DataSaving extends BaseTest {

  @Test
  public void saveProductInCart() {

    login();

    assertThat(addProductToCart().isDisplayed())
            .as("Hummingbird printed sweater should be in shopping cart")
            .isTrue();
  }
}
