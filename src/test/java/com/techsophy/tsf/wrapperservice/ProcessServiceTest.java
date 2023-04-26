package com.techsophy.tsf.wrapperservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.CamundaModifiedPathURL;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.constants.ApplicationConstants;
import com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.exception.*;
import com.techsophy.tsf.wrapperservice.service.impl.ProcessServiceImpl;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.sql.Ref;
import java.util.*;

import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.UPDATE_TASK;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpMethod.POST;

@SpringBootTest
@EnableWebMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProcessServiceTest {

    @Mock
    CamundaModifiedPathURL camundaModifiedPathURL;
    @Mock
    RestTemplate restTemplate;
    @Mock
    UriComponentsBuilder uriComponentsBuilder;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    GlobalMessageSource globalMessageSource;
    @InjectMocks
    ProcessServiceImpl processService;
    @Value(ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(ApplicationConstants.GATEWAY_URI_VARIABLE)
    private String gatewayURI;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(processService, "camundaServletContextPath", "http://apigateway.techsophy.com");
        ReflectionTestUtils.setField(processService, "gatewayURI", "http://apigateway.techsophy.com");
        when(camundaModifiedPathURL.getCamundaPathUri(anyString())).thenReturn("https://localhost:8080");
    }

    @Test
    void getTaskById() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("abc", "abc");
        ResponseEntity<Map> serviceResponse =
                new ResponseEntity<Map>(map, HttpStatus.OK);
        when(restTemplate.exchange(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.any(HttpMethod.class),
                ArgumentMatchers.any(),
                ArgumentMatchers.<Class<Map>>any()))
                .thenReturn(serviceResponse);
        TaskInstanceDTO taskInstanceDTO = new TaskInstanceDTO("1", "abc", "abc", new Date(), new Date(), new Date(), "abc", "abc", "abc", "abc", "abc", 1, "abc", "abc", "abc", "abc", "abc", "abc", true, "abc", "abc", null, null, null, null, null, List.of());
        when(objectMapper.convertValue(any(), eq(TaskInstanceDTO.class))).thenReturn(taskInstanceDTO);
        processService.getTaskById("1");
        Assertions.assertThrows(MissingMandatoryDataException.class, () -> processService.getTaskById(" "));
    }

    @Test
    void startProcessByDefinitionKey() throws Exception {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.START_PROCESS;
            URI uri = new URI(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
            HttpEntity<String> request = new HttpEntity<>(("bar"));
            mb.when(() -> UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder);
            Map<String, Object> map = new HashMap<>();
            map.put("abc", "abc");
            ProcessInstanceResponseDTO processInstanceResponseDTO = new ProcessInstanceResponseDTO("1", "a", map, true);
            ProcessInstance processInstance = new ProcessInstance("a", "a", map);
            ResponseEntity<Map> serviceResponse =
                    new ResponseEntity<Map>(map, HttpStatus.OK);
            when(restTemplate.exchange(
                    uriComponentsBuilder.toString(),
                    POST,
                    request,
                    Map.class))
                    .thenReturn(serviceResponse);
            Assertions.assertThrows(NullPointerException.class, () -> processService.startProcessByDefinitionKey(processInstance));
        }
    }

    @Test
    void deployProcess() throws Exception {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.START_PROCESS;
            URI uri = new URI(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
            HttpEntity<String> request = new HttpEntity<>(("bar"));
            mb.when(() -> UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder);
            Map<String, Object> map = new HashMap<>();
            map.put("abc", "abc");
            ProcessInstanceResponseDTO processInstanceResponseDTO = new ProcessInstanceResponseDTO("1", "a", map, true);
            ProcessInstance processInstance = new ProcessInstance("a", "a", map);
            ResponseEntity<Map> serviceResponse =
                    new ResponseEntity<Map>(map, HttpStatus.OK);
            when(restTemplate.exchange(
                    uriComponentsBuilder.toString(),
                    POST,
                    request,
                    Map.class))
                    .thenReturn(serviceResponse);
            MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
            Assertions.assertThrows(NullPointerException.class, () -> processService.deployProcess("a", multipartFile));
        }
    }

    @Test
    void resumeProcess() throws Exception {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            ResumeProcessRequestDTO resumeProcessRequestDTO = new ResumeProcessRequestDTO();
            String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.START_PROCESS;
            URI uri = new URI(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
            HttpEntity<String> request = new HttpEntity<>(("bar"));
            mb.when(() -> UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder).thenReturn(null);
            Map<String, Object> map = new HashMap<>();
            map.put("abc", "abc");
            ProcessInstanceResponseDTO processInstanceResponseDTO = new ProcessInstanceResponseDTO("1", "a", map, true);
            ProcessInstance processInstance = new ProcessInstance("a", "a", map);
            ResponseEntity<Map> serviceResponse =
                    new ResponseEntity<Map>(map, HttpStatus.OK);
            when(restTemplate.exchange(
                    uriComponentsBuilder.toString(),
                    POST,
                    request,
                    Map.class))
                    .thenReturn(serviceResponse).thenReturn(null);
            MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
            processService.resumeProcess(resumeProcessRequestDTO);
            Assertions.assertThrows(ResumeProcessException.class, () -> processService.resumeProcess(resumeProcessRequestDTO));
        }
    }

    @Test
    void claimTask() throws Exception {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            UserTaskActivityWrapperDTO userTaskActivityWrapperDTO = new UserTaskActivityWrapperDTO("1", "abc");
            String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.START_PROCESS;
            URI uri = new URI(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
            HttpEntity<String> request = new HttpEntity<>(("bar"));
            mb.when(() -> UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder);
            Map<String, Object> map = new HashMap<>();
            map.put("abc", "abc");
            ProcessInstanceResponseDTO processInstanceResponseDTO = new ProcessInstanceResponseDTO("1", "a", map, true);
            ProcessInstance processInstance = new ProcessInstance("a", "a", map);
            ResponseEntity<Map> serviceResponse =
                    new ResponseEntity<Map>(map, HttpStatus.OK);
            when(restTemplate.exchange(
                    uriComponentsBuilder.toString(),
                    POST,
                    request,
                    Map.class))
                    .thenReturn(serviceResponse);
            MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
            processService.claimTask(userTaskActivityWrapperDTO);
        }
    }

    @Test
    void setAssignee() throws Exception {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            UserTaskActivityWrapperDTO userTaskActivityWrapperDTO = new UserTaskActivityWrapperDTO("1", "abc");
            String url = gatewayURI + camundaServletContextPath + UPDATE_TASK + "/" + "123sdfg";
            URI uri = new URI(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
            HttpEntity<String> request = new HttpEntity<>(("bar"));
            mb.when(() -> UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder);
            Map<String, Object> map = new HashMap<>();
            map.put("abc", "abc");
            ProcessInstanceResponseDTO processInstanceResponseDTO = new ProcessInstanceResponseDTO("1", "a", map, true);
            ProcessInstance processInstance = new ProcessInstance("a", "a", map);
            ResponseEntity<Map> serviceResponse =
                    new ResponseEntity<Map>(map, HttpStatus.OK);
            when(restTemplate.exchange(
                    uriComponentsBuilder.toString(),
                    POST,
                    request,
                    Map.class))
                    .thenReturn(serviceResponse);
            MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
            processService.setAssignee(userTaskActivityWrapperDTO);
        }
    }

    @Test
    void getTasksByQueryTest(){
        TaskQueryDTO taskQueryDTO = Mockito.mock(TaskQueryDTO.class);
        UriComponents uriComponents = Mockito.mock(UriComponents.class);
        ResponseEntity response = Mockito.mock(ResponseEntity.class);

        Mockito.when(uriComponentsBuilder.path(any())).thenReturn(uriComponentsBuilder);
        Mockito.when(uriComponentsBuilder.buildAndExpand(anyLong())).thenReturn(uriComponents);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);

        PaginationDTO<List<TaskInstanceDTO>> actualOutput = processService.getTasksByQuery(taskQueryDTO, 1, 1);
        PaginationDTO<List<TaskInstanceDTO>> expectedOutput = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getAllTasksTest(){
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);

        PaginationDTO<List<TaskInstanceDTO>> actualOutput = processService.getAllTasks(1, 1);
        PaginationDTO<List<TaskInstanceDTO>> expectedOutput = objectMapper.convertValue(response.getBody(), new TypeReference<>() {});
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void getTaskCountTest(){
        TaskQueryDTO taskQueryDTO = Mockito.mock(TaskQueryDTO.class);
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);

        TaskCountDTO actualOutput = processService.getTasksCount(taskQueryDTO);
        TaskCountDTO expectedOutput = objectMapper.convertValue(response.getBody(), TaskCountDTO.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void completeTaskTest(){
        GenericDTO genericDTO = Mockito.mock(GenericDTO.class);

        processService.completeTask(genericDTO);
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class));
    }

    @Test
    void completeTaskTestWhileThrowingException(){
        GenericDTO genericDTO = Mockito.mock(GenericDTO.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenThrow(IllegalArgumentException.class);

        Assertions.assertThrows(IllegalArgumentException.class, () -> processService.completeTask(genericDTO));
    }

    @Test
    void updateTaskTest() throws JsonProcessingException {
        UpdateTaskDto updateTaskDto = Mockito.mock(UpdateTaskDto.class);
        String taskId = "taskId";

        processService.updateTask(taskId, updateTaskDto);
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class));
    }

    @Test
    void updateTaskTestWhileTaskIdIsEmpty() throws JsonProcessingException {
        UpdateTaskDto updateTaskDto = Mockito.mock(UpdateTaskDto.class);
        String taskId = "";
        Mockito.when(globalMessageSource.get(anyString())).thenReturn("error_message");

        Assertions.assertThrows(MissingMandatoryDataException.class, () -> processService.updateTask(taskId, updateTaskDto));
    }

    @Test
    void updateTaskTestWhileThrowingException() throws JsonProcessingException {
        UpdateTaskDto updateTaskDto = Mockito.mock(UpdateTaskDto.class);
        String taskId = "taskId";
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenThrow(UpdateTaskException.class);

        Assertions.assertThrows(UpdateTaskException.class, () -> processService.updateTask(taskId, updateTaskDto));
    }

    @Test
    void createTask() throws JsonProcessingException {
        TaskDto taskDto = Mockito.mock(TaskDto.class);

        processService.createTask(taskDto);
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class));
    }

    @Test
    void createTaskWhileThrowingException() throws JsonProcessingException {
        TaskDto taskDto = Mockito.mock(TaskDto.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenThrow(CreateTaskException.class);
        Assertions.assertThrows(CreateTaskException.class, () -> processService.createTask(taskDto));
    }

    @Test
    void addUserOrGroupToTaskTest() throws JsonProcessingException {
        IdentityLinksDto identityLinksDto = Mockito.mock(IdentityLinksDto.class);
        processService.addUserOrGroupToTask("id", identityLinksDto);
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class));
    }

    @Test
    void addUserOrGroupToTaskTestWhileThrowingException() throws JsonProcessingException {
        IdentityLinksDto identityLinksDto = Mockito.mock(IdentityLinksDto.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenThrow(AddUserOrGroupToTaskException.class);

        Assertions.assertThrows(AddUserOrGroupToTaskException.class, () -> processService.addUserOrGroupToTask("id", identityLinksDto));
    }

    @Test
    void getIdentityLinksOfTaskTest() throws JsonProcessingException {
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        TypeFactory typeFactory = Mockito.mock(TypeFactory.class);

        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);
        Mockito.when(objectMapper.getTypeFactory()).thenReturn(typeFactory);

        List<IdentityLinksDto> actualOutput = processService.getIdentityLinksOfTask("id");
        List<IdentityLinksDto> expectedOutput = objectMapper.convertValue(response.getBody(), this.objectMapper.getTypeFactory().constructCollectionType(List.class, IdentityLinksDto.class));
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void deleteIdentityLinkOfTaskTest() throws JsonProcessingException {
        IdentityLinksDto identityLinksDto = Mockito.mock(IdentityLinksDto.class);

        processService.deleteIdentityLinkOfTask("id", identityLinksDto);
        verify(restTemplate, times(1)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class));
    }

    @Test
    void deleteIdentityLinkOfTaskTestWhileThrowingException() throws JsonProcessingException {
        IdentityLinksDto identityLinksDto = Mockito.mock(IdentityLinksDto.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenThrow(DeleteIdentityLinkException.class);

        Assertions.assertThrows(DeleteIdentityLinkException.class, () -> processService.deleteIdentityLinkOfTask("id", identityLinksDto));
    }

    @Test
    void getHistoryOfTaskTest() throws JsonProcessingException {
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        TypeFactory typeFactory = Mockito.mock(TypeFactory.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);
        Mockito.when(objectMapper.getTypeFactory()).thenReturn(typeFactory);
        List<TaskHistoryDto> actualOutput = processService.getHistoryOfTask("id");
        List<TaskHistoryDto> expectedOutput = objectMapper.convertValue(response.getBody(), this.objectMapper.getTypeFactory().constructCollectionType(List.class, TaskHistoryDto.class));
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void completeTaskWithChecklistItemIdTest() throws JsonProcessingException {
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        ApiResponse apiResponse = Mockito.mock(ApiResponse.class);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("taskId", "task");
        linkedHashMap.put("status", "Complete");
        ChecklistItemInstanceDTO dto = Mockito.mock(ChecklistItemInstanceDTO.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);
        Mockito.when(objectMapper.convertValue(any(),eq(ApiResponse.class))).thenReturn(apiResponse);
        Mockito.when(apiResponse.getData()).thenReturn(linkedHashMap);
        Mockito.when(response.getBody()).thenReturn(List.of(linkedHashMap));

        processService.completeTaskWithChecklistItemId(dto);
        verify(restTemplate, times(3)).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class));
    }

    @Test
    void completeTaskWithChecklistItemIdTestWhileThrowingException() throws JsonProcessingException {
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        ApiResponse apiResponse = Mockito.mock(ApiResponse.class);
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put("taskId", "task");
        ChecklistItemInstanceDTO dto = Mockito.mock(ChecklistItemInstanceDTO.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);
        Mockito.when(objectMapper.convertValue(any(),eq(ApiResponse.class))).thenReturn(apiResponse);
        Mockito.when(apiResponse.getData()).thenReturn(linkedHashMap);

        Assertions.assertThrows(IllegalArgumentException.class, () -> processService.completeTaskWithChecklistItemId(dto));
    }

    @Test
    void getHistoryTasksByQuery() throws JsonProcessingException {
        HistoricQueryInstanceDTO dto = Mockito.mock(HistoricQueryInstanceDTO.class);
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(Object.class))).thenReturn(response);

        PaginationDTO<List<HistoricInstanceDTO>> actualOutput = processService.getHistoryTasksByQuery(dto, 1, 1);
        PaginationDTO<List<HistoricInstanceDTO>> expectedOutput = objectMapper.convertValue(response.getBody(), new TypeReference<>() {
        });

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
}

