package com.shoppractice.tests;

import com.shoppractice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FormValidationTest extends BaseTest {

    @Test
    public void verifyLoginWithEmptyFields() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.submitForm();
        String validationError = loginPage.getValidationError();
        Assert.assertTrue(validationError.contains("Email is required") || validationError.contains("*Email is required"), "Validation error message not found");
    }

    @Test
    public void verifyDuplicateEmailRegistration() {
        LoginPage loginPage = new LoginPage(getDriver());
        com.shoppractice.pages.RegisterPage registerPage = loginPage.goToRegisterPage();
        registerPage.registerUser("Test", "User", "anshika@gmail.com", "9876543210", "Iamking@000", "Iamking@000");
        String toastMessage = registerPage.getToastMessage();
        Assert.assertTrue(toastMessage.contains("already"), "Duplicate email validation message not found");
    }

    @Test
    public void verifyShortPasswordValidation() {
        LoginPage loginPage = new LoginPage(getDriver());
        com.shoppractice.pages.RegisterPage registerPage = loginPage.goToRegisterPage();
        registerPage.registerUser("Test", "User", "newuser123@gmail.com", "9876543210", "short", "short");
        String validationError = registerPage.getValidationError();
        Assert.assertTrue(validationError.contains("8 Characters"), "Password length validation message not found");
    }
}
