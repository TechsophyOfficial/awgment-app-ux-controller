package com.techsophy.tsf.wrapperservice.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static com.techsophy.tsf.wrapperservice.constants.ApplicationConstants.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LocaleConfigTest {

    @Mock
    HttpServletRequest request;
    @InjectMocks
    LocaleConfig localeConfig;

    @Test
    void resolveLocaleTest() {
        String actualOutput = localeConfig.resolveLocale(request).getLanguage();
        String expectedOutput = "en";
        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void resolveLocaleEmptyHeaderTest() {
        Locale actualOutput = localeConfig.resolveLocale(request);
        Locale expectedOutput = Locale.US;

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void messageSourceTest() {
        ReloadableResourceBundleMessageSource messageSource = Mockito.mock(ReloadableResourceBundleMessageSource.class);

        messageSource.setBasenames(BASENAME_ERROR_MESSAGES, BASENAME_MESSAGES);
        messageSource.setCacheMillis(CACHEMILLIS);
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        messageSource.setUseCodeAsDefaultMessage(USEDEFAULTCODEMESSAGE);

        localeConfig.messageSource();
        verify(messageSource, times(1)).setBasenames(anyString(), anyString());
    }
}