package com.rm.spring.service.user;

import com.rm.spring.common.exception.RestApiException;
import com.rm.spring.common.model.SqlErrorCode;
import com.rm.spring.domain.user.repository.UserMapper;
import com.rm.spring.service.user.dto.request.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    @Transactional
    public int register(UserRequestDto.Register requestDto) {
            return userMapper.register(requestDto);
    }
}
