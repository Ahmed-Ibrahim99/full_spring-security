package com.amaghrabi.Security.service;

import com.amaghrabi.Security.model.AppError;
import com.amaghrabi.Security.repository.AppErrorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppErrorService {

    private final AppErrorRepository appErrorRepository;

    public AppError getByName(String name) {
        return appErrorRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Error code not defined in DB: " + name));
    }
}
