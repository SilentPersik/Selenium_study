package TrainingTests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestCountriesSort_and_Timezones {

    private WebDriver driver;

    @Before
    public void Start() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void General() {
        driver.get("http://localhost/litecart/admin/");
        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement checkbox = driver.findElement(By.name("remember_me"));
        checkbox.click();
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        String searchPage = "Countries";
        SearchAndClick(searchPage);
        CheckCountries();

        searchPage = "Geo Zones";
        SearchAndClick(searchPage);
        CheckGeo_Zones();
    }

    private void CheckCountries() {

        String name = "Countries";
        String root = "td#content table.dataTable tbody";
        String table = "./tr[@class='row']/td[5]/a";
        CheckSortInList(root, table, name, true, true);

        table = "./tr[@class='row']/td[6]";
        List<WebElement> ListWE = FillListWEl(root, table);
        List<String> listS = FillList(ListWE, true, true);
        int i = 1;
        for (String zone : listS) {
            if (!zone.equals("0")) {
                table = "./tr[@class='row'][" + i + "]/td[5]/a";
                name = CurrentWEl(root, table).getAttribute("textContent");
                CurrentWEl(root, table).click();
                table = "./tr/td[3]";
                CheckSortInList(root, table, name, false, false);
                ButtonCancel_Click();
                driver.findElement(By.cssSelector(root));
            }
            i++;
        }
    }

    private void CheckGeo_Zones() {
        String name, zone;
        String root = "td#content table.dataTable tbody";
        String table = ".//tr/td[3]//option[@selected='selected']";
        int graph = driver.findElements(By.cssSelector("td#content table.dataTable tr.row")).size();
        for (int i = 1; i <= graph; i++) {
            zone = ".//tr[@class='row'][" + i + "]/td[3]/a";
            name = CurrentWEl(root, zone).getAttribute("textContent");
            CurrentWEl(root, zone).click();
            CheckSortInList(root, table, name, true, true);
            ButtonCancel_Click();
            driver.findElement(By.cssSelector(root));
        }
    }

    private void ButtonCancel_Click() {
        driver.findElement(By.cssSelector("td#content p button[name=cancel]")).click();
    }

    private WebElement CurrentWEl(String rootLoc, String element) {
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        return root.findElement(By.xpath(element));
    }

    private void CheckSortInList(String rootLoc, String tableLoc, String name, boolean first, boolean last) {
        List<WebElement> listWE = FillListWEl(rootLoc, tableLoc);
        List<String> list = FillList(listWE, first, last);
        Assert.assertTrue(IsListSorted(list)); // Проверка!
    }

    private List<WebElement> FillListWEl(String rootLoc, String tableLoc) {
        WebElement root = driver.findElement(By.cssSelector(rootLoc));
        List<WebElement> ListWEl = root.findElements(By.xpath(tableLoc));
        return ListWEl;
    }

    private List<String> FillList(List<WebElement> listWEl, boolean first, boolean last) {
        List<String> resultList = new ArrayList<>();
        for (WebElement we : listWEl)
            resultList.add(we.getAttribute("textContent"));
        if (!first) resultList.remove(0);
        if (!last) resultList.remove(resultList.size() - 1);
        return resultList;
    }

    private boolean IsListSorted(List<String> list) {
        String previous = list.get(0);
        String current;
        boolean result = true;
        for (int i = 1; i < list.size(); i++) {
            current = list.get(i);
            if (current.compareTo(previous) < 0) {
                result = false;
                break;
            }
            previous = current;
        }
        return result;
    }

    private void SearchAndClick(String point) {
        String locator = "", currentPage = "";
        int i = 0;
        while (!currentPage.equals(point)) {
            i++;
            locator = "ul#box-apps-menu li#app-:nth-child(" + i + ")";
            if (IsElementPresent(driver, By.cssSelector(locator)))
                currentPage = driver.findElement(By.cssSelector(locator)).getText();
        }
        driver.findElement(By.cssSelector(locator)).click();
    }

    boolean IsElementPresent(WebDriver driver, By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    @After
    public void stop() {
        driver.quit();
    }
}