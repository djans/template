package com.cogitosum.cmod.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.Locale;

@Controller
public class LanguageController {

    @GetMapping("/changeLanguage")
    public String changeLanguage(@RequestParam("lang") String lang) {
        // Set the language (locale)

        Locale.setDefault(new Locale(lang));
        return "redirect:/";  // Redirect to the home page or previous page
    }
}