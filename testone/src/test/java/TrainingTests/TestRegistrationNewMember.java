package TrainingTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class TestRegistrationNewMember {

    private WebDriver driver;

    @Before
    public void Start() {
        driver = new ChromeDriver();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void General() {
        driver.get("http://localhost/litecart/en/");

        String email = "Cleant_Eastwood@gmail.com";
        String password = "789166";

        createAccount(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);

        login(email, password);
        Thread.sleep(1000);

        logout();
        Thread.sleep(1000);

        deletingnewuser();
        Thread.sleep(1000);
    }

    private void createAccount(String email, String password) {
        driver.findElement(By.cssSelector("form[name='login_form'] table tr:last-child")).click();
        driver.findElement(By.name("firstname")).sendKeys("Clint");
        driver.findElement(By.name("lastname")).sendKeys("Eastwood");
        driver.findElement(By.name("address1")).sendKeys("San Francisco");
        driver.findElement(By.name("postcode")).sendKeys("64402");
        driver.findElement(By.name("city")).sendKeys("California");

        Select country = new Select(driver.findElement(By.name("country_code")));
        country.selectByVisibleText("United States");

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

    private void deletingnewuser() {
        driver.get("http://localhost/litecart/admin/?app=customers&doc=customers");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement checkbox = driver.findElement(By.name("remember_me"));
        checkbox.click();
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        driver.findElement(By.cssSelector("#content > form > table > tbody > tr.row > td:nth-child(8) > a")).click();
        driver.findElement(By.cssSelector("#content > form > p > span > button:nth-child(3)")).click();
        driver.switchTo().alert().accept();
    }

    @After
    public void Stop() {
        driver.quit();
    }
}
