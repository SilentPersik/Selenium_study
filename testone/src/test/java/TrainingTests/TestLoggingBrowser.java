package TrainingTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class TestLoggingBrowser {

    public WebDriver driver;
    public WebDriverWait wait;
    protected int timeout = 7;

    @Before
    public void Start() {
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        driver = new ChromeDriver(cap);
        //driver = new ChromeDriver();
        implicitlyWaitOn();
        wait = new WebDriverWait(driver, 10/*seconds*/);
    }

    @Test
    public void General() {

        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement checkbox = driver.findElement(By.name("remember_me"));
        checkbox.click();
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        clearBrowserLog();

        List<WebElement> Subcategory = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a[contains(text(),'Subcategory')]"));
        if (Subcategory.size() > 0) {
            for (int a = 0; a < Subcategory.size(); a++) {
                Subcategory = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a[contains(text(),'Subcategory')]"));
                Subcategory.get(a).click();
            }
        }

        List<WebElement> Ducks = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a"));
        for (int b = 0; b < Ducks.size(); b++) {
            Ducks = driver.findElements(By.xpath("//table[@class='dataTable']//td[3]/a"));
            Ducks.get(b).click();
            implicitlyWaitOff();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h1")));
            implicitlyWaitOn();
            driver.findElement(By.name("cancel")).click();
            int c = b + 1;
            System.out.println("После посещения страницы Товара № " + c + " -");
            printingBrowserLog();
            Assert.assertTrue(driver.manage().logs().get("browser").getAll().size() == 0);
        }
    }

    private void printingBrowserLog() {
        List<LogEntry> loggingList = driver.manage().logs().get("browser").getAll();
        if (loggingList.size() != 0) {
            System.out.println("Лог браузера содержит следующую информацию...");
            for (LogEntry log : loggingList)
                System.out.println(log);
        } else
            System.out.println("Лог браузера не содержит новой информации.");
        System.out.println();
    }

    private void clearBrowserLog() {
        driver.manage().logs().get("browser").getAll();
    }

    private void implicitlyWaitOn() {
        driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }

    private void implicitlyWaitOff() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    }

    @After
    public void Stop() {
        driver.quit();
    }
}
