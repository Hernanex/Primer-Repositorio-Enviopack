package Enviopack.App;

import com.enviopack.config.ConfigReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestDePrueba {

    protected WebDriver driver;
    protected String url;
    String email = "carla.r@enviopack.com";
    String pwd = "noAiaO7l";
    String seller = "5710";

    @BeforeMethod
    public void setUp() {
        try {
            ConfigReader.cargarConfiguracion("config/config.json");
            this.url = ConfigReader.getValor("url");
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar la configuración.");
        }

        WebDriverManager.chromedriver().setup(); // Configura el WebDriver usando WebDriverManager
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @Test
    public void baseTest() {
    	   // Abrir la página web
        driver.get(url);

        // Esperar hasta que los elementos estén presentes y visibles
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("password")));
        WebElement login = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//DIV[@class='button__wrapper button__wrapper--contained button__wrapper--contained-primary']")));
        
        // Ingresar credenciales y hacer login
        username.sendKeys(email);
        password.sendKeys(pwd);
        login.click();
        
        // Esperar la redirección o carga de contenido
        WebElement btn_accederCuenta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Acceder a una cuenta']")));
        
        // Hacer clic en el botón
        btn_accederCuenta.click();
        
        // Esperar la redirección y localizar input seller
        WebElement inputSeller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("empresa")));
        
        // Ingresar seller
        inputSeller.sendKeys(seller);
        
        // PROFUNDIZAR CON ESTA IDEA, LO IDEAL ES TENER LA LISTA DE ELEMENTOS Y NAVEGARLO DESDE AHI NO DESDE EL ID DEL ELEMENTO
        // Esperar hasta que todos los elementos sean visibles y localizados, usando visibilityOfAllElementsLocatedBy para obtener una lista.
        // List<WebElement> lstSeller = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("react-autowhatever-1")));

        // Seleccionar el primer elemento de la lista y espera del primer resultado
        WebElement firstSeller = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("react-autowhatever-1--item-0")));
        
        // Interactuar con el primer elemento 
        firstSeller.click();
    }
}
