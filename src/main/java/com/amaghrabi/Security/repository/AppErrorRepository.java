package com.amaghrabi.Security.repository;

import com.amaghrabi.Security.model.AppError;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppErrorRepository extends JpaRepository<AppError, Long> {

    Optional<AppError> findByName(String name);
}
