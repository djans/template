package com.cogitosum.cmod.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @ModelAttribute("module")
    public String module() {
        return "module1";
    }
    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("isHidden",true);
        return "index";
    }

    @GetMapping("/search")
    String search(@RequestParam String q, Model model) {
        model.addAttribute("isHidden",false);
        return "index";
    }
}
