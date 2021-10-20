package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class Sample9Task {
    WebDriver driver;

    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.get("https://kristinek.github.io/site/examples/loading_color");
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void loadGreenSleep() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        WebElement startLoadingGreenBtn = driver.findElement(By.id("start_green"));
        startLoadingGreenBtn.click();

        assertFalse(startLoadingGreenBtn.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());

        Thread.sleep(5000);

        assertFalse(loadingGreen.isDisplayed());

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertTrue(greenLoaded.isDisplayed());

    }

    @Test
    public void loadGreenImplicit() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement startLoadingGreenBtn = driver.findElement(By.id("start_green"));
        startLoadingGreenBtn.click();

        assertFalse(startLoadingGreenBtn.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());

        WebElement greenLoaded = driver.findElement(By.id("finish_green"));
        assertFalse(loadingGreen.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());


    }

    @Test
    public void loadGreenExplicitWait() throws Exception {
//         TODO:
//         * 1) click on start loading green button
//         * 2) check that button does not appear,
//         * but loading text is seen instead   "Loading green..."
//         * 3) check that both button
//         * and loading text is not seen,
//         * success is seen instead "Green Loaded"

        WebElement startLoadingGreenBtn = driver.findElement(By.id("start_green"));
        startLoadingGreenBtn.click();

        assertFalse(startLoadingGreenBtn.isDisplayed());

        WebElement loadingGreen = driver.findElement(By.id("loading_green"));
        assertTrue(loadingGreen.isDisplayed());


        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish_green")));
        WebElement greenLoaded = driver.findElement(By.id("finish_green"));

        assertFalse(loadingGreen.isDisplayed());
        assertTrue(greenLoaded.isDisplayed());



    }

    @Test
    public void loadGreenAndBlueBonus() {
        /* TODO:
         * 0) wait until button to load green and blue appears
         * 1) click on start loading green and blue button
         * 2) check that button does not appear, but loading text is seen instead for green
         * 3) check that button does not appear, but loading text is seen instead for green and blue
         * 4) check that button and loading green does not appear,
         * 		but loading text is seen instead for blue and success for green is seen
         * 5) check that both button and loading text is not seen, success is seen instead
         */

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("start_green_and_blue")));

        WebElement greenAndBlueBtn = driver.findElement(By.id("start_green_and_blue"));
        greenAndBlueBtn.click();

        assertFalse(greenAndBlueBtn.isDisplayed());

        WebElement greenLoading = driver.findElement(By.id("loading_green_without_blue"));
        assertTrue(greenLoading.isDisplayed());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_green_with_blue")));
        WebElement blueLoading = driver.findElement(By.id("loading_green_with_blue"));
        assertTrue(blueLoading.isDisplayed());

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loading_blue_without_green")));
        WebElement greenLoaded = driver.findElement(By.id("loading_blue_without_green"));
        assertTrue(greenLoaded.isDisplayed());

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("finish_green_and_blue")));
        WebElement blueAndGreenLoaded = driver.findElement(By.id("finish_green_and_blue"));
        assertTrue(blueAndGreenLoaded.isDisplayed());
    }

}