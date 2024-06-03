package com.co2.likeherotozero.service; // Paketdeklaration

import org.springframework.stereotype.Controller; // Import für Controller-Annotation
import org.springframework.web.bind.annotation.GetMapping; // Import für GetMapping-Annotation

@Controller // Controller-Klasse, die von Spring MVC verwaltet wird
public class LoginController {

    @GetMapping("/login") // Mapping für die URL "/login" und die HTTP-GET-Anfragemethode
    public String showLogin() { // Methode zur Anzeige des Anmeldeformulars
        return "auth/login"; // Gibt den Namen der HTML-Datei zurück, die das Anmeldeformular darstellt
    }

    @GetMapping("/logout") // Mapping für die URL "/logout" und die HTTP-GET-Anfragemethode
    public String logout() { // Methode für die Abmeldung des Benutzers
        return "logout"; // Gibt den Namen der HTML-Datei zurück, die die Abmeldung bestätigt
    }
}
