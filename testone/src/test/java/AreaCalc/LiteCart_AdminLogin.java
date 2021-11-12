package AreaCalc;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LiteCart_AdminLogin {
    @Test
    public void LiteCart_AdminLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost/litecart/admin/");
        WebElement login = driver.findElement(By.name("username"));
        login.sendKeys("Admin");
        login.submit();
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("Admin");
        password.submit();
        driver.quit();

    }
}
