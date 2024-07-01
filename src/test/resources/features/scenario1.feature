Feature: Scenario 1
  @task
  Scenario: Login, select Eyes Master item, and add to cart
    Given I am on the automation test store homepage
    When I login with username "tayyabansari" and password "TA123!"
    And I select the Eyes Master item
    And I add it to the cart
    Then I should see the item "Eye master" with quantity "2" and price "$82.00" in the cart
