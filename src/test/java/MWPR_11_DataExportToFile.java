import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_11_DataExportToFile extends BaseTest {

  @DisplayName("Data export into file")
  @Feature("Data saving into file")
  @Description("Save accessories names into txt file")
  @Test
  public void exportProductsNameToFile() {

    // click to accessories link
    startPage.startPageOpen();
    startPage.getAccessoriesMenu().click();
    wait.until(ExpectedConditions.visibilityOf(startPage.getFooterEcommerceLink()));

    // get products' name
    List<WebElement> accessories = accessoriesPage.getNameOfAccessoriesOnPage();
    ArrayList<String> access = new ArrayList<>();
    for (int i = 0; i < accessories.size(); i++) {
      access.add(accessories.get(i).getText());
    }

    // create directory
    String folderName = "exportedData";
    Path path = Paths.get(folderName);
    try {
      Files.createDirectories(path);
    } catch (IOException e) {
      System.err.println("Failed to create directory!" + e.getMessage());
    }

    // write products' name into txt file
    try {
      Files.write(Paths.get(folderName + "/accessories.txt"), access);
    } catch (IOException e) {
      System.out.println("Failed to write into file.");
    }

    // assertation

    ArrayList<String> result = null;
    try {
      result = new ArrayList<>(Files.readAllLines(Paths.get(folderName + "/accessories.txt")));
    } catch (IOException e) {
      System.out.println("File not found.");
    }

    assertThat(access.equals(result))
        .as("File content and list with products' name should be the same")
        .isTrue();
  }
}
