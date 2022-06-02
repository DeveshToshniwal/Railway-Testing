//Test case-14

package org.example.SeleniumTesting.test.tickets;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DeleteTicket {
    public static void main(String[] args) {
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
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            driver.quit();
            System.out.println("Test stopped due to the following issue : "+e.getMessage());
        }

        driver.findElement(By.xpath("//a[contains(text(),'Go to My Tickets')]")).click();

        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Test Case-14 stopped as mytickets page ould not load");
        }

        List<WebElement> elements=driver.findElements(By.xpath("//tbody[@id='body']/tr"));
        int num_rows=elements.size();

        String number="12309";
        String train_number_xpath="//tbody[@id='body']/tr[%s]/td[2]";
        String button_xpath="//tbody[@id='body']/tr[%s]/td[6]/button";

        for(int i=0;i<num_rows;i++)
        {
            WebElement ele=elements.get(i).findElement(By.xpath(String.format(train_number_xpath,i+1)));
            if(ele.getText().trim().equals(number))
            {
                WebElement webElement= elements.get(i).findElement(By.xpath(String.format(button_xpath,i+1)));
                WebDriverWait wait=new WebDriverWait(driver,4);
                wait.until(ExpectedConditions.elementToBeClickable(webElement));
                webElement.click();
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Test Case-14 stopped as swal alert could not be loaded");
                }

                driver.findElement(By.xpath("//button[contains(text(),'Yes')]")).click();

                try{
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    System.out.println("Test case-14 stopped after clicking Yes");
                }

                break;
            }
        }

        driver.navigate().refresh();

        try{
            Thread.sleep(200);
        } catch (InterruptedException e) {
            System.out.println("Test Case-14 stopped as myTickets could not load after deleting ticket");
        }

        elements=driver.findElements(By.xpath("//tbody[@id='body']/tr"));
        num_rows=elements.size();
        int i;
        for(i=0;i<num_rows;i++)
        {
            WebElement ele=elements.get(i).findElement(By.xpath(String.format(train_number_xpath,i+1)));
            if(ele.getText().trim().equals(number))
            {
                System.out.println("Test Case-14 failed");
                break;
            }
        }

        if(i==num_rows)
            System.out.println("Test Case-14 passed");

        driver.quit();
    }
}
