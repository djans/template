package com.cogitosum.cmod.controller;

import com.cogitosum.cmod.models.Document;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    @GetMapping("/searchReleve")
    String submit(@Valid Document doc,
                  BindingResult bindingResult,
                  Model model,
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
                  @RequestParam(value="NumeroCompte",required=true,defaultValue = "") String NumeroCompte
                ){
        // GESTION DES ERREURS AU NIVEAU DU UI ( VOIR CLASSE DOCUMENT )
        if (bindingResult.hasErrors()) {
            // If there are errors, return to the form view
            return "releve";
        }
        // APPEL API

        // FIN APPEL API

        model.addAttribute("resultIsHidden",false);
        // Container des documents retorunés par l'API
        List<Document> documents = new ArrayList<Document>();

        // Documents ( Mocked )
        Document doc1 = new Document();
        doc1.setCourtier(Courtier);
        doc1.setTypeDocument(TypeDocument);
        doc1.setSousTypeDocument(SousTypeDocument);
        doc1.setOperation(Operation);
        doc1.setCodeproduit(Codeproduit);
        doc1.setDateDebut(DateDebut);
        doc1.setDateFin(DateFin);
        doc1.setNumeroCompte(NumeroCompte);
        doc1.setAnneeFiscale(AnneeFiscale);
        documents.add(doc1);

        //model.addAttribute("documents",documents);

        // Transformation en JSON
        String jsonresult = "[";
        JsonObjectBuilder jsonBuilder= Json.createObjectBuilder();
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        //jsonresult = parseDocsReleve();
        // Transformation du container au format JSON
        return "releve";
    }
}
