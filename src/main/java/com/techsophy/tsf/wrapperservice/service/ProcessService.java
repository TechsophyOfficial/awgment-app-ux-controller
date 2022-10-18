package com.techsophy.tsf.wrapperservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public interface ProcessService
{
    /**
     * get task by task id
     * @param taskId
     * @return TaskInstanceDTO
     */
    TaskInstanceDTO getTaskById(String taskId);

    /**
     * get tasks using task query
     *
     * @param taskQueryDTO
     * @param page
     * @param size
     * @return List
     */
    PaginationDTO<List<TaskInstanceDTO>> getTasksByQuery(TaskQueryDTO taskQueryDTO, Integer page, Integer size);

    PaginationDTO<List<TaskInstanceDTO>> getAllTasks(Integer page, Integer size);

    /**
     * get tasks using task count
     * @param taskQueryDTO
     * @return TaskCountDTO
     */
    TaskCountDTO getTasksCount(TaskQueryDTO taskQueryDTO);

    /**
     * start process by definition key
     * @param processInstance
     * @return ProcessInstanceResponseDTO
     */
    ProcessInstanceResponseDTO startProcessByDefinitionKey(ProcessInstance processInstance);

    DeployProcessResponseDTO deployProcess(String name,MultipartFile file);
    /**
     *
     * @param taskDTO
     */
    void completeTask(GenericDTO taskDTO);

    /**
     *
     * @param userTaskActivityWrapperDTO
     */
    void claimTask(UserTaskActivityWrapperDTO userTaskActivityWrapperDTO);


    /**
     *
     * @param userTaskActivityWrapperDTO
     */
    void setAssignee(UserTaskActivityWrapperDTO userTaskActivityWrapperDTO);

    /**
     * resume process
     * @param resumeProcessRequestDTO
     */
    void resumeProcess(ResumeProcessRequestDTO resumeProcessRequestDTO);

    /**
     * Due date
     * @param updateTaskDto
     * @param  taskID
     */
    void updateTask(String taskID,UpdateTaskDto updateTaskDto) throws JsonProcessingException;

    /**
     * create task
     * @param taskDto
     */
    void createTask(TaskDto taskDto) throws JsonProcessingException;


    /**
     * add user or group to a task
     * @param identityLinksDto
     */
    void addUserOrGroupToTask(String taskId,IdentityLinksDto identityLinksDto) throws JsonProcessingException;

    List<IdentityLinksDto>  getIdentityLinksOfTask(String taskId) throws JsonProcessingException;

    /**
     * delete user or group to a task
     * @param identityLinksDto
     */
    void   deleteIdentityLinkOfTask(String taskId,IdentityLinksDto identityLinksDto) throws JsonProcessingException;


    List<TaskHistoryDto> getHistoryOfTask(String filter) throws JsonProcessingException;


    void completeTaskWithChecklistItemId(ChecklistItemInstanceDTO checklistItemInstanceDTO) throws JsonProcessingException;
    void deleteProcessById(DeleteTaskDTO deleteTaskDTO)throws JsonProcessingException;
    PaginationDTO<List<HistoricInstanceDTO>> getHistoryTasksByQuery(HistoricQueryInstanceDTO historicQueryInstanceDTO, Integer page, Integer size) throws JsonProcessingException;
}
