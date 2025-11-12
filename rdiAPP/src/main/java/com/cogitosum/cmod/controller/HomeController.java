package com.cogitosum.cmod.controller;


import com.cogitosum.cmod.helper.OktaStatus;
import com.cogitosum.cmod.models.Document;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Controller
@SessionAttributes("document")
public class HomeController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);
    // HOME PAGE
    @GetMapping("/")
    String index(Model model,
                 @AuthenticationPrincipal OidcUser oidcUser,
                 HttpServletRequest request,
                 HttpServletResponse response,
                 HttpSession session )
    {
        // Checking OKTA Token
        logger.debug("Checking OKTA Token status");
        OktaStatus okStatus = new OktaStatus();
        try {
            okStatus.checkStatus(request,response);
        } catch (IOException e) {
            logger.error("PROBLEM WITH OKTA STATUS");
            logger.error(e.getMessage());
            throw new RuntimeException(e);
        }

        logger.debug("Incoming request: HOME CONTROLLER : INDEX");
        // Managing the state of the page
        model.addAttribute("resultIsHidden",true);
        // Ajout aux liens "LANG" poour revenir vers la page actuelle
        model.addAttribute("contextPath", request.getRequestURI());
        // Getting the email of the user
        model.addAttribute("Login",oidcUser.getEmail());
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
    String releve(@ModelAttribute("document") Document document, Model model,
                  @AuthenticationPrincipal OidcUser oidcUser,
                  HttpServletRequest request,
                  HttpServletResponse response,
                  HttpSession session,
                  @RequestParam(value="SousTypeDocument",required=true,defaultValue = "") String SousTypeDocument,
                  @RequestParam(value="AnneeFiscale",required=true,defaultValue = "") String AnneeFiscale,
                  @RequestParam(value="Operation",required=true,defaultValue = "") String Operation,
                  @RequestParam(value="Codeproduit",required=true,defaultValue = "") String Codeproduit,
                  @RequestParam(value="DateDebut",required=true,defaultValue = "") String DateDebut,
                  @RequestParam(value="DateFin",required=true,defaultValue = "") String DateFin,
                  @RequestParam(value="NumeroCompte",required=true,defaultValue = "") String NumeroCompte) {
        logger.debug("Incoming request: HOME CONTROLLER : RELEVE");
        logger.debug("TYPEDOCUMENT : "+document.getTypeDocument());

        model.addAttribute("resultIsHidden",true);
        // Ajout aux liens "LANG" poour revenir vers la page actuelle
        model.addAttribute("contextPath", request.getRequestURI());
        return "releve";
    }
    @GetMapping("/xmlgouv")
    String xmlgouv(Model model,HttpServletRequest request,
                   HttpServletResponse response,
                   HttpSession session) {
        logger.debug("Incoming request: HOME CONTROLLER : XMLGOUV");
        logger.debug("Incoming request: " + request.getRequestURI());
        return "xml";
    }
    @GetMapping("/facture")
    String facture(Model model,HttpServletRequest request,
                   HttpServletResponse response,
                   HttpSession session) {
        logger.debug("Incoming request: HOME CONTROLLER : FACTURE");
        logger.debug("Incoming request: " + request.getRequestURI());
        return "facture";
    }
    @ModelAttribute("document")
    public Document getDocument() {
        return new Document(); // or prepopulate if needed
    }


}
