# SystemEmployeeApiDocumentManagementV5Api

All URIs are relative to *https://localhost:9010/ecm/dcmgmt/v5*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**createDocumentPrivateUsingPOST1**](SystemEmployeeApiDocumentManagementV5Api.md#createDocumentPrivateUsingPOST1) | **POST** /documents | Create new document |
| [**getDocumentUsingGET1**](SystemEmployeeApiDocumentManagementV5Api.md#getDocumentUsingGET1) | **GET** /documents/{docId}/content | Get document |
| [**searchUsingPOST1**](SystemEmployeeApiDocumentManagementV5Api.md#searchUsingPOST1) | **POST** /documents/criteria | Search documents |


<a id="createDocumentPrivateUsingPOST1"></a>
# **createDocumentPrivateUsingPOST1**
> Object createDocumentPrivateUsingPOST1(businessViewId, content, metadata)

Create new document

Create Document and returns DocID

### Example

```java
// Import classes:

import com.cogitosum.rdi.api.ApiClient;
import com.cogitosum.rdi.api.ApiException;
import com.cogitosum.rdi.api.Configuration;
import com.cogitosum.rdi.api.auth.ApiKeyAuth;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import com.cogitosum.rdi.api.api.SystemEmployeeApiDocumentManagementV5Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://localhost:9010/ecm/dcmgmt/v5");

        // Configure API key authorization: api-key-1
        ApiKeyAuth api -key - 1 = (ApiKeyAuth) defaultClient.getAuthentication("api-key-1");
        api - key - 1. setApiKey("YOUR API KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //api-key-1.setApiKeyPrefix("Token");

        SystemEmployeeApiDocumentManagementV5Api apiInstance = new SystemEmployeeApiDocumentManagementV5Api(defaultClient);
        String businessViewId = "businessViewId_example"; // String | The business view Id
        File content = new File("/path/to/file"); // File | The document to upload
        File metadata = new File("/path/to/file"); // File | The metadata list attached to the document
        try {
            Object result = apiInstance.createDocumentPrivateUsingPOST1(businessViewId, content, metadata);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SystemEmployeeApiDocumentManagementV5Api#createDocumentPrivateUsingPOST1");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **businessViewId** | **String**| The business view Id | |
| **content** | **File**| The document to upload | |
| **metadata** | **File**| The metadata list attached to the document | |

### Return type

**Object**

### Authorization

[api-key-1](../README.md#api-key-1)

### HTTP request headers

 - **Content-Type**: multipart/form-data
 - **Accept**: */*

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | OK |  -  |
| **201** | docId, document ID |  -  |
| **400** | Bad Request, global error. Code ERR_CMN_TE_02 |  -  |
| **404** | Not found, global error. Code ERR_CMN_TE_03 |  -  |
| **412** | Gateway mapping issue , Code ERR_CRE_TE_07 |  -  |
| **417** | Gateway config issue, Code ERR_CRE_TE_01 , ERR_CRE_TE_02, ERR_CRE_TE_08, ERR_CRE_TE_09 , ERR_CRE_TE_10,ERR_HDR_TE_01, ERR_HDR_TE_02 |  -  |
| **422** | Client issue, Required metadata missing, Code ERR_CRE_TE_04 |  -  |
| **428** | Client issue, clinet request missing meta data, Code ERR_CRE_TE_03 |  -  |
| **500** | Internal Server Error, global error. Code ERR_CRE_TE_06 |  -  |
| **502** | Gateway Error, routing issue. Code ERR_CRE_TE_06 |  -  |

<a id="getDocumentUsingGET1"></a>
# **getDocumentUsingGET1**
> Document getDocumentUsingGET1(businessViewId, docId)

Get document

Get Document by DocID

### Example

```java
// Import classes:

import com.cogitosum.rdi.api.ApiClient;
import com.cogitosum.rdi.api.ApiException;
import com.cogitosum.rdi.api.Configuration;
import com.cogitosum.rdi.api.auth.ApiKeyAuth;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import com.cogitosum.rdi.api.api.SystemEmployeeApiDocumentManagementV5Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://localhost:9010/ecm/dcmgmt/v5");

        // Configure API key authorization: api-key-1
        ApiKeyAuth api -key - 1 = (ApiKeyAuth) defaultClient.getAuthentication("api-key-1");
        api - key - 1. setApiKey("YOUR API KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //api-key-1.setApiKeyPrefix("Token");

        SystemEmployeeApiDocumentManagementV5Api apiInstance = new SystemEmployeeApiDocumentManagementV5Api(defaultClient);
        String businessViewId = "businessViewId_example"; // String | The Business View Id
        String docId = "docId_example"; // String | The Document Id
        try {
            Document result = apiInstance.getDocumentUsingGET1(businessViewId, docId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SystemEmployeeApiDocumentManagementV5Api#getDocumentUsingGET1");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **businessViewId** | **String**| The Business View Id | |
| **docId** | **String**| The Document Id | |

### Return type

[**Document**](Document.md)

### Authorization

[api-key-1](../README.md#api-key-1)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/octet-stream, application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | Document content |  -  |
| **400** | Bad Request, global error. Code ERR_CMN_TE_02 |  -  |
| **404** | Not found, global error. Code ERR_CMN_TE_03 |  -  |
| **412** | Gateway mapping issue , Code ERR_CRE_TE_07 |  -  |
| **417** | Gateway config issue, Code ERR_CRE_TE_01 , ERR_CRE_TE_02, ERR_CRE_TE_08, ERR_CRE_TE_09 , ERR_CRE_TE_10,ERR_HDR_TE_01, ERR_HDR_TE_02 |  -  |
| **422** | Client issue, Required metadata missing, Code ERR_CRE_TE_04 |  -  |
| **428** | Client issue, clinet request missing meta data, Code ERR_CRE_TE_03 |  -  |
| **500** | Internal Server Error, global error. Code ERR_CRE_TE_06 |  -  |
| **502** | Gateway Error, routing issue. Code ERR_CRE_TE_06 |  -  |

<a id="searchUsingPOST1"></a>
# **searchUsingPOST1**
> SearchResult searchUsingPOST1(businessViewId, searchCriteria)

Search documents

Search Document by criteria

### Example

```java
// Import classes:

import com.cogitosum.rdi.api.ApiClient;
import com.cogitosum.rdi.api.ApiException;
import com.cogitosum.rdi.api.Configuration;
import com.cogitosum.rdi.api.auth.ApiKeyAuth;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import com.cogitosum.rdi.api.api.SystemEmployeeApiDocumentManagementV5Api;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("https://localhost:9010/ecm/dcmgmt/v5");

        // Configure API key authorization: api-key-1
        ApiKeyAuth api -key - 1 = (ApiKeyAuth) defaultClient.getAuthentication("api-key-1");
        api - key - 1. setApiKey("YOUR API KEY");
        // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
        //api-key-1.setApiKeyPrefix("Token");

        SystemEmployeeApiDocumentManagementV5Api apiInstance = new SystemEmployeeApiDocumentManagementV5Api(defaultClient);
        String businessViewId = "businessViewId_example"; // String | The Business View Id
        SearchCriteria searchCriteria = new SearchCriteria(); // SearchCriteria | searchCriteria
        try {
            SearchResult result = apiInstance.searchUsingPOST1(businessViewId, searchCriteria);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling SystemEmployeeApiDocumentManagementV5Api#searchUsingPOST1");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **businessViewId** | **String**| The Business View Id | |
| **searchCriteria** | [**SearchCriteria**](SearchCriteria.md)| searchCriteria | |

### Return type

[**SearchResult**](SearchResult.md)

### Authorization

[api-key-1](../README.md#api-key-1)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **200** | An array of document and there properties and search infos |  -  |
| **400** | Bad Request, global error. Code ERR_CMN_TE_02 |  -  |
| **404** | Not found, global error. Code ERR_CMN_TE_03 |  -  |
| **412** | Hub mapping issue , Code ERR_CRE_TE_07 |  -  |
| **417** | Hub config issue, Code ERR_CRE_TE_01 , ERR_CRE_TE_02, ERR_CRE_TE_08, ERR_CRE_TE_09 , ERR_CRE_TE_10,ERR_HDR_TE_01, ERR_HDR_TE_02 |  -  |
| **428** | Client issue, clinet request is null, Code ERR_SER_TE_03 |  -  |
| **500** | Internal Server Error, global error. Code ERR_CMN_TE_04 |  -  |
| **502** | Hub Error, routing issue. Code ERR_CRE_TE_06 |  -  |

