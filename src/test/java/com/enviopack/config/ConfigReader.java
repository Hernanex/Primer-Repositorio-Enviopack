package com.enviopack.config;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConfigReader {

    private static JsonObject config;

    // Cargar el archivo de configuración
    public static void cargarConfiguracion(String fileName) throws IOException {
        if (config == null) {  // Cargar el archivo solo si no está cargado
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(fileName);
            if (inputStream == null) {
                throw new IOException("Archivo no encontrado: " + fileName);
            }
            InputStreamReader reader = new InputStreamReader(inputStream);
            config = JsonParser.parseReader(reader).getAsJsonObject();
        }
    }

    // Obtener el valor de una clave específica
    public static String getValor(String clave) {
        return config.get(clave).getAsString();
    }
}