package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class TestWithError {
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
        password.sendKeys("Password1!1");
        login.submit();
        WebElement error = webDriver.findElement(By.xpath("//div[@class='mw-message-box-error mw-message-box']"));
        Assert.assertTrue(error.getText().equals("Incorrect username or password entered. Please try again."));
        webDriver.close();

    }
}
