import io.qameta.allure.Feature;
import io.qameta.allure.Description;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class MWPR_04_LoginSuccess extends BaseTest {

  @DisplayName("Successful login")
  @Feature("Login")
  @Description("Do a successful login")
  @Test
  public void successfulLogin() {

    login();

  }
}
