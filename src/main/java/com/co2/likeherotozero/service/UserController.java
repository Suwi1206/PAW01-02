package com.co2.likeherotozero.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Controller // Dies ist ein Controller-Komponente in Spring MVC
public class UserController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/emissions")
    public String getAllEmissions(Model model) {
        List<Map<String, Object>> results = jdbcTemplate.queryForList("SELECT * FROM emissionen");
        model.addAttribute("emissions", results);
        return "user/emissions";
    }

    @GetMapping("/emission")
    public String getEmissionById(@RequestParam Long id, Model model) {
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT * FROM emissionen WHERE id = ?", id);
        model.addAttribute("emission", result);
        return "user/emission_details";
    }


    @GetMapping("/add_emission")
    public String showAddEmissionForm() {
        return "user/add_emission"; 
    }

    @PostMapping("/add_emission")
    public String addEmission(
            @RequestParam String seriesName,
            @RequestParam String seriesCode,
            @RequestParam String countryName,
            @RequestParam String countryCode,
            @RequestParam String yr2024) 
        {
            String sql = "INSERT INTO emissionen (SeriesName, SeriesCode, CountryName, CountryCode, YR2024, isActive) VALUES (?, ?, ?, ?, ?, False)";
            jdbcTemplate.update(sql, seriesName, seriesCode, countryName, countryCode, yr2024);
        return "redirect:/emissions";
    }




    @GetMapping("/edit_emission")
    public String showEditForm(@RequestParam Long id, Model model) {
        Map<String, Object> result = jdbcTemplate.queryForMap("SELECT * FROM emissionen WHERE id = ?", id);
        model.addAttribute("emission", result);
        return "user/edit_emission";
    }

    @PostMapping("/updateEmission")
    public String updateEmission(
            @RequestParam Long id,
            @RequestParam String seriesName,
            @RequestParam String seriesCode,
            @RequestParam String countryName,
            @RequestParam String countryCode,
            @RequestParam("YR2024") String yr2024) {
        jdbcTemplate.update(
                "UPDATE emissionen SET seriesName = ?, seriesCode = ?, countryName = ?, countryCode = ?, YR2024 = ? WHERE id = ?",
                seriesName, seriesCode, countryName, countryCode, yr2024, id);
        return "redirect:/emissions";
    }
} 
