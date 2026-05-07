package com.shoppractice.tests;

import com.shoppractice.pages.DashboardPage;
import com.shoppractice.pages.LoginPage;
import com.shoppractice.pages.OrderDetailsPage;
import com.shoppractice.pages.OrderHistoryPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class OrderHistoryTest extends BaseTest {
    
    @Test
    public void verifyOrderHistoryDisplay() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        OrderHistoryPage ordersPage = dashboardPage.goToOrdersPage();
        Assert.assertTrue(ordersPage.verifyOrderDisplay("ZARA COAT 3"));
    }

    @Test
    public void verifyOrderDetails() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        OrderHistoryPage ordersPage = dashboardPage.goToOrdersPage();
        OrderDetailsPage detailsPage = ordersPage.viewOrderDetails("ZARA COAT 3");
        Assert.assertEquals(detailsPage.getProductName(), "ZARA COAT 3");
    }
}
