package com.shoppractice.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(css = "[placeholder='Select Country']")
    private WebElement countryInput;

    @FindBy(css = ".ta-item:nth-of-type(2)")
    private WebElement selectCountry;

    @FindBy(css = ".action__submit")
    private WebElement submitButton;

    @FindBy(css = ".hero-primary")
    private WebElement confirmationMessage;

    By results = By.cssSelector(".ta-results");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectCountry(String countryName) {
        type(countryInput, countryName);
        waitForElementToAppear(driver.findElement(results));
        click(selectCountry);
    }

    public void submitOrder() {
        click(submitButton);
    }

    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }
}
