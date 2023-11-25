package com.rm.spring.domain.book.repository;

import com.rm.spring.service.book.dto.request.BookRequestDto;
import com.rm.spring.service.book.dto.response.BookResponseDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookMapper {

    int registerBook(BookRequestDto.BookInfo requestDto);

    int updateBook(int bookId, BookRequestDto.BookInfo requestDto);

    int loanBook(int bookStockId, String userId);

    Integer findBookIdFromBookLoan(int bookLoanId);
    Integer findBookIdFromBook(BookRequestDto.BookInfo bookRequestDto);

    int returnBook(int bookLoanId);

    boolean bookReturnStatus(int bookId);

    int returnStatusUpdate(int bookStockId, boolean status);

    List<BookResponseDto> loanHistory(Integer bookId, Integer bookStockId, Integer bookLoanId, String title, String author, String publisher, String userId);

    void registerBookStock(int bookId);
}
