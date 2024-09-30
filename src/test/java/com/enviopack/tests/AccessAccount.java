package com.enviopack.tests;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.enviopack.pages.HomePage;

public class AccessAccount extends LoginTest {
	
	  @Test
	  @Parameters({"seller"}) // Asegúrate de que la anotación esté aquí
	    public void AccessIdAccount(String seller) {
	        HomePage homepage = new HomePage(driver);
	        homepage.clickAccessAccountButton();
	        homepage.enterSeller(seller);
	        homepage.selectFirstSeller();
	}
}
