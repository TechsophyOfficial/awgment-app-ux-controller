package com.techsophy.tsf.wrapperservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.config.CamundaModifiedPathURL;
import com.techsophy.tsf.wrapperservice.constants.ApplicationConstants;
import com.techsophy.tsf.wrapperservice.dto.*;
import com.techsophy.tsf.wrapperservice.model.TaskModel;
import com.techsophy.tsf.wrapperservice.service.ProcessService;
import com.techsophy.tsf.wrapperservice.service.impl.CommentServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;

@SpringBootTest
@EnableWebMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CommentServiceTest {
    @Mock
    CamundaModifiedPathURL camundaModifiedPathURL;
    @Mock
    RestTemplate restTemplate;
    @Mock
    UriComponentsBuilder uriComponentsBuilder;
    @Mock
    ObjectMapper objectMapper;
    @Mock
    ProcessService processService;
    @Mock
    TaskInstanceDTO taskInstanceDTO;
    @Mock
    TaskModel taskModel;
    @InjectMocks
    CommentServiceImpl commentService;
    @Value(ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE)
    private String camundaServletContextPath;
    @Value(ApplicationConstants.GATEWAY_URI_VARIABLE)
    private String gatewayURI;
    @BeforeEach
    public void init() {
         ReflectionTestUtils.setField(commentService, "gatewayURI", "https://api-gateway.techsophy.com/api");
         ReflectionTestUtils.setField(commentService, "camundaServletContextPath", "/camunda");
        when(camundaModifiedPathURL.getCamundaPathUri(anyString())).thenReturn("https://localhost:8080");
    }

    @Test
    void getComments() throws Exception
    {
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {
            mb.when(()->UriComponentsBuilder.fromHttpUrl(anyString())).thenReturn(uriComponentsBuilder);
            when(uriComponentsBuilder.queryParam(anyString(),anyString())).thenReturn(uriComponentsBuilder);
            Mockito.when(uriComponentsBuilder.toUriString()).thenReturn("abc");
            CommentDTO commentDTO = new CommentDTO("1", "1", "abc", "abc");
            ResponseEntity<Object> serviceResponse = new ResponseEntity<Object>("abc", HttpStatus.OK);
            ResponseEntity<Object> serviceResponse1 = new ResponseEntity<Object>("abc",HttpStatus.BAD_REQUEST);
            when(restTemplate.exchange(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.any(HttpMethod.class),
                    ArgumentMatchers.any(),
                    ArgumentMatchers.<Class<Object>>any()))
                    .thenReturn(serviceResponse).thenReturn(serviceResponse1);
            ApiResponse apiResponse = new ApiResponse("abc", true, "abc");
            Mockito.when(objectMapper.convertValue(any(), eq(ApiResponse.class))).thenReturn(apiResponse);
            Mockito.when(objectMapper.convertValue(any(),any(TypeReference.class))).thenReturn(List.of(taskModel));
            commentService.getComments("1","1","abc");
            commentService.getComments("1","1","abc");
            Assertions.assertThrows(IllegalArgumentException.class,()->commentService.getComments(null,null,null));
        }
    }

    @Test
    void createCommentTest(){
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        ApiResponse apiResponse = Mockito.mock(ApiResponse.class);
        CommentDTO commentDTO = new CommentDTO("taskId", "pid", "bk", "comment");
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(Object.class))).thenReturn(response);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.ACCEPTED);
        Mockito.when(objectMapper.convertValue(any(), eq(ApiResponse.class))).thenReturn(apiResponse);

        Map<String, Object> actualOutput = commentService.createComment(commentDTO);
        Map<String, Object> expectedOutput = objectMapper.convertValue(apiResponse.getData(), Map.class);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void createCommentTestWhileThrowingException(){
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        CommentDTO commentDTO = new CommentDTO(null, "pid", null, "comment");
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(Object.class))).thenReturn(response);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        Assertions.assertThrows(IllegalArgumentException.class, () -> commentService.createComment(commentDTO));
    }

    @Test
    void createCommentTestWhileCommentDtoIsNull(){
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        Map<String, Object> expectedOutput = objectMapper.convertValue(response, Map.class);
        TaskModel task = Mockito.mock(TaskModel.class);
        CommentDTO commentDTO = new CommentDTO(null, null, "bKey", "comment");
        PaginationDTO<List<TaskInstanceDTO>> page = new PaginationDTO<>(List.of(taskInstanceDTO), 1, 1, 1, 1l, 1l);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(Object.class))).thenReturn(response);
        Mockito.when(processService.getTasksByQuery(any(), any(), any())).thenReturn(page);
        Mockito.when(objectMapper.convertValue(any(), any(TypeReference.class))).thenReturn(List.of(task));

        Map<String, Object> actualOutput = commentService.createComment(commentDTO);
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void createCommentTestWhileTaskIsEmpty(){
        ResponseEntity response = Mockito.mock(ResponseEntity.class);
        List<TaskModel> list = new ArrayList<>();
        CommentDTO commentDTO = new CommentDTO(null, null, "bKey", "comment");
        PaginationDTO<List<TaskInstanceDTO>> page = new PaginationDTO<>(List.of(taskInstanceDTO), 1, 1, 1, 1l, 1l);
        Mockito.when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(), eq(Object.class))).thenReturn(response);
        Mockito.when(response.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);
        Mockito.when(processService.getTasksByQuery(any(), any(), any())).thenReturn(page);
        Mockito.when(objectMapper.convertValue(any(), any(TypeReference.class))).thenReturn(list);

        Assertions.assertThrows(IllegalArgumentException.class, () -> commentService.createComment(commentDTO));
    }

}
