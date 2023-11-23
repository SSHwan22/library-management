package com.rm.spring.service.book.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class BookResponseDto {
    private int bookId;
    private String title;
    private String userId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;


}
