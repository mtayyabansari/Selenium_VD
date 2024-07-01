package objects;

import general.*;
import org.openqa.selenium.By;

public class Scenario1Page {

    // Define locators
    public static By byLoginOrRegister = By.xpath("//a[contains(text(),'Login or register')]");
    public static By byEmail = By.id("loginFrm_loginname");
    public static By byPassword = By.id("loginFrm_password");
    public static By byLoginButton = By.xpath("//button[@title='Login']");
    public static By bySkincareMenu = By.xpath("(//a[contains(text(),'Skincare')])[2]");
    public static By byEyes = By.xpath("//a[text()=\"Eyes\"]");
    public static By byEyesMaster = By.xpath("(//a[text()=\"Eye master\"])[1]");
    public static By byAddToCart = By.xpath("//a[@onclick=\"$(this).closest('form').submit(); return false;\"]");
    public static By shoppingCart = By.xpath("//span[text()=\" Shopping Cart\"]");
    public static By byCartItem = By.xpath("(//tbody/tr/td[2]/a)[2]");
    public static By byCartQuantity = By.xpath("//tbody/tr/td[5]/div/input");
    public static By byCartTotal = By.xpath("(//tbody/tr/td[6])");
    public static By byCartUnitPrice = By.xpath("(//tbody/tr/td[4])[2]");
    public static By removeFromCart = By.xpath("//tbody/tr/td[7]/a");

    // Actions
    public void clickLoginOrRegister() {
        MainCall.log.logInfo("Clicking on Login or Register button");
        MainCall.webDriverWaits.visibilityOf(byLoginOrRegister);
        MainCall.webDriverFactory.getDriver().findElement(byLoginOrRegister).click();
    }

    public void enterCredentials(String email, String password) {
        MainCall.log.logInfo("Entering credentials");
        MainCall.webDriverFactory.getDriver().findElement(byEmail).sendKeys(email);
        MainCall.webDriverFactory.getDriver().findElement(byPassword).sendKeys(password);
    }

    public void clickLoginButton() {
        MainCall.log.logInfo("Clicking on Login button");
        MainCall.webDriverFactory.getDriver().findElement(byLoginButton).click();
    }

    public void navigateToEyesMaster() {
        MainCall.log.logInfo("Navigating to Eyes Master");
        MainCall.webDriverWaits.visibilityOf(bySkincareMenu);
        MainCall.webDriverFactory.getDriver().findElement(bySkincareMenu).click();
        MainCall.webDriverWaits.visibilityOf(byEyes);
        MainCall.webDriverFactory.getDriver().findElement(byEyes).click();
        MainCall.webDriverWaits.visibilityOf(byEyesMaster);
        MainCall.webDriverFactory.getDriver().findElement(byEyesMaster).click();
    }

    public void addToCart() {
        MainCall.log.logInfo("Adding item to cart");
        MainCall.webDriverWaits.visibilityOf(byAddToCart);
        MainCall.webDriverFactory.getDriver().findElement(byAddToCart).click();
    }

    public void verifyCart(String expectedItem, String expectedQuantity, String expectedUnitPrice, String expectedTotal) {
        MainCall.log.logInfo("Verifying cart");
        MainCall.webDriverWaits.visibilityOf(shoppingCart);

//        CommonAssertions.logVerifyElementVisible(MainCall.webDriverFactory.getDriver(), byCartItem, "Cart item is visible");

        String actualItem = MainCall.webDriverFactory.getDriver().findElement(byCartItem).getText();
        String actualQuantity = MainCall.webDriverFactory.getDriver().findElement(byCartQuantity).getAttribute("value");
        String actualUnitPrice = MainCall.webDriverFactory.getDriver().findElement(byCartUnitPrice).getText();
        String actualTotal = MainCall.webDriverFactory.getDriver().findElement(byCartTotal).getText();

        CommonAssertions.logVerifyStringEqual(actualItem, expectedItem, "Item name matches");
        CommonAssertions.logVerifyStringEqual(actualQuantity, expectedQuantity, "Quantity matches");
        CommonAssertions.logVerifyStringEqual(actualUnitPrice, expectedUnitPrice, "Unit price matches");
        CommonAssertions.logVerifyStringEqual(actualTotal, expectedTotal, "Total amount matches");
        MainCall.webDriverWaits.visibilityOf(removeFromCart);
        MainCall.webDriverFactory.getDriver().findElement(removeFromCart).click();


    }
}
