package com.cogitosum.cmod.controller;


import com.cogitosum.cmod.models.Document;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
public class HomeController {

    @GetMapping("/")
    String index(Model model,
                 @AuthenticationPrincipal OidcUser oidcUser,
                 HttpServletRequest request,
                 HttpServletResponse response,
                 HttpSession session,
                 @RequestParam(value="Courtier",required=true,defaultValue = "") String Courtier,
                 @RequestParam(value="TypeDocument",required=true,defaultValue = "") String TypeDocument,
                 @RequestParam(value="SousTypeDocument",required=true,defaultValue = "") String SousTypeDocument,
                 @RequestParam(value="AnneeFiscale",required=true,defaultValue = "") String AnneeFiscale,
                 @RequestParam(value="Operation",required=true,defaultValue = "") String Operation,
                 @RequestParam(value="Codeproduit",required=true,defaultValue = "") String Codeproduit,
                 @RequestParam(value="DateDebut",required=true,defaultValue = "") String DateDebut,
                 @RequestParam(value="DateFin",required=true,defaultValue = "") String DateFin,
                 @RequestParam(value="NumeroCompte",required=true,defaultValue = "") String NumeroCompte)
    {

        Map<String,Object> oidcUserClaims= oidcUser.getClaims();
        model.addAttribute("resultIsHidden",true);
        model.addAttribute("title","Titre releve");
        // Ajout aux liens "LANG" poour revenir vers la page actuelle
        model.addAttribute("contextPath", request.getRequestURI());
        //System.out.println(oidcUser.getAttributes());
        //oidcUser.getAuthorities().forEach(authority -> System.out.println(authority.getAuthority()));
        //oidcUser.getClaims().forEach(oidcUserClaims::put);
        System.out.println("Incoming request: HOME CONTROLLER : INDEX");
        System.out.println("Incoming request: " + request.getRequestURI());

        model.addAttribute("Login",oidcUser.getEmail());
        //model.addAttribute("Login",oidcUser.getClaims().toString());
        return "releve";
    }

    @GetMapping("/search")
    String search(@RequestParam String q, Model model) {
        model.addAttribute("isHidden",false);
        return "index";
    }

    @GetMapping("/tabs")
    String tabs(Model model) {
        return "tabs";
    }

    @GetMapping("/releve")
    String releve(Model model, Locale locale,
                  @AuthenticationPrincipal OidcUser oidcUser,
                  HttpServletRequest request,
                  HttpServletResponse response,
                  HttpSession session,
                  @RequestParam(value="Releve01",required=true,defaultValue = "") String releve01,
                  @RequestParam(value="Releve02",required=true,defaultValue = "") String releve02,
                  @RequestParam(value="Releve03",required=true,defaultValue = "") String releve03
                  ) {
        System.out.println("Incoming request: HOME CONTROLLER : RELEVE");
        System.out.println("Incoming request: " + request.getRequestURI());
        model.addAttribute("resultIsHidden",true);
        model.addAttribute("title","Titre releve");
        // Ajout aux liens "LANG" poour revenir vers la page actuelle
        model.addAttribute("contextPath", request.getRequestURI());
        return "releve";
    }
    @GetMapping("/xmlgouv")
    String xmlgouv(Model model,HttpServletRequest request,
                   HttpServletResponse response,
                   HttpSession session) {
        System.out.println("Incoming request: HOME CONTROLLER : XMLGOUV");
        System.out.println("Incoming request: " + request.getRequestURI());
        return "xml";
    }
    @GetMapping("/facture")
    String facture(Model model,HttpServletRequest request,
                   HttpServletResponse response,
                   HttpSession session) {
        System.out.println("Incoming request: HOME CONTROLLER : FACTURE");
        System.out.println("Incoming request: " + request.getRequestURI());
        return "facture";
    }
}
