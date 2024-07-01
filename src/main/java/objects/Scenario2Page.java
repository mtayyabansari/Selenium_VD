package objects;

import general.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Scenario2Page {

    // Define locators
    public static By bySkincare = By.xpath("//a[contains(@href, 'product/category&path=43')]");
    public static By byFace = By.xpath("(//a[contains(@href, 'product/category&path=43_46')])[3]");
    public static By bySortBy = By.xpath("//select[@id='sort']");
    public static By lowToHighSort = By.xpath("//option[text()='Price Low > High']");
    public static By byAddToCartButtons = By.cssSelector("a[title='Add to Cart']");

    public static By byHairCare = By.xpath("//a[contains(@href, 'product/category&path=52')]");
    public static By byConditioner = By.xpath("(//a[contains(@href, 'product/category&path=52_54')])[3]");
    public static By highToLowSort = By.xpath("//option[text()='Price High > Low']");
    public static By quantityInput = By.name("quantity");
    public static By byCartLink = By.xpath("//span[text()='Shopping Cart']");
    public static By byCartItemNames = By.xpath("//td[@class='align_left']//a");
    public static By byCartItemQuantities = By.xpath("//td[@class='align_center']/input[@class='input-sm']");
    public static By byCartItemPrices = By.xpath("//td[@class='align_center'][contains(text(),'$')]");

    public static By cart = By.xpath("(//span[text()=\"Cart\"])[1]");

    // Actions
    public void navigateToFaceSection() {
        MainCall.log.logInfo("Navigating to Skincare > Face section");
        MainCall.webDriverWaits.visibilityOf(bySkincare);
        MainCall.webDriverFactory.getDriver().findElement(bySkincare).click();
        MainCall.webDriverWaits.visibilityOf(byFace);
        MainCall.webDriverFactory.getDriver().findElement(byFace).click();
    }

    public void sortByLowToHigh() {
        MainCall.log.logInfo("Sorting products by low to high price");
        MainCall.webDriverWaits.visibilityOf(bySortBy);
        MainCall.webDriverFactory.getDriver().findElement(bySortBy).click();
        MainCall.webDriverWaits.visibilityOf(lowToHighSort);
        MainCall.webDriverFactory.getDriver().findElement(lowToHighSort).click();
    }

    public void addTop3LowestPricedProductsToCart() {
        MainCall.log.logInfo("Adding top 3 lowest priced products to cart");

        MainCall.webDriverWaits.visibilityOf(byAddToCartButtons);
        List<WebElement> addToCartButtons = MainCall.webDriverFactory.getDriver().findElements(byAddToCartButtons);

        if (addToCartButtons.size() < 3) {
            throw new RuntimeException("Less than 3 items available to add to cart");
        }

        for (int i = 0; i < 3; i++) {
            WebElement addToCartButton = addToCartButtons.get(i);
            addToCartButton.click();
            MainCall.webDriverWaits.sleep(2000); // Adjust sleep time if necessary
        }
    }

    public void navigateToConditionerSection() {
        MainCall.log.logInfo("Navigating to Hair Care > Conditioner section");
        MainCall.webDriverWaits.visibilityOf(byHairCare);
        MainCall.webDriverFactory.getDriver().findElement(byHairCare).click();
        MainCall.webDriverWaits.visibilityOf(byConditioner);
        MainCall.webDriverFactory.getDriver().findElement(byConditioner).click();
    }

    public void sortByHighToLow() {
        MainCall.log.logInfo("Sorting products by high to low price");
        MainCall.webDriverWaits.visibilityOf(bySortBy);
        MainCall.webDriverFactory.getDriver().findElement(bySortBy).click();
        MainCall.webDriverWaits.visibilityOf(highToLowSort);
        MainCall.webDriverFactory.getDriver().findElement(highToLowSort).click();
    }

    public void addHighestPricedConditionerToCart() {
        MainCall.log.logInfo("Adding highest priced conditioner to cart");
        MainCall.webDriverWaits.visibilityOf(byAddToCartButtons);
        List<WebElement> addToCartButtons = MainCall.webDriverFactory.getDriver().findElements(byAddToCartButtons);

        if (addToCartButtons.isEmpty()) {
            throw new RuntimeException("No items available to add to cart");
        }

        WebElement highestPricedAddToCartButton = addToCartButtons.get(0);
        highestPricedAddToCartButton.click();
        MainCall.webDriverWaits.visibilityOf(quantityInput);
        WebElement quantityElement = MainCall.webDriverFactory.getDriver().findElement(quantityInput);
        quantityElement.clear();
        quantityElement.sendKeys("2");
        highestPricedAddToCartButton.click(); // Assuming the click adds the item with the updated quantity
    }

    public void verifyCartContents(String[] expectedItems, String[] expectedQuantities, String[] expectedPrices) {
        MainCall.log.logInfo("Verifying cart contents");
        MainCall.webDriverWaits.visibilityOf(cart);
        MainCall.webDriverFactory.getDriver().findElement(cart).click();

        for (int i = 0; i < expectedItems.length; i++) {
            String actualItem = MainCall.webDriverFactory.getDriver().findElements(byCartItemNames).get(i).getText();
            String actualQuantity = MainCall.webDriverFactory.getDriver().findElements(byCartItemQuantities).get(i).getAttribute("value");
            String actualPrice = MainCall.webDriverFactory.getDriver().findElements(byCartItemPrices).get(i).getText();

            CommonAssertions.logVerifyStringEqual(actualItem, expectedItems[i], "Item name matches");
            CommonAssertions.logVerifyStringEqual(actualQuantity, expectedQuantities[i], "Quantity matches");
            CommonAssertions.logVerifyStringEqual(actualPrice, expectedPrices[i], "Price matches");
        }
    }
}
