package TrainingTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestAddNewProduct {

    private WebDriver driver;

    @Before
    public void Start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void General() throws InterruptedException {

        driver.get("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement checkbox = driver.findElement(By.name("remember_me"));
        checkbox.click();
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        SearchAndClick("ul#box-apps-menu li#app-", "Catalog");
        Thread.sleep(1000);
        SearchAndClick("td#content a.button", "Add New Product");
        Thread.sleep(1000);

        String newItem = "Panda Po";
        String relativePath = "./src/test/resources/panda_po.png";
        Path filePath = Paths.get(relativePath);
        String absolutePath = filePath.normalize().toAbsolutePath().toString();

        FillingTabGeneral(newItem, absolutePath);

        SearchAndClick("div.tabs li", "Information");
        Thread.sleep(1000);
        FillingTabInformation();

        SearchAndClick("div.tabs li", "Prices");
        Thread.sleep(1000);
        FillingTabPrices();

        driver.findElement(By.cssSelector("button[name=save]")).click();
        Thread.sleep(1000);
    }

    private void FillingTabGeneral(String item, String path) {
        driver.findElement(By.name("name[en]")).sendKeys(item);
        driver.findElement(By.cssSelector("input[name=status][value='1']")).click();
        driver.findElement(By.name("code")).sendKeys("panda_po");
        driver.findElement(By.cssSelector("input[type=checkbox][value='0']")).click();
        driver.findElement(By.cssSelector("input[type=checkbox][value='1']")).click();
        driver.findElement(By.name("quantity")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.name("quantity")).sendKeys("1000");
        driver.findElement(By.name("new_images[]")).sendKeys(path);
    }

    private void FillingTabInformation() {
        Select manufact = new Select(driver.findElement(By.name("manufacturer_id")));
        manufact.selectByVisibleText("ACME Corp.");
        driver.findElement(By.name("short_description[en]")).sendKeys("Мастер По – титульный протагонист цикла произведений о Кунг-фу Панде");
        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Мастер По (настоящее имя - Лотос) " +
                "– титульный протагонист цикла произведений о Кунг-фу Панде. По – большая панда, приемный сын Господина " +
                "Пинга, один из учеников мастера Шифу в Нефритовом Дворце. Также он предсказанный в легендах Воин Дракона. ");
    }

    private void FillingTabPrices() {
        driver.findElement(By.name("purchase_price")).sendKeys(Keys.CONTROL + "a" + Keys.DELETE);
        driver.findElement(By.name("purchase_price")).sendKeys("100");
        Select curr_code = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        curr_code.selectByVisibleText("US Dollars");
        driver.findElement(By.name("prices[USD]")).sendKeys("130");
    }

    private void SearchAndClick(String linkList, String text) {
        List<WebElement> list = driver.findElements(By.cssSelector(linkList));
        String name;
        for (WebElement we : list) {
            name = we.getText();
            if (name.equals(text)) {
                we.click();
                break;
            }
        }
    }

    @After
    public void Stop() {
        driver.quit();
    }
}
