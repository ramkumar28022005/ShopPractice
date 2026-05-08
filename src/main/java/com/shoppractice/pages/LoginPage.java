package com.shoppractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "login")
    private WebElement loginButton;

    @FindBy(css = "[class*='flyInOut']")
    private WebElement errorMessage;

    @FindBy(css = ".invalid-feedback")
    private WebElement validationError;

    @FindBy(css = ".text-reset")
    private WebElement registerLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String email, String password) {
        type(emailInput, email);
        type(passwordInput, password);
        click(loginButton);
        return new DashboardPage(driver);
    }

    public String getErrorMessage() {
        waitForElementToAppear(errorMessage);
        return errorMessage.getText();
    }
    
    public String getValidationError() {
        waitForElementToAppear(validationError);
        return validationError.getText();
    }

    public void submitForm() {
        click(loginButton);
    }

    public RegisterPage goToRegisterPage() {
        click(registerLink);
        return new RegisterPage(driver);
    }
}
