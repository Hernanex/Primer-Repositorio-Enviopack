package com.enviopack.tests;

import org.testng.annotations.Test;
import com.enviopack.common.BaseTest;
import com.enviopack.pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void loginAdmin() {
    	performLoginAdmin();
    }

    @Test
    public void loginSeller() {
    	performLoginSeller();
    }
}