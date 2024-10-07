package com.enviopack.listeners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;

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
        // Inicializa el reporte
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extentReport.html");
        sparkReporter.config().setDocumentTitle("Reporte de Nachito");
        sparkReporter.config().setReportName("Reporte de Nacho");
        
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
    }

    // Se ejecuta una vez después de que termina la suite
    @Override
    public void onFinish(ISuite suite) {
        extent.flush(); // Guarda el reporte
        System.out.println("Reporte generado: extentReport.html");
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
