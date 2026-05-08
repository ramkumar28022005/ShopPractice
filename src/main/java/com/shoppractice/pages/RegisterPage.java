package com.shoppractice.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class RegisterPage extends BasePage {

    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement emailInput;

    @FindBy(id = "userMobile")
    private WebElement mobileInput;

    @FindBy(id = "userPassword")
    private WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    private WebElement confirmPasswordInput;

    @FindBy(css = "input[type='checkbox']")
    private WebElement termsCheckbox;

    @FindBy(css = "select[formcontrolname='occupation']")
    private WebElement occupationDropdown;

    @FindBy(xpath = "//input[@value='Male']")
    private WebElement genderRadio;

    @FindBy(css = "[type='submit']")
    private WebElement registerButton;

    @FindBy(css = ".invalid-feedback, .invalid-feedback div")
    private List<WebElement> validationErrors;
    
    @FindBy(css = "[class*='flyInOut']")
    private WebElement toastMessage;

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void registerUser(String firstName, String lastName, String email, String mobile, String password, String confirmPassword) {
        type(firstNameInput, firstName);
        type(lastNameInput, lastName);
        type(emailInput, email);
        type(mobileInput, mobile);
        type(passwordInput, password);
        type(confirmPasswordInput, confirmPassword);
        try {
            click(genderRadio);
            org.openqa.selenium.support.ui.Select occupation = new org.openqa.selenium.support.ui.Select(occupationDropdown);
            occupation.selectByVisibleText("Engineer");
        } catch (Exception e) {}
        click(termsCheckbox);
        click(registerButton);
    }

    public String getValidationError() {
        for (WebElement error : validationErrors) {
            try {
                if (error.isDisplayed()) {
                    return error.getText();
                }
            } catch (Exception e) {}
        }
        return "";
    }
    
    public String getToastMessage() {
        waitForElementToAppear(toastMessage);
        return toastMessage.getText();
    }
}
