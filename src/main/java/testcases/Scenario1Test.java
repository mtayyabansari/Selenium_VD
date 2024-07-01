package testcases;

import general.*;
import org.testng.annotations.Test;

public class Scenario1Test extends BaseTest {

    @Test(description = "Scenario 1: Login, select Eyes Master item, and add to cart")
    public void scenario1() throws InterruptedException {
        MainCall.genericFunctions.driverStart("https://automationteststore.com/");
        MainCall.scenario1Page.clickLoginOrRegister();
        MainCall.scenario1Page.enterCredentials("tayyabansari", "TA123!");
        MainCall.scenario1Page.clickLoginButton();
        MainCall.scenario1Page.navigateToEyesMaster();
        MainCall.scenario1Page.addToCart();
        MainCall.scenario1Page.verifyCart("Eye master", "2", "$82.00", "$164.00");
    }
}
