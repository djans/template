package ca.nbfg.rbo.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.bnc.spti.rbo.auth.api.*;
import com.bnc.spti.rbo.auth.invoker.*;
import com.bnc.spti.rbo.auth.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertiesUtil {
	
	public static Map<String, String> entries= new HashMap<String, String>();
	static String returnedJndiString;
	static String nodeName;
	Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	public String getProperty(String jndi) {
		returnedJndiString= null;

		if (entries.containsKey(jndi)) {
			logger.debug("PROPERTY FOUND IN THE CACHE: " + jndi);
			return entries.get(jndi);
		} else {
			try {
				logger.debug("PROPERTY RECHERCHE: " + jndi);
//				InitialContext ctx = new InitialContext();
//				returnedJndiString = (String) ctx.lookup("cell/persistent/"+jndi);
				String oktaProp = "okta.properties";
				InputStream oktaInputStream = getClass().getClassLoader().getResourceAsStream(oktaProp);
				Properties prop = new Properties();
				prop.load(oktaInputStream);
				returnedJndiString=prop.getProperty(jndi);
				entries.put(jndi, returnedJndiString);
				logger.debug("PROPERTY VALEUR ; " + jndi + " = " + returnedJndiString);
			} catch (IOException e) {
				e.printStackTrace();
                throw new RuntimeException(e);
            }
            return returnedJndiString;
		}
	}
}