//////////////////////////////////////////////////////////
// DATE DE DEBUT PAR DEFAUT
//////////////////////////////////////////////////////////
document.addEventListener('DOMContentLoaded', function() {
    const dateInput = document.getElementById('DateDebut');
    if(dateInput!=null) {
        const today = new Date();

        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0'); // Month is 0-indexed, add 1, then pad
        const day = String(today.getDate()).padStart(2, '0'); // Pad day

        const formattedDate = `${year}-${month}-${day}`;
        dateInput.value = formattedDate;
    }
});
//////////////////////////////////////////////////////////
// DATE DE FIN PAR DEFAUT
//////////////////////////////////////////////////////////
document.addEventListener('DOMContentLoaded', function() {
    const dateInput = document.getElementById('DateFin');
    if(dateInput!=null) {
        const today = new Date();

        const year = today.getFullYear();
        const month = String(today.getMonth() + 1).padStart(2, '0'); // Month is 0-indexed, add 1, then pad
        const day = String(today.getDate()).padStart(2, '0'); // Pad day

        const formattedDate = `${year}-${month}-${day}`;
        dateInput.value = formattedDate;
    }
});
//////////////////////////////////////////////////////////
// CHANGEMENT DE COURTIER - CHANGEMENT DE TYPE DE DOCUMENT
//////////////////////////////////////////////////////////

//-------------------------------------------------
// TYPE DE DOCUMENT SUPPORTE PAR CHAQUE COURTIER
//  La valeur et le texte sont séparés par "#"
//  -------------------------------------------------

const typedocumentparcourtier = {
    C9645: ['TAXSTM#Feuillet fiscal'],
    C7954: ['TAXSTM#Feuillet fiscal', 'PRTFSTM#Relevés', 'TradeConfig#Avis exécution','NOTIF#Lettre de confirmation' ],
    C7947: ['TAXSTM#Feuillet fiscal', 'PRTFSTM#Relevés', 'TradeConfig#Avis exécution','NOTIF#Lettre de confirmation' ],
    C9952: ['TAXSTM#Feuillet fiscal', 'PRTFSTM#Relevés', 'TradeConfig#Avis exécution','NOTIF#Lettre de confirmation' ],
    C3436: ['TAXSTM#Feuillet fiscal', 'PRTFSTM#Relevés' ]
};
//-------------------------------------------------
// SOUS-TYPE DE DOCUMENT SUPPORTE PAR CHAQUE COURTIER
// Exemple : C9645TAXSTM = sous ty-pe supporté par le courtier 9645 et le type de documemt TAXSTM
//  -------------------------------------------------

