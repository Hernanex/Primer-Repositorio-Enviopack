package com.enviopack.common;

import com.enviopack.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;

public class BaseTest {
    protected WebDriver driver;
    protected String url;			
    protected String email;
    protected String password;

    @BeforeMethod
    public void setUp() {
        try {
            ConfigReader.cargarConfiguracion("config/config.json");
            this.url = ConfigReader.getValor("url");
            this.email = ConfigReader.getValor("email");
            this.password = ConfigReader.getValor("password");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la configuración.");
        }

        String browser = ConfigReader.getValor("browser");
        if (browser == null || browser.isEmpty()) {
            throw new IllegalArgumentException("El navegador no está definido en la configuración.");
        }

        driver = CustomWebDriverManager.getDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
    	CustomWebDriverManager.quitDriver();
    }
}

