package com.bnc.spti.rbo.dealer.api;

import com.bnc.spti.rbo.dealer.invoker.ApiException;
import com.bnc.spti.rbo.dealer.invoker.ApiClient;
import com.bnc.spti.rbo.dealer.invoker.Configuration;
import com.bnc.spti.rbo.dealer.invoker.Pair;

import javax.ws.rs.core.GenericType;

//import com.bnc.spti.rbo.dealer.model.ErrorResponse;
import com.bnc.spti.rbo.dealer.model.GetDealerStructureResponseType;

import ca.nbfg.rbo.config.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-01-11T09:20:13.171-05:00")
public class DealerStructureApi {
  private ApiClient apiClient;

  public DealerStructureApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DealerStructureApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Get Dealer Structure for all dealers
   * This Method will retrieve all branches and advisors for all dealers
   * @param authorization The OpenID Connect access token. The value is in the format: Bearer access_token_value (required)
   * @param sessionId Rpm session id (required)
   * @return GetDealerStructureResponseType
   * @throws ApiException if fails to make API call
   */
  public GetDealerStructureResponseType getAllDealersStructure(String authorization) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'authorization' is set
    if (authorization == null) {
      throw new ApiException(400, "Missing the required parameter 'authorization' when calling getAllDealersStructure");
    }
    
    // verify the required parameter 'sessionId' is set
//    if (sessionId == null) {
//      throw new ApiException(400, "Missing the required parameter 'sessionId' when calling getAllDealersStructure");
//    }
    
    // create path and map variables
//    String localVarPath = "/v1/dealer/structure";
    String localVarPath = "/wm/v2/dealer-structure";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
    
    /** 
     * Ajout par Daniel JANS 
     * 11 Janvier 2019
     */
    String ibmid = PropertiesUtil.getProperty("rbo/doc/dealer/xibmclientid");
    localVarHeaderParams.put("x-ibm-client-id",ibmid); 
    /**
     * Fin de l'ajout
     */


    if (authorization != null)
      localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));
    /**
     * Suppression du SessionId
     * Auteur : Daniel JANS
     * Date : 28 août 2019
     * Raison : on ne passe plus par RPM pour l'authentification
     */
//if (sessionId != null)
//      localVarHeaderParams.put("sessionId", apiClient.parameterToString(sessionId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<GetDealerStructureResponseType> localVarReturnType = new GenericType<GetDealerStructureResponseType>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Get Dealer Structure using the Dealer Code
   * This Method will retrieve all branches and advisors related to a specific dealer
   * @param orgUnitCd Dealer Code (required)
   * @param authorization The OpenID Connect access token. The value is in the format: Bearer access_token_value (required)
   * @param sessionId Rpm session id (required)
   * @return GetDealerStructureResponseType
   * @throws ApiException if fails to make API call
   */
  public GetDealerStructureResponseType getDealerStructure(String orgUnitCd, String authorization, String sessionId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'orgUnitCd' is set
    if (orgUnitCd == null) {
      throw new ApiException(400, "Missing the required parameter 'orgUnitCd' when calling getDealerStructure");
    }
    
    // verify the required parameter 'authorization' is set
    if (authorization == null) {
      throw new ApiException(400, "Missing the required parameter 'authorization' when calling getDealerStructure");
    }
    
    // verify the required parameter 'sessionId' is set
    if (sessionId == null) {
      throw new ApiException(400, "Missing the required parameter 'sessionId' when calling getDealerStructure");
    }
    
    // create path and map variables
    String localVarPath = "/v1/dealer/structure/{orgUnitCd}"
      .replaceAll("\\{" + "orgUnitCd" + "\\}", apiClient.escapeString(orgUnitCd.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
    
    /** 
     * Ajout par Daniel JANS 
     * 11 Janvier 2019
     */
    String ibmid = PropertiesUtil.getProperty("rbo/doc/dealer/xibmclientid");
    localVarHeaderParams.put("x-ibm-client-id",ibmid); 
    /**
     * Fin de l'ajout
     */


    if (authorization != null)
      localVarHeaderParams.put("Authorization", apiClient.parameterToString(authorization));
if (sessionId != null)
      localVarHeaderParams.put("sessionId", apiClient.parameterToString(sessionId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<GetDealerStructureResponseType> localVarReturnType = new GenericType<GetDealerStructureResponseType>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
