package TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.maintests;

import TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.mainapplication_and_utilclasses.Application;
import io.cucumber.java8.En;

public class ShopCart_Cucumber_Class implements En {
    public Application app = new Application();

    public ShopCart_Cucumber_Class() {
        Given("^Customer go to Internet Shop$", () -> {
            app.litecartMainPage.open();
        });
        When("^Customer getting Items into a Cart, while Items in cart becomes three units$", () -> {
            for (int i = 1; i <= 3; i++) {
                app.litecartMainPage.chooseFirstItem();
                app.addProductToCart.addProductToCart();
                app.addProductToCart.goToMainPage();
            }
        });
        Then("^Customer go to Cart Page$", () -> {
            app.cartRemoving.open();
        });
        And("^delete all units of Items from the Cart$", () -> {
            app.cartRemoving.clearCart();
            app.quit();
        });
    }
}
