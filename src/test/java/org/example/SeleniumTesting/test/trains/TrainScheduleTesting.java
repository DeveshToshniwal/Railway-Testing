//Test Cases - 11,18

package org.example.SeleniumTesting.test.trains;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TrainScheduleTesting {

    public static void withoutLogIn(WebDriver driver)
    {
        //WebDriverManager.chromedriver().setup();
        //WebDriver driver=new ChromeDriver();
        driver.get("http://127.0.0.1:5501/pages/trainSchedule.html");
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

        String url=driver.getCurrentUrl();

        if(url.contains("403.html"))
        {
            System.out.println("Test Case-18 Passed");
        }
        else
        {
            if(url.contains("trainSchedule.html"))
                System.out.println("Test Case-18 failed");
            else
                System.out.println("Some other issue");
        }

        return;
    }

    public static void withLogin(WebDriver driver)
    {
        //WebDriverManager.chromedriver().setup();
        //WebDriver driver=new ChromeDriver();
        driver.get("http://127.0.0.1:5501/index.html");


        try {
                Thread.sleep(300);
        } catch (InterruptedException e) {
                throw new RuntimeException(e);
        }

        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("naman@yahoo.com");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Capman@22");
        driver.findElement(By.xpath("//button[contains(text(),'Submit')]")).click();

        try{
            Thread.sleep(3000);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if(driver.getCurrentUrl().contains("home.html"))
        {
            driver.findElement(By.xpath("//a[@href='./trainSchedule.html']")).click();
            try{
                Thread.sleep(500);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            driver.findElement(By.xpath("//select[@id='trainNumber']")).click();
            WebElement options=driver.findElement(By.xpath("//option[@value='12567']"));
            String trainNumber=options.getText().trim();
            options.click();
            driver.findElement(By.xpath("//a[@id='submitBtn']")).click();

            try{
                Thread.sleep(200);
            }catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            try{
                WebElement element=driver.findElement(By.xpath("//tbody/tr[1]/th"));
                if(element.getText().trim().equals(trainNumber))
                {
                    System.out.println("Test Case-11 Passed");
                }
                else
                {
                    System.out.println("Test Case-11 Failed");
                }
            }catch(Exception e)
            {
                System.out.println("Could not find xpath in table");
            }

        }
        else {
            System.out.println("Could not get to home.html");
        }

        return;
    }

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        //check with user login (test case id-11)
        withLogin(driver);

        //check without login (test case id-18)
        withoutLogIn(driver);

        driver.quit();


    }
}
