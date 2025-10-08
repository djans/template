package com.cogitosum.cmod.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Map;

@Controller
public class HomeController {

    @ModelAttribute("module")
    public String module() {
        return "module1";
    }

    @GetMapping("/")
    String index(Model model, @AuthenticationPrincipal OidcUser oidcUser) {
        model.addAttribute("isHidden",true);
        Map<String,Object> oidcUserClaims= oidcUser.getClaims();
        //model.addAttribute("Login",oidcUser.getEmail());
        model.addAttribute("Login",oidcUser.getClaims().toString());
        return "index";
    }

    @GetMapping("/search")
    String search(@RequestParam String q, Model model) {
        model.addAttribute("isHidden",false);
        return "index";
    }
}
