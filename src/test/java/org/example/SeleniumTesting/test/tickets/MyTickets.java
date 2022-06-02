//Test Case-9

package org.example.SeleniumTesting.test.tickets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MyTickets {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

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

        driver.findElement(By.xpath("//a[contains(text(),'Go to My Tickets')]")).click();

        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.xpath("//h1")).getText().trim().equals("My tickets")){
            System.out.println("Test Case-9 passed");
        }
        else
        {
            System.out.println("Test Case-9 failed");
        }

        driver.quit();

    }
}
