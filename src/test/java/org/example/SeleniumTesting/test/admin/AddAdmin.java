//Test Case-15,19

package org.example.SeleniumTesting.test.admin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddAdmin {

    private static void validDetails(WebDriver driver)
    {
        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Keshav");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Keshav@1234");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        WebDriverWait wait=new WebDriverWait(driver,4);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='swal2-html-container']")));
        if(driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText().trim().equals("Admin has been added"))
        {
            System.out.println("Test Case-15 passed");
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
        }
        else
        {
            System.out.println("Test Case-15 failed");
        }

        return;
    }

    private static void invalidDetails(WebDriver driver) {

        driver.navigate().refresh();

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("ramesh");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("ramu@2");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try
        {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Test Case-15 stopped "+e.getMessage());
        }

        WebElement ele= driver.findElement(By.xpath("//div[@id='message']"));
        String str= ele.getText().trim();
        if( str.equals("Invalid password, should contain atleast one Small letter, one Capital letter, one digit, one special character and should be 8 character long")
                || str.equals("Username cant be empty") || str.equals("Password cant be empty") )
        {
            System.out.println("Test Case-19 passed");
        }
        else
        {
            System.out.println("Test Case-19 failed");
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


        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("devesh");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Pass@1234");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();


            WebDriverWait wait=new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='swal2-html-container']")));

            if(driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText().trim().equals("Successfully logged in!")){

                driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Test Case-15 stopped : "+e.getMessage());
                }

                driver.findElement(By.xpath("//a[contains(text(),'Create new Admin')]")).click();

                try{
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Test Case-15 stopped due to "+e.getMessage());
                }

                validDetails(driver);

                invalidDetails(driver);

            }
            else
            {
                System.out.println("Test Case-15 stopped as admin login failed");
            }


        driver.quit();

    }

}
