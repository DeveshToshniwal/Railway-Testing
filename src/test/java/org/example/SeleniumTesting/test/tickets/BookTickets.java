//Test Case-6

package org.example.SeleniumTesting.test.tickets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BookTickets {

    private static void validData(WebDriver driver, String trainNumber) {


        driver.findElement(By.xpath("//select[@id='trainSource']")).click();
        driver.findElement(By.xpath("//select[@id='trainSource']/option[@value='Patna']")).click();
        driver.findElement(By.xpath("//select[@id='trainDestination']")).click();
        driver.findElement(By.xpath("//select[@id='trainDestination']/option[@value='Bengaluru']")).click();
        driver.findElement(By.xpath("//select[@id='trainNumber']")).click();
        driver.findElement(By.xpath("//select[@id='trainNumber']/option[@value="+trainNumber+"]")).click();
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement element=driver.findElement(By.xpath("//h2[@id='swal2-title']"));
        WebDriverWait wait=new WebDriverWait(driver,4);
        wait.until(ExpectedConditions.visibilityOf(element));
        if(element.getText().equals("Do you want to book another ticket?")){
            driver.findElement(By.xpath("//button[contains(text(),'No')]")).click();
            try{
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(driver.getCurrentUrl().contains("home.html")){
                driver.findElement(By.xpath("//a[contains(text(),'Go to My Tickets')]")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody/tr/th")));

                List<WebElement> rows=driver.findElements(By.xpath("//tbody[@id='body']/tr"));
                int num_rows=rows.size();
                String xpath="//tbody[@id='body']/tr[%s]/td[2]";

                for (int i=num_rows-1;i>=0;i--)
                {
                    WebElement ele= rows.get(i).findElement(By.xpath(String.format(xpath,i+1)));
                    if(ele.getText().trim().equals(trainNumber))
                    {
                        System.out.println(i);
                        System.out.println("Test case-6 passed for valid data");
                        return;
                    }
                }

                System.out.println("Test case-6 failed for valid data");

                /*element=driver.findElement(By.xpath("//tbody/tr/td[2]"));

                if(element.getText().trim().equals("12309")){
                    System.out.println("Test-Case 6 passed for valid data");
                }
                else {
                    System.out.println("Test case-6 failed for valid data");
                }*/
            }
            else
            {
                System.out.println("Test-case 6 failed - did not redirect to home page");
            }
        }
        else
        {
            System.out.println("Test case-6 failed - wrong swal alert");
        }

        return;
    }

    private static void invalidData(WebDriver driver) {
        driver.findElement(By.xpath("//select[@id='trainSource']")).click();
        driver.findElement(By.xpath("//select[@id='trainSource']/option[@value='Bengaluru'][1]")).click();
        driver.findElement(By.xpath("//select[@id='trainDestination']")).click();
        driver.findElement(By.xpath("//select[@id='trainDestination']/option[@value='Patna'][1]")).click();
        driver.findElement(By.xpath("//select[@id='trainNumber']")).click();
        driver.findElement(By.xpath("//select[@id='trainNumber']/option[@value='12309']")).click();
        driver.findElement(By.xpath("//button[@id='submitBtn']")).click();

        try{
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(driver.findElement(By.xpath("//div[@id='swal2-html-container']")).getText().trim().equals("Invalid or missing data. Please re-check")){
            System.out.println("Test case-6 passed for invalid data");
        }
        else{
            System.out.println("Test case-6 failed for Invalid data");
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

        driver.findElement(By.xpath("//a[contains(text(),'Go to Ticket Booking')]")).click();

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //valid Train details
        validData(driver,"10121");

        //invalid Train details
        //invalidData(driver);

        driver.quit();

    }
}
