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
        List<WebElement> Stickers = driver.findElements(By.xpath("//*[contains (@class,'sticker')]"));
        List<WebElement> Goods = driver.findElements(By.xpath("//*[contains (@class,'product column')]"));
        Assert.assertEquals(Stickers.size(), Goods.size());
        /*
        * Просто сравниваю выборку стикеров и выборку товаров. В случае расхождений,
        * количество стикеров на каждый товар не может быть равным 1, следовательно, тест падает.
        */
        driver.quit();
    }
}