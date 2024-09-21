package Enviopack.App;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class Laboratorio1 {
    WebDriver driver;
    String url = "https://stg1-appv2.enviopack.com/login";
    String email = "carla.r@enviopack.com";
    String pwd = "noAiaO7l";
    
    @Before
    public void setUp() {
        // Configurar el WebDriver
        System.setProperty("webdriver.chrome.driver", "..\\App\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void tearDown() {
        // Cerrar el navegador
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void lab1_e1() {
        // Abrir la página web
        driver.get(url);

        // Esperar hasta que los elementos estén presentes y visibles
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@class='button__wrapper button__wrapper--contained button__wrapper--contained-primary']")));

        // Ingresar credenciales y hacer login
        username.sendKeys(email);
        password.sendKeys(pwd);
        login.click();
    }
}
