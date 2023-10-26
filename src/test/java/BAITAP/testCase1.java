package BAITAP;

import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.File;

@Test
public class testCase1
{
    public static void main() {
// Create an instance of the ChromeDrive

        WebDriver driver = driverFactory.getChromeDriver();
// Step 1: Navigate to the specified URL
        try{
            driver.get("http://live.techpanda.org/");
// Step 2: Verify the title of the page

            String expectedTitle = "THIS IS DEMO SITE FOR";
            String actualTitle = driver.findElement(By.xpath("//h2[1]")).getText().trim();
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Title verification successful: " + actualTitle);
            } else {
                System.out.println("Title verification failed: " + actualTitle);
            }
// Step 3: Click on the "MOBILE" menu
            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }
            WebElement mobileMenu = driver.findElement(By.linkText("MOBILE"));
            mobileMenu.click();
// Step 4: Select SORT BY -> dropdown as name

            WebElement sortByDropdown = driver.findElement(By.cssSelector("select[title='Sort By']"));
            Select select = new Select(sortByDropdown);
            select.selectByVisibleText("Name");

            try{
                Thread.sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }

// Step 5: Verify all products are sorted by name

            WebElement productList = driver.findElement(By.className("products-grid"));
            if (isSorted(productList, "h2")) {
                System.out.println("Products are sorted by name");
            } else {
                System.out.println("Products are not sorted by name");
            }
            TakesScreenshot screenshot =((TakesScreenshot)driver);
            File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
            String png = ("C:\\Users\\PC\\Desktop\\SpringBootProject\\selenium-webdriver-java\\ScreenShot\\testcase1.png");
            FileUtils.copyFile(srcFile, new File(png));
        } catch (Exception e) {
            e.printStackTrace();
        }
// Close the browser

        driver.quit();
    }
    private static boolean isSorted(WebElement element, String tag) {
        java.util.List<WebElement> elements = element.findElements(By.tagName(tag));
        String[] arr = new String[elements.size()];
        for (int i = 0; i < elements.size(); i++) {
            arr[i] = elements.get(i).getText();
        }
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1].compareTo(arr[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}