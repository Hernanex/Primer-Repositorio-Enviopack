package com.enviopack.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateHelper {

    
    public static String getCurrentDateTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    
    public static String getCurrentDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDateTime.now().format(formatter);
    }

    
    public static String getCurrentTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }
}

