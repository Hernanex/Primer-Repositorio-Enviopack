package com.enviopack.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class ValidationHelper {

    // Verifica que un elemento esté visible
    public static void validateElementIsVisible(WebElement element) {
        Assert.assertTrue(element.isDisplayed(), "El elemento no está visible.");
    }

    // Verifica que la URL actual contiene un texto específico, con una espera antes
    public static void validateUrlContainsWithWait(WebDriver driver, String text, int waitInSeconds) {
        String currentUrl = WaitHelper.getCurrentUrlWithWait(driver, waitInSeconds); 
        Assert.assertTrue(currentUrl.contains(text), "La URL no contiene el texto esperado: " + text);
    }

    // Verifica que un elemento esté presente en la página
    public static void validateElementIsPresent(WebDriver driver, By locator) {
        Assert.assertTrue(driver.findElements(locator).size() > 0, "El elemento no está presente en la página.");
    }

    // Verifica que el texto de un elemento sea igual al esperado
    public static void validateText(WebElement element, String expectedText) {
        String actualText = element.getText();
        Assert.assertEquals(actualText, expectedText, "El texto del elemento no coincide con el esperado.");
    }

    // Verifica si un elemento es clicable
    public static void validateElementIsClickable(WebElement element) {
        Assert.assertTrue(element.isEnabled(), "El elemento no es clicable.");
    }
}