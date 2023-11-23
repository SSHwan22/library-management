package com.rm.spring.service.user;

import com.rm.spring.common.exception.RestApiException;
import com.rm.spring.common.model.SqlErrorCode;
import com.rm.spring.domain.user.repository.UserMapper;
import com.rm.spring.service.user.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public int register(UserRequestDto.Register requestDto) {
        try {
            return userMapper.register(requestDto);
        } catch (DuplicateKeyException e) {
            throw new RestApiException(SqlErrorCode.DUPLICATE_KEY);
        }
    }
}
