package com.shoppractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailsPage extends BasePage {

    @FindBy(css = ".col-text")
    private WebElement productNameLabel;

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productNameLabel);
    }
}
