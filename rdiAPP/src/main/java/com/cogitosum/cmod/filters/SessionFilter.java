package com.cogitosum.cmod.filters;

import ca.nbfg.rbo.config.TokenGenerator;
import com.bnc.spti.rbo.auth.invoker.ApiClient;
import com.bnc.spti.rbo.auth.invoker.ApiException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Request;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



import javax.crypto.SecretKey;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;


@Component
public class SessionFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(SessionFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        System.out.println("Incoming request: " + req.getRequestURI());
        HttpSession session = req.getSession();
        if (request.getParameter("code") != null && session.getAttribute("code") == null ) {            
            String token =this.getJwtToken(request.getParameter("code"));
            session.setAttribute("token", request.getParameter("token"));
        } else if(session.getAttribute("code") == null){
            HttpServletResponse res = (HttpServletResponse) response;
            res.sendRedirect("https://trial-4877959.okta.com/oauth2/v1/authorize?client_id=0oawbjp7n3GIrOihx697&response_type=code&scope=openid&redirect_uri=https%3A%2F%2Faurora.cogitosum.com%2F&state=state-296bc9a0-a2a2-4a57-be1a-d0e2fd9bb601");
        }
        // Continue the filter chain
        chain.doFilter(request, response);
        
    }

    private String getJwtToken(String code) {
        String token=null;
        ApiClient apiclient = new ApiClient();
        apiclient.setBasePath("");
        apiclient.setDebugging(true);
        TokenGenerator tokengenerator = new TokenGenerator();
        try {
            token= tokengenerator.generateUserToken(apiclient,code,this.createCodeVerifier());
            logger.debug("Token generated: " + token);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
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
        logger.debug("code_verifier: " + code_verifier);
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
            challenge = Base64.getEncoder().encodeToString(digest);
            logger.debug("challenge: " + challenge);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return challenge;
    }
}

