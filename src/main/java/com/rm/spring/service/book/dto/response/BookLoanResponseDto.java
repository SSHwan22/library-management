package com.rm.spring.service.book.dto.response;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookLoanResponseDto {
    private int bookStockId;
    private int bookLoanId;
    private String userId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
}
