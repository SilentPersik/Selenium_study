package TrainingTests.TestShoppingCart_PO_MODEL.maintests;

import TrainingTests.TestShoppingCart_PO_MODEL.mainapplication_and_utilclasses.TestBase;
import org.junit.Test;

public class TestShoppingCart_Main extends TestBase {

    @Test
    public void General() {
        app.litecartMainPage.open();
        for (int i = 1; i <= 3; i++) {
            app.litecartMainPage.chooseFirstItem();
            app.addProductToCart.addProductToCart();
            app.addProductToCart.goToMainPage();
        }
        app.cartRemoving.open();
        app.cartRemoving.clearCart();
    }
}