package com.rm.spring.controller.user;

import com.rm.spring.common.dto.response.ApiResponse;
import com.rm.spring.service.user.UserService;
import com.rm.spring.service.user.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
