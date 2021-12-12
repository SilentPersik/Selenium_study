package TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.pages_data_and_actions;

import TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.mainapplication_and_utilclasses.Utility_Classes;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Litecart_MainPage extends Utility_Classes {

    @FindBy(css = "div.content div.name")
    public WebElement itemToAdd;

    public Litecart_MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public void chooseFirstItem() {
        itemToAdd.click();
    }
}