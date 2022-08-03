package com.techsophy.tsf.wrapperservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techsophy.tsf.wrapperservice.exception.ServerErrorException;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.MESSAGE;
import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

/**
 * Bean configuration for rest template
 */
@Configuration
public class BeanConfiguration
{

    /**
     * Initializing bean
     * @return RestTemplate
     */
    @Bean
    public RestTemplate getRestTemplate()
    {
        return new RestTemplateBuilder().errorHandler(new RestTemplateResponseErrorHandler()).build();
    }

    public class RestTemplateResponseErrorHandler
            implements ResponseErrorHandler
    {
        @Override
        public boolean hasError(ClientHttpResponse httpResponse)
                throws IOException
        {
            return (
                    httpResponse.getStatusCode().series() == CLIENT_ERROR
                            || httpResponse.getStatusCode().series() == SERVER_ERROR);
        }

        @Override
        public void handleError(ClientHttpResponse httpResponse)
                throws IOException
        {
            if (List.of(HttpStatus.Series.SERVER_ERROR, CLIENT_ERROR).contains(httpResponse.getStatusCode().series())) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(httpResponse.getBody()))) {
                    String httpBodyResponse = reader.lines().collect(Collectors.joining(""));
                    String errorMessage = httpBodyResponse;
                    ObjectMapper objectMapper=new ObjectMapper();
                    Map<String,String> apiErrorResponse=objectMapper.readValue(errorMessage,Map.class);
                    throw new ServerErrorException(apiErrorResponse.get(MESSAGE));
                }
            }
        }
    }
}
