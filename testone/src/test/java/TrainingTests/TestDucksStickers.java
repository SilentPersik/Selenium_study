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
        for (int b = 0; b < Products.size(); b++) {
            Products = driver.findElements(By.xpath("//*[contains (@class,'product column')]"));
            Products.get(b).findElements(By.xpath("//*[contains (@class,'sticker')]"));
            Assert.assertEquals(driver.findElements(By.xpath("//*[contains (@class,'sticker')]")).size(), Products.size());
        }
        driver.quit();
    }
}