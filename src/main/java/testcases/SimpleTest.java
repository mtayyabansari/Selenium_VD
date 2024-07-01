
package testcases;

import general.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SimpleTest {

    @Test
    public void openBrowser() {
        WebDriver driver = null;
        try {
            driver = WebDriverFactory.getInstance();
            driver.get("https://www.google.com");
            assert driver.getTitle().contains("Google");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
