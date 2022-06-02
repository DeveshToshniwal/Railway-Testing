//Test case-10

package org.example.SeleniumTesting.test.meals;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddMealsTest {

    private static void validData(WebDriver driver) {

        driver.findElement(By.xpath("//select[@id='trainNumber']")).click();
        driver.findElement(By.xpath("//select[@id='trainNumber']/option[@value='12207']")).click();
        driver.findElement(By.xpath("//input[@id='desc']")).sendKeys("Fish");
        driver.findElement(By.xpath("//select[@id='isFreeMeal']")).click();
        driver.findElement(By.xpath("//select[@id='isFreeMeal']/option[@value='1']")).click();
        driver.findElement(By.xpath("//a[@id='submitBtn']")).click();

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            System.out.println("Test Case-10 stopped due to failure in swal alert display");
        }

        if(driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText().trim().equals("Meal added Successfully"))
        {
            driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
            System.out.println("Test Case-10 passed for valid data");
        }
        else
        {
            System.out.println("Test Case-10 failed for valid data");
        }

        return;

    }

    private static void partialData(WebDriver driver) {

        driver.findElement(By.xpath("//select[@id='trainNumber']")).click();
        driver.findElement(By.xpath("//select[@id='trainNumber']/option[@value='12207']")).click();
        driver.findElement(By.xpath("//select[@id='isFreeMeal']")).click();
        driver.findElement(By.xpath("//select[@id='isFreeMeal']/option[@value='1']")).click();
        driver.findElement(By.xpath("//a[@id='submitBtn']")).click();

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Test Case-10 for invalid data stopped due to alert display failure");
        }

        Alert alert=driver.switchTo().alert();
        if(alert==null)
        {
            System.out.println("Test Case-10 with invalid data failed as alert not found");
        }
        else{

            if(alert.getText().trim().equals("Please fill all the details."))
            {
                alert.accept();
                System.out.println("Test Case-10 with invalid data passed");
            }
            else
            {
                System.out.println("Test Case-10 with invalid data failed as alert text does not match");
            }

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

        driver.findElement(By.xpath("//button[contains(text(),'OK!')]")).click();

        try{
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//a[contains(text(),'Add meals')]")).click();

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("Test Stopped as meals page could not load");
        }

        //entering all the data necessary
        validData(driver);

        //not entering the meal description
        partialData(driver);

        driver.quit();
    }
}
