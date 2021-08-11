import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_05_DataListing extends BaseTest {

  @DisplayName("Data listing")
  @Feature("Listing from low to high price")
  @Description("Listing accessories from low to high price")
  @Test
  public void listingAccessoriesFromLowToHighPrice() {

    startPage.startPageOpen();
    startPage.getAccessoriesMenu().click();

    // wait for menu
    wait.until(
            ExpectedConditions.elementToBeClickable(accessoriesPage.getChooseSortingMethodMenu()))
        .click();

    // choose from menu
    wait.until(ExpectedConditions.elementToBeClickable(accessoriesPage.getPricesLowToHighLink()))
        .click();

    // wait for products
    wait.until(
        ExpectedConditions.elementToBeClickable(
            accessoriesPage.getPricesLowToHighAfterClickingIt()));

    assertThat(accessoriesPage.dataListing())
        .as("Prices should be ordered from low to high.")
        .isFalse();
  }
}
