package com.enviopack.reports;

import com.enviopack.config.ConfigReader;
import com.enviopack.helpers.DateHelper;
import org.testng.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReportListener extends TestListenerAdapter implements IInvokedMethodListener, ISuiteListener {

    private static final String TEMPLATE_NAME = "TestResult";  // Nombre del template Thymeleaf (sin extensión)
    private boolean reportGenerated = false;  // Flag para evitar que se genere el reporte varias veces

    // Lista para almacenar los resultados de las pruebas
    private List<TestResultInfo> testResults = new ArrayList<>();

    @Override
    public void onTestSuccess(ITestResult result) {
        testResults.add(new TestResultInfo(result.getName(), "PASSED", ""));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        testResults.add(new TestResultInfo(result.getName(), "FAILED", result.getThrowable().getMessage()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testResults.add(new TestResultInfo(result.getName(), "SKIPPED", ""));
    }

    @Override
    public void onFinish(ISuite suite) {
        // Generar el reporte solo al final de la suite (una vez por ejecución de suite)
        if (!reportGenerated) {
            generateReport();
            reportGenerated = true;  // Marcar el reporte como generado
        }
    }

    // Método para generar el reporte usando Thymeleaf
    private void generateReport() {
        try {
            // (Modificación 1) Verificar si la carpeta 'output' existe, si no, crearla
            String folderPath = "output/Ejecucion de pruebas del dia " + DateHelper.getCurrentDate();
            if (!Files.exists(Paths.get(folderPath))) {
                Files.createDirectories(Paths.get(folderPath));
            }

            // Recuperar la configuración desde el archivo JSON
            String url = ConfigReader.getValor("url");
            String browser = ConfigReader.getValor("browser");
            String startTime = DateHelper.getCurrentDateTime(); 
            String endTime = DateHelper.getCurrentDateTime();

            // (Modificación 2) Validar que la configuración no sea nula
            if (url == null || browser == null) {
                throw new IllegalArgumentException("La configuración 'url' o 'browser' no puede ser null");
            }

            // Crear el contexto de Thymeleaf
            Context context = new Context();
            context.setVariable("url", url);
            context.setVariable("browser", browser);
            context.setVariable("startTime", startTime);
            context.setVariable("endTime", endTime);
            context.setVariable("testResults", testResults);  // Pasar los resultados de las pruebas al template

            // (Modificación 4) Configurar el motor de plantillas con ajustes adicionales
            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/");  // Carpeta de templates
            templateResolver.setSuffix(".html");  // Extensión del archivo HTML
            templateResolver.setTemplateMode("HTML");  // Modo de plantilla
            templateResolver.setCacheable(false);  // Desactivar la caché para facilitar depuración (nuevo)

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            // (Modificación 5) Validación adicional al procesar el template
            String processedHtml = templateEngine.process(TEMPLATE_NAME, context);
            if (processedHtml == null) {
                throw new Exception("Error al procesar la plantilla. Asegúrate de que la plantilla 'TestResult.html' esté disponible en la carpeta 'templates'.");
            }

            // (Modificación 6) Generar el nombre del archivo con fecha y hora
            String reportFileName = "Reporte de pruebas " + DateHelper.getCurrentDate() + ".html";  // Nombre del archivo con fecha y hora
            String outputPath = folderPath + "/" + reportFileName;  // Ruta completa del archivo

            // Guardar el reporte generado en un archivo HTML
            try (FileWriter writer = new FileWriter(outputPath)) {
                writer.write(processedHtml);
            }

            System.out.println("Reporte generado en: " + outputPath);

        } catch (IOException e) {
            System.err.println("Error al escribir el reporte HTML: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Error de configuración: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error inesperado al generar el reporte: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

