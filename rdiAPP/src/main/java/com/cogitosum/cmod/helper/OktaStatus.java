package com.cogitosum.cmod.helper;

import ca.nbfg.rbo.config.TokenGenerator;
import com.bnc.spti.rbo.auth.invoker.ApiClient;
import com.bnc.spti.rbo.auth.invoker.ApiException;
import com.cogitosum.cmod.filters.OktaFilter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class OktaStatus {
    Logger logger = LoggerFactory.getLogger(OktaStatus.class);

    public void checkStatus(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI();
        String status=null;
        // Handling the request for code/jwt token
        HttpSession session =  request.getSession();
        if (request.getParameter("code") != null && session.getAttribute("token") == null ) {
            logger.debug("GETTING A TOKEN");
            String token =this.getJwtToken(request);
            session.setAttribute("token", token);
        }else if(session.getAttribute("token") == null){
            logger.debug("GETTING A CODE");
            // Creation code_verifer and challenge. Adding code_verifier to the session when client comes back
            String code_verifier = this.createCodeVerifier();
            request.getSession().setAttribute("code_verifier", code_verifier);
            String challenge = this.createChallenge(code_verifier);
            response.sendRedirect("https://trial-4720571.okta.com/oauth2/ausx9881y2wfrfUSX697/v1/authorize?client_id=0oaxa7vyyifeUn7N3697&response_type=code&scope=Role01&redirect_uri=https%3A%2F%2Faurora.cogitosum.com%2F&state=state-296bc9a0-a2a2-4a57-be1a-d0e2fd9bb601&code_challenge_method=S256&code_challenge="+challenge);
        } else {
            logger.debug("ALREADY LOGGED IN OKTA");
        }
    }

    private String getJwtToken(HttpServletRequest request) {
        // The Api Client
        ApiClient apiclient = new ApiClient();
        apiclient.setBasePath("https://trial-4720571.okta.com/oauth2/ausx9881y2wfrfUSX697/v1");
        apiclient.setDebugging(true);
        TokenGenerator tokengenerator = new TokenGenerator();
        // The code of the client
        String code = request.getParameter("code");
        // The token
        String token=null;
        logger.debug("GETTING A JWTTOKEN");
        try {
            HttpSession session = request.getSession();
            String code_verifier = session.getAttribute("code_verifier").toString();
            token= tokengenerator.generateUserToken(apiclient,code,code_verifier);
            logger.debug("Token generated: " + token);
        } catch (ApiException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        logger.debug("JWT Token : " + token);
        return token;
    }

    private String createCodeVerifier(){
        final String AB="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom secureRandom = new SecureRandom();
        StringBuilder sb = new StringBuilder(64);
        for (int i = 0; i < 64; i++) {
            sb.append(AB.charAt(secureRandom.nextInt(AB.length())));
        }
        String code_verifier=  sb.toString();
        logger.debug("CODE VERIFIER : " + code_verifier);
        return code_verifier;
    }

    private String createChallenge(String codeVerifier) {
        MessageDigest md;
        String challenge = null;
        try {
            byte[] bytes = codeVerifier.getBytes("US-ASCII");
            md = MessageDigest.getInstance("SHA-256");
            md.update(bytes, 0, bytes.length);
            byte[] digest = md.digest();
            challenge = Base64.getUrlEncoder().withoutPadding().encodeToString(digest);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        logger.debug("CHALLENGE: " + challenge);
        return challenge;
    }
}
