package com.bnc.spti.rbo.docs.api;

import com.bnc.spti.rbo.docs.invoker.ApiException;
import com.bnc.spti.rbo.docs.invoker.ApiClient;
import com.bnc.spti.rbo.docs.invoker.Configuration;
import com.bnc.spti.rbo.docs.invoker.Pair;

import javax.ws.rs.core.GenericType;

import com.bnc.spti.rbo.docs.model.Document;
import com.bnc.spti.rbo.docs.model.Error;
import java.io.File;
import com.bnc.spti.rbo.docs.model.Metadata;
import com.bnc.spti.rbo.docs.model.SearchCriteria;
import com.bnc.spti.rbo.docs.model.SearchResult;

import ca.nbfg.rbo.config.PropertiesUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2019-08-07T11:44:33.827-04:00")
public class SystemEmployeeApiDocumentManagementV5Api {
  private ApiClient apiClient;

  public SystemEmployeeApiDocumentManagementV5Api() {
    this(Configuration.getDefaultApiClient());
  }

  public SystemEmployeeApiDocumentManagementV5Api(ApiClient apiClient) {
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
   * @param businessViewId The business view Id (required)
   * @param content The document to upload (required)
   * @param metadata The metadata list attached to the document (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object createDocumentPrivateUsingPOST1(String businessViewId, File content, File metadata) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'businessViewId' is set
    if (businessViewId == null) {
      throw new ApiException(400, "Missing the required parameter 'businessViewId' when calling createDocumentPrivateUsingPOST1");
    }
    
    // verify the required parameter 'content' is set
    if (content == null) {
      throw new ApiException(400, "Missing the required parameter 'content' when calling createDocumentPrivateUsingPOST1");
    }
    
    // verify the required parameter 'metadata' is set
    if (metadata == null) {
      throw new ApiException(400, "Missing the required parameter 'metadata' when calling createDocumentPrivateUsingPOST1");
    }
    
    // create path and map variables
    String localVarPath = "/documents";

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (businessViewId != null)
      localVarHeaderParams.put("businessViewId", apiClient.parameterToString(businessViewId));

    if (content != null)
      localVarFormParams.put("content", content);
if (metadata != null)
      localVarFormParams.put("metadata", metadata);

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
   * @param businessViewId The Business View Id (required)
   * @param docId The Document Id (required)
   * @return Document
   * @throws ApiException if fails to make API call
   */
  public Document getDocumentUsingGET1(String businessViewId, String docId) throws ApiException {
    Object localVarPostBody = null;
    
    // verify the required parameter 'businessViewId' is set
    if (businessViewId == null) {
      throw new ApiException(400, "Missing the required parameter 'businessViewId' when calling getDocumentUsingGET1");
    }
    
    // verify the required parameter 'docId' is set
    if (docId == null) {
      throw new ApiException(400, "Missing the required parameter 'docId' when calling getDocumentUsingGET1");
    }
    /** 
     * Mofification par Daniel JANS 
     * 09 Août 2019
     */
    // create path and map variables
    String localVarPath = "/dcmgmt/v5/documents/{docId}/content"
      .replaceAll("\\{" + "docId" + "\\}", apiClient.escapeString(docId.toString()));
    /**
     * Fin de la modification
     */
    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
    
    /** 
     * Ajout par Daniel JANS 
     * 07 Août 2019
     */
    String ibmid = PropertiesUtil.getProperty("rbo/doc/docs/xibmclientid");
    localVarHeaderParams.put("x-ibm-client-id",ibmid); 
    /**
     * Fin de l'ajout
     */


    if (businessViewId != null)
      localVarHeaderParams.put("businessViewId", apiClient.parameterToString(businessViewId));

    
    final String[] localVarAccepts = {
      "application/octet-stream", "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<Document> localVarReturnType = new GenericType<Document>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Search documents
   * Search Document by criteria
   * @param businessViewId The Business View Id (required)
   * @param searchCriteria searchCriteria (required)
   * @return SearchResult
   * @throws ApiException if fails to make API call
   */
  public SearchResult searchUsingPOST1(String businessViewId, SearchCriteria searchCriteria) throws ApiException {
    Object localVarPostBody = searchCriteria;
    
    // verify the required parameter 'businessViewId' is set
    if (businessViewId == null) {
      throw new ApiException(400, "Missing the required parameter 'businessViewId' when calling searchUsingPOST1");
    }
    
    // verify the required parameter 'searchCriteria' is set
    if (searchCriteria == null) {
      throw new ApiException(400, "Missing the required parameter 'searchCriteria' when calling searchUsingPOST1");
    }
    
    /** 
     * Mofification par Daniel JANS 
     * 09 Août 2019
     */
    // create path and map variables
    String localVarPath = "/dcmgmt/v5/documents/criteria";
    /**
     * Fin de la modification
     */
    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();
    
    /** 
     * Ajout par Daniel JANS 
     * 07 Août 2019
     */
    String ibmid = PropertiesUtil.getProperty("rbo/doc/docs/xibmclientid");
    localVarHeaderParams.put("x-ibm-client-id",ibmid); 
    /**
     * Fin de l'ajout
     */


    if (businessViewId != null)
      localVarHeaderParams.put("businessViewId", apiClient.parameterToString(businessViewId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<SearchResult> localVarReturnType = new GenericType<SearchResult>() {};
    return apiClient.invokeAPI(localVarPath, "POST", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Update document metadata
   * Update document metadata by DocID
   * @param businessViewId The Business View Id (required)
   * @param docId The Document Id (required)
   * @param request The JSON request (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object updateUsingPATCH1(String businessViewId, String docId, List<Metadata> request) throws ApiException {
    Object localVarPostBody = request;
    
    // verify the required parameter 'businessViewId' is set
    if (businessViewId == null) {
      throw new ApiException(400, "Missing the required parameter 'businessViewId' when calling updateUsingPATCH1");
    }
    
    // verify the required parameter 'docId' is set
    if (docId == null) {
      throw new ApiException(400, "Missing the required parameter 'docId' when calling updateUsingPATCH1");
    }
    
    // verify the required parameter 'request' is set
    if (request == null) {
      throw new ApiException(400, "Missing the required parameter 'request' when calling updateUsingPATCH1");
    }
    
    // create path and map variables
    String localVarPath = "/documents/{docId}/metadata"
      .replaceAll("\\{" + "docId" + "\\}", apiClient.escapeString(docId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (businessViewId != null)
      localVarHeaderParams.put("businessViewId", apiClient.parameterToString(businessViewId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "PATCH", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Update document metadata
   * Update document metadata by DocID
   * @param businessViewId The Business View Id (required)
   * @param docId The Document Id (required)
   * @param request The JSON request (required)
   * @return Object
   * @throws ApiException if fails to make API call
   */
  public Object updateUsingPUT1(String businessViewId, String docId, List<Metadata> request) throws ApiException {
    Object localVarPostBody = request;
    
    // verify the required parameter 'businessViewId' is set
    if (businessViewId == null) {
      throw new ApiException(400, "Missing the required parameter 'businessViewId' when calling updateUsingPUT1");
    }
    
    // verify the required parameter 'docId' is set
    if (docId == null) {
      throw new ApiException(400, "Missing the required parameter 'docId' when calling updateUsingPUT1");
    }
    
    // verify the required parameter 'request' is set
    if (request == null) {
      throw new ApiException(400, "Missing the required parameter 'request' when calling updateUsingPUT1");
    }
    
    // create path and map variables
    String localVarPath = "/documents/{docId}/metadata"
      .replaceAll("\\{" + "docId" + "\\}", apiClient.escapeString(docId.toString()));

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    if (businessViewId != null)
      localVarHeaderParams.put("businessViewId", apiClient.parameterToString(businessViewId));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      "application/json"
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "api-key-1" };

    GenericType<Object> localVarReturnType = new GenericType<Object>() {};
    return apiClient.invokeAPI(localVarPath, "PUT", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
