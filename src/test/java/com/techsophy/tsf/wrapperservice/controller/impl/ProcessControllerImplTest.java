package com.techsophy.tsf.wrapperservice.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.constants.MessageConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.service.ProcessService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProcessControllerImplTest {

    @Mock
    ProcessService processService;
    @InjectMocks
    ProcessControllerImpl processController;

    Integer page, size;

    @BeforeEach
    void setUp() {
        page = 1;
        size = 1;
    }

    @Test
    void getTasksByQueryTest() {
        TaskQueryDTO taskQueryDTO = Mockito.mock(TaskQueryDTO.class);
        TaskInstanceDTO taskInstanceDTO = Mockito.mock(TaskInstanceDTO.class);
        List list = List.of(taskInstanceDTO);
        Mockito.when(processService.getTasksByQuery(taskQueryDTO, page, size)).thenReturn(new PaginationDTO<>(list, 1, 1, 1, 1l, 1l));
        PaginationDTO<List<TaskInstanceDTO>> actualOutput = processController.getTasksByQuery(taskQueryDTO, page, size);
        PaginationDTO<List<TaskInstanceDTO>> expectedOutput = processService.getTasksByQuery(taskQueryDTO, page, size);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getHistoryTasksByQueryTest() throws JsonProcessingException {
        HistoricQueryInstanceDTO dto = Mockito.mock(HistoricQueryInstanceDTO.class);
        HistoricInstanceDTO historicInstanceDTO = Mockito.mock(HistoricInstanceDTO.class);
        List list = List.of(historicInstanceDTO);
        Mockito.when(processService.getHistoryTasksByQuery(dto, page, size)).thenReturn(new PaginationDTO<>(list, 1, 1, 1, 1l, 1l));
        PaginationDTO<List<HistoricInstanceDTO>> actualOutput = processController.getHistoryTasksByQuery(dto, page, size);
        PaginationDTO<List<HistoricInstanceDTO>> expectedOutput = processService.getHistoryTasksByQuery(dto, page, size);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllTasksTest() throws JsonProcessingException {
        TaskInstanceDTO taskInstanceDTO = Mockito.mock(TaskInstanceDTO.class);
        List list = List.of(taskInstanceDTO);
        Mockito.when(processService.getAllTasks(page, size)).thenReturn(new PaginationDTO<>(list, 1, 1, 1, 1l, 1l));
        PaginationDTO<List<TaskInstanceDTO>> actualOutput = processController.getAllTasks(page, size);
        PaginationDTO<List<TaskInstanceDTO>> expectedOutput = processService.getAllTasks(page, size);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getTasksCountTest() throws JsonProcessingException {
        TaskQueryDTO taskQuery = Mockito.mock(TaskQueryDTO.class);
        TaskCountDTO taskCountDTO = Mockito.mock(TaskCountDTO.class);
        Mockito.when(processService.getTasksCount(taskQuery)).thenReturn(taskCountDTO);

        ApiResponse<TaskCountDTO> actualOutput = processController.getTasksCount(taskQuery);
        ApiResponse<TaskCountDTO> expectedOutput = new ApiResponse<>(processService.getTasksCount(taskQuery), true, MessageConstants.GET_TASK_COUNT_SUCCESS);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void completeActiveTaskByBusinessKeyTest() throws JsonProcessingException {
        GenericDTO dto = Mockito.mock(GenericDTO.class);
        ApiResponse<Void> actualOutput = processController.completeActiveTaskByBusinessKey(dto);
        ApiResponse<Void> expectedOutput = new ApiResponse<>(null, true, MessageConstants.COMPLETED_TASK_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void claimTaskTest() throws JsonProcessingException {
        UserTaskActivityWrapperDTO dto = Mockito.mock(UserTaskActivityWrapperDTO.class);
        ApiResponse<Void> actualOutput = processController.claimTask(dto);
        ApiResponse<Void> expectedOutput = new ApiResponse(null, true, CLAIM_TASK_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void setAssigneeToTaskTest() throws JsonProcessingException {
        UserTaskActivityWrapperDTO dto = Mockito.mock(UserTaskActivityWrapperDTO.class);
        processController.setAssigneeToTask(dto);
        verify(processService, times(1)).setAssignee(dto);
    }

    @Test
    void resumeProcessTest() throws JsonProcessingException {
        ResumeProcessRequestDTO dto = Mockito.mock(ResumeProcessRequestDTO.class);
        processController.resumeProcess(dto);
        verify(processService, times(1)).resumeProcess(dto);
    }

    @Test
    void updateTaskTest() throws JsonProcessingException {
        String id = "Id";
        UpdateTaskDto dto = Mockito.mock(UpdateTaskDto.class);
        processController.updateTask(id, dto);
        verify(processService, times(1)).updateTask(id, dto);
    }

    @Test
    void createTaskTest() throws JsonProcessingException {
        TaskDto dto = Mockito.mock(TaskDto.class);
        processController.createTask(dto);
        verify(processService, times(1)).createTask(dto);
    }

    @Test
    void adduserOrGroupToTaskTest() throws JsonProcessingException {
        String id = "id";
        IdentityLinksDto dto = Mockito.mock(IdentityLinksDto.class);
        processController.adduserOrGroupToTask(id, dto);
        verify(processService, times(1)).addUserOrGroupToTask(id, dto);
    }

    @Test
    void getIdentityLinksOfTaskTest() throws JsonProcessingException {
        String taskId = "task_id";
        IdentityLinksDto identityLinksDto = Mockito.mock(IdentityLinksDto.class);
        Mockito.when(processService.getIdentityLinksOfTask(taskId)).thenReturn(List.of(identityLinksDto));
        ApiResponse<List<IdentityLinksDto>> actualOutput = processController.getIdentityLinksOfTask(taskId);
        ApiResponse<List<IdentityLinksDto>> expectedOutput = new ApiResponse<>(processService.getIdentityLinksOfTask(taskId), true, IDENTITY_LINKS_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void deleteIdentityLinkOfTaskTest() throws JsonProcessingException {
        String id = "id";
        IdentityLinksDto dto = Mockito.mock(IdentityLinksDto.class);
        processController.deleteIdentityLinkOfTask(id, dto);
        verify(processService, times(1)).deleteIdentityLinkOfTask(id, dto);
    }

    @Test
    void getHistoryOfTaskTest() throws JsonProcessingException {
        String filter = "filter";
        TaskHistoryDto taskHistoryDto = Mockito.mock(TaskHistoryDto.class);
        Mockito.when(processService.getHistoryOfTask(filter)).thenReturn(List.of(taskHistoryDto));
        ApiResponse<List<TaskHistoryDto>> actualOutput = processController.getHistoryOfTask(filter);
        ApiResponse<List<TaskHistoryDto>> expectedOutput = new ApiResponse<>(processService.getHistoryOfTask(filter), true, GET_HISTORY_SUCCESS);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void completeTaskWithChecklistItemInstanceIdTest() throws JsonProcessingException {
        ChecklistItemInstanceDTO dto = Mockito.mock(ChecklistItemInstanceDTO.class);
        processController.completeTaskWithChecklistItemInstanceId(dto);
        verify(processService, times(1)).completeTaskWithChecklistItemId(dto);
    }

}
