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

    private static final String TEMPLATE_NAME = "TestResult";
    private boolean reportGenerated = false;

    private List<TestResultInfo> testResults = new ArrayList<>();
    private String startTime;
    private String endTime;

    @Override
    public void onStart(ISuite suite) {
        this.startTime = DateHelper.getCurrentDateTime();
    }

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
        this.endTime = DateHelper.getCurrentDateTime();
        if (!reportGenerated) {
            generateReport();
            reportGenerated = true;
        }
    }

    private void generateReport() {
        try {
            String folderPath = "output/Ejecucion de pruebas del dia " + DateHelper.getCurrentDate();
            if (!Files.exists(Paths.get(folderPath))) {
                Files.createDirectories(Paths.get(folderPath));
            }

            String url = ConfigReader.getValor("url");
            String browser = ConfigReader.getValor("browser");

            if (url == null || browser == null) {
                throw new IllegalArgumentException("La configuración 'url' o 'browser' no puede ser null");
            }

            Context context = new Context();
            context.setVariable("url", url);
            context.setVariable("browser", browser);
            context.setVariable("startTime", this.startTime);
            context.setVariable("endTime", this.endTime);
            context.setVariable("testResults", testResults);

            ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
            templateResolver.setPrefix("templates/");
            templateResolver.setSuffix(".html");
            templateResolver.setTemplateMode("HTML");
            templateResolver.setCacheable(false);

            TemplateEngine templateEngine = new TemplateEngine();
            templateEngine.setTemplateResolver(templateResolver);

            String processedHtml = templateEngine.process(TEMPLATE_NAME, context);
            if (processedHtml == null) {
                throw new Exception("Error al procesar la plantilla.");
            }

            String reportFileName = "Reporte de pruebas " + DateHelper.getCurrentDate() + ".html";
            String outputPath = folderPath + "/" + reportFileName;

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
