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

        WebElement username = driver.findElement(By.name("username"));
        username.sendKeys("admin");
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("admin");
        WebElement checkbox = driver.findElement(By.name("remember_me"));
        checkbox.click();
        WebElement login = driver.findElement(By.name("login"));
        login.click();

        List<WebElement> LeftPanel = driver.findElements(By.cssSelector("ul#box-apps-menu>li"));
        for (int b = 0; b < LeftPanel.size(); b++) {
            LeftPanel = driver.findElements(By.cssSelector("ul#box-apps-menu>li"));
            LeftPanel.get(b).click();
            Assert.assertTrue(driver.findElements(By.cssSelector("td#content>h1")).size() == 1);
            List<WebElement> InnerPanels = driver.findElements(By.cssSelector("ul.docs>li"));
            if (InnerPanels.size() > 1) {
                for (int c = 0; c < InnerPanels.size(); c++) {
                    InnerPanels = driver.findElements(By.cssSelector("ul.docs>li"));
                    InnerPanels.get(c).click();
                    Assert.assertTrue(driver.findElements(By.cssSelector("td#content>h1")).size() == 1);
                }
            }
        }
        driver.quit();
    }
}