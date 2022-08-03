package com.techsophy.tsf.wrapperservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.*;

import java.util.List;

public interface AllTasksService {

    TaskCountDTO allTasksCount(AllTasksCountDTO allTasksCountDTO) throws JsonProcessingException;
    List<AllTasksDTO> allTasks(AllTasksCountDTO allTasksCountDTO, Integer firstResult, Integer maxResults) throws JsonProcessingException;
    AllTaskCaseInstanceDTO allTaskCaseInstance(String id) throws JsonProcessingException;
    AllTaskFormsDTO allTaskVariables(String id) throws JsonProcessingException;
    AllTaskFormVariablesDTO allTaskFormVariables(String id) throws JsonProcessingException;
    List<CaseActivityInstanceDTO> allTaskCaseActivityInstance(String caseInstanceId) throws JsonProcessingException;
}
