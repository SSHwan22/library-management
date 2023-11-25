package com.rm.spring.service.book;

import com.rm.spring.common.exception.RestApiException;
import com.rm.spring.common.model.SqlErrorCode;
import com.rm.spring.domain.book.repository.BookMapper;
import com.rm.spring.service.book.dto.request.BookRequestDto;
import com.rm.spring.service.book.dto.response.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    @Transactional
    public  void registerBook(BookRequestDto.BookInfo requestDto) {
        Integer bookId = bookMapper.findBookIdFromBook(requestDto);

        if(bookId == null){
            bookMapper.registerBook(requestDto);  //등록된 책이 없다면 책 등록 및 재고 등록
            bookMapper.registerBookStock(requestDto.getBookId());
        }else{
            bookMapper.registerBookStock(bookId); //등록된 책이 있다면 재고 등록
        }
    }

    @Transactional
    public void updateBook(int bookId, BookRequestDto.BookInfo requestDto) {
        int result = bookMapper.updateBook(bookId, requestDto);

        if(result == 0)
            throw new RestApiException(SqlErrorCode.NOT_FOUND);
    }

    @Transactional
    public void loanBook(int bookStockId, String userId) {
        boolean result1 = bookMapper.bookReturnStatus(bookStockId);
        if(!result1)
            throw new RestApiException(SqlErrorCode.BOOK_LOAN);

        int result2 = bookMapper.loanBook(bookStockId, userId);
        if(result2!=0) {
            bookMapper.returnStatusUpdate(bookStockId, false);
        }
    }

    @Transactional
    public void returnBook(int bookStockId, int bookLoanId) {

            Integer result1 = bookMapper.findBookIdFromBookLoan(bookLoanId);

            if(result1 == null){
                throw new RestApiException(SqlErrorCode.NOT_FOUND_LOAN);
            }
            if(result1!=bookStockId) {
                throw new RestApiException(SqlErrorCode.DIFFERENT_BOOK);
            }

            int result2 = bookMapper.returnStatusUpdate(bookStockId, true);
            if(result2!=0){
                bookMapper.returnBook(bookLoanId);
            }
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> loanHistory(Integer bookId
                                            , Integer bookStockId
                                            , Integer bookLoanId
                                            , String title
                                            , String author
                                            , String publisher
                                            , String userId) {
        List<BookResponseDto> list = bookMapper.loanHistory(bookId, bookStockId, bookLoanId, title, author, publisher, userId);
        return list;
    }
}
