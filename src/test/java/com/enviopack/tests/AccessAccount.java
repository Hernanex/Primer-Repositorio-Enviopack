package com.enviopack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.enviopack.common.BaseTest;
import com.enviopack.pages.HomePage;

public class AccessAccount extends BaseTest {
    @Test
    @Parameters({"seller"}) 
    public void AccessIdAccount(String seller) {

    	performLoginAdmin();  // Realiza el login como administrador

        HomePage homepage = new HomePage(getDriver());
        homepage.clickAccessAccountButton();
        homepage.enterSeller(seller);
        homepage.selectFirstSeller();
    }
}
