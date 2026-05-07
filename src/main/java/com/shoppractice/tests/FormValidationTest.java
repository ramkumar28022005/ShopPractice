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
}
