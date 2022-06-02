//Test Case-8

package org.example.SeleniumTesting.test.trains;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddTrainTests {

    private static void validData(WebDriver driver) {

        driver.findElement(By.xpath("//input[@id='trainNumber']")).sendKeys("12207");
        driver.findElement(By.xpath("//input[@id='totalSeats']")).sendKeys("200");
        driver.findElement(By.xpath("//input[@id='departure']")).sendKeys("Jamshedpur");
        driver.findElement(By.xpath("//input[@id='arrival']")).sendKeys("Malda");
        driver.findElement(By.xpath("//input[@id='departureDate']")).sendKeys("2022-09-09");
        driver.findElement(By.xpath("//input[@id='arrivalDate']")).sendKeys("2022-09-10");

        driver.findElement(By.xpath("//a[contains(text(),'Add New Train')]")).click();

        WebDriverWait wait=new WebDriverWait(driver,1);
        WebElement message=driver.findElement(By.xpath("//div[@id='messages']"));
        if(wait.until(ExpectedConditions.textToBePresentInElement(message,"Train Created")))
        {
            System.out.println("Test Case-8 with valid details passed");
        }
        else
        {
            System.out.println("Test Case-8 with valid details failed");
        }

        return;
    }

    private static void invalidData(WebDriver driver) {

        driver.navigate().refresh();

        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//input[@id='trainNumber']")).sendKeys("1220");
        driver.findElement(By.xpath("//input[@id='totalSeats']")).sendKeys("200");
        driver.findElement(By.xpath("//input[@id='departure']")).sendKeys("Jamshedpur");
        driver.findElement(By.xpath("//input[@id='arrival']")).sendKeys("Malda");
        driver.findElement(By.xpath("//input[@id='departureDate']")).sendKeys("2022-09-09");
        driver.findElement(By.xpath("//input[@id='arrivalDate']")).sendKeys("2022-09-10");

        driver.findElement(By.xpath("//a[contains(text(),'Add New Train')]")).click();

        WebDriverWait wait=new WebDriverWait(driver,1);
        WebElement message=driver.findElement(By.xpath("//div[@id='messages']"));
        if(wait.until(ExpectedConditions.textToBePresentInElement(message,"Train number must have 5 digit")))
        {
            System.out.println("Test Case-8 with invalid trainNumber passed");
        }
        else
        {
            System.out.println("Test Case-8 with invalid trainNumber failed");
        }

        return;

    }

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("http://127.0.0.1:5501/index.html");
        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//button[contains(text(),'Admin Login')]")).click();

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Admin@123");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        WebDriverWait wait=new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='swal2-html-container']")));

        driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//a[contains(text(),'Create new trains')]")).click();

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //valid details
        validData(driver);

        //invalid details train number
        invalidData(driver);

        driver.quit();
    }

}
