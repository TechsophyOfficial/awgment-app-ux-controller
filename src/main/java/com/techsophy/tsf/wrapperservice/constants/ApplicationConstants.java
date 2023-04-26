package com.techsophy.tsf.wrapperservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApplicationConstants
{
    public static final String CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE = "${camunda-engine.servlet.context-path}";
    public static final String GATEWAY_URI_VARIABLE = "${gateway.uri}";
    public static final String PATH_ID="id";
    public static final String PROCESSINSTANCEID="processInstanceId";
    public static final String CASEINSTANCEID="caseInstanceId";
    public static final String BUSINESSKEY="businessKey";
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String MESSAGE="message";
    public static final String GATEWAY_URL="${gateway.uri}";
    public static final String UX_CONTROLLER ="tp-app-ux-controller";
    public static final String VERSION_V1 ="/v1";
    public static final String UX_CONTROLLER_MODELER_API_VERSION_V1 ="Ux Controller API v1";
    public static final String CURRENT_PROJECT="com.techsophy.tsf.wrapperservice.*";
    public static final String MULTITENANCY_PROJECT="com.techsophy.multitenancy.mongo.*";
    public static final String ACCEPT_LANGUAGE = "Accept-Language";
    public static final String BASENAME_ERROR_MESSAGES = "classpath:errorMessages";
    public static final String BASENAME_MESSAGES = "classpath:messages";
    public static final Long CACHEMILLIS = 3600L;
    public static final Boolean USEDEFAULTCODEMESSAGE = true;
    public static final String CHECKLIST_ITEM_INSTANCE_COMPLETION_URL = "/checklist-engine/v1/checklist-item-instances/complete";
    public static final String VARIABLE_INSTANCE = "/engine-rest/history/variable-instance";
    public static final String DEFAULT_DATABASE_NAME_PATH = "${database.name}";
    public static final String SHARED_WORKFLOW_ENGINE_PATH = "${shared-workflow-engine}";
}
