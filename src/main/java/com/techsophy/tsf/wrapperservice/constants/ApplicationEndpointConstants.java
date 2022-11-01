package com.techsophy.tsf.wrapperservice.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApplicationEndpointConstants
{
    public static final String BASEURL = "/workflow";
    public static final String VERSION_1 = "/v1";
    public static final String TASKCONSTANT ="/task";
    public static final String TASKS = "/tasks";
    public static final String COMPLETE="/complete";
    public static final String HISTORY_TASKS = "/history/tasks";
    public static final String COMPLETE_TASK = TASKS + COMPLETE;
    public static final String TASKS_COUNT = TASKS + "/count";
    public static final String START_PROCESS = "/process/start";
    public static final String DEPLOY_PROCESS = "/deploy";
    public static final String RESUME_PROCESS = "/process/resume";
    public static final String COMMENTS = "/comments";
    public static final String ID= "/{id}";
    public static final String SAVE_REQUESTPARAM_NAME="name";
    public static final String FILE="file";
    public static final String ENGINE_REST = "/engine-rest";
    public static final String TASK = ENGINE_REST + TASKCONSTANT;

    public static final String URL_SEPERATOR="/";


    // Roles
    public static final String HAS_ANY_AUTHORITY="hasAnyAuthority('";
    public static final String HAS_ANY_AUTHORITY_ENDING="')";
    public static final String AWGMENT_UX_CONTROLLER_CREATE_OR_UPDATE = "awgment-ux-controller-create-or-update";
    public static final String AWGMENT_UX_CONTROLLER_READ = "awgment-ux-controller-read";
    public static final String AWGMENT_UX_CONTROLLER_DELETE = "awgment-ux-controller-delete";
    public static final String AWGMENT_UX_CONTROLLER_ALL = "awgment-ux-controller-all";
    public static final String OR=" or ";
    public static final String CREATE_OR_ALL_ACCESS =HAS_ANY_AUTHORITY+ AWGMENT_UX_CONTROLLER_CREATE_OR_UPDATE +HAS_ANY_AUTHORITY_ENDING+OR+HAS_ANY_AUTHORITY+ AWGMENT_UX_CONTROLLER_ALL +HAS_ANY_AUTHORITY_ENDING;
    public static final String READ_OR_ALL_ACCESS =HAS_ANY_AUTHORITY+ AWGMENT_UX_CONTROLLER_READ +HAS_ANY_AUTHORITY_ENDING+OR+HAS_ANY_AUTHORITY+ AWGMENT_UX_CONTROLLER_ALL +HAS_ANY_AUTHORITY_ENDING;
    public static final String DELETE_OR_ALL_ACCESS =HAS_ANY_AUTHORITY+ AWGMENT_UX_CONTROLLER_DELETE +HAS_ANY_AUTHORITY_ENDING+OR+HAS_ANY_AUTHORITY+ AWGMENT_UX_CONTROLLER_ALL +HAS_ANY_AUTHORITY_ENDING;

    //JWTRoleConverter
    public static final String CLIENT_ROLES="clientRoles";
    public static final String USER_INFO_URL= "techsophy-platform/protocol/openid-connect/userinfo";
    public static final String GET="GET";

    //JWTRoleConverter
    public static final String AWGMENT_ROLES_MISSING_IN_CLIENT_ROLES ="AwgmentRoles are missing in clientRoles";
    public static final String CLIENT_ROLES_MISSING_IN_USER_INFORMATION="ClientRoles are missing in the userInformation";
    public static final String CREATE="/create";
    public static final String EXECUTE_LIST="/{id}/list";
    public static final String FILTER ="/filter";
    public static final String FORM_VARIABLES ="/form-variables";


    public static final String CREATE_TASK = "/task/create";
    public static final String IDENTITY_LINK = "/identity-links";
    public static final String DELETE = "/delete";
    public static final String TASK_HISTORY_USER_OPERATION= "/history/user-operation";
    public static final String FILTER_ID ="id";
    public static final String MY_TASKS ="/mytasks";

    public static final String CHECKLIST_ITEM="/checklist-item-instances";




}
