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
import static javax.swing.UIManager.get;

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
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
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
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
                objectMapper.writeValueAsString(new UserTaskActivityDTO(userTaskActivityWrapperDTO.getTaskId(), userTaskActivityWrapperDTO.getAssignee())), httpHeaders);
        try
        {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        }
        catch (Exception e)
        {
            throw new RuntimeException(CLAIM_TASK_FAILED + userTaskActivityWrapperDTO.getTaskId());
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
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
                objectMapper.writeValueAsString(new UserTaskActivityDTO(userTaskActivityWrapperDTO.getTaskId(), userTaskActivityWrapperDTO.getAssignee())), httpHeaders);
        try
        {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        }
        catch (Exception e)
        {
            throw new RuntimeException(SET_ASSIGNEE_FAILED + userTaskActivityWrapperDTO.getTaskId());
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
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
                objectMapper.writeValueAsString(taskDto), httpHeaders);
        try {
            restTemplate.exchange(builder.toUriString(), HttpMethod.POST, httpEntity, Object.class);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
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
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
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
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
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
        HttpEntity<?> httpEntity = new HttpEntity<Object>(
                checklistItemInstanceDTO, httpHeaders);
        ResponseEntity responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, httpEntity, Object.class);
        ApiResponse apiResponse = this.objectMapper.convertValue(responseEntity.getBody(),ApiResponse.class);

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
            HttpEntity<?> httpEntity1 = new HttpEntity<Object>(variable,httpHeaders1);
            ResponseEntity responseEntity1 = restTemplate.exchange(uriComponentsBuilder.toUriString(), HttpMethod.POST, httpEntity1, Object.class);
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
    public void deleteProcessById(DeleteTaskDTO deleteTaskDTO) throws JsonProcessingException
    {
        Map<String,Object> formDataMap=new HashMap<>();
        String ticketValue=deleteTaskDTO.getTicketNumder();
        Map<String,String> ticketData =new HashMap<>();
        String ticketNumber="Review Ticket-"+ticketValue;
        ticketData.put("name",ticketNumber);
        String ticketDetailsUrl=gatewayURI+camundaServletContextPath+ENGINE_REST+TASK;
        UriComponentsBuilder uriComponentsBuilder1 = UriComponentsBuilder.fromHttpUrl(ticketDetailsUrl);
        HttpHeaders httpHeaders1 = new HttpHeaders();
        httpHeaders1.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        httpHeaders1.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> httpEntity1 = new HttpEntity<Object>(ticketData,httpHeaders1);
        ResponseEntity responseEntity1 = restTemplate.exchange(uriComponentsBuilder1.toUriString(), HttpMethod.POST, httpEntity1, Object.class);
        List<Map<String,Object>> responseEntity1Body = (List<Map<String, Object>>) responseEntity1.getBody();
        String processInstanceId= String.valueOf(responseEntity1Body.get(0).get("processInstanceId"));
        String deleteTicketUrl=gatewayURI+camundaServletContextPath+ENGINE_REST+DELETE_TASK_BY_PROCESS_INSTANCE_ID+processInstanceId;
        UriComponentsBuilder uriComponentsBuilder2 = UriComponentsBuilder.fromHttpUrl(deleteTicketUrl);
        HttpHeaders deletehttpHeaders = new HttpHeaders();
        deletehttpHeaders.add(HttpHeaders.AUTHORIZATION, getBearerToken());
        deletehttpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<?> deletehttpEntity = new HttpEntity<Object>(null, deletehttpHeaders);
        ResponseEntity<Object> response = restTemplate.exchange(uriComponentsBuilder2.toUriString(), HttpMethod.DELETE, deletehttpEntity, Object.class);

        //           if(response.getStatusCode().is2xxSuccessful())
//           {
//               String formDataUpdateUrl=gatewayURI+FORM_DATA_UPDATE_URL;
//               StatusCodeUpdateDTO statusCodeUpdateDTO=new StatusCodeUpdateDTO();
//               Map<String,Object> formData=new HashMap<>();
//               formData.put("ticketNumber",deleteTaskDTO.getTicketNumder());
//               formData.put("ticketType",deleteTaskDTO.getTicketType());
//               formData.put("ticketDescription",deleteTaskDTO.getTicketDescription());
//               formData.put("emailId",deleteTaskDTO.getEmailId());
//               formData.put("mobileNumber",deleteTaskDTO.getMobileNumber());
//               formData.put("createdOn",deleteTaskDTO.getTicketNumder());
//               formData.put("status","Cancel");
//               statusCodeUpdateDTO.setId("959323698389225472");
//               statusCodeUpdateDTO.setFormData(formData);
//               UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(formDataUpdateUrl);
//               HttpHeaders formDataHttpHeaders = new HttpHeaders();
//               httpHeaders1.add(HttpHeaders.AUTHORIZATION, getBearerToken());
//               httpHeaders1.setContentType(MediaType.APPLICATION_JSON);
//               HttpEntity<?> formDataHttpEntity = new HttpEntity<Object>(statusCodeUpdateDTO, formDataHttpHeaders);
//               ResponseEntity formDataResponse = restTemplate.exchange(uriComponentsBuilder1.toUriString(), HttpMethod.POST, formDataHttpEntity, Object.class);
//
//           }
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


//    @Override
//    public void deleteProcessById( ) throws JsonProcessingException
//    {
//        String ticketDetailsUrl=gatewayURI+camundaServletContextPath+
//
//        String url=gatewayURI+camundaServletContextPath+DELETE_TASK_BY_PROCESS_INSTANCE_ID;
//
//
//    }


}
