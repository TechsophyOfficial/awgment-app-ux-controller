package com.techsophy.tsf.wrapperservice.config;

import com.techsophy.tsf.wrapperservice.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.URL_SEPERATOR;

@Component
public class TenantWorkflowResolver {

    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;
    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;
    @Value(SHARED_WORKFLOW_ENGINE_PATH)
    private String sharedWorkflowEngine;
    @Value(DEFAULT_DATABASE_NAME_PATH)
    private String defaultRealm;
    @Autowired
    TokenUtils tokenUtils;
    public String getCamundaPathUri(String relativeUrl) {
        String tenant = tokenUtils.getIssuerFromToken(tokenUtils.getTokenFromContext());
        return (Boolean.parseBoolean(sharedWorkflowEngine)&&tenant.equals(defaultRealm))?
                gatewayURI + camundaServletContextPath + relativeUrl:
                gatewayURI + URL_SEPERATOR + tenant + camundaServletContextPath + relativeUrl;
    }
}
