package com.rm.spring.service.book.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class BookRequestDto {

    @Getter
    public static class BookInfo {
        private int bookId;
        @NotEmpty(message = "제목 값이 필요합니다.")
        private String title;
        @NotEmpty(message = "저자 값이 필요합니다.")
        private String author;
        @NotEmpty(message = "출판사 값이 필요합니다.")
        private String publisher;
    }
}
