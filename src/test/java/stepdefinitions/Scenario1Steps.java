package stepdefinitions;

import general.MainCall;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class Scenario1Steps {

    @Given("I am on the automation test store homepage")
    public void i_am_on_the_automation_test_store_homepage() throws InterruptedException {
        MainCall.genericFunctions.driverStart("https://automationteststore.com/");
    }

    @When("I login with username {string} and password {string}")
    public void i_login_with_username_and_password(String username, String password) throws InterruptedException {
        MainCall.scenario1Page.clickLoginOrRegister();
        MainCall.scenario1Page.enterCredentials(username, password);
        MainCall.scenario1Page.clickLoginButton();
    }

    @When("I select the Eyes Master item")
    public void i_select_the_eyes_master_item() throws InterruptedException {
        MainCall.scenario1Page.navigateToEyesMaster();
    }

    @When("I add it to the cart")
    public void i_add_it_to_the_cart() throws InterruptedException {
        MainCall.scenario1Page.addToCart();
    }

    @Then("I should see the item {string} with quantity {string} and price {string} in the cart")
    public void i_should_see_the_item_with_quantity_and_price_in_the_cart(String itemName, String quantity, String price) throws InterruptedException {
        MainCall.scenario1Page.verifyCart(itemName, quantity, price, "$164.00");
    }
}
