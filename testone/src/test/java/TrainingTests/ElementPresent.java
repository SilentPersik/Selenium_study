package TrainingTests;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElementPresent {

    private WebDriver driver = new ChromeDriver();

    public boolean IsElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }

    }
    public boolean AreElementsPresent(By locator) {
        return driver.findElements(locator).size()>0;
        }

    }
