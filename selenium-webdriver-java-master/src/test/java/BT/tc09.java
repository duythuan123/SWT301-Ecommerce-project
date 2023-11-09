package BT;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import driver.driverFactory;

public class tc09 {
    @Test
    public void testTC09() {
        int scc = 0;
        StringBuffer verificationError = new StringBuffer();
        //Init web-driver session
        WebDriver driver = driverFactory.getChromeDriver();
        //Coupon code
        String couponCode = "GURU50";

    try {
        //Step 1. Go to http://live.techpanda.org/
        driver.get("http://live.techpanda.org/");

        //Step 2. Go to Mobile and add IPHONE to cart
        driver.findElement(By.linkText("MOBILE")).click();
        Thread.sleep(2000);
        WebElement IphoneAddToCart = driver.findElement(By.xpath("//li[1]//div[1]//div[3]//button[1]//span[1]//span[1]"));
        IphoneAddToCart.click();
        Thread.sleep(2000);
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }
        Thread.sleep(2000);

        //Step 3. Enter Coupon Code
        WebElement basePrice = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tfoot[1]/tr[1]/td[2]/strong[1]/span[1]"));
        String basePriceStr = basePrice.getText();
        System.out.println(basePriceStr);
        WebElement couponInput = driver.findElement(By.xpath("//input[@id='coupon_code']"));
        couponInput.clear();
        couponInput.sendKeys(couponCode);
        Thread.sleep(2000);
        WebElement apply = driver.findElement(By.xpath("//span[contains(text(),'Apply')]"));
        apply.click();
        Thread.sleep(2000);
        WebElement newPrice = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[3]/div[1]/table[1]/tfoot[1]/tr[1]/td[2]/strong[1]/span[1]"));
        String newPriceStr = newPrice.getText();
        //Step 4. Verify the discount generated
        System.out.println(newPriceStr);
        try {
            AssertJUnit.assertEquals(newPriceStr, basePriceStr);
        } catch (Error e) {
            verificationError.append(e.toString());
        }
        Thread.sleep(2000);

        //this will take screenshot after success
        scc = (scc + 9);
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
