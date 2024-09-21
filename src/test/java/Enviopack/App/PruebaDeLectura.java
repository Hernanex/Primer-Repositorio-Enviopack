package Enviopack.App;

import com.enviopack.config.ConfigReader;
import org.testng.annotations.Test;
import java.io.IOException;

public class PruebaDeLectura {

    @Test
    public void leerConfiguracion() {
        try {
            // Cargar el archivo de configuración
            ConfigReader.cargarConfiguracion("config/config.json");

            // Obtener el valor de la URL
            String url = ConfigReader.getValor("url");

            // Imprimir la URL
            System.out.println("URL: " + url);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}