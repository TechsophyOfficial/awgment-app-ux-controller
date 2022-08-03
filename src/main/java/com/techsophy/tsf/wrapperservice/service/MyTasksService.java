package com.techsophy.tsf.wrapperservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.dto.MyTasksDTO;

import java.util.List;
import java.util.Map;

public interface MyTasksService
{
   String myTasksCount(MyTasksDTO myTasksDTO);
   List<Map<String, Object>> getAllTasks(MyTasksDTO myTasksDTO, String firstResult, String maxResult) throws JsonProcessingException;
   List<Map<String, Object>> getMyTasksHistory(String caseInstanceId) throws JsonProcessingException;
  Map<String,Object> getMyTasksById(String id) throws JsonProcessingException;
}
