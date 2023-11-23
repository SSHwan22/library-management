package com.rm.spring.service.user.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;

public class UserRequestDto {
    @Getter
    public static class Register {
        @NotEmpty(message = "ID는 필수값입니다.")
        private String id;

        @NotEmpty(message = "비밀번호는 필수값입니다.")
        private String password;

        @NotEmpty(message = "이름은 필수값입니다.")
        private String name;
    }

}
