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
        waitForElementToAppear(driver.findElement(toastMessage));
        waitForElementToDisappear(driver.findElement(spinner));
    }

    public CartPage goToCartPage() {
        click(cartButton);
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
}
