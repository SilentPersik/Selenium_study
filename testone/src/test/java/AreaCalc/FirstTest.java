
package AreaCalc;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class FirstTest {
    @Test
    public void TestGoogleSearch() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        WebElement searchbox = driver.findElement(By.name("q"));
        searchbox.sendKeys("ChromeDriver");
        searchbox.submit();
        driver.quit();
    }
}