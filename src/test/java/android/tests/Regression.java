package android.tests;

import android.pages.HomePage;
import android.pages.LogInPage;
import android.pages.SignUpPage;
import com.pnt.mobileautomation.TestBase;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Regression extends TestBase {
    HomePage homePage;
    LogInPage logInPage;
    SignUpPage signUpPage;

    @BeforeMethod
    public void setUpObject() {
        homePage = PageFactory.initElements(getDriver(), HomePage.class);
        logInPage = PageFactory.initElements(getDriver(), LogInPage.class);
        signUpPage = PageFactory.initElements(getDriver(), SignUpPage.class);
    }


    @Test
    public void userBeingAbleToClickOnSIgnUpButton() {
        homePage.clickOnSignUpButton();
    }

    @Test
    public void userBeingAbleToProvideNameInSignUpPage() {
        homePage.clickOnSignUpButton();
        signUpPage.provideName();
    }

    @Test
    public void userBeingAbleToProvidePhoneNumberInSignUpPage() {
        homePage.clickOnSignUpButton();
        signUpPage.providePhoneNumber();
    }

    @Test
    public void userBeingAbleToSIgnUp() {
        homePage.clickOnSignUpButton();
        signUpPage.provideName();
        signUpPage.providePhoneNumber();
    }

    @Test
    public void userBeingAbleToClickOnLogInButton() {
        homePage.clickOnLogInButton();
    }

    @Test
    public void userBeingAbleToProvideEmailInLogInPage() {
        homePage.clickOnLogInButton();
        logInPage.provideEmail();
    }

    @Test
    public void userBeingAbleToProvidePasswordInLogInPage() {
        homePage.clickOnLogInButton();
        logInPage.providePassword();
    }


    @Test
    public void userNotBeingAbleToSignInWithInvalidCredentials() {
        homePage.clickOnLogInButton();
        logInPage.provideEmail();
        logInPage.providePassword();
        logInPage.clickOnLogInButton();
    }

    @Test
    public void userBeingAbleToClickOnDateOfBirthTab() {
        homePage.clickOnSignUpButton();
        signUpPage.hideKeyBoard();
        signUpPage.clickOnDateOfBirthTab();
    }

    @Test
    public void userBeingAbleToSetADateOfBirth() {
        homePage.clickOnSignUpButton();
        signUpPage.hideKeyBoard();
        signUpPage.clickOnDateOfBirthTab();
        signUpPage.clickOnSetButton();
    }


}
