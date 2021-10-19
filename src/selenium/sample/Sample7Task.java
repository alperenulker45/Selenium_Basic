package selenium.sample;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.*;

public class Sample7Task {
    WebDriver driver;
    String base_url = "https://kristinek.github.io/site/examples/actions";

    // method which is being run before each test
    @Before
    public void startingTests() throws Exception {
        // from Sample 1:
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        // declaration above:
        driver = new ChromeDriver();
        //open page:
        driver.get(base_url);
    }

    // method which is being run after each test
    @After
    public void endingTests() throws Exception {
        driver.close();
    }

    @Test
    public void selectCheckBox() throws Exception {
//         TODO:
//        check that none of the checkboxes are ticked
//        tick  "Option 2"
//        check that "Option 1" and "Option 3" are not ticked, but "Option 2" is ticked
//        tick  "Option 3"
//        click result
//        check that text 'You selected value(s): Option 2, Option 3' is being displayed

        List<WebElement> checkBoxList = driver.findElements(By.cssSelector(".w3-check[type='checkbox']"));
        try {
            for (WebElement e : checkBoxList) {
                assertFalse(e.isSelected());
            }
        } catch (AssertionError e) {
            System.out.println("Checkbox should not be selected");
            e.printStackTrace();
        }

        checkBoxList.get(1).click(); //click option 2
        assertFalse(checkBoxList.get(0).isSelected());
        assertFalse(checkBoxList.get(2).isSelected());

        WebElement option3 = driver.findElement(By.cssSelector(".w3-check[value='Option 3'][type='checkbox']"));
        option3.click();

        WebElement resultChckBoxBtn = driver.findElement(By.id("result_button_checkbox"));
        resultChckBoxBtn.click();

        WebElement displayedTxt = driver.findElement(By.xpath("//p[contains(text(), 'selected')]"));
        String expectedTxt = "You selected value(s): Option 2, Option 3";

        assertEquals(expectedTxt, displayedTxt.getText());

    }


    @Test
    public void selectRadioButton() throws Exception {
//         TODO:
//        check that none of the radio are selected
//        select  "Option 3"
//        check that "Option 1" and "Option 2' are not select, but "Option 3" is selected
//        select  "Option 1"
//        check that "Option 2" and "Option 3' are not select, but "Option 1" is selected
//        click result
//        check that 'You selected option: Option 1' text is being displayed

        List<WebElement> radioBtns = driver.findElements(By.cssSelector(".w3-check[type='radio']"));
        try {
            for (WebElement e : radioBtns) {
                assertFalse(e.isSelected());
            }
        } catch (AssertionError e) {
            System.out.println("Radio button should not be selected");
            e.printStackTrace();
        }

        WebElement option3 = driver.findElement(By.cssSelector("[value='Option 3'][type='radio']"));
        option3.click();

        assertFalse(radioBtns.get(0).isSelected()); // checking is option 1 selected
        assertFalse(radioBtns.get(1).isSelected()); // checking is option 2 selected

        radioBtns.get(0).click(); //option 1 clicked

        assertFalse(radioBtns.get(1).isSelected()); // checking is option 2 selected
        assertFalse(option3.isSelected()); // checking is option 3 selected

        WebElement radioResultBtn = driver.findElement(By.id("result_button_ratio"));
        radioResultBtn.click();

        WebElement radioDisplayedTxt = driver.findElement(By.id("result_radio"));
        String expectedTxt = "You selected option: Option 1";

        assertEquals(expectedTxt, radioDisplayedTxt.getText());

    }

    @Test
    public void selectOption() throws Exception {
//        select "Option 3" in Select
//        check that selected option is "Option 3"
//        select "Option 2" in Select
//        check that selected option is "Option 2"
//        click result
//        check that 'You selected option: Option 2' text is being displayed

        Select dropdown = new Select(driver.findElement(By.id("vfb-12")));
        dropdown.selectByVisibleText("Option 3");
        assertEquals("Option 3", dropdown.getFirstSelectedOption().getText());

        dropdown.selectByIndex(2);
        assertEquals("Option 2", dropdown.getFirstSelectedOption().getText());

        WebElement resultBtn = driver.findElement(By.cssSelector("#result_button_select"));
        resultBtn.click();

        WebElement resultMsg = driver.findElement(By.cssSelector("#result_select"));
        String expectedMsg = "You selected option: Option 2";

        assertEquals(expectedMsg, resultMsg.getText());

    }

    @Test
    public void chooseDateViaCalendarBonus() throws Exception {
//         TODO:
//        enter date '4 of July 2007' using calendar widget
//        check that correct date is added
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.clear();
        assertEquals("", dateBox.getAttribute("value"));

        dateBox.click();

        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
//    go back 10 month in calendar on page
        for (int i = 0; i < 171; i++) {
            Thread.sleep(15);
            dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
        driver.findElement(By.id("result_button_date")).click(); //click on result button
        Thread.sleep(2000);

        WebElement displayedTxt = driver.findElement(By.id("result_date"));

        String expectedTxt = "You entered date: 07/04/2007";

        try {
            assertEquals(expectedTxt, displayedTxt.getText());
        } catch (AssertionError e) {
            System.out.println("Result should be : \"You entered date: 07/04/2007\"");
            e.printStackTrace();
        }
    }

    @Test
    public void chooseDateViaTextBoxBonus() throws Exception {
//         TODO:
//        enter date '2 of May 1959' using text
//        check that correct date is added

        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.clear();
        dateBox.sendKeys("05/02/1959");
        Thread.sleep(2000);

        assertEquals("05/02/1959", dateBox.getAttribute("value"));
    }

    @Test
    public void CalendarTestBonusDifferentWay() throws InterruptedException {
        WebElement dateBox = driver.findElement(By.id("vfb-8"));
        dateBox.click();

        WebElement dateWidget = driver.findElement(By.id("ui-datepicker-div"));
        WebElement year;
        WebElement month;

        boolean is2007July;
        while (is2007July=true) {
            year = driver.findElement(By.cssSelector(".ui-datepicker-year"));
            month = driver.findElement(By.cssSelector(".ui-datepicker-month"));
            if (year.getText().equals(String.valueOf(2007)) && month.getText().equals("July")) {
                break;
            }
            else dateWidget.findElement(By.className("ui-datepicker-prev")).click();
        }

        dateWidget.findElement(By.xpath("//a[text()='4']")).click();
        driver.findElement(By.id("result_button_date")).click(); //click on result button
        Thread.sleep(1000);

        WebElement displayedTxt = driver.findElement(By.id("result_date"));

        String expectedTxt = "You entered date: 07/04/2007";

        try {
            assertEquals(expectedTxt, displayedTxt.getText());
        } catch (AssertionError e) {
            System.out.println("Result should be : \"You entered date: 07/04/2007\"");
            e.printStackTrace();
        }
    }
}
