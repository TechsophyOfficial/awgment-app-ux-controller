package com.techsophy.tsf.wrapperservice.config;

import com.techsophy.tsf.wrapperservice.exception.InvalidInputException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import static com.techsophy.tsf.wrapperservice.constants.ErrorConstants.REQUEST_NOT_NULL;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * retreve token from request
 */
@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenConfig
{
    private static GlobalMessageSource globalMessageSource;

    public static String getBearerToken()
    {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes==null)
        {
            throw new InvalidInputException(REQUEST_NOT_NULL,globalMessageSource.get(REQUEST_NOT_NULL));
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest().getHeader(AUTHORIZATION);
        }
}
