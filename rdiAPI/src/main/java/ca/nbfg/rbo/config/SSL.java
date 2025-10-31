package ca.nbfg.rbo.config;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.Properties;

public class SSL {
    public  SSLContext createSSLContext()  {
        Properties prop = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sslsetting.properties");
        SSLContext sslContext=null;
        try {
            prop.load(inputStream);
            String keyStorePath = prop.getProperty("keyStore.path");
            String keyStorePassword = prop.getProperty("keyStore.password");
            String trustStorePath = prop.getProperty("trustStore.path");
            String trustStorePassword = prop.getProperty("trustStore.password");
            KeyStore keyStore = KeyStore.getInstance("JKS");
            //LOAD Keystore
            try( InputStream jksInputStream = getClass().getClassLoader().getResourceAsStream(keyStorePath)){
                keyStore.load(jksInputStream, keyStorePassword.toCharArray());
            };
            // LOAD TrustStore
            KeyStore trustStore = KeyStore.getInstance("JKS");
            try( InputStream jksInputStream = getClass().getClassLoader().getResourceAsStream(trustStorePath)){
                trustStore.load(jksInputStream, trustStorePassword.toCharArray());
            };
            // Initialiaze KeyStore KeyManager
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(keyStore, keyStorePassword.toCharArray());
            // Initialiaze TrustStore KeyManager
            TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            tmf.init(trustStore);
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sslContext;

    }
}
