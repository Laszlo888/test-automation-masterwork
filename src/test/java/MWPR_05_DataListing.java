import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_05_DataListing extends BaseTest {

  @Test
  public void listingAccessoriesFromLowToHighPrice() {

    driver
        .findElement(
            By.xpath("//a[@href='http://test-automation-shop1.greenfox.academy/6-accessories']"))
        .click();
    wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Relevance')]")))
        .click();
    wait.until(
            ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(),'Price, low to high')]")))
        .click();
    wait.until(
        ExpectedConditions.elementToBeClickable(
            By.xpath("//button[contains(text(),'Price, low to high')]")));

    // collect prices
    List<WebElement> prices = driver.findElements(By.xpath("//span[@class='price']"));
    boolean result = false;

    // check prices are in good order
    for (int i = 0; i < prices.size() - 1; i++) {
      if (Double.parseDouble(prices.get(i).getText().substring(1))
          > Double.parseDouble(prices.get(i + 1).getText().substring(1))) {
        result = true;
        break;
      }
    }

    assertThat(result).as("Prices should be ordered from low to high.").isFalse();
  }
}
