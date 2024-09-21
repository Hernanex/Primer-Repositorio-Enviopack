package com.enviopack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.enviopack.helpers.WaitHelper;

public class LoginPage {
    WebDriver driver;
    WaitHelper waitHelper;

    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.waitHelper = new WaitHelper(driver);;
    }
    
    // Localizadores
    By emailInput = By.name("email");
    By passwordInput = By.name("password");
    By loginBtn = By.xpath("//div[@class='button__wrapper button__wrapper--contained button__wrapper--contained-primary']");

    // Metodos 
    public void enterEmail(String email) {
        waitHelper.waitForVisibility(emailInput).sendKeys(email);
    }

    public void enterPassword(String password) {
        waitHelper.waitForVisibility(passwordInput).sendKeys(password);
    }

    public void clickLogin() {
        waitHelper.waitForClickability(loginBtn).click();
    }
}