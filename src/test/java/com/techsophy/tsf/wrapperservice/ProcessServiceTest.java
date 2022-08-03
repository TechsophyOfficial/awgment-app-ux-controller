package com.techsophy.tsf.wrapperservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.constants.ApplicationConstants;
import com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.exception.MissingMandatoryDataException;
import com.techsophy.tsf.wrapperservice.exception.ResumeProcessException;
import com.techsophy.tsf.wrapperservice.service.impl.ProcessServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.UPDATE_TASK;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@SpringBootTest
@EnableWebMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProcessServiceTest {
    @Mock
    RestTemplate restTemplate;
    @Mock
    UriComponentsBuilder uriComponentsBuilder;
    @Mock
    ObjectMapper objectMapper;
    @InjectMocks
    ProcessServiceImpl processService;
    @Value(ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;

    @Value(ApplicationConstants.GATEWAY_URI_VARIABLE)
    private String gatewayURI;
    @Test
    void getTaskById() throws Exception
    {
        Map<String,Object> map = new HashMap<>();
        map.put("abc","abc");
        ResponseEntity<Map> serviceResponse =
                new ResponseEntity<Map>(map, HttpStatus.OK);
        when(restTemplate.exchange(
                        ArgumentMatchers.anyString(),
                        ArgumentMatchers.any(HttpMethod.class),
                        ArgumentMatchers.any(),
                        ArgumentMatchers.<Class<Map>>any()))
                .thenReturn(serviceResponse);
        TaskInstanceDTO taskInstanceDTO= new TaskInstanceDTO("1","abc", "abc", new Date(), new Date(),new Date(),"abc","abc","abc","abc","abc",1,"abc","abc","abc","abc","abc","abc",true,"abc","abc", null, null, null,null, null, List.of());
        when(objectMapper.convertValue(any(),eq(TaskInstanceDTO.class))).thenReturn(taskInstanceDTO);
        processService.getTaskById("1");
       Assertions.assertThrows(NullPointerException.class,()-> processService.getTaskById(" "));
    }
    @Test
    void startProcessByDefinitionKey() throws Exception
    {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.START_PROCESS;
            URI uri = new URI(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
            HttpEntity<String> request = new HttpEntity<>(("bar"));
            mb.when(()->UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder);
            Map<String, Object> map = new HashMap<>();
            map.put("abc", "abc");
            ProcessInstanceResponseDTO processInstanceResponseDTO = new ProcessInstanceResponseDTO("1","a",map,true);
            ProcessInstance processInstance = new ProcessInstance("a", "a", map);
            ResponseEntity<Map> serviceResponse =
                    new ResponseEntity<Map>(map, HttpStatus.OK);
            when(restTemplate.exchange(
                            uriComponentsBuilder.toString(),
                            POST,
                            request,
                           Map.class))
                    .thenReturn(serviceResponse);
           Assertions.assertThrows(NullPointerException.class, ()->processService.startProcessByDefinitionKey(processInstance));
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
            Assertions.assertThrows(NullPointerException.class, ()->processService.deployProcess("a", multipartFile));
        }
    }
    @Test
    void resumeProcess() throws Exception
    {
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
         Assertions.assertThrows(ResumeProcessException.class,()->processService.resumeProcess(resumeProcessRequestDTO));
        }
    }
    @Test
    void claimTask() throws Exception
    {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

          UserTaskActivityWrapperDTO   userTaskActivityWrapperDTO = new UserTaskActivityWrapperDTO("1","abc");
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
    void setAssignee() throws Exception
    {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            UserTaskActivityWrapperDTO   userTaskActivityWrapperDTO = new UserTaskActivityWrapperDTO("1","abc");
            String url = gatewayURI + camundaServletContextPath + UPDATE_TASK +"/"+"123sdfg";
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
//    @Test
//    void updateTask() throws Exception
//    {
//        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {
//
//            UpdateTaskDto updateTaskDto = new UpdateTaskDto("22/04/2022","22/05/06");
//            String url = gatewayURI + camundaServletContextPath + CamundaApiConstants.START_PROCESS;
//            URI uri = new URI(url);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.TEXT_PLAIN);
//            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
//            HttpEntity<String> request = new HttpEntity<>(("bar"));
//            mb.when(() -> UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder);
//            Map<String, Object> map = new HashMap<>();
//            map.put("abc", "abc");
//
//            ResponseEntity<Map> serviceResponse =
//                    new ResponseEntity<Map>(map, HttpStatus.OK);
//            when(restTemplate.exchange(
//                    uriComponentsBuilder.toString(),
//                    PUT,
//                    request,
//                    Map.class))
//                    .thenReturn(serviceResponse);
//            MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
//            processService.updateTask("1234",updateTaskDto);
//
   }

