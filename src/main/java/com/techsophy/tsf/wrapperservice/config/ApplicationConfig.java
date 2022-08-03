package com.techsophy.tsf.wrapperservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import java.awt.image.BufferedImage;
import java.util.List;
import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.*;

@Configuration
public class ApplicationConfig
{
    @Value(GATEWAY_URL)
    String gatewayUrl;

    @Bean
    public HttpMessageConverter<BufferedImage> httpMessageConverter()
    {
        return new BufferedImageHttpMessageConverter();
    }

    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title(UX_CONTROLLER).version(VERSION_V1).description(UX_CONTROLLER_MODELER_API_VERSION_V1))
                .servers( List.of(new Server().url(gatewayUrl)));
    }
}
