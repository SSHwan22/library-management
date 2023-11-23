package com.rm.spring.controller.user;

import com.rm.spring.common.dto.ApiResponse;
import com.rm.spring.common.exception.RestApiException;
import com.rm.spring.common.model.UserErrorCode;
import com.rm.spring.service.user.UserService;
import com.rm.spring.service.user.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.ws.Response;

//@EnableStub
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("")
    public ApiResponse register(@RequestBody @Valid UserRequestDto.Register requestDto){
        userService.register(requestDto);
        return ApiResponse.SUCCESS;
    }
}
