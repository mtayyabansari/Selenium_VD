package testcases;

import general.BaseTest;
import general.MainCall;
import objects.Scenario2Page;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Scenario2Test extends BaseTest {



    @Test(description = "Scenario 2: Login, select Eyes Master item, and add to cart")
    public void scenario2() throws InterruptedException {
        MainCall.genericFunctions.driverStart("https://automationteststore.com/");
        MainCall.scenario1Page.clickLoginOrRegister();
        MainCall.scenario1Page.enterCredentials("tayyabansari", "TA123!");
        MainCall.scenario1Page.clickLoginButton();

        // Navigate to the Skincare > Face section
        MainCall.scenario2Page.navigateToFaceSection();
        MainCall.scenario2Page.sortByLowToHigh();
        MainCall.scenario2Page.addTop3LowestPricedProductsToCart();

        // Navigate to the Hair Care > Conditioner section
        MainCall.scenario2Page.navigateToConditionerSection();
        MainCall.scenario2Page.sortByHighToLow();
        MainCall.scenario2Page.addHighestPricedConditionerToCart();

        // Verify cart contents
        String[] expectedItems = {"Item 1 Name", "Item 2 Name", "Item 3 Name", "Highest Priced Conditioner Name"};
        String[] expectedQuantities = {"1", "1", "1", "2"};
        String[] expectedPrices = {"$Price1", "$Price2", "$Price3", "$HighestPricedConditionerPrice"};

        MainCall.scenario2Page.verifyCartContents(expectedItems, expectedQuantities, expectedPrices);
    }

}
