package selenium.tasks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import selenium.pages.FeedBackPage;
import selenium.pages.FeedBackPageSubmitted;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Task2 {
    static WebDriver driver;
    static FeedBackPage feedBackPage;
    static FeedBackPageSubmitted feedBackPageSubmitted;
    WebDriverWait wait;


    @Before
    public void openPage() {
        String libWithDriversLocation = System.getProperty("user.dir") + File.separator + "lib" + File.separator;
        System.setProperty("webdriver.chrome.driver", libWithDriversLocation + "chromedriver" + new selenium.ChangeToFileExtension().extension());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://kristinek.github.io/site/tasks/provide_feedback");
        driver.manage().window().maximize();
        feedBackPage = PageFactory.initElements(driver, FeedBackPage.class);
        feedBackPageSubmitted = PageFactory.initElements(driver, FeedBackPageSubmitted.class);
    }

    @After
    public void closeBrowser() {
        driver.close();
    }

    @Test
    public void initialFeedbackPage() throws Exception {
//         TODO:
//         check that all field are empty and no tick are clicked
//         "Don't know" is selected in "Genre"
//         "Choose your option" in "How do you like us?"
//         check that the button send is blue with white letters
        feedBackPage.checkAllFieldsEmptyAndNotSelected();
        feedBackPage.checkSendBtnColor();
    }

    @Test
    public void emptyFeedbackPage() throws Exception {
//         TODO:
//         click "Send" without entering any data
//         check fields are empty or null
//         check button colors
//         (green with white letter and red with white letters)

        feedBackPage.clickOnSendBtn();
        feedBackPageSubmitted.checkAllFieldsEmpty();
        feedBackPageSubmitted.checkButtonColors();

    }

    @Test
    public void notEmptyFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form, click "Send"
//         check fields are filled correctly
//         check button colors
//         (green with white letter and red with white letters)
        feedBackPage.enterName("Alperen Ulker");
        feedBackPage.enterAge(32);
        feedBackPage.selectLanguageCheckBox("English");
        feedBackPage.selectGender("Male");
        feedBackPage.selectHowYouLikeUs("Good");
        feedBackPage.fillTheCommentArea("Everything is fine");
        feedBackPage.clickOnSendBtn();

        //check fields are filled correctly
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourName(), "Alperen Ulker");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourAge(), 32);
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourLanguage(), "English");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourGender(), "Male");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourOptionOfUs(), "Good");
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getYourComment(), "Everything is fine");

        feedBackPageSubmitted.checkButtonColors();

    }

    @Test
    public void yesOnWithNameFeedbackPage() throws Exception {
//         TODO:
//         enter only name
//         click "Send"
//         click "Yes"
//         check message text: "Thank you, NAME, for your feedback!"
//         color of text is white with green on the background
        feedBackPage.enterName("Alperen Ulker");
        feedBackPage.clickOnSendBtn();
        feedBackPageSubmitted.clickOnYesBtn();

        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getSubmitMessage(),
                "Thank you, Alperen Ulker, for your feedback!");

        feedBackPageSubmitted.checkSubmitMessageColor();

    }

    @Test
    public void yesOnWithoutNameFeedbackPage() throws Exception {
//         TODO:
//         click "Send" (without entering anything
//         click "Yes"
//         check message text: "Thank you for your feedback!"
//         color of text is white with green on the background

        feedBackPage.clickOnSendBtn();
        feedBackPageSubmitted.clickOnYesBtn();
        feedBackPageSubmitted.verifyContainsText(driver, feedBackPageSubmitted.getSubmitMessage(), "Thank you for your feedback!");

        feedBackPageSubmitted.checkSubmitMessageColor();
    }

    @Test
    public void noOnFeedbackPage() throws Exception {
//         TODO:
//         fill the whole form
//         click "Send"
//         click "No"
//         check fields are filled correctly
        feedBackPage.enterName("Alperen Ulker");
        feedBackPage.enterAge(32);
        feedBackPage.selectLanguageCheckBox("English");
        feedBackPage.selectGender("Male");
        feedBackPage.selectHowYouLikeUs("Good");
        feedBackPage.fillTheCommentArea("Everything is fine");
        feedBackPage.clickOnSendBtn();

        wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(feedBackPageSubmitted.getNoBtn()));
        feedBackPageSubmitted.clickOnNoBtn();

        feedBackPage.checkFormAfterNoBtn();
        feedBackPage.checkIsSelected(feedBackPage.getCheckBoxEnglish());
        feedBackPage.checkIsSelected(feedBackPage.getRadioBtnGender().get(0));
        feedBackPage.checkIsSelected(feedBackPage.getLikeUsDDoptions().get(1));

    }
}
