package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;

public class Sample6Task {
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
        driver.get("https://kristinek.github.io/site/examples/locators");
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void findElementByXPath() throws Exception {
//         TODO:
//        2 ways to find text: "Heading 2 text":
        WebElement heading2Text = driver.findElement(By.xpath("//h2[@id='heading_2']"));
        driver.findElement(By.xpath("//h2[text()='Heading 2 text']"));//second way

        assertEquals("Heading 2 text", heading2Text.getText());

//        1-2 ways to find text: "Test Text 1"
        WebElement testTxt1 = driver.findElement(By.xpath("//div[@id='test1']//p[@class='test']"));
        driver.findElement(By.xpath("//p[text()='Test Text 1']"));//second way

        assertEquals("Test Text 1", testTxt1.getText());

//        1-2 ways to find text: "Test Text 2"
        WebElement testTxt2 = driver.findElement(By.xpath("//div[@id='test1']//p[@class='twoTest']"));
        driver.findElement(By.xpath("//p[text()='Test Text 2']"));//second way

        assertEquals("Test Text 2", testTxt2.getText());

//        1-2 ways to find text: "Test Text 3"
        WebElement testTxt3 = driver.findElement(By.xpath("//div[@id='test3']//p[@class='test']"));
        driver.findElement(By.xpath("//p[text()='Test Text 3']"));//second way

        assertEquals("Test Text 3", testTxt3.getText());

//
//        1-2 ways to find text: "Test Text 4"
        WebElement testTxt4 = driver.findElement(By.xpath("//div[@id='test3']//p[@class='test'][2]"));
        driver.findElement(By.xpath("//p[text()='Test Text 4']"));//second way

        assertEquals("Test Text 4", testTxt4.getText());

//        1-2 ways to find text: "Test Text 5"
       WebElement testTxt5 = driver.findElement(By.xpath("//div[@id='test2']//p[@class='Test']"));
        driver.findElement(By.xpath("//p[text()='Test Text 5']"));//second way

        assertEquals("Test Text 5", testTxt5.getText());

//        1-2 ways to find text: "This is also a button"
        WebElement randomBtn2 = driver.findElement(By.xpath("//input[@name='randomButton2']"));
        driver.findElement((By.xpath("//input[@type='button'][@id='buttonId']")));//second way

        assertEquals("This is also a button", randomBtn2.getAttribute("value"));
    }

    @Test
    public void findElementByCssName() throws Exception {
//         TODO:
//        1-2 ways to find text: "Heading 2 text"
        WebElement heading2Txt = driver.findElement(By.cssSelector("#heading_2"));
        driver.findElement(By.cssSelector("h2[id='heading_2']"));//second way

        assertEquals("Heading 2 text", heading2Txt.getText());

//        1-2 ways to find text: "Test Text 1"
        WebElement testTxt1 = driver.findElement(By.cssSelector("#test1>p"));
        driver.findElement(By.cssSelector("#test1>p.test"));//second way

        assertEquals("Test Text 1", testTxt1.getText());

//        1-2 ways to find text: "Test Text 2"
        WebElement testTxt2 = driver.findElement(By.cssSelector("#test1>p.twoTest"));
        assertEquals("Test Text 2", testTxt2.getText());

//        1-2 ways to find text: "Test Text 3"
        WebElement testTxt3 = driver.findElement(By.cssSelector("div#test3 > p:nth-child(1)"));
        driver.findElement(By.cssSelector("div#test3 > p.test"));//second way

        assertEquals("Test Text 3", testTxt3.getText());

//        1-2 ways to find text: "This is also a button"
        WebElement randomBtn2 = driver.findElement(By.cssSelector("input[name='randomButton2']"));
        driver.findElement(By.cssSelector("input#buttonID"));//second way

        assertEquals("This is also a button", randomBtn2.getAttribute("value"));
    }
}
