package com.techsophy.tsf.wrapperservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.*;

import java.util.List;

public interface GroupService {

     TaskCountDTO groupCount(GroupTaskCountReqDTO groupTaskCountReqDTO) throws JsonProcessingException;
     List<GroupTaskDTO> groupTask(GroupTaskCountReqDTO groupTaskDTO,Integer firstResult,Integer maxResults) throws JsonProcessingException;
     List<GroupTaskHistoryDTO> groupTaskHistory(String caseInstanceId) throws JsonProcessingException;
     GroupCaseInstanceDTO groupCaseInstance(String caseInstanceId) throws JsonProcessingException;
     GroupFormDTo groupFormVariables(String caseInstanceId) throws JsonProcessingException;
     AllTaskCaseInstanceDTO groupVariables(String id) throws JsonProcessingException;
     GroupTaskCaseDefinition groupCaseDefinition(String id) throws JsonProcessingException;
}
