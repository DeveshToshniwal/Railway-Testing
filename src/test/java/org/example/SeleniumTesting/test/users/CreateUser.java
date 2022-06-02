//Test Case 5

package org.example.SeleniumTesting.test.users;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class CreateUser {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();

        driver.get("http://127.0.0.1:5501/index.html");

        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//a")).click();

        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//input[@id='username']")).sendKeys("Krishna");
        driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("khushi@gmail.com");
        driver.findElement(By.xpath("//input[@id='address']")).sendKeys("Bangalore");
        driver.findElement(By.xpath("//input[@id='dob']")).sendKeys("04/03/2004");
        driver.findElement(By.xpath("//input[@id='age']")).sendKeys("18");
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("khushi@1234");
        driver.findElement(By.xpath("//input[@id='aadhaar']")).sendKeys("7657 4356 5009");

        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();


        /*WebDriverWait wait=new WebDriverWait(driver,1);
        WebElement message= wait.until(new ExpectedCondition<WebElement>() {
            @NullableDecl
            @Override
            public WebElement apply(@NullableDecl WebDriver webDriver) {
                WebElement e=webDriver.findElement(By.xpath("//div[@id='message']"));
                if(e.getText().length()>0)
                    return e;
                else
                    return null;
            }
        });*/

        WebElement message=driver.findElement(By.xpath("//div[@id='message']"));

        if(message.getText().equals("User register successfully!")){
            System.out.println("Test Case-5 with proper user details passed");
        }
        else if(message.getText().equals("Request Failed ,Please try again!")){
            System.out.println("Test Case-5 with invalid user details passed");
        }
        else{
            System.out.println("Test Case-5 failed");
        }

        driver.quit();

    }
}
