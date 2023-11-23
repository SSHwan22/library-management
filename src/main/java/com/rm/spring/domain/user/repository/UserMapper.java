package com.rm.spring.domain.user.repository;

import com.rm.spring.service.user.dto.request.UserRequestDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    int register(UserRequestDto.Register requestDto);

}
