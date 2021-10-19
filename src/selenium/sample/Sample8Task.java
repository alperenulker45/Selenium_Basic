package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class Sample8Task {
    WebDriver driver;

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();

        //open page:
        driver.get("https://kristinek.github.io/site/examples/po");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void styleChecks() throws Exception {
//         TODO:
//        check the background of top 2 sections
//        rgba(255, 221, 221, 1);
//        check h1 element font-size 64px

        WebElement h1 = driver.findElement(By.xpath("//h1"));
        System.out.println("h1.getCssValue(\"font-size\") = " + h1.getCssValue("font-size"));
        assertEquals("64px", h1.getCssValue("font-size"));//check h1 element font-size 64px

        WebElement topSection1 = driver.findElement(By.cssSelector("h1.w3-jumbo"));
        System.out.println(topSection1.getCssValue("background-color"));

        WebElement topSection2 = driver.findElement(By.cssSelector("div.w3-pale-red"));
        System.out.println(topSection2.getCssValue("background-color"));

        try {
            assertEquals("rgba(255, 221, 221, 1)", topSection1.getCssValue("background-color"));
        }
        catch (AssertionError e) {
            System.out.println("Background color should be rgba(255, 221, 221, 1)");
            e.printStackTrace();
        }

        try {
            assertEquals(topSection2.getCssValue("background-color"), "rgba(255, 221, 221, 1)");
        }
        catch (AssertionError e) {
            System.out.println("Background color should be rgba(255, 221, 221, 1)");
            e.printStackTrace();
        }

    }
}
