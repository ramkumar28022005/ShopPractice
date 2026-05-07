package com.shoppractice.tests;

import com.shoppractice.pages.DashboardPage;
import com.shoppractice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(dataProvider = "validLoginData")
    public void verifySuccessfulLogin(String email, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(email, password);
        Assert.assertTrue(dashboardPage.getProductList().size() > 0, "Products are not displayed, login failed");
    }

    @Test(dataProvider = "invalidLoginData")
    public void verifyLoginFailure(String email, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.login(email, password);
        String errorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(errorMessage, "Incorrect email or password.");
    }

    @Test(dataProvider = "validLoginData")
    public void verifyLogout(String email, String password) {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login(email, password);
        LoginPage newLoginPage = dashboardPage.logout();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("auth/login"), "Did not redirect to login page after logout");
    }

    @DataProvider
    public Object[][] validLoginData() {
        return new Object[][] {
            {"anshika@gmail.com", "Iamking@000"}
        };
    }

    @DataProvider
    public Object[][] invalidLoginData() {
        return new Object[][] {
            {"anshika@gmail.com", "wrongPassword"},
            {"wrongemail@gmail.com", "Iamking@000"}
        };
    }
}