const soustypedocumentparcourtier = {
    C9645TAXSTM: ['CONTRRCPT#Reçu de cotisation',"NR4#NR4","T4A#T4A","T3#T5","T5008#T5008","Relevé 1#Relevé 1","Relevé 2#Relevé 2","Relevé 3#Relevé 3","T3/RL16#T3/RL16","T4A/RL1#T4A/RL1","T5/RL3#T5/RL3","T5008/RL18#T5008/RL18"],

    C7954TAXSTM: ['CONTRRCPT#Reçu de cotisation',"NR4#NR4","RC249#RC249","T4A#T4A","T4RIF#T4RIF","T4RSP#T4RSP","T3#T3","T5#T5","T5008#T5008","RL1#Relevé 1","RL2#Relevé 2","RL3#Relevé 3","RL7#Relevé 7","RL32#Relevé 32","T3/RL16#T3/RL16","T4A/RL1#T4A/RL1","T4RIF/RL2#T4RIF/RL2","T4RSP/RL2#T4RSP/RL2","T5/RL3#T5/RL3","T5008/RL18#T5008/RL18","T4FHSA#T4FHSA","T4FHSA/RL32#T4FHSA/RL32"],
    C7954PRTFSTM: ['PRTFSTM#Relevé de compte'],
    C7954TradeConfig: ['BOUGHT#Achat','SOLD#Vente'],
    C7954NOTIF: ['GICRenewal#Lettre de renouvellement CPG','GICBuy#Lettre achat CPG','PRNRENEW#Préavis de renouvellement CPG'],

    C7947TAXSTM: ['CONTRRCPT#Reçu de cotisation',"NR4#NR4","RC249#RC249","T4A#T4A","T4RIF#T4RIF","T4RSP#T4RSP","T3#T3","T5#T5","T5008#T5008","RL1#Relevé 1","RL2#Relevé 2","RL3#Relevé 3","RL7#Relevé 7","RL32#Relevé 32","T3/RL16#T3/RL16","T4A/RL1#T4A/RL1","T4RIF/RL2#T4RIF/RL2","T4RSP/RL2#T4RSP/RL2","T5/RL3#T5/RL3","T5008/RL18#T5008/RL18","T4FHSA#T4FHSA","T4FHSA/RL32#T4FHSA/RL32"],
    C7947PRTFSTM: ['PRTFSTM#Relevé de compte',"CCAR#Rapport annuel entreprise"],
    C7947TradeConfig: ['BOUGHT#Achat','SOLD#Vente'],
    C7947NOTIF: ['RESPEAP#Lettre paiement REEE','GICRenewal#Lettre de renouvellement CPG','GICBuy#Lettre achat CPG','PRNRENEW#Préavis de renouvellement CPG'],

    C9952TAXSTM: ['CONTRRCPT#Reçu de cotisation',"NR4#NR4","RC249#RC249","T4A#T4A","T4RIF#T4RIF","T4RSP#T4RSP","T5#T5","T5008#T5008","RL1#Relevé 1","RL2#Relevé 2","RL3#Relevé 3"],
    C9952PRTFSTM: ['PRTFSTM#Relevé de compte',"FEEPERF#Rapport annuel"],
    C9952TradeConfig: ['BOUGHT#Achat','SOLD#Vente'],
    C9952NOTIF: ['RESPEAP#Lettre paiement REEE','GICBuy#Lettre achat CPG'],

    C3436TAXSTM: ['CONTRRCPT#Reçu de cotisation',"NR4#NR4","RC249#RC249","T4A#T4A","T4RIF#T4RIF","T4RSP#T4RSP","T5#T5","T5008#T5008","RL1#Relevé 1","RL2#Relevé 2","RL3#Relevé 3"],
    C3436PRTFSTM: ['PRTFSTM#Relevé de compte']
};

document.addEventListener('change', function(event) {
    console.log("CALLED BY DOCUMENT.ADDLISTENER");
    updateDocumentFields(event.target.id,event.target.value,"live");
});

function updateDocumentFields(triggerId,triggerValue,triggerEvent){
    console.log("TRIGGERED "+triggerId+" "+triggerValue);
    // Champ à mettre à jour
    const ElementTypeDocumentValue = document.getElementById('TypeDocument').value;
    const ElementSousTypeDocument = document.getElementById('SousTypeDocument').value;
    const urlParams = new URLSearchParams(window.location.search);

    // Mise à zéro du type de document. Espace est requis sinon erreur

    // Construction de la liste de type de documents selon le courtier choisi
    if(triggerId === 'Courtier') {
        TypeDocument.innerHTML = '<option value=""> </option>';
        const listtypedocumentcourtier = "C"+triggerValue;
        const listtypedocuments=typedocumentparcourtier[listtypedocumentcourtier];
        if (listtypedocuments!=null) {
            if (listtypedocuments.length > 0) {
                listtypedocuments.forEach(element => {
                    const option = document.createElement("option");
                    option.textContent = element.split("#")[1];
                    option.value = element.split("#")[0];
                    TypeDocument.appendChild(option);
                })
            }
            if(triggerEvent==="reload") {
                TypeDocument.value = ElementTypeDocumentValue;
            }
        }
        // MONTRER TYPE DE DOCUMENT ET NUMERO DE COMPTE SI COURTIER !=""
        if (triggerValue !== '') {
            document.getElementById("ligneTypeDocument").style.display = '';
            document.getElementById("ligneNumeroCompte").style.display = '';
        }
    }

    // Construction de la liste de sous type de documents selon le type de document choisi
    if(triggerId === 'TypeDocument') {
        SousTypeDocument.innerHTML = '<option value=""> </option>';
        const listsoustypedocumentcourtier = "C"+document.getElementById("Courtier").value+triggerValue;
        const listsoustypedocuments=soustypedocumentparcourtier[listsoustypedocumentcourtier];
        if(listsoustypedocuments!=null) {
            if (listsoustypedocuments.length > 0) {
                listsoustypedocuments.forEach(element => {
                    const option = document.createElement("option");
                    option.textContent = element.split("#")[1];
                    option.value = element.split("#")[0];
                    SousTypeDocument.appendChild(option);
                })
            }
            SousTypeDocument.value = ElementSousTypeDocument;
        }
    }

}

