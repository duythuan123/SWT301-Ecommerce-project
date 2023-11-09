package BT;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import POM.beLoginPage;
import driver.driverFactory;

public class tc10 {
    @Test
    public void testTC10() {
        int scc = 0;

        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        beLoginPage beLoginPage = new beLoginPage(driver);

        // Values for beLoginPage
        String email_address = "user01";
        String password = "guru99com";

        // Values for OrderId, FromDate -> ToDate
        String orderId = "100021245";
        String fromDate = "11/7/2023";
        String toDate = "11/10/2023";

    try {
        //Step 1. Go to http://live.techpanda.org/index.php/backendlogin
        driver.get("http://live.techpanda.org/index.php/backendlogin");
        
        //Step 2. Login the credentials provided
        beLoginPage.enterEmail(email_address);
        beLoginPage.enterPassword(password);
        beLoginPage.clickLoginButton();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(2000);
        WebElement closeMsg = driver.findElement(By.xpath("//span[normalize-space()='close']"));
        closeMsg.click();
        Thread.sleep(2000);

        //Step 3. Go to Sales-> Orders menu
        WebElement saveMenu = driver.findElement(By.xpath("//span[normalize-space()='Sales']"));
        saveMenu.click();  
        Thread.sleep(2000);
        WebElement orders = driver.findElement(By.xpath("//span[normalize-space()='Orders']"));
        orders.click();  
        Thread.sleep(2000);

        //Step 4. Input OrderId and FromDate -> ToDate
        WebElement OrderIdInput = driver.findElement(By.xpath("//input[@id='sales_order_grid_filter_real_order_id']"));
        OrderIdInput.clear();
        OrderIdInput.sendKeys(orderId);
        Thread.sleep(2000);
        
        WebElement FromDateInput = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[3]/div[1]/div[2]/div[1]/table[1]/thead[1]/tr[2]/th[3]/div[1]/div[1]/input[1]"));
        FromDateInput.clear();
        FromDateInput.sendKeys(fromDate);
        Thread.sleep(2000);

        WebElement ToDateInput = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[3]/div[1]/div[2]/div[1]/table[1]/thead[1]/tr[2]/th[3]/div[1]/div[2]/input[1]"));
        ToDateInput.clear();
        ToDateInput.sendKeys(toDate);
        Thread.sleep(2000);

        //Step 5. Click Search button
        WebElement searchButton = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[4]/div[1]/div[3]/div[1]/table[1]/tbody[1]/tr[1]/td[3]/button[2]/span[1]/span[1]/span[1]"));
        searchButton.click();
        Thread.sleep(2000);

        //Step 6. Screenshot capture.
        scc = (scc + 10);
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        String png = ("D:\\SWT301\\SWT301-Ecommerce-project\\selenium-webdriver-java-master\\src\\test\\resources\\testcase" + scc + ".png");
        FileUtils.copyFile(scrFile, new File(png));
        
    } catch (Exception e){
        e.printStackTrace();
    }

    // Quit browser
        driver.quit();
    }
}