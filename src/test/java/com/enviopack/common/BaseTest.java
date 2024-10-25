package com.enviopack.common;

import com.enviopack.config.ConfigReader;
import com.enviopack.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;

public class BaseTest {
    private WebDriver driver; 
    private String url;  

    @BeforeMethod
    public void setUp() {
        try {
            ConfigReader.cargarConfiguracion("config/config.json");
            this.url = ConfigReader.getValor("url");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la configuración.");
        }

        String browser = ConfigReader.getValor("browser");
        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("El navegador no está definido en la configuración.");
        }

        driver = CustomWebDriverManager.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(url);  
        
    }

    @AfterMethod
    public void tearDown() {
        CustomWebDriverManager.quitDriver();
    }
    
    // Método para obtener el WebDriver
    protected WebDriver getDriver() {
        return driver;
    }
    
    // Obtener credenciales admin
    protected String[] getAdminCredentials() {
        String email = ConfigReader.getValor("email_admin");
        String password = ConfigReader.getValor("password_admin");
        return new String[]{email, password};
    }
    // Obtener credenciales seller
    protected String[] getSellerCredentials() {
        String email = ConfigReader.getValor("email_seller");
        String password = ConfigReader.getValor("password_seller");
        return new String[]{email, password};
    }

    // Método centralizado para login
    protected void login(String email, String password) {
        new LoginPage(driver)
        .enterEmail(email)
        .enterPassword(password)
        .clickLogin();
    } 
    
    //Metodo para acceder como admin
    protected void performLoginAdmin() {
        String[] adminCredentials = getAdminCredentials();
        login(adminCredentials[0], adminCredentials[1]);
    }
    
    //Metodo para acceder como seller
    protected void performLoginSeller() {
        String[] sellerCredentials = getSellerCredentials();
        login(sellerCredentials[0], sellerCredentials[1]);
    }
}