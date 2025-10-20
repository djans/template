package com.cogitosum.cmod.services;


import ca.nbfg.rbo.config.TokenGenerator;
import com.bnc.spti.rbo.auth.invoker.ApiClient;
import com.bnc.spti.rbo.auth.invoker.ApiException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Calendar;

public class CallAuth {
    String username;

    public static Calendar timeOut;
    public static String token;


    public Calendar calUser;
    public Calendar timOutUser;


    public  String getUserToken(String code, String code_verifier){
        Logger logger = LogManager.getLogger(CallAuth.class);
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("https://aurora.cogitosum.com/");
        TokenGenerator tokenGenerator = new TokenGenerator();
        try {
            token = tokenGenerator.generateUserToken(apiClient,code,code_verifier);
        } catch (ApiException e) {
            e.printStackTrace();
            logger.debug("Error generating user token");
            logger.debug("Message: " + e.getMessage());
            throw new RuntimeException(e);
        }


        return "";
    }
}
