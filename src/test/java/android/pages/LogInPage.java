package android.pages;

import com.pnt.mobileautomation.ExtentTestManager;
import com.pnt.mobileautomation.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class LogInPage extends TestBase {

    @FindBy(xpath = "//android.widget.EditText[@package='com.twitter.android.lite']")
    private WebElement emailField;

    @FindBy(xpath = "(//android.widget.EditText[@package='com.twitter.android.lite'])[2]")
    private WebElement passwordField;

    @FindBy(xpath = "//android.widget.Button[@text='Log in']")
    private WebElement logInButton;

    @FindBy(xpath = "//android.view.View[@text='The email and password you entered did not match our records. Please double-check and try again.']")
    private WebElement errorMessage;


    public void provideEmail() {
        emailField.sendKeys("lyes@gmail.com");
        ExtentTestManager.log("email is provided");
    }

    public void providePassword() {
        passwordField.sendKeys("012546");
        ExtentTestManager.log("password is provided");
    }

    public void clickOnLogInButton() {
        getDriver().hideKeyboard();
        sleepFor(6);
        logInButton.click();
        ExtentTestManager.log("log in button clicked");
        Assert.assertTrue(errorMessage.isDisplayed());
    }
}
