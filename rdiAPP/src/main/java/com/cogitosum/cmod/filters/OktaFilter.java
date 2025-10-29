package com.cogitosum.cmod.filters;

import ca.nbfg.rbo.config.TokenGenerator;
import com.bnc.spti.rbo.auth.invoker.ApiClient;
import com.bnc.spti.rbo.auth.invoker.ApiException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;
import org.springframework.core.annotation.Order;
import org.springframework.boot.web.servlet.FilterRegistration;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

@Component
@Order(1)
@FilterRegistration(urlPatterns = "/*")
public class OktaFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(OktaFilter.class);
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session =  req.getSession();
        if (req.getParameter("code") != null && session.getAttribute("code") == null ) {
            String token =this.getJwtToken(req.getParameter("code"));
            session.setAttribute("token", req.getParameter("token"));
        filterChain.doFilter(req, resp);
        } else if(session.getAttribute("code") == null){
            HttpServletResponse res = (HttpServletResponse) resp;
            res.sendRedirect("https://trial-4877959.okta.com/oauth2/v1/authorize?client_id=0oawbjp7n3GIrOihx697&response_type=code&scope=openid&redirect_uri=https%3A%2F%2Faurora.cogitosum.com%2F&state=state-296bc9a0-a2a2-4a57-be1a-d0e2fd9bb601");
        }
    }
    private String getJwtToken(String code) {
        String token=null;
        ApiClient apiclient = new ApiClient();
        apiclient.setBasePath("https://trial-4877959.okta.com/oauth2/v1");
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
