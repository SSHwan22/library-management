package com.rm.spring.service.book.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class BookResponseDto {
    private int bookId;
    private String title;
    private String author;
    private String publisher;
    private List<BookLoanResponseDto> loanList;
}
