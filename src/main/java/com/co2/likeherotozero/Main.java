package com.co2.likeherotozero;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.co2.likeherotozero.config.SQLiteDBInitializer;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        // Start der Spring Boot-Anwendung
        SpringApplication.run(Main.class, args);
        // Ausgabe einer Willkommensnachricht
        System.out.println("Wilkommen");

        // Spezifizieren des CSV-Dateinamens, der konvertiert werden soll
        String csvFileName = "co2.csv";
        // Instanziierung der SQLiteDBInitializer-Klasse
        SQLiteDBInitializer fileConverter = new SQLiteDBInitializer();
        // Aufruf der Konvertierungsmethode direkt
        fileConverter.convertCSVtoSQLite(csvFileName);
        // Erstellen der Benutzertabelle
        fileConverter.BenutzerTable();
    }

}
