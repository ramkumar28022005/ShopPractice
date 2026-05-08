package com.shoppractice.tests;

import com.shoppractice.pages.CartPage;
import com.shoppractice.pages.CheckoutPage;
import com.shoppractice.pages.DashboardPage;
import com.shoppractice.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartTest extends BaseTest {

    @Test
    public void verifyCartProducts() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        String priceOnDashboard = dashboardPage.getProductPrice("ZARA COAT 3");
        int count1 = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.waitForCartBadgeToUpdate(count1 + 1);
        CartPage cartPage = dashboardPage.goToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay("ZARA COAT 3"), "Product name not found in cart");
        String priceOnCart = cartPage.getProductPrice("ZARA COAT 3");
        Assert.assertNotNull(priceOnCart, "Could not fetch price from cart");
        Assert.assertTrue(priceOnCart.contains(priceOnDashboard) || priceOnDashboard.contains(priceOnCart), "Price mismatch");
    }

    @Test
    public void verifyTotalCartPrice() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        int count2 = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.waitForCartBadgeToUpdate(count2 + 1);
        int count3 = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ADIDAS ORIGINAL");
        dashboardPage.waitForCartBadgeToUpdate(count3 + 1);
        CartPage cartPage = dashboardPage.goToCartPage();
        String total = cartPage.getTotalCartPrice();
        Assert.assertNotNull(total, "Total price is not displayed");
        Assert.assertFalse(total.isEmpty(), "Total price is empty");
    }

    @Test
    public void verifyDeleteProductFromCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        int count4 = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.waitForCartBadgeToUpdate(count4 + 1);
        int count5 = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ADIDAS ORIGINAL");
        dashboardPage.waitForCartBadgeToUpdate(count5 + 1);
        CartPage cartPage = dashboardPage.goToCartPage();
        cartPage.deleteProduct("ZARA COAT 3");
        Assert.assertFalse(cartPage.verifyProductDisplay("ZARA COAT 3"), "Product was not deleted from cart");
    }

    @Test
    public void verifyProceedToCheckout() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        int count = dashboardPage.getCartBadgeCount();
        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.waitForCartBadgeToUpdate(count + 1);
        CartPage cartPage = dashboardPage.goToCartPage();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        checkoutPage.submitOrder();
        Assert.assertTrue(checkoutPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}
