package com.rm.spring.common.handler;

import com.rm.spring.common.dto.response.ErrorResponse;
import com.rm.spring.common.exception.RestApiException;
import com.rm.spring.common.model.CommonErrorCode;
import com.rm.spring.common.model.ErrorCode;
import com.rm.spring.common.model.SqlErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<Object> handleCustomException(RestApiException e) {
        return handleExceptionInternal(e.getErrorCode());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("handleIllegalArgument", e);
        return handleExceptionInternal(CommonErrorCode.INVALID_PARAMETER, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Object> handleBindException(BindException e) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(e, CommonErrorCode.INVALID_PARAMETER);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicateKey(DuplicateKeyException e){
        log.error(e.getMessage(), e);
        return handleExceptionInternal(SqlErrorCode.DUPLICATE_KEY, SqlErrorCode.DUPLICATE_KEY.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleIntegrityViolation(DataIntegrityViolationException e) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(SqlErrorCode.FOREIGN_KEY_CONSTRAINT_VIOLATION, SqlErrorCode.FOREIGN_KEY_CONSTRAINT_VIOLATION.getMessage());
    }

    @ExceptionHandler(BindingException.class)
    public ResponseEntity<Object> handleBindingException(BindingException e) {
        log.error(e.getMessage(), e);
        return handleExceptionInternal(SqlErrorCode.NOT_FOUND, SqlErrorCode.NOT_FOUND.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAllException(Exception e) {
        log.warn("handleAllException", e);
        return handleExceptionInternal(CommonErrorCode.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode));
    }
    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, String message){
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, message));

    }
    private ResponseEntity<Object> handleExceptionInternal(BindException e, ErrorCode errorCode){
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(e, errorCode));

    }


    private Object makeErrorResponse(ErrorCode errorCode) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .build();
    }
    private Object makeErrorResponse(ErrorCode errorCode, String message) {
        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(message)
                .build();
    }
    private ErrorResponse makeErrorResponse(BindException e, ErrorCode errorCode){
        List<ErrorResponse.ValidationError> validationErrorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(ErrorResponse.ValidationError::of)
                .collect(Collectors.toList());

        return ErrorResponse.builder()
                .code(errorCode.name())
                .message(errorCode.getMessage())
                .errors(validationErrorList)
                .build();
    }


}
