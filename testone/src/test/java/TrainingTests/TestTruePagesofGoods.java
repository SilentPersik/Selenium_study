package TrainingTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestTruePagesofGoods {

    private WebDriver driver;

    @Before
    public void start() {
        driver = new ChromeDriver();
        /*driver = new FirefoxDriver();
         * driver = new InternetExplorerDriver();
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

        Assert.assertEquals(ListMainPage[0], ListItemPage[0]);
        Assert.assertEquals(ListMainPage[1], ListItemPage[1]);
        Assert.assertEquals(ListMainPage[5], ListItemPage[5]);
        Assert.assertTrue(ListMainPage[2] != ListItemPage[2]);
        Assert.assertTrue(ListMainPage[3] != ListItemPage[3]);
        Assert.assertTrue(ListMainPage[6] != ListItemPage[6]);
        Assert.assertTrue(ListMainPage[7] != ListItemPage[7]);
        Assert.assertTrue(ListMainPage[8].compareTo(ListItemPage[4]) > 0);
        Assert.assertTrue(ListMainPage[4].compareTo(ListItemPage[8]) < 0);
    }

    private String[] FillList(WebElement root) {
        String[] List = new String[9];
        List[1] = root.findElement(By.cssSelector("s.regular-price")).getText();
        List[2] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        List[3] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        List[4] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size");

        List[5] = root.findElement(By.cssSelector("strong.campaign-price")).getText();
        List[6] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        List[7] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-weight");
        List[8] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size");

        return List;
    }

    @After
    public void stop() {
        driver.quit();
    }
}