package com.amaghrabi.Security.config;

import com.amaghrabi.Security.constants.ErrorCodes;
import com.amaghrabi.Security.exception.ErrorResponse;
import com.amaghrabi.Security.model.AppError;
import com.amaghrabi.Security.service.AppErrorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Invoked by Spring Security when an authenticated user tries to access
 * a resource their role does not permit (HTTP 403).
 * Returns a JSON body whose description is read from the error_codes
 * table using the ACCESS_DENIED error name.
 */
@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final AppErrorService appErrorService;
    private final ObjectMapper    objectMapper;

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {

        AppError error = appErrorService.getByName(ErrorCodes.ACCESS_DENIED);

        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(
                response.getWriter(),
                new ErrorResponse(error.getCode(), error.getName(), error.getDescription())
        );
    }
}