//////////////////////////////////////////////////////////
// REGLE DE HIDE/WHEN
// MONTRER SI LA COMBINAISON "COURTIER#TYPE DE DOCUMENT"
//////////////////////////////////////////////////////////
const ruleshide={
    SOUS_TYPE_FIELD_TAXSTM:['9645#TAXSTM','7954#TAXSTM','7947#TAXSTM,9952#TAXSTM','3436#TAXSTM'],
    SOUS_TYPE_FIELD_PRTFSTM:['9645#PRTFSTM','7954#PRTFSTM','7947#PRTFSTM,9952#PRTFSTM','3436#PRTFSTM'],
    SOUS_TYPE_FIELD_TradeConfig:[],
    SOUS_TYPE_FIELD_NOTIF:['9645#NOTIF','7954#NOTIF','7947#NOTIF,9952#NOTIF','3436#NOTIF'],

    DATES_DEBUT_FIN_FIELD_TAXSTM:[],
    DATES_DEBUT_FIN_FIELD_PRTFSTM:['9645#PRTFSTM','7954#PRTFSTM','9952#PRTFSTM'],
    DATES_DEBUT_FIN_FIELD_TradeConfig:['9645#TradeConfig','7954#TradeConfig','7947#TradeConfig,9952#TradeConfig','3436#TradeConfig'],
    DATES_DEBUT_FIN_FIELD_NOTIF:['9645#NOTIF','7954#NOTIF','7947#NOTIF,9952#NOTIF','3436#NOTIF'],

    DATE_FISCALE_FIELD_TAXSTM:['9645#TAXSTM','7954#TAXSTM','7947#TAXSTM,9952#TAXSTM','3436#TAXSTM'],
    DATE_FISCALE_FIELD_PRTFSTM:[],
    DATE_FISCALE_FIELD_TradeConfig:[],
    DATE_FISCALE_FIELD_NOTIF:[],

    OPERATIONS_FIELD_TAXSTM:[],
    OPERATIONS_FIELD_PRTFSTM:[],
    OPERATIONS_FIELD_TradeConfig:['9645#TradeConfig','7954#TradeConfig','7947#TradeConfig,9952#TradeConfig','3436#TradeConfig'],
    OPERATIONS_FIELD_NOTIF:[],

    CODE_PRODUIT_FIELD_TAXSTM:[],
    CODE_PRODUIT_FIELD_PRTFSTM:[],
    CODE_PRODUIT_FIELD_TradeConfig:['9645#TradeConfig','7954#TradeConfig','7947#TradeConfig,9952#TradeConfig','3436#TradeConfig'],
    CODE_PRODUIT_FIELD_NOTIF:[]

};
// CONCATENATION
HIDE_SOUS_TYPE_FIELD = ruleshide['SOUS_TYPE_FIELD_TAXSTM']
    .concat(ruleshide['SOUS_TYPE_FIELD_PRTFSTM'])
    .concat(ruleshide['SOUS_TYPE_FIELD_TradeConfig'])
    .concat(ruleshide['SOUS_TYPE_FIELD_NOTIF']);
HIDE_DATES_DEBUT_FIN_FIELD= ruleshide['DATES_DEBUT_FIN_FIELD_TAXSTM']
    .concat(ruleshide['DATES_DEBUT_FIN_FIELD_PRTFSTM'])
    .concat(ruleshide['DATES_DEBUT_FIN_FIELD_TradeConfig'])
    .concat(ruleshide['DATES_DEBUT_FIN_FIELD_NOTIF']);
HIDE_DATE_FISCALE_FIELD = ruleshide['DATE_FISCALE_FIELD_TAXSTM']
    .concat(ruleshide['DATE_FISCALE_FIELD_PRTFSTM'])
    .concat(ruleshide['DATE_FISCALE_FIELD_TradeConfig'])
    .concat(ruleshide['DATE_FISCALE_FIELD_NOTIF']);
HIDE_OPERATIONS_FIELD = ruleshide['OPERATIONS_FIELD_TAXSTM']
    .concat(ruleshide['OPERATIONS_FIELD_PRTFSTM'])
    .concat(ruleshide['OPERATIONS_FIELD_TradeConfig'])
    .concat(ruleshide['OPERATIONS_FIELD_NOTIF']);
