package com.shoppractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class OrderDetailsPage extends BasePage {

    @FindBy(css = ".col-text")
    private List<WebElement> colTexts;

    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        for(WebElement el : colTexts) {
            String text = el.getAttribute("innerText");
            if(text != null) {
                text = text.trim();
                if(!text.matches("^[a-fA-F0-9]{24}$") && !text.isEmpty() && !text.equalsIgnoreCase("order summary")) {
                    return text;
                }
            }
        }
        return "";
    }
}
