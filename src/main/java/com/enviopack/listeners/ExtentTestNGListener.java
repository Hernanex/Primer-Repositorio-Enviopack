package com.enviopack.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import com.enviopack.helpers.DateHelper;

import java.io.File;

public class ExtentTestNGListener implements ITestListener, ISuiteListener {

    private static ExtentReports extent;
    private static ExtentTest test;
    private static int countPassedTests;
    private static int countSkippedTests;
    private static int countFailedTests;
    private static int countTotalTests;

    // Se ejecuta una vez antes de que comience la suite
    @Override
    public void onStart(ISuite suite) {
        // Obtener la fecha actual y crear el nombre de la carpeta
        String folderName = "Ejecuciones automatizadas - " + DateHelper.getCurrentDate();
        String reportFolderPath = System.getProperty("user.dir") + File.separator + folderName;
        
        // Crear la carpeta si no existe
        File reportFolder = new File(reportFolderPath);
        if (!reportFolder.exists()) {
            reportFolder.mkdir();
        }

        // Ruta completa para el archivo de reporte dentro de la carpeta
        String reportFilePath = reportFolderPath + File.separator + "extentReport.html";
        
        // Inicializa el reporte en la carpeta especificada
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportFilePath);
        sparkReporter.config().setDocumentTitle("Reporte de pruebas - " + DateHelper.getCurrentDateTime());
        sparkReporter.config().setReportName("Reporte de Nacho - " + DateHelper.getCurrentDateTime());
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // Se ejecuta una vez después de que termina la suite
    @Override
    public void onFinish(ISuite suite) {
        extent.flush(); // Guarda el reporte
        System.out.println("Reporte generado en la carpeta: Ejecuciones automatizadas - " + DateHelper.getCurrentDate());
    }

    // Se ejecuta antes de cada prueba
    @Override
    public void onTestStart(ITestResult result) {
        countTotalTests++;
        test = extent.createTest(result.getMethod().getMethodName());
    }

    // Se ejecuta cuando una prueba pasa
    @Override
    public void onTestSuccess(ITestResult result) {
        countPassedTests++;
        test.pass("Test Passed");
    }

    // Se ejecuta cuando una prueba falla
    @Override
    public void onTestFailure(ITestResult result) {
        countFailedTests++;
        test.fail("Test Failed: " + result.getThrowable().getMessage());
        test.fail(result.getThrowable()); // Añadir la excepción al reporte
    }

    // Se ejecuta cuando una prueba es ignorada
    @Override
    public void onTestSkipped(ITestResult result) {
        countSkippedTests++;
        test.skip("Test Skipped: " + result.getThrowable().getMessage());
    }
}
