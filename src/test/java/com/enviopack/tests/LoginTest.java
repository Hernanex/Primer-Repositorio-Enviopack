package com.enviopack.tests;


import org.testng.annotations.Test;
import com.enviopack.common.BaseTest;
import com.enviopack.helpers.ValidationHelper;


public class LoginTest extends BaseTest {

    @Test
    public void loginAdmin() {
    	performLoginAdmin();
    	
    	 ValidationHelper.validateUrlContainsWithWait(getDriver(), "backoffice", 5);
    }

    @Test
    public void loginSeller() {
    	performLoginSeller();
    	
    	ValidationHelper.validateUrlContainsWithWait(getDriver(), "ordenes", 5);
    }
}