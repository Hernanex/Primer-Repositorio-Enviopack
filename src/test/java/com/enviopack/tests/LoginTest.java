package com.enviopack.tests;

import org.testng.annotations.Test;
import com.enviopack.common.BaseTest;
import com.enviopack.pages.LoginPage;

public class LoginTest extends BaseTest {
    
    @Test
    public void testLogin() {
        driver.get(url);
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
    }
}
