package com.amaghrabi.Security.config;

import com.amaghrabi.Security.constants.ErrorCodes;
import com.amaghrabi.Security.exception.ErrorResponse;
import com.amaghrabi.Security.model.AppError;
import com.amaghrabi.Security.service.AppErrorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Invoked by Spring Security whenever an unauthenticated request hits a
 * protected endpoint.  Instead of the default WWW-Authenticate header /
 * HTML page, it returns a JSON body whose description is read from the
 * error_codes table using the INVALID_CREDENTIALS error name.
 */
@Component
@RequiredArgsConstructor
public class CustomBasicAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final AppErrorService appErrorService;
    private final ObjectMapper objectMapper;

    @Override
    public void commence(@NonNull HttpServletRequest request,
                         HttpServletResponse response,
                         @NonNull AuthenticationException authException) throws IOException {

        AppError error = appErrorService.getByName(ErrorCodes.INVALID_CREDENTIALS);

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(
                response.getWriter(),
                new ErrorResponse(error.getCode(), error.getName(), error.getDescription())
        );
    }
}
