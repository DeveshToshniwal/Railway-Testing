//Test Case-17 for Deactivating admin

package org.example.SeleniumTesting.test.admin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class DeactivatingAdmin {

    private static int deactivate(WebDriver driver)
    {
        List<WebElement> elements=driver.findElements(By.xpath("//tbody/tr"));
        int num_admins= elements.size();
        int row=1;
        WebDriverWait wait=new WebDriverWait(driver,3);
        String xpath="//tbody/tr[%s]/td[2]";

        for(WebElement e:elements)
        {
            if(driver.findElement(By.xpath(String.format(xpath,row))).getText().equals("devesh"))
            {
                driver.findElement(By.xpath(String.format("//tbody/tr[%s]/td[4]/button",row))).click();

                try
                {
                    WebElement ele=driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
                    wait.until(ExpectedConditions.elementToBeClickable(ele));
                    ele.click();

                } catch (Exception ex) {
                    System.out.println("Test-case 17 for deactivating admin stopped due to : "+ex.getMessage());
                }

                break;
            }
            row=row+1;
        }

        driver.navigate().refresh();

        try{
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement element=driver.findElement(By.xpath(String.format("//tbody/tr[%s]/td[3]/span",row)));
        //wait.until(ExpectedConditions.visibilityOf(element));
        if(element.getText().trim().equals("Inactive"))
            System.out.println("Test-Case 17 passed for deactivating an admin");
        else
            System.out.println("Test Case-17 failed for deactivating an admin");

        return row;

    }

    private static void activate(WebDriver driver,int row)
    {
        driver.navigate().refresh();
        WebDriverWait wait=new WebDriverWait(driver,3);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Test Case-17 for actiavting admin stopped "+e.getMessage());
        }

        driver.findElement(By.xpath(String.format("//tbody/tr[%s]/td[4]/button",row))).click();

        try
        {
            WebElement ele=driver.findElement(By.xpath("//button[contains(text(),'Yes')]"));
            wait.until(ExpectedConditions.elementToBeClickable(ele));
            ele.click();

        } catch (Exception ex) {
            System.out.println("Test-case 17 for activating admin stopped due to : "+ex.getMessage());
        }

        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Test-case 17 for activating admin stopped due to : "+e.getMessage());
        }

        WebElement ele=driver.findElement(By.xpath("//button[contains(text(),'OK!')]"));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
        ele.click();

        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Test-case 17 for actiavting admin stopped due to : "+e.getMessage());
        }

        WebElement element=driver.findElement(By.xpath(String.format("//tbody/tr[%s]/td[3]/span",row)));
        //wait.until(ExpectedConditions.visibilityOf(element));
        if(element.getText().trim().equals("Active"))
            System.out.println("Test-Case 17 passed for activating an admin");
        else
            System.out.println("Test Case-17 failed for activating an admin");

        return;

    }

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

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
            System.out.println("Test Case-17 stopped as admin could not login");
        }

        driver.findElement(By.xpath("//a[contains(text(),'View all admins')]")).click();

        try
        {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println("Test-Case 17 stopped as the view admins page could not load");
        }

        int row=deactivate(driver);

        activate(driver,row);

        driver.quit();
    }
}


