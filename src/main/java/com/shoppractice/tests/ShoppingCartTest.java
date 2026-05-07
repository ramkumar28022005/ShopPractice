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
        dashboardPage.addProductToCart("ZARA COAT 3");
        CartPage cartPage = dashboardPage.goToCartPage();
        Assert.assertTrue(cartPage.verifyProductDisplay("ZARA COAT 3"));
    }

    @Test
    public void verifyDeleteProductFromCart() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        dashboardPage.addProductToCart("ZARA COAT 3");
        dashboardPage.addProductToCart("ADIDAS ORIGINAL");
        CartPage cartPage = dashboardPage.goToCartPage();
        cartPage.deleteProduct("ZARA COAT 3");
        Assert.assertFalse(cartPage.verifyProductDisplay("ZARA COAT 3"), "Product was not deleted from cart");
    }

    @Test
    public void verifyProceedToCheckout() {
        LoginPage loginPage = new LoginPage(getDriver());
        DashboardPage dashboardPage = loginPage.login("anshika@gmail.com", "Iamking@000");
        dashboardPage.addProductToCart("ZARA COAT 3");
        CartPage cartPage = dashboardPage.goToCartPage();
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        checkoutPage.submitOrder();
        Assert.assertTrue(checkoutPage.getConfirmationMessage().equalsIgnoreCase("THANKYOU FOR THE ORDER."));
    }
}
