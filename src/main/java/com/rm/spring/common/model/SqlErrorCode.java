package com.rm.spring.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum SqlErrorCode implements ErrorCode{
    DUPLICATE_KEY(HttpStatus.BAD_REQUEST, "중복된 id입니다."),
    NOT_FOUND(HttpStatus.BAD_REQUEST, "등록되지 않은 책입니다."),
    FOREIGN_KEY_CONSTRAINT_VIOLATION(HttpStatus.BAD_REQUEST, "외래 키 제약 조건 위배입니다."),
    BOOK_LOAN(HttpStatus.BAD_REQUEST,"책이 대출된 상태입니다."),
    DIFFERENT_BOOK(HttpStatus.BAD_REQUEST, "다른 책입니다."),
    NOT_FOUND_LOAN(HttpStatus.BAD_REQUEST, "조회되지않은 대출코드입니다.")
    ;

    private final HttpStatus httpStatus;
    private final String message;
}
