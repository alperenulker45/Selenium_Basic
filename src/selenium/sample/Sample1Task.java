package selenium.sample;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample1Task {
    static String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;

    @Test
    public void goToHomepage() throws Exception {
//        TODO:
//         define driver
//         go to https://kristinek.github.io/site/index2.html
//         get title of page
//         get URL of current page
//         close browser

        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        WebDriver driver = new ChromeDriver();

        driver.get("https://kristinek.github.io/site/index2.html");
        System.out.println("Page Title :" + driver.getTitle());
        driver.manage().window().maximize();
        Thread.sleep(2000);
        System.out.println("CurrentUrl = " + driver.getCurrentUrl());
        driver.quit();
    }
}
