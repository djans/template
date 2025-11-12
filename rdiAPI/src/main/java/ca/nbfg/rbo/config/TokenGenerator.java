package ca.nbfg.rbo.config;

import com.bnc.spti.rbo.auth.invoker.ApiClient;
import com.bnc.spti.rbo.auth.api.TokensApi;
import com.bnc.spti.rbo.auth.model.IamxTokenRequest;
import com.bnc.spti.rbo.auth.model.IamxTokenResponse;
import com.bnc.spti.rbo.auth.api.*;
import com.bnc.spti.rbo.auth.invoker.*;
import com.bnc.spti.rbo.auth.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class TokenGenerator {
	Logger logger = LoggerFactory.getLogger(TokenGenerator.class);

	public static Calendar timeOut;
	public static String token;
	public static Calendar cal;

	public Calendar timeOutUser;
	public Calendar calUser;
	public String o() {
		return "";
	}


	public String generateUserToken(ApiClient client
			, String code, String code_verifier) throws ApiException {
		logger.debug("GENERATING A USER TOKEN");
		calUser = Calendar.getInstance();
		String resultToken = new String();
		IamxTokenRequest tokenRequest = new IamxTokenRequest();
		tokenRequest.setClientId(new PropertiesUtil().getProperty("clientid"));
		tokenRequest.setCode(code);
		tokenRequest.setGrantType(new PropertiesUtil().getProperty("granttype"));
//		tokenRequest.setScope(scope);
		tokenRequest.setCodeVerifier(code_verifier);
//		logger.debug(tokenRequest.toString());
		TokensApi tokensApi = new TokensApi(client);
		logger.debug("CREATING A TOKEN REQUEST. START");
		IamxTokenResponse iamxTokenResponse = tokensApi.createToken(tokenRequest);
		logger.debug("CREATING A TOKEN REQUEST. END");
		String tokenUser = iamxTokenResponse.getAccessToken();
		String newexpiration = iamxTokenResponse.getExpiresIn();
		int toAdd = new Integer(newexpiration).intValue();
		calUser.add(Calendar.SECOND, toAdd);
		timeOutUser = calUser;		
		logger.debug("TOKEN USER : NOUVEAU TOKEN : " + tokenUser);
		logger.debug("TOKEN USER : EXPIRATION : " + calUser.getTime());
		return tokenUser;
	}

	public static void resetToken() {
		TokenGenerator.timeOut = null;
		TokenGenerator.token = null;
	}

	public boolean timeToRefresh(Calendar dt) {
		if (TokenGenerator.timeOut == null || TokenGenerator.token == null) {
			return true;
		} else if (dt.after(timeOut)) {
			return true;
		} else {
			logger.debug("TOKEN SYSTEM : NON ECHU : " + TokenGenerator.timeOut.getTime());
			return false;
		}
	}
}
