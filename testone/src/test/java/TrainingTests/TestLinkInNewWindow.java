package TrainingTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestLinkInNewWindow {

    public WebDriver driver;
    public WebDriverWait wait;
    protected int timeout = 6;

    @Before
    public void start() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        implicitlyWaitOn();
        wait = new WebDriverWait(driver, 10/*seconds*/);
    }

    @Test
    public void mainTest() {
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement checkbox = driver.findElement(By.name("remember_me"));
        checkbox.click();
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        driver.get("http://localhost/litecart/admin/?app=countries&doc=edit_country");
        clickAndOpenWindow(By.cssSelector("i.fa-external-link"));
    }

    private void clickAndOpenWindow(By locator) {
        String mainWindow = driver.getWindowHandle();
        Set<String> oldWindows = driver.getWindowHandles();
        List<WebElement> list = driver.findElements(locator);
        for (WebElement we : list) {
            we.click();
            openAndCloseNewWindow(oldWindows);
            driver.switchTo().window(mainWindow);
        }
    }

    private void openAndCloseNewWindow(Set<String> oldWindows) {
        implicitlyWaitOff();
        String newWindow = wait.until(anyOtherWindow(oldWindows));
        driver.switchTo().window(newWindow);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
        implicitlyWaitOn();
        driver.close();
    }

    public ExpectedCondition<String> anyOtherWindow(Set<String> oldWindows) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver dr) {
                Set<String> handles = dr.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

    private void implicitlyWaitOn() {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    private void implicitlyWaitOff() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @After
    public void stop() {
        driver.quit();
    }
}

