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
        Assert.assertEquals(ListMainPage[1], ListItemPage[1]); // на главной странице и на странице товара совпадают величины цен: обычной и -->
        Assert.assertEquals(ListMainPage[5], ListItemPage[5]); //                                                             акционной.
        Assert.assertEquals("s", ListMainPage[3]);// обычная цена зачёркнутая на главной странице.
        Assert.assertEquals("s", ListItemPage[3]);// обычная цена зачёркнутая на странице товара.
        Assert.assertTrue(grayColorCheck(ListMainPage[2].replaceAll("rgba", ""))); // серый шрифт обычной цены на главной странице.
        Assert.assertTrue(grayColorCheck(ListItemPage[2].replaceAll("rgba", ""))); // серый шрифт обычной цены на странице товара.
        Assert.assertEquals("strong", ListMainPage[7]);// акционная цена жирная на главной странице.
        Assert.assertEquals("strong", ListItemPage[7]);// акционная цена жирная на странице товара.
        Assert.assertTrue(redColorCheck(ListMainPage[6].replaceAll("rgba", ""))); // красный шрифт акционной цены на главной странице.
        Assert.assertTrue(redColorCheck(ListItemPage[6].replaceAll("rgba", ""))); // красный шрифт акционной цены на странице товара.

        Double a = Double.parseDouble(ListMainPage[4].replaceAll("[^\\d.]", "")); // размер шрифта обычной цены на главной странице.
        Double b = Double.parseDouble(ListMainPage[8].replaceAll("[^\\d.]", "")); // размер шрифта акционной цены на главной странице.
        Double c = Double.parseDouble(ListItemPage[4].replaceAll("[^\\d.]", "")); // размер шрифта обычной цены на странице товара.
        Double d = Double.parseDouble(ListItemPage[8].replaceAll("[^\\d.]", "")); // размер шрифта акционной цены на странице товара.

        Assert.assertTrue(b.compareTo(a) > 0); // размер шрифта акционной цены на главной странице больше размера обычной.
        Assert.assertTrue(d.compareTo(c) > 0); // размер шрифта акционной цены на странице товара больше размера обычной.
    }

    public String[] FillList(WebElement root) {
        String[] List = new String[9];
        List[1] = root.findElement(By.cssSelector("s.regular-price")).getText(); // величина обычной цены.
        List[2] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("color"); // цвет шрифта обычной цены.
        List[3] = root.findElement(By.cssSelector("s.regular-price")).getTagName();// критерий наличия специального тега, обозначающего зачеркнутость обычной цены.
        List[4] = root.findElement(By.cssSelector("s.regular-price")).getCssValue("font-size"); // размер шрифта обычной цены.

        List[5] = root.findElement(By.cssSelector("strong.campaign-price")).getText(); // величина акционной цены.
        List[6] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color"); // цвет шрифта акционной цены.
        List[7] = root.findElement(By.cssSelector("strong.campaign-price")).getTagName(); // толщина (жирность) шрифта акционной цены.
        List[8] = root.findElement(By.cssSelector("strong.campaign-price")).getCssValue("font-size"); // размер шрифта акционной цены.

        return List;
    }

    public List<Integer> colorToMassive(String ColorQuantity) {
        Matcher m = Pattern.compile("\\d+").matcher(ColorQuantity);
        List<Integer> rgbaQuantityArray = new ArrayList<>();
        while (m.find()) {
            rgbaQuantityArray.add(Integer.parseInt(m.group()));
        }
        return rgbaQuantityArray;
    }

    public boolean grayColorCheck(String ColorQuantity) {
        List<Integer> rgbaQuantityArray = colorToMassive(ColorQuantity);
        int rValue = rgbaQuantityArray.get(0);
        int gValue = rgbaQuantityArray.get(1);
        int bValue = rgbaQuantityArray.get(2);
        return rValue == gValue &&
                gValue == bValue;
    }

    public boolean redColorCheck(String ColorQuantity) {
        List<Integer> rgbaQuantityArray = colorToMassive(ColorQuantity);
        int gValue = rgbaQuantityArray.get(1);
        int bValue = rgbaQuantityArray.get(2);
        return gValue == 0 && bValue == 0;
    }

    @After
    public void Stop() {
        driver.quit();
    }
}