package com.enviopack.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.enviopack.helpers.WaitHelper;

public class HomePage {
    private WaitHelper waitHelper;

    // Localizadores
    private By accessAccountButton = By.xpath("//span[normalize-space()='Acceder a una cuenta']");
    private By sellerInput = By.name("empresa");
    private By firstSellerItem = By.id("react-autowhatever-1--item-0");
    
    // Constructor
    public HomePage(WebDriver driver) {
        this.waitHelper = new WaitHelper(driver); 
    }
    
    // Métodos encadenados
    public HomePage clickAccessAccountButton() {
        waitHelper.waitForClickability(accessAccountButton).click();
        return this;  // Devuelve la instancia actual para permitir el encadenamiento
    }
    
    public HomePage enterSeller(String seller) {
        waitHelper.waitForVisibility(sellerInput).sendKeys(seller);
        return this;  // Devuelve la instancia actual para permitir el encadenamiento
    }
    
    public HomePage selectFirstSeller() {
        waitHelper.waitForClickability(firstSellerItem).click();
        return this;  // Devuelve la instancia actual para permitir el encadenamiento
    }
}

