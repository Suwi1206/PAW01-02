package com.co2.likeherotozero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public AdminController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // emissions Register
    @GetMapping("/adminemissions") // Hier haben wir den Pfad geändert
    public String getAllEmissions(Model model) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM emissionen WHERE isActive = false");
        model.addAttribute("emissions", results);
        return "admin/adminemissions"; // Hier haben wir den Dateinamen geändert
    }
    
    
    
    @GetMapping("/admineditemissions")
    public String showEditForm(@RequestParam Long id, Model model) {
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT * FROM emissionen WHERE id = ?", id);
        model.addAttribute("emission", result);
        return "admin/admineditemissions";
    }
    
    
    // @PostMapping("/updateAdminEmission")
    // public String updateAdminEmission(
    //         @RequestParam Long id,
    //         @RequestParam String seriesName,
    //         @RequestParam String seriesCode,
    //         @RequestParam String countryName,
    //         @RequestParam String countryCode,
    //         @RequestParam("YR2024") String yr2024,
    //         @RequestParam("isActive") boolean isActive) { // Änderung des Datentyps auf boolean
    //     jdbcTemplate.update(
    //             "UPDATE emissionen SET seriesName = ?, seriesCode = ?, countryName = ?, countryCode = ?, YR2024 = ?, isActive = ? WHERE id = ?", // Aktualisierung der SQL-Abfrage
    //             seriesName, seriesCode, countryName, countryCode, yr2024, isActive, id); // Hinzufügen von isActive zum Update
    //     return "redirect:/adminemissions";
    // }


    @PostMapping("/updateAdminEmission")
    public String updateAdminEmission(@RequestParam Long id,
                                      @RequestParam String seriesName,
                                      @RequestParam String seriesCode,
                                      @RequestParam String countryName,
                                      @RequestParam String countryCode,
                                      @RequestParam String YR2024,
                                      @RequestParam(defaultValue = "false") boolean isActive) {
        jdbcTemplate.update(
                "UPDATE emissionen SET seriesName = ?, seriesCode = ?, countryName = ?, countryCode = ?, YR2024 = ?, isActive = ?  WHERE id = ?",
                seriesName, seriesCode, countryName, countryCode, YR2024, isActive, id);
        return "redirect:/adminemissions";
    }
    
    
}