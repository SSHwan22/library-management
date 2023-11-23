package com.rm.spring.domain.book;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
