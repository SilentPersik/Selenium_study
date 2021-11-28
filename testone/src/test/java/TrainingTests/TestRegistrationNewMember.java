package TrainingTests;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TestRegistrationNewMember {

    private WebDriver driver;

    @Before
    public void Start() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void General() throws InterruptedException {

        driver.get("http://localhost/litecart/en/");

        String email = generateNewEmail("gmail.com", 8);
        String password = "789166";

        createAccount(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);

        login(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);
    }

    private void createAccount(String email, String password) {

        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();

        driver.findElement(By.name("firstname")).sendKeys("Clint");
        driver.findElement(By.name("lastname")).sendKeys("Eastwood");
        driver.findElement(By.name("address1")).sendKeys("San Francisco");
        driver.findElement(By.name("postcode")).sendKeys("64402");
        driver.findElement(By.name("city")).sendKeys("California");

        driver.findElement(By.cssSelector(".select2-selection__arrow")).click();
        WebElement Country = driver.findElement(By.cssSelector(".select2-search__field"));
        Country.sendKeys("United States");
        Country.sendKeys(Keys.ENTER);

        Select zone = new Select(driver.findElement(By.cssSelector("select[name='zone_code']")));
        zone.selectByVisibleText("California");

        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("phone")).sendKeys("+1234657843");
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("confirmed_password")).sendKeys(password);
        driver.findElement(By.name("create_account")).click();
    }

    private void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    private void logout() {

        driver.findElement(By.cssSelector("div#box-account div.content li:last-child a")).click();
    }

    public String generateNewEmail(String domain, int length) {
        return RandomStringUtils.random(length, "abcdefghijklmnopqrstuvwxyz") + "@" + domain;
    }

    @After
    public void Stop() {

        driver.quit();
    }
}
