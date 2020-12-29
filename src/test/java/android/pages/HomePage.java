package android.pages;

import com.pnt.mobileautomation.ExtentTestManager;
import com.pnt.mobileautomation.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class HomePage extends TestBase {

    @FindBy(xpath = "//android.widget.Button[@text='Sign up']")
    private WebElement signUpButton;

    @FindBy(xpath = "//android.widget.Button[text='Use email instead']")
    private WebElement useEmailInstead;

    @FindBy(xpath = "(//android.widget.EditText[@package='com.twitter.android.lite'])[2]")
    private WebElement emailField;

    @FindBy(xpath = "//android.widget.Image[@text='Twitter']")
    private WebElement twitterSign;

    @FindBy(xpath = "//android.widget.Button[@text='Not now']")
    private WebElement notNowButton;

    @FindBy(xpath = "(//android.widget.EditText[@package='com.twitter.android.lite'])[2]")
    private WebElement phoneTab;

    @FindBy(xpath = "//android.widget.Button[@text='Log in']")
    private WebElement logInButton;


    public void clickOnSignUpButton() {
        signUpButton.click();
        sleepFor(5);
        ExtentTestManager.log("sign up button is clicked");
    }

    public void clickOnLogInButton() {
        logInButton.click();
        sleepFor(5);
        ExtentTestManager.log("Log In button is clicked");
        Assert.assertTrue(phoneTab.isDisplayed());
    }


}
