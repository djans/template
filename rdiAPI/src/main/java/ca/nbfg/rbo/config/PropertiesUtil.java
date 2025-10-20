package ca.nbfg.rbo.config;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import java.util.HashMap;
import java.util.Map;

import com.bnc.spti.rbo.auth.api.*;
import com.bnc.spti.rbo.auth.invoker.*;
import com.bnc.spti.rbo.auth.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PropertiesUtil {
	
	public static Map<String, String> entries= new HashMap<String, String>();
	static String returnedJndiString;
	static String nodeName;
	public static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

	public static String getProperty(String jndi) {		
		returnedJndiString= null;

		if (entries.containsKey(jndi)) {
			return entries.get(jndi);
		} else {
			try {
				logger.debug("JNDI RECHERCHE: " + jndi);
				InitialContext ctx = new InitialContext();
				returnedJndiString = (String) ctx.lookup("cell/persistent/"+jndi);
				entries.put(jndi, returnedJndiString);
				logger.debug("JNDI VALEUR ; " + jndi + " = " + returnedJndiString);
			} catch (NamingException e) {
				e.printStackTrace();
			}
			return returnedJndiString;
		}
	}
}