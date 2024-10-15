package com.enviopack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.enviopack.common.BaseTest;
import com.enviopack.helpers.ValidationHelper;
import com.enviopack.pages.HomePage;

public class AccessAccount extends BaseTest {
	
    @Test
    @Parameters({"seller"}) 
    
    public void AccessIdAccount(String seller) {
    	String expectedText = "ordenes";
    	try {
        	performLoginAdmin();
            HomePage homepage = new HomePage(getDriver());
            homepage.clickAccessAccountButton();
            homepage.enterSeller(seller);
            homepage.selectFirstSeller();
            ValidationHelper.validateUrlContainsWithWait(getDriver(), expectedText, 5);
    	} catch(AssertionError e ) {
    		String actualUrl = getDriver().getCurrentUrl();
            throw new AssertionError("Error: Se esperaba que la URL contuviera '" + expectedText + "', pero la URL obtenida fue '" + actualUrl + "'");
    	}
    }
}
