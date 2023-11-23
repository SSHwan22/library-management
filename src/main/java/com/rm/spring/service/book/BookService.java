package com.rm.spring.service.book;

import com.rm.spring.common.exception.RestApiException;
import com.rm.spring.common.model.SqlErrorCode;
import com.rm.spring.domain.book.repository.BookMapper;
import com.rm.spring.service.book.dto.request.BookRequestDto;
import com.rm.spring.service.book.dto.response.BookResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.binding.BindingException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    @Transactional
    public  void registerBook(BookRequestDto.BookInfo requestDto) {
        bookMapper.registerBook(requestDto);
    }

    @Transactional
    public void updateBook(int bookId, BookRequestDto.BookInfo requestDto) {
        int result = bookMapper.updateBook(bookId, requestDto);

        if(result == 0)
            throw new RestApiException(SqlErrorCode.NOT_FOUND);
    }

    @Transactional
    public void loanBook(int bookId, String userId) {
        try {
            boolean result1 = bookMapper.bookReturnStatus(bookId);
            if(!result1)
                throw new RestApiException(SqlErrorCode.BOOK_LOAN);

            int result2 = bookMapper.loanBook(bookId, userId);
            if(result2!=0) {
                bookMapper.returnStatusUpdate(bookId, false);
            }
        } catch (DataIntegrityViolationException e) {
            throw new RestApiException(SqlErrorCode.FOREIGN_KEY_CONSTRAINT_VIOLATION);
        } catch (BindingException e) {
            throw new RestApiException(SqlErrorCode.NOT_FOUND);
        }
    }

    @Transactional
    public void returnBook(int bookId, int bookLoanId) {
        try {
            Integer result1 = bookMapper.findBookId(bookLoanId);
            if(result1!=bookId) {
                throw new RestApiException(SqlErrorCode.DIFFERENT_BOOK);
            }

            int result2 = bookMapper.returnStatusUpdate(bookId, true);
            if(result2!=0){
                bookMapper.returnBook(bookLoanId);
            }
        } catch (NullPointerException e){
            throw new RestApiException(SqlErrorCode.NOT_FOUND_LOAN);
        }
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> loanHistory(String title) {
        return bookMapper.loanHistory(title);
    }
}
