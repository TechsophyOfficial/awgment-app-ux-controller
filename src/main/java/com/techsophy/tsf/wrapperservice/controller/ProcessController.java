package com.techsophy.tsf.wrapperservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.COMPLETE_TASK;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.DEPLOY_PROCESS;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.RESUME_PROCESS;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationEndpointConstants.START_PROCESS;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;

/**
 * wrapper api for process
 */
@RequestMapping(ApplicationEndpointConstants.BASEURL + ApplicationEndpointConstants.VERSION_1)
public interface ProcessController {
    /**
     * get task by task id
     *
     * @param taskId
     * @return ApiResponse
     */
    @GetMapping(TASKS + ID)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<TaskInstanceDTO> getTaskById(@PathVariable(PATH_ID) String taskId);

    /**
     * get all task
     *
     * @param taskQuery
     * @param page
     * @param size
     * @return ApiResponse
     */
    @PostMapping(TASKS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    PaginationDTO<List<TaskInstanceDTO>> getTasksByQuery(@RequestBody TaskQueryDTO taskQuery, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size);

    @PostMapping(HISTORY_TASKS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    PaginationDTO<List<HistoricInstanceDTO>> getHistoryTasksByQuery(@RequestBody HistoricQueryInstanceDTO historicQueryInstanceDTO, @RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size) throws JsonProcessingException;

    @GetMapping(TASKS)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    PaginationDTO<List<TaskInstanceDTO>> getAllTasks(@RequestParam(required = false, defaultValue = "0") Integer page, @RequestParam(required = false, defaultValue = "20") Integer size);

    /**
     * get number of tasks
     *
     * @param taskQuery
     * @return ApiResponse
     */
    @PostMapping(TASKS_COUNT)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<TaskCountDTO> getTasksCount(@RequestBody TaskQueryDTO taskQuery);

    /**
     * start process by process definition key
     *
     * @param processInstance
     * @return ApiResponse
     */
    @PostMapping(START_PROCESS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<ProcessInstanceResponseDTO> startProcessByDefinitionKey(@RequestBody ProcessInstance processInstance);

    @PostMapping(value = DEPLOY_PROCESS, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<DeployProcessResponseDTO> deployProcess(@RequestPart(SAVE_REQUESTPARAM_NAME) @NotEmpty @NotNull String name,
                                                        @RequestPart(FILE) MultipartFile file);

    /**
     * complete task by process instance
     *
     * @param genericDTO
     * @return ApiResponse
     */
    @PostMapping(COMPLETE_TASK)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> completeActiveTaskByBusinessKey(@RequestBody GenericDTO genericDTO);

    /**
     * @param userTaskActivityWrapperDTO
     * @return
     */
    @PostMapping(CLAIM_TASK)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> claimTask(@RequestBody UserTaskActivityWrapperDTO userTaskActivityWrapperDTO);

    /**
     * @param userTaskActivityWrapperDTO
     * @return
     */
    @PostMapping(SET_ASSIGNEE)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> setAssigneeToTask(@RequestBody UserTaskActivityWrapperDTO userTaskActivityWrapperDTO);

    /**
     * Resume process
     *
     * @param resumeProcessRequestDTO
     * @return
     */
    @PostMapping(RESUME_PROCESS)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> resumeProcess(@RequestBody ResumeProcessRequestDTO resumeProcessRequestDTO);

    /**
     * Due date
     *
     * @param setDueDateFollowUpDateDto
     * @return
     */
    @PutMapping(TASK + ID)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> updateTask(@PathVariable(PATH_ID) String taskId, @RequestBody UpdateTaskDto setDueDateFollowUpDateDto) throws JsonProcessingException;

    /**
     * @param taskDto
     * @return
     */
    @PostMapping(ApplicationEndpointConstants.CREATE_TASK)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> createTask(@RequestBody TaskDto taskDto) throws JsonProcessingException;

    /**
     * @param identityLinksDto
     * @return
     */
    @PostMapping(TASKCONSTANT + ID + ApplicationEndpointConstants.IDENTITY_LINK)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> adduserOrGroupToTask(@PathVariable(PATH_ID) String taskId, @RequestBody IdentityLinksDto identityLinksDto) throws JsonProcessingException;

    @GetMapping(TASKCONSTANT + ID + ApplicationEndpointConstants.IDENTITY_LINK)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<List<IdentityLinksDto>> getIdentityLinksOfTask(@PathVariable(PATH_ID) String taskId) throws JsonProcessingException;

    @PostMapping(TASKCONSTANT + ID + ApplicationEndpointConstants.IDENTITY_LINK + DELETE)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void> deleteIdentityLinkOfTask(@PathVariable(PATH_ID) String taskId, @RequestBody IdentityLinksDto identityLinksDto) throws JsonProcessingException;


    @GetMapping(ApplicationEndpointConstants.TASK_HISTORY_USER_OPERATION)
    @PreAuthorize(READ_OR_ALL_ACCESS)
    ApiResponse<List<TaskHistoryDto>> getHistoryOfTask(@RequestParam String filter) throws JsonProcessingException;

    @PostMapping(CHECKLIST_ITEM+COMPLETE)
    @PreAuthorize(CREATE_OR_ALL_ACCESS)
    ApiResponse<Void>completeTaskWithChecklistItemInstanceId(@RequestBody ChecklistItemInstanceDTO checklistItemInstanceDTO) throws JsonProcessingException;

    @DeleteMapping(TASKS)
    @PreAuthorize(DELETE_OR_ALL_ACCESS)
    ApiResponse<Void>deleteProcessById(@RequestParam String ticketNumber, @RequestParam String formDataId) throws JsonProcessingException;

}
