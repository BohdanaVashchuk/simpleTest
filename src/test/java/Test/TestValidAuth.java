package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class TestValidAuth {
    @org.testng.annotations.Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

        webDriver.manage().window().maximize();

        webDriver.get("https://uk.wikipedia.org");
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        webDriver.findElement(By.xpath("//a[@lang = 'en']")).click();
        WebElement title = webDriver.findElement(By.xpath("//a[@title='View the content page [alt-shift-c]']//span[contains(text(),'Main Page')]"));
        Assert.assertTrue(title.getText().equals("Main Page"));
        webDriver.findElement(By.xpath("//span[normalize-space()='Log in']")).click();
        WebElement username = webDriver.findElement(By.id("wpName1"));
        WebElement password = webDriver.findElement(By.id("wpPassword1"));
        WebElement login = webDriver.findElement(By.id("wpLoginAttempt"));
        username.sendKeys("DanaTest");
        password.sendKeys("Password1!");
        WebElement checkbox = webDriver.findElement(By.name("wpRemember"));
        for (int i=0; i<2; i++){
            checkbox.click();
        }
        login.submit();
        WebElement confirmation = webDriver.findElement(By.xpath("//a/span[contains(text(),'DanaTest')]"));
        Assert.assertTrue(confirmation.getText().equals("DanaTest"));
        WebElement search = webDriver.findElement(By.id("searchInput"));
        search.sendKeys("Ukraine");
        search.submit();
        WebElement ukrPage = webDriver.findElement(By.xpath("//span[@class='mw-page-title-main' and contains(text(),'Ukraine')]"));
        Assert.assertTrue(ukrPage.getText().equals("Ukraine"));
        webDriver.close();

}
}
