package com.bnc.spti.rbo.docs4.api;

import com.bnc.spti.rbo.docs4.invoker.ApiException;
import com.bnc.spti.rbo.docs4.invoker.ApiClient;
import com.bnc.spti.rbo.docs4.invoker.Configuration;
import com.bnc.spti.rbo.docs4.invoker.Pair;

import javax.ws.rs.core.GenericType;

import com.bnc.spti.rbo.docs4.model.APICreateResponse;
import com.bnc.spti.rbo.docs4.model.APIGetResponse;
import com.bnc.spti.rbo.docs4.model.APIResponseError;
import com.bnc.spti.rbo.docs4.model.APISearchResponse;
import com.bnc.spti.rbo.docs4.model.APIUpdateResponse;
import com.bnc.spti.rbo.docs4.model.ClientSearchRequest;
import com.bnc.spti.rbo.docs4.model.ClientUpdateRequest;

import ca.nbfg.rbo.config.PropertiesUtil;

import java.io.File;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-10-17T09:20:33.591-04:00")
public class SystemEmployeeApiDocumentManagementApi {
  private ApiClient apiClient;

  public SystemEmployeeApiDocumentManagementApi() {
    this(Configuration.getDefaultApiClient());
  }

  public SystemEmployeeApiDocumentManagementApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Create new document
   * Create Document and returns DocID
   * @param fileContent The document to upload (required)
   * @param fileMetadata The metadata list attached to the document (required)
   * @param viewId viewId (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object createDocumentPrivateUsingPOST(File fileContent, File fileMetadata, String viewId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'fileContent' is set
    if (fileContent == null) {
      throw new ApiException(400, "Missing the required parameter 'fileContent' when calling createDocumentPrivateUsingPOST");
    }
    
    // verify the required parameter 'fileMetadata' is set
    if (fileMetadata == null) {
      throw new ApiException(400, "Missing the required parameter 'fileMetadata' when calling createDocumentPrivateUsingPOST");
    }
    
    // verify the required parameter 'viewId' is set
    if (viewId == null) {
      throw new ApiException(400, "Missing the required parameter 'viewId' when calling createDocumentPrivateUsingPOST");
    }
    
    // create path and map variables
    String localVarPath = "/documents";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (viewId != null)
      localVarHeaderParams.put("viewId", apiClient.parameterToString(viewId));

    if (fileContent != null)
      localVarFormParams.put("fileContent", fileContent);
if (fileMetadata != null)
      localVarFormParams.put("fileMetadata", fileMetadata);

    final String[] localVarAccepts = {
      "*/*"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "multipart/form-data"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Get document
   * Get Document by DocID
   * @param docId docId (required)
   * @param viewId viewId (required)
   * @return APIGetResponse
   * @throws ApiException if fails to make API call
   */
  public APIGetResponse getDocumentUsingGET(String docId, String viewId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'docId' is set
    if (docId == null) {
      throw new ApiException(400, "Missing the required parameter 'docId' when calling getDocumentUsingGET");
    }
    
    // verify the required parameter 'viewId' is set
    if (viewId == null) {
      throw new ApiException(400, "Missing the required parameter 'viewId' when calling getDocumentUsingGET");
    }
    
    // create path and map variables
    String localVarPath = "/doc-mgmt/v4/documents";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
    /** 
     * Ajout par Daniel JANS 
     * 18 Octobre 2019
     */
    String ibmid = PropertiesUtil.getProperty("rbo/doc/docs/xibmclientid");
    localVarHeaderParams.put("x-ibm-client-id",ibmid); 
    /**
     * Fin de l'ajout
     */

    if (docId != null)
      localVarHeaderParams.put("docId", apiClient.parameterToString(docId));
if (viewId != null)
      localVarHeaderParams.put("viewId", apiClient.parameterToString(viewId));

    
    final String[] localVarAccepts = {
      "application/octet-stream", "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<APIGetResponse> localVarReturnType = new GenericType<APIGetResponse>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Search documents
   * Search Document by client request SQL
   * @param clientSearchRequest clientSearchRequest (required)
   * @param viewId viewId (required)
   * @return APISearchResponse
   * @throws ApiException if fails to make API call
   */
  public APISearchResponse searchUsingPOST(ClientSearchRequest clientSearchRequest, String viewId) throws ApiException {
    Object localVarPostBody = clientSearchRequest;
    
    // verify the required parameter 'clientSearchRequest' is set
    if (clientSearchRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'clientSearchRequest' when calling searchUsingPOST");
    }
    
    // verify the required parameter 'viewId' is set
    if (viewId == null) {
      throw new ApiException(400, "Missing the required parameter 'viewId' when calling searchUsingPOST");
    }
    
    // create path and map variables
    String localVarPath = "/doc-mgmt/v4/documents/search";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
    
    /** 
     * Ajout par Daniel JANS 
     * 18 Octobre 2019
     */
    String ibmid = PropertiesUtil.getProperty("rbo/doc/docs/xibmclientid");
    localVarHeaderParams.put("x-ibm-client-id",ibmid); 
    /**
     * Fin de l'ajout
     */


    if (viewId != null)
      localVarHeaderParams.put("viewId", apiClient.parameterToString(viewId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<APISearchResponse> localVarReturnType = new GenericType<APISearchResponse>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Update document metadata
   * Update document metadata by DocID
   * @param clientUpdateRequest clientUpdateRequest (required)
   * @param viewId viewId (required)
   * @return APIUpdateResponse
   * @throws ApiException if fails to make API call
   */
  public APIUpdateResponse updateUsingPATCH(ClientUpdateRequest clientUpdateRequest, String viewId) throws ApiException {
    Object localVarPostBody = clientUpdateRequest;
    
    // verify the required parameter 'clientUpdateRequest' is set
    if (clientUpdateRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'clientUpdateRequest' when calling updateUsingPATCH");
    }
    
    // verify the required parameter 'viewId' is set
    if (viewId == null) {
      throw new ApiException(400, "Missing the required parameter 'viewId' when calling updateUsingPATCH");
    }
    
    // create path and map variables
    String localVarPath = "/documents";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (viewId != null)
      localVarHeaderParams.put("viewId", apiClient.parameterToString(viewId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<APIUpdateResponse> localVarReturnType = new GenericType<APIUpdateResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Update document metadata
   * Update document metadata by DocID
   * @param clientUpdateRequest clientUpdateRequest (required)
   * @param viewId viewId (required)
   * @return APIUpdateResponse
   * @throws ApiException if fails to make API call
   */
  public APIUpdateResponse updateUsingPUT(ClientUpdateRequest clientUpdateRequest, String viewId) throws ApiException {
    Object localVarPostBody = clientUpdateRequest;
    
    // verify the required parameter 'clientUpdateRequest' is set
    if (clientUpdateRequest == null) {
      throw new ApiException(400, "Missing the required parameter 'clientUpdateRequest' when calling updateUsingPUT");
    }
    
    // verify the required parameter 'viewId' is set
    if (viewId == null) {
      throw new ApiException(400, "Missing the required parameter 'viewId' when calling updateUsingPUT");
    }
    
    // create path and map variables
    String localVarPath = "/documents";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (viewId != null)
      localVarHeaderParams.put("viewId", apiClient.parameterToString(viewId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<APIUpdateResponse> localVarReturnType = new GenericType<APIUpdateResponse>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
