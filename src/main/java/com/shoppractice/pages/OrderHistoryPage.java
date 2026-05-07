package com.shoppractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class OrderHistoryPage extends BasePage {

    @FindBy(css = "tr td:nth-child(3)")
    private List<WebElement> productNames;

    @FindBy(css = ".btn-primary")
    private List<WebElement> viewOrderButtons;

    public OrderHistoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyOrderDisplay(String productName) {
        try {
            waitForElementsToAppear(productNames);
        } catch (Exception e) {}
        return productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
    }

    public OrderDetailsPage viewOrderDetails(String productName) {
        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equalsIgnoreCase(productName)) {
                click(viewOrderButtons.get(i));
                break;
            }
        }
        return new OrderDetailsPage(driver);
    }
}
