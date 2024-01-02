package com.rm.spring.controller.book;

import com.rm.spring.common.dto.response.ApiResponse;
import com.rm.spring.service.book.BookService;
import com.rm.spring.service.book.dto.request.BookRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping("")
    public ApiResponse registerBook(@RequestBody @Valid BookRequestDto.BookInfo requestDto){
        bookService.registerBook(requestDto);
        System.out.println("test");
        return ApiResponse.SUCCESS;
    }

    @PutMapping("/{bookId}")
    public ApiResponse updateBook(@PathVariable("bookId") int bookId
                                , @RequestBody @Valid BookRequestDto.BookInfo requestDto) {
        bookService.updateBook(bookId, requestDto);
        return ApiResponse.SUCCESS;
    }

    @PostMapping("/stock/{bookStockId}/loan")
    public ApiResponse loanBook(@PathVariable("bookStockId") int bookStockId
                              , @RequestHeader(value = "UserId") String userId){

        bookService.loanBook(bookStockId, userId);
        return ApiResponse.SUCCESS;
    }

    @PutMapping("/{bookStockId}/loan/{bookLoanId}")
    public ApiResponse returnBook(@PathVariable("bookStockId") int bookStockId
                                , @PathVariable("bookLoanId") int bookLoanId) {
        bookService.returnBook(bookStockId, bookLoanId);
        return ApiResponse.SUCCESS;
    }

    @GetMapping("")
    public ApiResponse loanHistory(@RequestParam(required = false) Integer bookId
                                   , @RequestParam(required = false) Integer bookStockId
                                   , @RequestParam(required = false) Integer bookLoanId
                                   , @RequestParam(required = false) String title
                                   , @RequestParam(required = false) String author
                                   , @RequestParam(required = false) String publisher
                                   , @RequestHeader(value = "UserId") String userId) {

        return ApiResponse.success(bookService.loanHistory(bookId, bookStockId, bookLoanId, title, author, publisher, userId));
    }

}
