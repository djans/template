package com.cogitosum.cmod.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;


import java.util.Locale;

@Controller
public class LanguageController {
    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/changeLanguage")
    public String changeLanguage(
            HttpServletRequest request,
            HttpServletResponse response,
            Model model,
            @RequestParam("lang") String lang,
            @RequestParam("path") String path
    ) {
        localeResolver.setLocale(request, response, new Locale(lang));
//        PRESERVING QUERY PARAMETERS
//        String fullPath = request.getRequestURI();
//        String query = request.getQueryString();
//        String redirectUrl = query != null ? fullPath + "?" + query : fullPath;
//        return "redirect:" + redirectUrl;
        return "redirect:" + path;
    }
}