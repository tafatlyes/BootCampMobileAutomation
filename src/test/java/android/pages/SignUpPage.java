package android.pages;

import com.pnt.mobileautomation.ExtentTestManager;
import com.pnt.mobileautomation.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SignUpPage extends TestBase {

    @FindBy(xpath = "//android.widget.EditText[@package='com.twitter.android.lite']")
    private WebElement nameField;

    @FindBy(xpath = "(//android.widget.EditText[@package='com.twitter.android.lite'])[2]")
    private WebElement phoneTab;

    @FindBy(xpath = "//android.widget.Spinner[@package='com.twitter.android.lite']")
    private WebElement dateOfBirthTab;

    @FindBy(xpath = "//android.widget.LinearLayout[@package='com.twitter.android.lite']")
    private WebElement calendarTab;

    @FindBy(xpath = "//android.widget.Button[@text='SET']")
    private WebElement setButton;


    public void provideName() {
        nameField.sendKeys("John");
        ExtentTestManager.log("User Name is entered");
        sleepFor(3);
        getDriver().hideKeyboard();
        sleepFor(6);
    }


    public void providePhoneNumber() {
        phoneTab.sendKeys("8608715482");
        ExtentTestManager.log("Phone number procided");
    }

    public void clickOnDateOfBirthTab() {
        dateOfBirthTab.click();
        ExtentTestManager.log("date of birth tab clicked");
        sleepFor(3);
        Assert.assertTrue(calendarTab.isDisplayed());
    }

    public void hideKeyBoard() {
        getDriver().hideKeyboard();
        sleepFor(5);
    }

    public void clickOnSetButton() {
        setButton.click();
        ExtentTestManager.log("set button is clicked");
    }
}
