package com.shoppractice.tests;

import com.shoppractice.pages.CartPage;
import com.shoppractice.pages.DashboardPage;
import com.shoppractice.pages.LoginPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductListingTest extends BaseTest {

    @Test
    public void verifyDashboardLoadsProducts() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        Assert.assertTrue(dashboardPage.getProductList().size() > 0, "Dashboard did not load products");
    }

    @Test
    public void verifyProductCardDetails() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        WebElement product = dashboardPage.getProductByName("ZARA COAT 3");
        Assert.assertNotNull(product, "Product card not found");
        Assert.assertTrue(product.getText().contains("$"), "Product card does not show price");
    }

    @Test
    public void verifyAddToCartAndBadgeUpdate() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        int initialCount = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.waitForCartBadgeToUpdate(initialCount + 1);
        int newCount = dashboardPage.getCartBadgeCount();
        Assert.assertTrue(newCount > initialCount, "Cart badge count did not update");
        CartPage cartPage = dashboardPage.goToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay("ZARA COAT 3"), "Product not added to cart");
    }
    
    @Test
    public void verifyMultipleProductsCanBeAdded() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        int count1 = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.waitForCartBadgeToUpdate(count1 + 1);
        int count2 = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ADIDAS ORIGINAL");
        dashboardPage.waitForCartBadgeToUpdate(count2 + 1);
        CartPage cartPage = dashboardPage.goToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay("ZARA COAT 3"));
        Assert.assertTrue(cartPage.verifyProductDisplay("ADIDAS ORIGINAL"));
    }
}
