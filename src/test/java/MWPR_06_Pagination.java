import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_06_Pagination extends BaseTest {


  @DisplayName("Pagination")
  @Feature("pagination")
  @Description("Display products on more pages and gets their names")
  @Test
  public void pagination() {

    startPage.startPageOpen();
    startPage.getAllProducts().click();

    // wait till footer is loaded
    wait.until(ExpectedConditions.visibilityOf(homePage.getFooterEcommerceLink()));
    // get products name on first page

    ArrayList<String> fPage = homePage.productNames();

    // move to second page
    homePage.getNextPageLink().click();

    // wait till footer is loaded
    wait.until(ExpectedConditions.visibilityOf(homePage.getFooterEcommerceLink()));

    // get products name on second page

    ArrayList<String> sPage = homePage.productNames();

    // compare two arraylist, we do not need any match
    boolean result = false;

    for (int k = 0; k < sPage.size(); k++) {
      if (fPage.contains(sPage.get(k))) {
        result = true;
        break;
      }
    }

    assertThat(result).as("Should not be same product from first page on second page").isFalse();

    fPage.clear();
    sPage.clear();
  }
}
