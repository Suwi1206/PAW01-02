package com.co2.likeherotozero.config; // Paketdeklaration

import com.opencsv.CSVReader; // Import für CSV-Reader
import java.io.FileReader; // Import für FileReader
import java.sql.Connection; // Import für Connection
import java.sql.DriverManager; // Import für DriverManager
import java.sql.PreparedStatement; // Import für PreparedStatement
import java.sql.Statement; // Import für Statement

public class SQLiteDBInitializer {

    // Methode zum Konvertieren der CSV-Datei in SQLite
    public void convertCSVtoSQLite(String csvFileName) {

        try {
            // SQLite JDBC-Treiber laden
            Class.forName("org.sqlite.JDBC");

            // Verbindung zur SQLite-Datenbank herstellen
            Connection conn = DriverManager.getConnection("jdbc:sqlite:co2.db");

            // Tabelle erstellen, wenn sie nicht existiert
            String createTableSQL = "CREATE TABLE IF NOT EXISTS emissionen (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " + // Neue id-Spalte
                    "SeriesName TEXT, SeriesCode TEXT, CountryName TEXT, CountryCode TEXT, " +
                    "YR1990 REAL, YR2000 REAL, YR2014 REAL, YR2015 REAL, YR2016 REAL, " +
                    "YR2017 REAL, YR2018 REAL, YR2019 REAL, YR2020 REAL, YR2021 REAL, " +
                    "YR2022 REAL, YR2023 REAL, YR2024 TEXT DEFAULT NULL, isActive BOOLEAN DEFAULT True)";
            conn.createStatement().execute(createTableSQL);

            // Vorbereiten der INSERT-Anweisung
            String insertSQL = "INSERT INTO emissionen (SeriesName, SeriesCode, CountryName, CountryCode, " +
                    "YR1990, YR2000, YR2014, YR2015, YR2016, YR2017, YR2018, YR2019, YR2020, YR2021, YR2022, YR2023, YR2024, isActive) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);

            // CSV lesen und Daten in SQLite einfügen
            CSVReader reader = new CSVReader(new FileReader(csvFileName));
            @SuppressWarnings("unused")
            String[] headers = reader.readNext(); // Kopfzeile überspringen
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                for (int i = 0; i < nextLine.length; i++) {
                    // Fehlende Daten, die als ".." dargestellt sind, behandeln
                    if (nextLine[i].equals("..")) {
                        pstmt.setNull(i + 1, java.sql.Types.REAL);
                    } else {
                        pstmt.setString(i + 1, nextLine[i]);
                    }
                }
                // Standardwerte für YR2024 und isActive setzen
                pstmt.setNull(17, java.sql.Types.VARCHAR); // Index 17 entspricht YR2024
                pstmt.setBoolean(18, true); // Index 18 entspricht isActive
                pstmt.executeUpdate();
            }

            // Ressourcen schließen
            pstmt.close();
            conn.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Methode zum Erstellen der Benutzer-Tabelle
    public void BenutzerTable() {
        String url = "jdbc:sqlite:co2.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // Tabelle erstellen
            String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                    + "    teilnehmer TEXT,\n"
                    + "    username TEXT,\n"
                    + "    email TEXT,\n"
                    + "    password TEXT,\n"
                    + "    isactive BOOLEAN,\n"
                    + "    isadmin BOOLEAN\n"
                    + ");";
            stmt.execute(sql);
            // System.out.println("Table 'benutzer' created successfully");
        } catch (Exception e) {
            System.err.println("Error creating table 'benutzer': " + e.getMessage());
        }
    }
}
