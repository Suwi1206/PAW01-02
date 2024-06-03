package com.co2.likeherotozero.service; // Paketdeklaration

import org.springframework.stereotype.Controller; // Import für Controller-Annotation
import org.springframework.web.bind.annotation.GetMapping; // Import für GetMapping-Annotation

@Controller // Controller-Klasse, die von Spring MVC verwaltet wird
public class HomeController {

    @GetMapping("/") // Mapping für die URL "/" und die HTTP-GET-Anfragemethode
    public String home() { // Methode zur Behandlung der Startseite-Anforderung
        return "index"; // Gibt den Namen der HTML-Datei zurück, die die Startseite darstellt
    }
}
