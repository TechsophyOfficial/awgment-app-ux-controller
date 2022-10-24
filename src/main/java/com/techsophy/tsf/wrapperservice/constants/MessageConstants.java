package com.techsophy.tsf.wrapperservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageConstants
{
    public static final String GET_TASK_BY_ID_SUCCESS = "Fetched task successfully";
    public static final String START_PROCESS_SUCCESS = "Process started successfully";
    public static final String DEPLOY_PROCESS_SUCCESS = "Process deployed successfully";
    public static final String CREATE_COMMENT_SUCCESS = "Comment created successfully";
    public static final String GET_COMMENTS_SUCCESS = "Fetched comments successfully";
    public static final String COMPLETED_TASK_SUCCESS = "Completed task successfully";
    public static final String GET_TASK_COUNT_SUCCESS = "Task count fetched successfully";
    public static final String GET_GROUP_TASKS="Group Tasks fetched successfully";
    public static final String GET_CASE_INSTANCES="Case Instances fetched successfully";
    public static final String GET_ALL_TASKS="All Tasks Fetched successfully";
    public static final String RESUME_PROCESS_FAILED = "Unable to resume process";

    public static final String MISSING_MANDATORY_PARAMS="Missing Mandatory params. Please provide either processInstanceId or businessKey";
    public static final String FAILED_WHILE_FETCHING_TASK="Failed while fetching task";

    public static final String CLAIM_TASK_SUCCESS = "Task claimed successfully";
    public static final String UPDATE_TASK_SUCCESS = "Task updated successfully";
    public static final String SET_ASSIGNEE_SUCCESS = "Assignee set to task successfully";
    public static final String CLAIM_TASK_FAILED = "Failed to claim task with id :";
    public static final String UPDATE_TASK_FAILED = "Failed to update task with id :";
    public static final String SET_ASSIGNEE_FAILED = "Failed to set assignee to task with id :";

    //LoggingHandler
    public static final String CONTROLLER_CLASS_PATH = "execution(* com.techsophy.tsf.wrapperservic.controller..*(..))";
    public static final String SERVICE_CLASS_PATH= "execution(* com.techsophy.tsf.wrapperservice.service..*(..))";
    public static final String EXCEPTION = "ex";
    public static final String IS_INVOKED_IN_CONTROLLER= "() is invoked in controller ";
    public static final String IS_INVOKED_IN_SERVICE= "() is invoked in service ";
    public static final String EXECUTION_IS_COMPLETED_IN_CONTROLLER="() execution is completed  in controller";
    public static final String EXECUTION_IS_COMPLETED_IN_SERVICE="() execution is completed  in service";
    public static final String EXCEPTION_THROWN="An exception has been thrown in ";
    public static final String CAUSE="Cause : ";
    public static final String BRACKETS_IN_CONTROLLER="() in controller";
    public static final String BRACKETS_IN_SERVICE="() in service";
    //webclient constants
    public static final String GET="GET";
    public static final String PUT="PUT";
    public static final String DELETE="DELETE";
    public static final String AUTHENTICATION_FAILED="Authentication failed";
    public static final String AUTHORIZATION="Authorization";
    public static final String CONTENT_TYPE="Content-Type";
    public static final String APPLICATION_JSON="application/json";
    //History constants
    public static final String GROUP="/group";
    public static final String HISTORY="/history";
    public static final String CASE_ACTIVITY_INSTANCE="/case-activity-instance";

    public static final String TASK="/task";
    public static final String COUNT="/count";
    public static final String CREATE_TASK_COUNT_SUCCESS="create task count success";

    //TokenUtilsConstants
    public static final String BEARER= "Bearer ";
    public static final String EXEC_ACTIONS= "/execute-actions-email?lifespan=";
    public static final String REGEX_SPLIT="\\.";
    public static final String INVALID_TOKEN="Invalid token";
    public static final String UNABLE_GET_TOKEN="Unable to get token";
    public static final String PREFERED_USERNAME="preferred_username";
    public static final String CREATED_ON="createdOn";
    public static final String  COLON=":";
    public static final String ISS="iss";
    public static final String KEYCLOAK_ISSUER_URI = "${keycloak.issuer-uri}";
    public static final String DEFAULT_PAGE_LIMIT= "${default.pagelimit}";
    public static final String  URL_SEPERATOR="/";
    public static final String EMPTY_STRING = "String Is Empty";
    public static final int SEVEN =7;
    public static final int ONE =1 ;
    public static final String INVALID_PAGE_REQUEST="Invalid page request";
    public static final String DESCENDING="desc";

    public static final String CREATE_TASK_SUCCESS = "Task Created successfully";
    public static final String USE_ADD_SUCCESS = "User or Group added successfully";
    public static final String IDENTITY_LINKS_SUCCESS = "Identity Links fetched successfully";
    public static final String IDENTITY_LINKS_DELETE = "Identity Link deleted successfully";
    public static final String GET_HISTORY_SUCCESS = "Identity Links fetched successfully";

    public static final String CREATE_FILTER_SUCCESS = "Filter created successfully";
    public static final String EXECUTE_FILTER_SUCCESS = "Filter executed successfully";
    public static final String GET_FILTER_SUCCESS = "Filter data fetched successfully";
    public static final String GET_FILTER_COUNT_SUCCESS = "Filter count fetched successfully";
    public static final String GET_FILTER_FORM_VARIABLES = "Filter form variables fetched  successfully";




    public static final String  LIST="list";

    public static final String ID= "id";
    public static final String CASEINSTANCE = "/caseInstance/{id}";
    public static final String FORM = "/form/{id}";

    public static final String GET_TASK_COUNT = "Count returned successfully";

    public static final String GET_ALL_TASKS_SUCCESS = "All tasks fetched successfully";
    public static final String GET_MY_TASK_COUNT =  "/task/count";
    public static final String GET_MY_TASKS_HISTORY_SUCCESS = " My tasks history fetched successfully";
    public static final String GET_MY_TASKS_SUCCESS = " Task fetched successfully";
    public static final String FORM_DATA_UPDATE_URL = "/form-runtime/v1/form-data";
    public static final String FORM_DATA_GET_URL = "/form-runtime/v1/form-data?formId=951380543294529536";

    public static final String NAME="name";
    public static final String STATUS="status";
    public static final String CANCELLED="Cancelled";
    public static final String PROCESS_INSTANCE_ID="processInstanceId";
    public static final String REVIEW_TICKET="Review Ticket-";







}
