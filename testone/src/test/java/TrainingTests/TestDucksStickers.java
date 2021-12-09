package TrainingTests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestDucksStickers {

    private WebDriver driver = new ChromeDriver();

    @Test
    public void MightyDucks() {
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost/litecart");

        List<WebElement> Products = driver.findElements(By.xpath("//*[contains (@class,'product column')]"));
        for (WebElement e : Products) {
            Assert.assertEquals(e.findElements(By.className("sticker")).size(), 1);
        }
        driver.quit();
    }
}