package TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.pages_data_and_actions;

import TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.mainapplication_and_utilclasses.Utility_Classes;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AddProductToCart extends Utility_Classes {

    @FindBy(css = "div#cart span.quantity")
    private WebElement spanquantity;

    @FindBy(css = "td.options option")
    private List<WebElement> selectoptions;

    @FindBy(name = "add_cart_product")
    private WebElement addingBtn;

    public AddProductToCart(WebDriver driver) {
        super(driver);
    }

    public void addProductToCart() {
        By locator = By.cssSelector("div#cart span.quantity");
        String itemCount = spanquantity.getText();
        Integer next = Integer.parseInt(itemCount) + 1;
        itemCount = next.toString();

        if (!isElementNotPresent(driver, By.cssSelector("td.options"))) {
            int count = selectoptions.size();
            choiseFromSelect(By.cssSelector("select[name='options[Size]'"), count, false);
        }
        implicitlyWaitOff();
        addingBtn.click();
        wait.until(ExpectedConditions.textToBe(locator, itemCount));
        String quantity = spanquantity.getText();
        Assert.assertTrue(quantity.equals(itemCount));
        implicitlyWaitOn();
    }
}