package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.controller.ProcessController;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.ProcessService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;

/**
 * process controller wrapper apis
 */
@RestController
@AllArgsConstructor
public class ProcessControllerImpl implements ProcessController {
    private final ProcessService processService;

    /**
     * wrapper api for get task by Id
     *
     * @param taskId
     * @return ApiResponse
     */
    @Override
    public ApiResponse<TaskInstanceDTO> getTaskById(String taskId) {
        return new ApiResponse<>(this.processService.getTaskById(taskId), true, MessageConstants.GET_TASK_BY_ID_SUCCESS);
    }

    /*
     *    If businessKey is provided tasks will be filter by that value
     *    or else it returns all tasks
     */
    @Override
    public PaginationDTO<List<TaskInstanceDTO>> getTasksByQuery(TaskQueryDTO taskQuery, Integer page, Integer size) {
        return this.processService.getTasksByQuery(taskQuery, page, size);
    }

    @Override
    public PaginationDTO<List<HistoricInstanceDTO>> getHistoryTasksByQuery(HistoricQueryInstanceDTO historicQueryInstanceDTO, Integer page, Integer size) throws JsonProcessingException {
        return this.processService.getHistoryTasksByQuery(historicQueryInstanceDTO,page,size);
    }

    @Override
    public PaginationDTO<List<TaskInstanceDTO>> getAllTasks(Integer page, Integer size) {
        return this.processService.getAllTasks(page, size);
    }

    /**
     * get task conunt using task query
     *
     * @param taskQuery
     * @return ApiResponse
     */
    @Override
    public ApiResponse<TaskCountDTO> getTasksCount(TaskQueryDTO taskQuery) {
        return new ApiResponse<>(this.processService.getTasksCount(taskQuery), true, MessageConstants.GET_TASK_COUNT_SUCCESS);
    }

    /**
     * start process by process definition
     *
     * @param processInstance
     * @return ApiResponse
     */
    @Override
    @SneakyThrows
    public ApiResponse<ProcessInstanceResponseDTO> startProcessByDefinitionKey(ProcessInstance processInstance) {
        return new ApiResponse<>(this.processService.startProcessByDefinitionKey(processInstance), true, MessageConstants.START_PROCESS_SUCCESS);
    }

    @Override
    @SneakyThrows
    public ApiResponse<DeployProcessResponseDTO> deployProcess(String name, MultipartFile file) {
        return new ApiResponse<>(this.processService.deployProcess(name, file), true, MessageConstants.DEPLOY_PROCESS_SUCCESS);
    }

    /**
     * cmplet a active task by business key
     *
     * @param genericDTO
     * @return ApiResponse
     */
    @Override
    @SneakyThrows
    public ApiResponse<Void> completeActiveTaskByBusinessKey(GenericDTO genericDTO) {
        this.processService.completeTask(genericDTO);
        return new ApiResponse<>(null, true, MessageConstants.COMPLETED_TASK_SUCCESS);
    }

    /**
     * claim task
     *
     * @param userTaskActivityWrapperDTO
     * @return
     */
    @Override
    public ApiResponse<Void> claimTask(UserTaskActivityWrapperDTO userTaskActivityWrapperDTO) {
        this.processService.claimTask(userTaskActivityWrapperDTO);
        return new ApiResponse<>(null, true, CLAIM_TASK_SUCCESS);
    }

    /**
     * set assignee to task
     *
     * @param userTaskActivityWrapperDTO
     * @return
     */
    @Override
    public ApiResponse<Void> setAssigneeToTask(UserTaskActivityWrapperDTO userTaskActivityWrapperDTO) {
        this.processService.setAssignee(userTaskActivityWrapperDTO);
        return new ApiResponse<>(null, true, SET_ASSIGNEE_SUCCESS);
    }

    /**
     * resume process instance
     *
     * @param resumeProcessRequestDTO
     * @return
     */
    @Override
    @SneakyThrows
    public ApiResponse<Void> resumeProcess(ResumeProcessRequestDTO resumeProcessRequestDTO) {
        this.processService.resumeProcess(resumeProcessRequestDTO);
        return new ApiResponse<>(null, true, MessageConstants.COMPLETED_TASK_SUCCESS);
    }

    @Override
    public ApiResponse<Void> updateTask(String taskId, UpdateTaskDto updateTaskDto) throws JsonProcessingException {
        this.processService.updateTask(taskId, updateTaskDto);
        return new ApiResponse<>(null, true, UPDATE_TASK_SUCCESS);
    }

    @Override
    public ApiResponse<Void> createTask(TaskDto taskDto) throws JsonProcessingException {
        this.processService.createTask(taskDto);
        return new ApiResponse<>(null, true, CREATE_TASK_SUCCESS);
    }

    @Override
    public ApiResponse<Void> adduserOrGroupToTask(String taskId, IdentityLinksDto identityLinksDto) throws JsonProcessingException {
        this.processService.addUserOrGroupToTask(taskId, identityLinksDto);
        return new ApiResponse<>(null, true, USE_ADD_SUCCESS);
    }

    @Override
    public ApiResponse<List<IdentityLinksDto>> getIdentityLinksOfTask(String taskId) throws JsonProcessingException {

        return new ApiResponse<>(this.processService.getIdentityLinksOfTask(taskId), true, IDENTITY_LINKS_SUCCESS);
    }

    @Override
    public ApiResponse<Void> deleteIdentityLinkOfTask(String taskId, IdentityLinksDto identityLinksDto) throws JsonProcessingException {
        this.processService.deleteIdentityLinkOfTask(taskId, identityLinksDto);
        return new ApiResponse<>(null, true, IDENTITY_LINKS_DELETE);
    }

    @Override
    public ApiResponse<List<TaskHistoryDto>> getHistoryOfTask(String filter) throws JsonProcessingException {
        return new ApiResponse<>(this.processService.getHistoryOfTask(filter), true, GET_HISTORY_SUCCESS);

    }

    @Override
    public ApiResponse<Void> completeTaskWithChecklistItemInstanceId(ChecklistItemInstanceDTO checklistItemInstanceDTO) throws JsonProcessingException {
        this.processService.completeTaskWithChecklistItemId(checklistItemInstanceDTO);
        return new ApiResponse<>(null, true, "Task Completed successfully");
    }

}
