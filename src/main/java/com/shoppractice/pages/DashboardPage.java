package com.shoppractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DashboardPage extends BasePage {

    @FindBy(css = ".mb-3")
    private List<WebElement> products;

    @FindBy(css = "[routerlink*='cart']")
    private WebElement cartButton;

    @FindBy(css = ".fa-sign-out")
    private WebElement signOutButton;
    
    @FindBy(css = "[routerlink*='myorders']")
    private WebElement ordersButton;

    By productsBy = By.cssSelector(".mb-3");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");
    By spinner = By.cssSelector(".ng-animating");

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getProductList() {
        try {
            waitForElementToAppear(driver.findElement(productsBy));
        } catch (Exception e) {
             // In case there are no products
        }
        return products;
    }

    public WebElement getProductByName(String productName) {
        return getProductList().stream().filter(product ->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
    }

    public void addProductToCart(String productName) {
        WebElement prod = getProductByName(productName);
        prod.findElement(addToCart).click();
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(spinner));
        wait.until(org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated(toastMessage));
    }

    public CartPage goToCartPage() {
        try {
            click(cartButton);
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", cartButton);
        }
        return new CartPage(driver);
    }
    
    public OrderHistoryPage goToOrdersPage() {
        click(ordersButton);
        return new OrderHistoryPage(driver);
    }

    public LoginPage logout() {
        click(signOutButton);
        return new LoginPage(driver);
    }

    public int getCartBadgeCount() {
        try {
            String countText = cartButton.getText();
            String numberOnly = countText.replaceAll("[^0-9]", "");
            return numberOnly.isEmpty() ? 0 : Integer.parseInt(numberOnly);
        } catch (Exception e) {
            return 0;
        }
    }

    public void waitForCartBadgeToUpdate(int expectedCount) {
        wait.until(driver -> getCartBadgeCount() >= expectedCount);
    }

    public String getProductPrice(String productName) {
        WebElement prod = getProductByName(productName);
        if (prod != null) {
            String text = prod.getText();
            String[] parts = text.split("\\$");
            if (parts.length > 1) {
                return parts[1].trim().split("\\n")[0]; // Extract price value
            }
        }
        return null;
    }
}
