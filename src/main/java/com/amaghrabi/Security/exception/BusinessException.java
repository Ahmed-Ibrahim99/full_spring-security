package com.amaghrabi.Security.exception;

import lombok.Getter;

/**
 * Thrown for any business-rule violation.
 * Pass one of the constants from {@link com.amaghrabi.Security.constants.ErrorCodes}
 * as the errorName — the actual human-readable description is resolved
 * from the error_codes table at runtime by {@link GlobalExceptionHandler}.
 *
 * Example:
 *   throw new BusinessException(ErrorCodes.USER_ALREADY_EXISTS);
 */
@Getter
public class BusinessException extends RuntimeException {

    private final String errorName;

    public BusinessException(String errorName) {
        super(errorName);
        this.errorName = errorName;
    }
}
