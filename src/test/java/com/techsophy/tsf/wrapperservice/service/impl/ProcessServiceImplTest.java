package com.techsophy.tsf.wrapperservice.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.techsophy.tsf.wrapperservice.config.GlobalMessageSource;
import com.techsophy.tsf.wrapperservice.config.TokenConfig;
import com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants;
import com.techsophy.tsf.wrapperservice.dto.DeleteTaskDTO;
import com.techsophy.tsf.wrapperservice.dto.ProcessInstance;
import com.techsophy.tsf.wrapperservice.dto.ProcessInstanceResponseDTO;
import com.techsophy.tsf.wrapperservice.dto.ResumeProcessRequestDTO;
import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import com.techsophy.tsf.wrapperservice.exception.ResumeProcessException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.CAMUNDA_SERVLET_CONTEXT_PATH_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.GATEWAY_URI_VARIABLE;
import static com.techsophy.tsf.wrapperservice.constants.CamundaApiConstants.UPDATE_TASK;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.POST;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class ProcessServiceImplTest {
    @Mock
    TokenConfig tokenConfig;
    @Mock
    RestTemplate restTemplate;
    @Mock
    GlobalMessageSource globalMessageSource;
    @Mock
    UriComponentsBuilder uriComponentsBuilder;

    @InjectMocks
    ProcessServiceImpl processService;

    private String camundaServletContextPath="google.com";
    private String gatewayURI="http://";

    @Test
    void deleteProcessById() throws JsonProcessingException, URISyntaxException {
//        String idToken = "Bearer eyJraWQiOiIxZTlnZGs3IiwiYWxnIjoiUlMyNTYifQ.ewogImlzcyI6ICJodHRwOi8vc2VydmVyLmV4YW1wbGUuY29tIiwKICJzdWIiOiAiMjQ4Mjg5NzYxMDAxIiwKICJhdWQiOiAiczZCaGRSa3F0MyIsCiAibm9uY2UiOiAibi0wUzZfV3pBMk1qIiwKICJleHAiOiAxMzExMjgxOTcwLAogImlhdCI6IDEzMTEyODA5NzAsCiAibmFtZSI6ICJKYW5lIERvZSIsCiAiZ2l2ZW5fbmFtZSI6ICJKYW5lIiwKICJmYW1pbHlfbmFtZSI6ICJEb2UiLAogImdlbmRlciI6ICJmZW1hbGUiLAogImJpcnRoZGF0ZSI6ICIwMDAwLTEwLTMxIiwKICJlbWFpbCI6ICJqYW5lZG9lQGV4YW1wbGUuY29tIiwKICJwaWN0dXJlIjogImh0dHA6Ly9leGFtcGxlLmNvbS9qYW5lZG9lL21lLmpwZyIKfQ.rHQjEmBqn9Jre0OLykYNnspA10Qql2rvx4FsD00jwlB0Sym4NzpgvPKsDjn_wMkHxcp6CilPcoKrWHcipR2iAjzLvDNAReF97zoJqq880ZD1bwY82JDauCXELVR9O6_B0w3K-E7yM2macAAgNCUwtik6SjoSUZRcf-O5lygIyLENx882p6MtmwaL1hd6qn5RZOQ0TLrOYu0532g9Exxcm-ChymrB4xLykpDj3lUivJt63eEGGN6DH5K6o33TcxkIjNrCD4XB1CKKumZvCedgHHF3IAK4dVEDSUoGlH9z4pP_eWYNXvqQOjGs-rDaQzUHl6cQQWNiDpWOl_lxXjQEvQ";
//        RequestAttributes requestAttributes = mock(RequestAttributes.class);
//        RequestContextHolder requestContextHolder = mock(RequestContextHolder.class);
        DeleteTaskDTO deleteTaskDTO = new DeleteTaskDTO("firstname","lastname",
                "123","9090909090","ticketDescription",
                "ticketType","email", "creadtedOn","status");
//        ReflectionTestUtils.setField(processService, "camundaServletContextPath", camundaServletContextPath);
//        ReflectionTestUtils.setField(processService, "gatewayURI", gatewayURI);
//        when(tokenConfig.getBearerToken()).thenReturn(idToken);
//        //Assertions.assertThrows(NullPointerException.class,()->
//        processService.deleteProcessById(deleteTaskDTO);
        try (MockedStatic<UriComponentsBuilder> mb = Mockito.mockStatic(UriComponentsBuilder.class)) {

            ResumeProcessRequestDTO resumeProcessRequestDTO = new ResumeProcessRequestDTO();
            String url = gatewayURI + camundaServletContextPath + UPDATE_TASK +"/"+"123sdfg";
            URI uri = new URI(url);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.TEXT_PLAIN);
            HttpEntity<String> entity = new HttpEntity<>("Hello World", headers);
            HttpEntity<String> request = new HttpEntity<>(("bar"));
            HttpEntity<?> deletehttpEntity = new HttpEntity<Object>(null,headers);
            mb.when(() -> UriComponentsBuilder.fromHttpUrl(any())).thenReturn(uriComponentsBuilder);
            Map<String, Object> map = new HashMap<>();
            map.put("abc", "abc");
            ProcessInstanceResponseDTO processInstanceResponseDTO = new ProcessInstanceResponseDTO("1", "a", map, true);
            ProcessInstance processInstance = new ProcessInstance("a", "a", map);
            ResponseEntity<Map> serviceResponse =
                    new ResponseEntity<Map>(map, HttpStatus.OK);
            when(restTemplate.exchange(
                    uriComponentsBuilder.toString(),
                    DELETE,
                    deletehttpEntity,
                    Map.class))
                    .thenReturn(serviceResponse);
            MultipartFile multipartFile = new MockMultipartFile("sourceFile.tmp", "Hello World".getBytes());
            Assertions.assertThrows(NullPointerException.class,()->processService.deleteProcessById(deleteTaskDTO));
        }
    }
}