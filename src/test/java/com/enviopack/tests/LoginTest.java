package com.enviopack.tests;

import org.testng.annotations.Test;
import com.enviopack.common.BaseTest;
import com.enviopack.helpers.ValidationHelper;

public class LoginTest extends BaseTest {

    @Test
    public void loginAdmin() {
        String expectedText = "backoffice";
        try {
            performLoginAdmin();
            ValidationHelper.validateUrlContainsWithWait(getDriver(), expectedText, 5);
        } catch (AssertionError e) {
            String actualUrl = getDriver().getCurrentUrl();
            throw new AssertionError("Error: Se esperaba que la URL contuviera '" + expectedText + "', pero la URL obtenida fue '" + actualUrl + "'");
        }
    }

    @Test
    public void loginSeller() {
        String expectedText = "ordenes";
        try {
            performLoginSeller();
            ValidationHelper.validateUrlContainsWithWait(getDriver(), expectedText, 5);
        } catch (AssertionError e) {
            String actualUrl = getDriver().getCurrentUrl();
            throw new AssertionError("Error: Se esperaba que la URL contuviera '" + expectedText + "', pero la URL obtenida fue '" + actualUrl + "'");
        }
    }
}
