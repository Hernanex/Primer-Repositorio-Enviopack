package com.enviopack.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class WaitHelper {
    WebDriver driver;
    WebDriverWait wait;

    
    public WaitHelper(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    // Método que espera a que un elemento sea visible en la página
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // Método que espera a que un elemento esté listo para recibir clics
    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Método que espera a que un elemento esté presente en el DOM (no necesariamente visible)
    public WebElement waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    // Método que espera a que un elemento sea visible y clickeable
    public WebElement waitForVisibilityAndClickability(By locator) {
        WebElement element = waitForVisibility(locator);
        waitForClickability(locator);
        return element;
    }
    
    // Esperar unos segundos antes de obtener la URL
    public static String getCurrentUrlWithWait(WebDriver driver, int seconds) {
        try {
            
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return driver.getCurrentUrl();
    }
}


