//Test Case-7
package org.example.SeleniumTesting.test.trains;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class ListTrainsTesting {

    private static void withoutLogin(WebDriver driver) {

        driver.get("http://127.0.0.1:5501/pages/listTrains.html");

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Test Stopped due to the following reason : "+e.getMessage());
        }

        if(driver.getCurrentUrl().contains("index.html"))
        {
            System.out.println("Test case-7 for without log in passed");
        }
        else
        {
            System.out.println("Test case-7 for without log in failed");
        }
        return;
    }

    private static void withLogin(WebDriver driver) {

        driver.get("http://127.0.0.1:5501/index.html");


        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("naman@yahoo.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Capman@22");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (driver.getCurrentUrl().contains("home.html")) {
            driver.findElement(By.xpath("//a[contains(text(),'Go to List Trains')]")).click();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            if(driver.getCurrentUrl().contains("listTrains.html"))
            {
                System.out.println("Test Case-7 with user login passed");
            }
            else
            {
                System.out.println("Test Case-7 with user login failed");
            }

        } else {
            System.out.println("Could not get to home.html");
        }

        return;
    }

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        //trying to access listTrains.html without login as admin or user
        withoutLogin(driver);

        //trying to access listTrains.html after logging in as user
        withLogin(driver);

        driver.quit();
    }

}
