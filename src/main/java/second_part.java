import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class second_part {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        //options.addArguments("--headless");
        options.addArguments("start-maximized");


        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://gb.ru/courses/design");

        WebElement webElement1 = driver.findElement(By.name("text"));
        WebElement webElement2 = driver.findElement(By.cssSelector("input.text"));
        WebElement webElement3 = driver.findElement(By.xpath(".//input[@name='text']"));

        try {
            WebElement webElementError = driver.findElement(By.name("error"));
        } catch (NoSuchElementException e){
            System.out.println(e.getSuppressed());
        }

        List<WebElement> webElements = driver.findElements(By.name("error"));
        if(webElements.size()>1){
            //todo
        }

        webElement1.click();
        webElement2.sendKeys("Поиск");

        new WebDriverWait(driver, 5).until(ExpectedConditions.urlContains("GeekBrains"));

        driver.navigate().to("https://gb.ru/courses/design");

        try {
            webElement3.sendKeys("Привет");
        } catch (StaleElementReferenceException e){
            System.out.println(e.getSuppressed());
        }

        try {
            driver.findElement(By.xpath(".//textarea")).click();
        } catch (ElementNotInteractableException e){
            System.out.println(e.getSuppressed());
        }
    }
}
