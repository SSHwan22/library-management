package com.rm.spring.controller.book;

import com.rm.spring.common.dto.ApiResponse;
import com.rm.spring.service.book.BookService;
import com.rm.spring.service.book.dto.request.BookRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/book")
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ApiResponse registerBook(@RequestBody BookRequestDto.BookInfo requestDto){
        bookService.registerBook(requestDto);
        return ApiResponse.success(requestDto);
    }

    @PutMapping("/{bookId}")
    public ApiResponse updateBook(@PathVariable("bookId") int bookId
                                , @RequestBody @Valid BookRequestDto.BookInfo requestDto){
        bookService.updateBook(bookId, requestDto);
        return ApiResponse.SUCCESS;
    }

    @GetMapping("/{title}")
    public ApiResponse loanHistory(@PathVariable("title") String title) {
        return ApiResponse.success(bookService.loanHistory(title));
    }

    @PostMapping("/{bookId}/loan")
    public ApiResponse loanBook(@PathVariable("bookId") int bookId
                              , @RequestBody String userId){
        bookService.loanBook(bookId, userId);
        return ApiResponse.SUCCESS;
    }

    @PutMapping("/{bookId}/loan/{bookLoanId}")
    public ApiResponse returnBook(@PathVariable("bookId") int bookId
                                , @PathVariable("bookLoanId") int bookLoanId) {
        bookService.returnBook(bookId, bookLoanId);
        return ApiResponse.SUCCESS;
    }

}
