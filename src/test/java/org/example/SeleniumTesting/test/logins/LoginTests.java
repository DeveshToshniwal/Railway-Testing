//Test Cases - 2,3,4,13

package org.example.SeleniumTesting.test.logins;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class LoginTests {

    private static void userLoginValid()
    {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("http://127.0.0.1:5501/index.html");

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test stopped due to the following issue : "+e.getMessage());
        }

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("naman@yahoo.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Capman@22");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test stopped due to the following issue : "+e.getMessage());
        }

        if(driver.getCurrentUrl().contains("home.html")){
            System.out.println("Test Case-2 Passed");
        }
        else
        {
            System.out.println("Test Case-2 Failed "+driver.getCurrentUrl());
        }

        driver.quit();
        return;

    }

    private static void userLoginInvalid() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("http://127.0.0.1:5501/index.html");

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test stopped due to the following issue : "+e.getMessage());
        }

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("naman@yahoo.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Capma@22");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test stopped due to the following issue : "+e.getMessage());
        }

        if(driver.getCurrentUrl().contains("index.html")){
            System.out.println("Test Case-3 Passed");
        }
        else
        {
            System.out.println("Test Case-3 Failed "+driver.getCurrentUrl());
        }

        driver.quit();
        return;
    }

    private static void adminLoginValid() {

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


        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("devesh");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Pass@1234");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();


        try{

            WebDriverWait wait=new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='swal2-html-container']")));

            if(driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText().trim().equals("Successfully logged in!")){

                driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
                Thread.sleep(100);
                if(driver.getCurrentUrl().contains("adminPanel.html"))
                    System.out.println("Test Case-4 Passed");
                else
                    System.out.println("Test Case-4 Failed");

            }
        }catch(Exception e)
        {
            System.out.println("Test case-4 Stopped due to the following reason : "+e.getMessage());
        }

        driver.quit();
        return;
    }

    private static void adminLoginInvalid() {

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("http://127.0.0.1:5501/index.html");

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test case-13 stopped due to the following issue : "+e.getMessage());
        }

        driver.findElement(By.xpath("//button[contains(text(),'Admin Login')]")).click();

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test case-13 stopped due to the following issue : "+e.getMessage());
        }

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("devesh");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("pass@12345");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test case-13 stopped due to the following issue : "+e.getMessage());
        }

        WebElement element=driver.findElement(By.xpath("//div[@id='message']"));
        String msg= element.getText().trim();

        if(msg.equals("Incorrect password") || msg.equals("Admin doesnt exist by that username") ||
           msg.equals("Invalid password, should contain atleast one Small letter, one Capital letter, one digit, one special character and should be 8 character long"))
        {
            System.out.println("Test Case-13 passed");
        }
        else
        {
            System.out.println("Test Case-13 failed "+msg);
        }

        driver.quit();
        return;
    }

    public static void main(String[] args) {

        //Test Case-2 User login with valid credentials
        userLoginValid();

        //Test Case-3 User login with invalid credentials
        userLoginInvalid();

        //Test Case-4 Admin Login with valid credentials
        adminLoginValid();

        //Test Case-13 Admin Login with Invalid credentials
        adminLoginInvalid();

    }



}
