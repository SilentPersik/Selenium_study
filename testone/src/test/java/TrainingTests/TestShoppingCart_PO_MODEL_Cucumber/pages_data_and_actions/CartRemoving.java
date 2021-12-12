package TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.pages_data_and_actions;

import TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.mainapplication_and_utilclasses.Utility_Classes;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartRemoving extends Utility_Classes {

    @FindBy(css = "div#cart a.link")
    public WebElement cartLink;

    @FindBy(css = "li.shortcut")
    public List<WebElement> allItemsList;

    @FindBy(css = "li.shortcut")
    public WebElement itemtoremove;

    @FindBy(name = "remove_cart_item")
    public WebElement removeBtn;

    public CartRemoving(WebDriver driver) {
        super(driver);
    }

    public void open() {
        cartLink.click();
    }

    public void clearCart() {
        int count = allItemsList.size();
        for (int i = count; i > 1; i--) {
            itemtoremove.click();
            removeBtn.click();
            implicitlyWaitOff();
            wait.until(ExpectedConditions.numberOfElementsToBeLessThan(
                    By.cssSelector("table.dataTable td.item"), i));
            implicitlyWaitOn();
        }
        isElementPresent(driver, By.cssSelector("table.dataTable td.item"));
        removeBtn.click();
        implicitlyWaitOff();
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("table.dataTable td.item"), 0));
        implicitlyWaitOn();
        Assert.assertTrue(isElementNotPresent(driver, By.cssSelector("table.dataTable td.item")));
    }
}