package com.rm.spring.common.dto.response;

import lombok.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

    public static final ApiResponse<String> SUCCESS = success("ok");

    private Boolean status;
    private String message;

    private T data;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, "", data);
    }
    public static ApiResponse<Object> error(ErrorResponse error) {
        return new ApiResponse<>(false, null, null);
    }

    public static <T> ApiResponse<T> error(ErrorResponse error, String message) {
        return new ApiResponse<>(false, message, null);
    }

}
