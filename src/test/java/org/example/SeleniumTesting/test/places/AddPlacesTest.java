//Test Case-16

package org.example.SeleniumTesting.test.places;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddPlacesTest {

    private static void empty(WebDriver driver)
    {
        driver.navigate().refresh();

        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try
        {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Test-Case 16 for empty place stopped due to "+e.getMessage());
        }

        Alert alert=driver.switchTo().alert();
        if(alert==null)
        {
            System.out.println("Test-Case 16 failed as alert did not display");
        }
        else if(! alert.getText().equals("Failed"))
        {
            System.out.println("Test Case-16 failed as alert does not show Failed");
        }
        else
        {
            System.out.println("Test Case-16 passed for empty place");
        }

        return;
    }

    private static void validPlace(WebDriver driver)
    {

        driver.navigate().refresh();

        WebElement element=driver.findElement(By.xpath("//input[@id='placeId']"));
        element.sendKeys("Delhi");

        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try
        {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Test-Case 16 for valid place stopped due to "+e.getMessage());
        }

        Alert alert=driver.switchTo().alert();
        if(alert==null)
        {
            System.out.println("Test-Case 16 failed for valid place as alert did not display");
        }
        else if(! alert.getText().equals("Added successfully"))
        {
            System.out.println("Test Case-16 failed as alert does not Added successfully");
        }
        else
        {
            System.out.println("Test Case-16 passed for valid place");
        }

        return;
    }

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("http://127.0.0.1:5501/index.html");

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//button[contains(text(),'Admin Login')]")).click();

        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("devesh");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("Pass@1234");
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();


        WebDriverWait wait = new WebDriverWait(driver, 1);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='swal2-html-container']")));

        if (driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText().trim().equals("Successfully logged in!")) {
            driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();
        } else {
            System.out.println("Test Case-16 stopped as admin could not login");
        }

        driver.findElement(By.xpath("//a[contains(text(),'Create new places')]")).click();

        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Test Case 16 stopped due to "+e.getMessage());
        }

        //leaving place input empty
        //empty(driver);

        //entering value in place input
        validPlace(driver);

        driver.quit();

    }

}
