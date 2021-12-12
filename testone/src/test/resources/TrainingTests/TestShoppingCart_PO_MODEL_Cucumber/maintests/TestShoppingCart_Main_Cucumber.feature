Feature: TestShoppingCart_PO_MODEL_Cucumber

  Scenario: TestShoppingCart_PO_MODEL_Cucumber

    Given Customer go to Internet Shop
    When Customer getting Items into a Cart, while Items in cart becomes three units
    Then Customer go to Cart Page
    And delete all units of Items from the Cart