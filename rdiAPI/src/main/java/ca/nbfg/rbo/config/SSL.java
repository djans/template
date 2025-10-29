package ca.nbfg.rbo.config;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.util.Properties;

public class SSL {
    public static SSLContext createSSLContext() throws Exception {
        Properties prop = new Properties();
        prop.load(new FileInputStream("sslsetting.properties"));

        String keyStorePath = prop.getProperty("keyStore.path");
        String keyStorePassword = prop.getProperty("keyStore.password");
        String trustStorePath = prop.getProperty("trustStore.path");
        String trustStorePassword = prop.getProperty("trustStore.password");

        KeyStore keyStore = KeyStore.getInstance("JKS");
        //LOAD Keystore
        try (FileInputStream fis = new FileInputStream(keyStorePath)) {
            keyStore.load(fis, keyStorePassword.toCharArray());
        }
        // LOAD TrustStore
        KeyStore trustStore = KeyStore.getInstance("JKS");
        try (FileInputStream fis = new FileInputStream(trustStorePath)) {
            trustStore.load(fis, trustStorePassword.toCharArray());
        }
        // Initialiaze KeyStore KeyManager
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(keyStore, keyStorePassword.toCharArray());

        // Initialiaze TrustStore KeyManager
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(trustStore);
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        return sslContext;

    }
}
