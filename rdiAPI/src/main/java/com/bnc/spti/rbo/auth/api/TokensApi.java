package com.bnc.spti.rbo.auth.api;

import com.bnc.spti.rbo.auth.invoker.ApiException;
import com.bnc.spti.rbo.auth.invoker.ApiClient;
import com.bnc.spti.rbo.auth.invoker.Configuration;
import com.bnc.spti.rbo.auth.invoker.Pair;

import jakarta.ws.rs.core.GenericType;

import org.apache.commons.codec.binary.Base64;

import com.bnc.spti.rbo.auth.model.IamxProspectRequest;
import com.bnc.spti.rbo.auth.model.IamxTokenRequest;
import com.bnc.spti.rbo.auth.model.IamxTokenResponse;
import com.bnc.spti.rbo.auth.model.ResponseMessage;
import com.bnc.spti.rbo.auth.model.ServiceInformation;

import ca.nbfg.rbo.config.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2018-11-22T11:03:19.343-05:00")
public class TokensApi {
  private ApiClient apiClient;

  public TokensApi() {
    this(Configuration.getDefaultApiClient());
  }

  public TokensApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Create a token to use for captcha protected forms
   * This API is for non-authenticated user.
   * @param iamxProspectRequest The recaptcha response representing the value of the recaptcha.  (required)
   * @return IamxTokenResponse
   * @throws ApiException if fails to make API call
   */
  public IamxTokenResponse createCaptchaToken(IamxProspectRequest iamxProspectRequest) throws ApiException {
    Object localVarPostBody = iamxProspectRequest;
    
    // verify the required parameter 'iamxProspectRequest' is set
    if (iamxProspectRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'iamxProspectRequest' when calling createCaptchaToken");
    }
    
    // create path and map variables
    String localVarPath = "/tokens/captcha";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
  
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
    		
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "clientIdHeader" };

    GenericType<IamxTokenResponse> localVarReturnType = new GenericType<IamxTokenResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Create a token
   * This API requires Authentication Bearer
   * @param iamxTokenRequest Create Token Payload Object  (required)
   * @return IamxTokenResponse
   * @throws ApiException if fails to make API call
   */
  public IamxTokenResponse createToken(IamxTokenRequest iamxTokenRequest) throws ApiException {
    Object localVarPostBody = iamxTokenRequest;
    
    // verify the required parameter 'iamxTokenRequest' is set
    if (iamxTokenRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'iamxTokenRequest' when calling createToken");
    }
    
    // create path and map variables
    
    /** 	Modification par Daniel JANS - QUERY PARAMETERS
     *    Valeur originale : String localVarPath = "/token";
     */
   
    String localVarPath = "/token";
    /** Fin de Modification par Daniel JANS - QUERY PARAMETERS  */
    
    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
    
    /** 
     * Ajout par Daniel JANS 
     * 06 Janvier 2020
     */
    localVarFormParams.put("grant_type",iamxTokenRequest.getGrantType());
//    localVarFormParams.put("scope",iamxTokenRequest.getScope());
    localVarFormParams.put("redirect_uri", PropertiesUtil.getProperty("rbo/token/user/redirecturi"));
    localVarFormParams.put("client_id",iamxTokenRequest.getClientId());
    localVarFormParams.put("code",iamxTokenRequest.getCode());
    localVarFormParams.put("code_verifier",iamxTokenRequest.getCodeVerifier());
    
    String client_id = iamxTokenRequest.getClientId();
    String client_secret = iamxTokenRequest.getCode();    
    String toEncode = client_id+":"+client_secret;
    byte[] encodedBytes = Base64.encodeBase64(toEncode.getBytes());
    String encodeauthorization = new String(encodedBytes);
    
//    localVarHeaderParams.put("Authorization","Basic "+encodeauthorization);
    
    /**  Fin de Ajout par Daniel JANS  */
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
    		/**
    		 * Ajout par Daniel JANS
    		 * 28 Novembre 2018
    		 */
    		"application/x-www-form-urlencoded"
    		/** Fin de Ajout par Daniel JANS */
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "clientIdHeader" };

    GenericType<IamxTokenResponse> localVarReturnType = new GenericType<IamxTokenResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}