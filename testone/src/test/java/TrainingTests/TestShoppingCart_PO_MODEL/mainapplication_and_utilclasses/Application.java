package TrainingTests.TestShoppingCart_PO_MODEL.mainapplication_and_utilclasses;

import TrainingTests.TestShoppingCart_PO_MODEL.pages_data_and_actions.AddProductToCart;
import TrainingTests.TestShoppingCart_PO_MODEL.pages_data_and_actions.CartRemoving;
import TrainingTests.TestShoppingCart_PO_MODEL.pages_data_and_actions.Litecart_MainPage;
import org.junit.After;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Application {
    private WebDriver driver;
    private WebDriverWait wait;
    public Litecart_MainPage litecartMainPage;
    public CartRemoving cartRemoving;
    public AddProductToCart addProductToCart;

    public Application() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 10);
        litecartMainPage = new Litecart_MainPage(driver);
        cartRemoving = new CartRemoving(driver);
        addProductToCart = new AddProductToCart(driver);
    }

    @After
    public void quit() {
        driver.quit();
    }
}