HIDE_CODE_PRODUIT_FIELD = ruleshide['CODE_PRODUIT_FIELD_TAXSTM']
    .concat(ruleshide['CODE_PRODUIT_FIELD_PRTFSTM'])
    .concat(ruleshide['CODE_PRODUIT_FIELD_TradeConfig'])
    .concat(ruleshide['CODE_PRODUIT_FIELD_NOTIF']);

document.addEventListener('change', function(event) {
    if(event.target.id === 'TypeDocument'|| event.target.id ==='Courtier') {
        const courtier = document.getElementById('Courtier').value;
        const TypeDocument = document.getElementById('TypeDocument').value;
        const concatCourtierTypeDocument = courtier + "#" + TypeDocument;
        // REGLE CHAMP SOUS-TYPE DE DOCUMENT
        if (!HIDE_SOUS_TYPE_FIELD.includes(concatCourtierTypeDocument)) {
            document.getElementById('SousTypeDocument').value='';
            document.getElementById('ligneSousTypeDocument').style.display='none';
        } else {
            document.getElementById('SousTypeDocument').value='';
            document.getElementById('ligneSousTypeDocument').removeAttribute('style');
        }
        // REGLE CHAMP DATE DEBUT ET FIN
        if (!HIDE_DATES_DEBUT_FIN_FIELD.includes(concatCourtierTypeDocument)) {
            document.getElementById('DateDebut').value='';
            document.getElementById('ligneDateDebut').style.display='none';
            document.getElementById('DateFin').value='';
            document.getElementById('ligneDateFin').style.display='none';
        } else {
            document.getElementById('DateDebut').value=new Date().toISOString().split('T')[0];;
            document.getElementById('DateFin').value=new Date().toISOString().split('T')[0];;
            document.getElementById('ligneDateDebut').removeAttribute('style');
            document.getElementById('ligneDateFin').removeAttribute('style');
        }
        // REGLE CHAMP ANNE FISCALE
        if (!HIDE_DATE_FISCALE_FIELD.includes(concatCourtierTypeDocument)) {
            document.getElementById('AnneeFiscale').value='';
            document.getElementById('ligneAnneeFiscale').style.display='none';
        } else {
            document.getElementById('AnneeFiscale').value=new Date().getFullYear();
            document.getElementById('ligneAnneeFiscale').removeAttribute('style');
        }
        // REGLE CHAMP OPERATION
        if (!HIDE_OPERATIONS_FIELD.includes(concatCourtierTypeDocument)) {
            document.getElementById('Operation').value='';
            document.getElementById('ligneOperation').style.display='none';
        } else {
            document.getElementById('Operation').value='';
            document.getElementById('ligneOperation').removeAttribute('style');
        }
        // REGLE CHAMP CODE PROUIT
        if (!HIDE_CODE_PRODUIT_FIELD.includes(concatCourtierTypeDocument)) {
            document.getElementById('Codeproduit').value='';
            document.getElementById('ligneCodeProduit').style.display='none';
        } else {
            document.getElementById('Codeproduit').value='';
            document.getElementById('ligneCodeProduit').removeAttribute('style');
        }
        // GESTION DES EXCEPTIONS
        if(
            document.getElementById('Courtier').value==='7947'
            && document.getElementById('TypeDocument').value==='PRTFSTM'
            && document.getElementById('SousTypeDocument').value==='CCAR' ){
                document.getElementById('DateDebut').value='';
                document.getElementById('ligneDateDebut').style.display='none';
                document.getElementById('DateFin').value='';
                document.getElementById('ligneDateFin').style.display='none';
                document.getElementById('ligneAnneFiscale').removeAttribute('style');
                document.getElementById('ligneAnneeFiscale').value=new Date().getFullYear();

        }
    }
})

window.addEventListener('DOMContentLoaded', function (event) {
    console.log("CALLED BY WINDOW.ADDLISTENER");
    const courtierSelect = document.getElementById('Courtier');
    if (courtierSelect) {
        updateDocumentFields(courtierSelect.id, courtierSelect.value,"reload");
    }
    const typeDocumentSelect = document.getElementById('TypeDocument');
    if (typeDocumentSelect) {
        updateDocumentFields(typeDocumentSelect.id, typeDocumentSelect.value);
    }
    const sTypeDocumentSelect = document.getElementById('SousTypeDocument');
    if (false) {
        sTypeDocumentSelect.dispatchEvent(new Event('change'));
    }
});



