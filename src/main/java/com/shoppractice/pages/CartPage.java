package com.shoppractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends BasePage {

    @FindBy(css = ".cartSection h3")
    private List<WebElement> cartProducts;

    @FindBy(css = ".totalRow button")
    private WebElement checkoutButton;

    @FindBy(css = ".btn-danger")
    private List<WebElement> deleteButtons;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyProductDisplay(String productName) {
        try {
            waitForElementsToAppear(cartProducts);
        } catch (Exception e) {}
        return cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public void deleteProduct(String productName) {
        for (int i = 0; i < cartProducts.size(); i++) {
            if (cartProducts.get(i).getText().equalsIgnoreCase(productName)) {
                click(deleteButtons.get(i));
                break;
            }
        }
        try {
            Thread.sleep(1500); // Explicitly required in some dom structures after delete without spinner
        } catch(InterruptedException e) {}
    }
    
    public int getCartItemCount() {
        return cartProducts.size();
    }

    public CheckoutPage goToCheckout() {
        click(checkoutButton);
        return new CheckoutPage(driver);
    }

    public String getProductPrice(String productName) {
        try {
            return driver.findElement(org.openqa.selenium.By.xpath("//h3[text()='" + productName + "']/parent::div/parent::div/div[@class='prodTotal cartSection']/p")).getText().trim();
        } catch (Exception e) {
            // fallback
            return driver.findElement(org.openqa.selenium.By.xpath("//h3[text()='" + productName + "']/following-sibling::p")).getText().trim();
        }
    }

    public String getTotalCartPrice() {
        return driver.findElement(org.openqa.selenium.By.cssSelector(".totalRow .value")).getText().trim();
    }
}
