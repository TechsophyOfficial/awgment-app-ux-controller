package com.techsophy.tsf.wrapperservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CamundaApiConstants
{
    public static final String ENGINE_REST = "/engine-rest";
    public static final String GET_TASK = ENGINE_REST + "/task";
    public static final String GET_TASK_COUNT_HISTORY = ENGINE_REST + "/history/task/count";
//     static final String GET_TASK_COUNT = ENGINE_REST + "/task/count";
    public static final String INVOLVED_USER="involvedUser";


    // Custom Camunda api
    public static final String CUSTOM_CAMUNDA_BASE_URL = "/service/v1";
    public static final String START_PROCESS =CUSTOM_CAMUNDA_BASE_URL+ "/process-instance/start";
    public static final String DEPLOY_PROCESS ="/engine-rest/deployment/create";
    public static final String GET_ALL_TASK = CUSTOM_CAMUNDA_BASE_URL + "/task";
    public static final String GET_ALL_HISTORY_TASK = CUSTOM_CAMUNDA_BASE_URL + "/history/task";

    public static final String CREATE_COMMENT = CUSTOM_CAMUNDA_BASE_URL + "/comment/create";
    public static final String GET_COMMENT = CUSTOM_CAMUNDA_BASE_URL + "/comment";
    public static final String COMPLETE_TASK = CUSTOM_CAMUNDA_BASE_URL + "/task/complete";
    public static final String RESUME_PROCESS = CUSTOM_CAMUNDA_BASE_URL + "/process-instance/resume";
    public static final String COMPLETE_TASK_BY_ID = ENGINE_REST + "/task/{id}/complete";
    public static final String CLAIM_TASK = ENGINE_REST + "/task/{id}/claim";
    public static final String SET_ASSIGNEE = ENGINE_REST + "/task/{id}/assignee";
    public static final String FIRST_RESULT = "firstResult";
    public static final String MAX_RESULTS = "maxResults";
    public static final String POST = "post";

    public static final String CASE_INSTANCEID = "caseInstanceId";

    public static final String CREATE_TASK = ENGINE_REST + "/task/create";
    public static final String IDENTITY_LINK = ENGINE_REST + "/task/{id}/identity-links";
    public static final String IDENTITY_LINK_DELETE = ENGINE_REST + "/task/{id}/identity-links/delete";
    public static final String TASK_HISTORY_USER_OPERATION = ENGINE_REST + "/history/user-operation";
    public static final String GROUPTASK = "/grouptask";
    public static final String GET_GROUP_TASK_COUNT =ENGINE_REST+"/task/count";
    public static final String GET_GROUP_TASK =ENGINE_REST+"/task";
    public static final String GET_GROUP_TASK_CASEINSTANCE =ENGINE_REST+"/case-instance/";
    public static final String ALLTASK_CASE_ACTIVITY_INSTANCE =ENGINE_REST+"/history/case-activity-instance";
    public static final String ALLTASK_FORM_VARIABLES =ENGINE_REST+"/task/";
    public static final String ALLTASK_CASE_INSTANCE =ENGINE_REST+"/case-instance/";
    public static final String VARIABLES ="/variables";
    public static final String VARIABLE ="/variables/{id}";
    public static final String CASE_DEFINITION ="/case-definition/{id}";
    public static final String FIRST_RESULT_GROUP_TASK="?firstResult=";
    public static final String MAX_RESULTS_GROUP_TASK="&maxResults=";
    public static final String GET_TASK_DATA_HISTORY = ENGINE_REST + "/history/task";
    public static final String GET_TASK_COUNT = ENGINE_REST + "/task/count";
    public static final String GROUP_TASK_CASE_INSTANCE=ENGINE_REST+"/history/case-activity-instance";
    public static final String GROUP_CASE_INSTANCE=ENGINE_REST+"/case-instance/";
    public static final String GROUP_CASE_DEFINITION=ENGINE_REST+"/case-definition/";
    public static final String GROUP_FORM_VARIABLE=ENGINE_REST+"/task/";
    public static final String CASE_INSTANCE_ID="?caseInstanceId=";
    public static final String FORM_VARIABLE="/form-variables";
    public static final String ALLTASK_FORM_VARIABLE="/form-variables/{id}";

    public static final String UPDATE_TASK = ENGINE_REST + "/task";
    public static final String GET_CREATE_FILTER = ENGINE_REST + "/filter/create";
    public static final String GET_EXECUTE_FILTER_LIST = ENGINE_REST + "/filter";

    public static final String FIRSTRESULT = "?firstResult=";
    public static final String MAXRESULT = "&maxResults=";
    public static final String ALLTASKS="/alltasks";
    public static final String CASEINSTANCE="/caseinstance/{id}";
    public static final String CASEACTIVITYINSTANCE="/caseactivityinstance";
    public static final String FORMVARIABLES="/formvariables/{id}";
    public static final String CASEINSTANCEID="caseInstanceId";
    public static final String CASE_INSTANCE="case-instance";

    public static final String ALLTASKCASEINSTANCEID="?caseInstanceId=";
    public static final String ALL_TASK_CASE_INSTANCE=ENGINE_REST+"/case-instance/";

}
