package TrainingTests.TestShoppingCart_PO_MODEL_Cucumber.mainapplication_and_utilclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Utility_Classes {

    public WebDriver driver;
    public WebDriverWait wait;
    private int timeout = 3;

    public Utility_Classes(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 10);
        PageFactory.initElements(driver, this);
    }

    public void open() {
    }

    public void implicitlyWaitOn() {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    public void implicitlyWaitOff() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    public boolean isElementPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementNotPresent(WebDriver driver, By locator) {
        try {
            implicitlyWaitOff();
            return driver.findElements(locator).size() == 0;
        } finally {
            implicitlyWaitOn();
        }
    }

    public void choiseFromSelect(By locator, int size, boolean isFirst) {
        Select menu = new Select(driver.findElement(locator));
        int index = (int) (Math.random() * size);
        if (!isFirst && index == 0) index++;
        menu.selectByIndex(index);
    }

    public void goToMainPage() {
        driver.findElement(By.id("logotype-wrapper")).click();
    }
}