package TrainingTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestTruePagesofGoods {

    private WebDriver driver;

    @Before
    public void Start() {
        //driver = new ChromeDriver();
        driver = new FirefoxDriver();
        //driver = new InternetExplorerDriver();
         /*
         * Возможно ли такое представление выбора драйверов?
         * В случае необходимости просто раскомментировать строку с необходимым драйвером.
         * Либо необходимо прописать для этого отдельный класс?
         */
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void General() {

        driver.get("http://localhost/litecart/en/");
        String[] ListMainPage;
        String[] ListItemPage;

        WebElement item = driver.findElement(By.xpath(".//div[@id='box-campaigns']//li[1]"));
        ListMainPage = FillList(item);
        ListMainPage[0] = item.findElement(By.cssSelector("div.name")).getText();
        item.click();

        item = driver.findElement(By.cssSelector("div#box-product div.information"));
        ListItemPage = FillList(item);
        ListItemPage[0] = driver.findElement(By.cssSelector("div#box-product h1.title")).getText();

        Assert.assertEquals(ListMainPage[0], ListItemPage[0]); // на главной странице и на странице товара совпадает текст названия товара.
        Assert.assertEquals(ListMainPage[1], ListItemPage[1]); // на главной странице и на странице товара совпадают цены: обычная и -->
        Assert.assertEquals(ListMainPage[5], ListItemPage[5]); //                                                             акционная.
        Assert.assertEquals("s", ListMainPage[3]);// обычная цена зачёркнутая на главной странице.
        Assert.assertEquals("s", ListItemPage[3]);// обычная цена зачёркнутая на странице товара.
    }

    private String[] FillList(WebElement root) {
        String[] List = new String[9];
        List[1] = root.findElement(By.cssSelector("s.regular-price")).getText(); // величина обычной цены.
        List[2] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("color"); // цвет шрифта обычной цены.
        List[3] = root.findElement(By.cssSelector("s.regular-price")).getTagName();// критерий наличия специального тега, обозначающего зачеркнутость обычной цены.
        List[4] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size"); // размер шрифта обычной цены.

        List[5] = root.findElement(By.cssSelector("strong.campaign-price")).getText(); // величина акционной цены.
        List[6] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color"); // цвет шрифта акционной цены.
        List[7] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight"); // толщина шрифта акционной цены.
        List[8] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size"); // размер шрифта акционной цены.

        return List;
    }

    @After
    public void Stop() {
        driver.quit();
    }
}