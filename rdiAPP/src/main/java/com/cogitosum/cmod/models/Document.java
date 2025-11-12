package com.cogitosum.cmod.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.ModelAttribute;

public class Document {
    @NotBlank(message = "Courtier est obligatoire")
    private String Courtier;
    @NotBlank(message = "Type de document est obligatoire")
    private String TypeDocument;
    private String SousTypeDocument;
    private String AnneeFiscale;
    private String Operation;
    private String Codeproduit;
    private String DateDebut;
    private String DateFin;
    @NotBlank(message = "Numéro de compte est obligatoire")
    private String NumeroCompte;

    public String getCourtier() {
        return Courtier;
    }

    public void setCourtier(String courtier) {
        Courtier = courtier;
    }

    public String getTypeDocument() {
        return TypeDocument;
    }

    public void setTypeDocument(String typeDocument) {
        TypeDocument = typeDocument;
    }

    public String getSousTypeDocument() {
        return SousTypeDocument;
    }

    public void setSousTypeDocument(String sousTypeDocument) {
        SousTypeDocument = sousTypeDocument;
    }

    public String getAnneeFiscale() {
        return AnneeFiscale;
    }

    public void setAnneeFiscale(String anneeFiscale) {
        AnneeFiscale = anneeFiscale;
    }

    public String getOperation() {
        return Operation;
    }

    public void setOperation(String operation) {
        Operation = operation;
    }

    public String getCodeproduit() {
        return Codeproduit;
    }

    public void setCodeproduit(String codeproduit) {
        Codeproduit = codeproduit;
    }

    public String getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(String dateDebut) {
        DateDebut = dateDebut;
    }

    public String getDateFin() {
        return DateFin;
    }

    public void setDateFin(String dateFin) {
        DateFin = dateFin;
    }

    public String getNumeroCompte() {
        return NumeroCompte;
    }

    public void setNumeroCompte(String numeroCompte) {
        NumeroCompte = numeroCompte;
    }
}
