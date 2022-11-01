package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.exception.*;
import com.techsophy.tsf.wrapperservice.service.ProcessService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.config.TokenConfig.getBearerToken;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.*;
import static com.techsophy.tsf.wrapperservice.constants.ErrorConstants.NULL_OR_EMPTY_TASK_ID;
import static com.techsophy.tsf.wrapperservice.constants.MessageConstants.*;

@RefreshScope
@Service
@RequiredArgsConstructor
public class ProcessServiceImpl implements ProcessService
{
    @Value(CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;
    @Value(GATEWAY_URI_VARIABLE)
    private String gatewayURI;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final GlobalMessageSource  globalMessageSource;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * get task by Id
     * @param taskId
     * @return TaskInstanceDTO
     */
    @Override
    public TaskInstanceDTO getTaskById(String taskId)
    {
        if(taskId.isEmpty() || taskId.isBlank())
        {
            throw new MissingMandatoryDataException(NULL_OR_EMPTY_TASK_ID,globalMessageSource.get(NULL_OR_EMPTY_TASK_ID));
        }
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.GET_TASK + "/" + taskId;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Object.class);
        return this.objectMapper.convertValue(response.getBody(), TaskInstanceDTO.class);
    }

    /**
     * get all tasks
     *
     * @param taskQueryDTO
     * @param page
     * @param size
     * @return List
     */
    @Override
    @SneakyThrows
    public PaginationDTO<List<TaskInstanceDTO>> getTasksByQuery(TaskQueryDTO taskQueryDTO, Integer page, Integer size)
    {
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.GET_ALL_TASK;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(PAGE, page)
                .queryParam(SIZE, size);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(this.objectMapper.writeValueAsString(taskQueryDTO), httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        return this.objectMapper.convertValue(response.getBody(), new TypeReference<>() {
        });
    }

    @Override
    public PaginationDTO<List<TaskInstanceDTO>> getAllTasks(Integer page, Integer size)
    {
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.GET_ALL_TASK;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(PAGE, page)
                .queryParam(SIZE, size);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>( httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Object.class);
        return this.objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
    }

    /**
     * get task count
     * @param taskQueryDTO
     * @return TaskCountDTO
     */
    @Override
    @SneakyThrows
    public TaskCountDTO getTasksCount(TaskQueryDTO taskQueryDTO)
    {
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.GET_TASK_COUNT;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity<>(this.objectMapper.writeValueAsString(taskQueryDTO), httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        return this.objectMapper.convertValue(response.getBody(), TaskCountDTO.class);
    }

    /**
     * start process by definition key
     * @param processInstance
     * @return ProcessInstanceResponseDTO
     */
    @Override
    @SneakyThrows({JsonProcessingException.class})
    public ProcessInstanceResponseDTO startProcessByDefinitionKey(ProcessInstance processInstance)
           {
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.START_PROCESS;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(processInstance), httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        ApiResponse<Object> apiResponse = this.objectMapper.convertValue(response.getBody(), ApiResponse.class);
        return this.objectMapper.convertValue(apiResponse.getData(), ProcessInstanceResponseDTO.class);
    }

    @Override
    @SneakyThrows
    public DeployProcessResponseDTO deployProcess(String name, MultipartFile file)
    {
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.DEPLOY_PROCESS;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        ByteArrayResource contentsAsResource = new ByteArrayResource(file.getBytes()){
            @Override public String getFilename()
            {
                return file.getOriginalFilename();
            }
        };
        LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file",contentsAsResource);
        body.add("deployment-name",name);
        HttpEntity<LinkedMultiValueMap<String, Object>> httpEntity = new HttpEntity<>(body,httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        ApiResponse<Object> apiResponse = this.objectMapper.convertValue(response.getBody(), ApiResponse.class);
        return this.objectMapper.convertValue(apiResponse.getData(), DeployProcessResponseDTO.class);
    }

    /**
     * complete current active task by business key
     * @param genericDTO
     */
    @Override
    @SneakyThrows
    public void completeTask(GenericDTO genericDTO)
    {
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.COMPLETE_TASK;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(genericDTO), httpHeaders);
        try
        {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    /**
     * complete current active task by business key
     * @param resumeProcessRequestDTO
     */
    @Override
    @SneakyThrows
    public void resumeProcess(ResumeProcessRequestDTO resumeProcessRequestDTO)
    {
        String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.RESUME_PROCESS;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(objectMapper.writeValueAsString(resumeProcessRequestDTO), httpHeaders);
        try
        {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        }
        catch (Exception e)
        {
            throw new ResumeProcessException(RESUME_PROCESS_FAILED,e.getMessage());
        }
    }

    @Override
    public void updateTask(String taskId,UpdateTaskDto updateTaskDto) throws JsonProcessingException {
        if(taskId.isEmpty() || taskId.isBlank())
        {
            throw new MissingMandatoryDataException(NULL_OR_EMPTY_TASK_ID,globalMessageSource.get(NULL_OR_EMPTY_TASK_ID));
        }
        String url = gatewayURI + camundaServletContextPath + UPDATE_TASK +"/"+taskId;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(
                objectMapper.writeValueAsString(updateTaskDto), httpHeaders);
        try
        {
            restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, httpEntity, Object.class);
        }
        catch (Exception e)
        {
            throw new UpdateTaskException(UPDATE_TASK_FAILED + taskId,e.getMessage());
        }
    }

    /**
     *
     * @param userTaskActivityWrapperDTO
     */
    @Override
    @SneakyThrows
    public void claimTask(UserTaskActivityWrapperDTO userTaskActivityWrapperDTO)
    {
        String url = gatewayURI + camundaServletContextPath + CLAIM_TASK.replace("{id}", userTaskActivityWrapperDTO.getTaskId());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(
                objectMapper.writeValueAsString(new UserTaskActivityDTO(userTaskActivityWrapperDTO.getTaskId(), userTaskActivityWrapperDTO.getAssignee())), httpHeaders);
        try
        {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(CLAIM_TASK_FAILED + userTaskActivityWrapperDTO.getTaskId());
        }
    }

    /**
     *
     * @param userTaskActivityWrapperDTO
     */
    @Override
    @SneakyThrows
    public void setAssignee(UserTaskActivityWrapperDTO userTaskActivityWrapperDTO)
    {
        String url = gatewayURI + camundaServletContextPath + SET_ASSIGNEE.replace("{id}", userTaskActivityWrapperDTO.getTaskId());
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(
                objectMapper.writeValueAsString(new UserTaskActivityDTO(userTaskActivityWrapperDTO.getTaskId(), userTaskActivityWrapperDTO.getAssignee())), httpHeaders);
        try
        {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        }
        catch (Exception e)
        {
            throw new IllegalArgumentException(SET_ASSIGNEE_FAILED + userTaskActivityWrapperDTO.getTaskId());
        }
    }

    /**
     * create task
     *
     * @param taskDto
     */
    @Override
    public void createTask(TaskDto taskDto) throws JsonProcessingException {


        String url = gatewayURI + camundaServletContextPath + CREATE_TASK;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(
                objectMapper.writeValueAsString(taskDto), httpHeaders);
        try {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        } catch (Exception ex) {
            throw new CreateTaskException(ex.getMessage(), ex.getMessage());
        }

    }

    @Override
    public void addUserOrGroupToTask(String taskId, IdentityLinksDto identityLinksDto) throws JsonProcessingException {
        String url = gatewayURI + camundaServletContextPath + IDENTITY_LINK.replace("{id}", taskId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(
                objectMapper.writeValueAsString(identityLinksDto), httpHeaders);
        try {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        } catch (Exception ex) {
            throw new AddUserOrGroupToTaskException(ex.getMessage(), ex.getMessage());
        }
    }

    @Override
    public List<IdentityLinksDto> getIdentityLinksOfTask(String taskId) throws JsonProcessingException {
        String url = gatewayURI + camundaServletContextPath + IDENTITY_LINK.replace("{id}", taskId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Object.class);
        return this.objectMapper.convertValue(response.getBody(), this.objectMapper.getTypeFactory().constructCollectionType(List.class, IdentityLinksDto.class));
    }

    @Override
    public void deleteIdentityLinkOfTask(String taskId, IdentityLinksDto identityLinksDto) throws JsonProcessingException {
        String url = gatewayURI + camundaServletContextPath + IDENTITY_LINK_DELETE.replace("{id}", taskId);
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(
                objectMapper.writeValueAsString(identityLinksDto), httpHeaders);
        try {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        } catch (Exception ex) {
            throw new DeleteIdentityLinkException(ex.getMessage(), ex.getMessage());
        }
    }

    @Override
    public List<TaskHistoryDto> getHistoryOfTask(String filter) throws JsonProcessingException {
        String url = gatewayURI + camundaServletContextPath + TASK_HISTORY_USER_OPERATION + "?" + filter.replace(";", "&");
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, httpEntity, Object.class);
        return this.objectMapper.convertValue(response.getBody(), this.objectMapper.getTypeFactory().constructCollectionType(List.class, TaskHistoryDto.class));
    }

    @Override
    public void completeTaskWithChecklistItemId(ChecklistItemInstanceDTO checklistItemInstanceDTO) throws JsonProcessingException
    {
        //For getting checklistInstanceId
        String url = gatewayURI+CHECKLIST_ITEM_INSTANCE_COMPLETION_URL;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(
                checklistItemInstanceDTO, httpHeaders);
        ResponseEntity<?> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, httpEntity, Object.class);
        ApiResponse<?> apiResponse = this.objectMapper.convertValue(responseEntity.getBody(),ApiResponse.class);

        // getting task completed
        if(((LinkedHashMap) apiResponse.getData()).get("status")!=null && ((LinkedHashMap) apiResponse.getData()).get("status").equals("Complete"))
        {
            String checklistInstanceId = (String) ((LinkedHashMap) apiResponse.getData()).get("checklistInstanceId");
            Map<String,Object> variable = new HashMap<>();
            variable.put("variableName","checklistInstanceId");
            variable.put("variableValue",checklistInstanceId);

            String url1 = gatewayURI+ camundaServletContextPath+VARIABLE_INSTANCE;
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url1);
            HttpHeaders httpHeaders1 = new HttpHeaders();
            httpHeaders1.add(HttpHeaders.AUTHORIZATION, getBearerToken());
            httpHeaders1.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> httpEntity1 = new HttpEntity<>(variable,httpHeaders1);
            ResponseEntity<?> responseEntity1 = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.POST, httpEntity1, Object.class);
            List<Map<String,Object>> responseEntity1Body = (List<Map<String, Object>>) responseEntity1.getBody();
            Map<String,Object> map2 = responseEntity1Body.get(0);
            String taskId = map2.get("taskId").toString();
            //Complete task
            GenericDTO genericDTO = new GenericDTO(null,taskId,Map.of());
            this.completeTask(genericDTO);
        }
        else {
            throw new IllegalArgumentException("Complete all pending item-instances");
        }

    }

    @Override
    public PaginationDTO<List<HistoricInstanceDTO>> getHistoryTasksByQuery(HistoricQueryInstanceDTO historicQueryInstanceDTO, Integer page, Integer size) throws JsonProcessingException {
        String url = gatewayURI + camundaServletContextPath + GET_ALL_HISTORY_TASK;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam(PAGE, page)
                .queryParam(SIZE, size);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity = new HttpEntity<>(this.objectMapper.writeValueAsString(historicQueryInstanceDTO), httpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        return this.objectMapper.convertValue(response.getBody(), new TypeReference<>() {
        });
    }

}
