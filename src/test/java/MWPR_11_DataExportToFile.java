import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MWPR_11_DataExportToFile extends BaseTest {

  @Test
  public void exportProductsNameToFile() {

    // click to accessories link
    driver
        .findElement(
            By.xpath("//a[@href='http://test-automation-shop1.greenfox.academy/6-accessories']"))
        .click();

    // get products' name
    List<WebElement> accessories = driver.findElements(By.xpath("//h2/a"));
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