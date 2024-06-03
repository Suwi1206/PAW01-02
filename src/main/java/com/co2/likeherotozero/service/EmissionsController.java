package com.co2.likeherotozero.service; // Paketdeklaration

import org.springframework.beans.factory.annotation.Autowired; // Import für Autowired-Annotation
import org.springframework.jdbc.core.JdbcTemplate; // Import für JdbcTemplate
import org.springframework.stereotype.Controller; // Import für Controller-Annotation
import org.springframework.ui.Model; // Import für Model
import org.springframework.web.bind.annotation.*; // Import für verschiedene Annotations
import java.util.Map; // Import für Map
import java.util.List; // Import für List

@Controller // Controller-Klasse, die von Spring MVC verwaltet wird
public class EmissionsController {

    @Autowired // Automatische Verdrahtung des JdbcTemplate-Beans
    private JdbcTemplate jdbcTemplate;

    // Methode zum Abrufen aller aktiven Emissionen für Berichte
    @GetMapping("/berichte") // Mapping für die URL "/berichte" und die HTTP-GET-Anfragemethode
    public String getAllActiveEmissions(Model model) { // Methode zum Abrufen aller aktiven Emissionen für Berichte
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM emissionen WHERE isActive = true");
        model.addAttribute("emissions", results); // Emissionen zur Model-Attribute hinzufügen
        return "public/all_emissions"; // HTML-Datei für die Anzeige aller Emissionen zurückgeben
    }

    @GetMapping("/impressum") // Mapping für die URL "/impressum" und die HTTP-GET-Anfragemethode
    public String getImpressum() { // Methode zum Abrufen der Impressumsseite
        return "public/impressum"; // Gibt den Namen der HTML-Datei zurück, die die Impressumsseite darstellt
    } 

}