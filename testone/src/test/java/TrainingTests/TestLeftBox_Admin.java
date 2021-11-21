package TrainingTests;


import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TestLeftBox_Admin {

    private WebDriver driver = new ChromeDriver();

    @Test
    public void LeftBox() {
        driver.manage().window().maximize();
        driver.navigate().to("http://localhost/litecart/admin/");

        Boolean HeaderLoginPage = driver.findElements(By.tagName("h1")).size() < 0;
        System.out.println("Header is " + HeaderLoginPage); // Проверка наличия тега h1.
        Assert.assertFalse(driver.findElements(By.cssSelector("td#content h1")).size() > 0);

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement checkbox = driver.findElement(By.name("remember_me"));
        checkbox.click();
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        Boolean HeaderAdmin = driver.findElements(By.tagName("h1")).size() < 0;
        System.out.println("Header is " + HeaderAdmin); // Проверка наличия тега h1.
        Assert.assertFalse(driver.findElements(By.cssSelector("td#content h1")).size() > 0);

        List<WebElement> LeftPanel = driver.findElements(By.cssSelector("ul#box-apps-menu>li"));
        List<WebElement> InnerPanels;
        for (int b = 0; b < LeftPanel.size(); b++) {
            LeftPanel = driver.findElements(By.cssSelector("ul#box-apps-menu>li"));
            LeftPanel.get(b).click();
            InnerPanels = driver.findElements(By.cssSelector("ul.docs>li"));
            if (InnerPanels.size() > 1) {
                for (int c = 0; c < InnerPanels.size(); c++) {
                    InnerPanels = driver.findElements(By.cssSelector("ul.docs>li"));
                    InnerPanels.get(c).click();
                }
            }
        }
        driver.quit();
    }
}

