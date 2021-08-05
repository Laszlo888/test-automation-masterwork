import io.qameta.allure.Feature;
import jdk.jfr.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_06_Pagination extends BaseTest {

  @DisplayName("Pagination")
  @Feature("pagination")
  @Description("Display products on more pages")
  @Test
  public void pagination() {

    driver.findElement(By.xpath("//a[contains(text(),'All products')]")).click();
    // wait till footer is loaded
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));
    // get products name on first page
    List<WebElement> firstPage = driver.findElements(By.xpath("//h2/a"));
    ArrayList<String> fPage = new ArrayList<>();
    for (int i = 0; i < firstPage.size(); i++) {
      fPage.add(firstPage.get(i).getText());
    }

    // move to second page
    driver.findElement(By.xpath("//nav[@class='pagination']//a[contains(text(),'Next')]")).click();
    // wait till footer is loaded
    wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Ecommerce")));
    // get products name on second page
    List<WebElement> secondPage = driver.findElements(By.xpath("//h2/a"));
    ArrayList<String> sPage = new ArrayList<>();
    for (int j = 0; j < secondPage.size(); j++) {
      sPage.add(secondPage.get(j).getText());
    }

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
