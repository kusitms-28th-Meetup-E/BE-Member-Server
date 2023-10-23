package com.example.memberservice.service;

import com.example.memberservice.dto.MemberDto;
import com.example.memberservice.entity.MemberEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberService extends UserDetailsService {
    MemberDto createUser(MemberDto userDto);

    MemberDto getUserByUserId(String userId);

    Iterable<MemberEntity> getUserByAll();

    MemberDto getUserDetailsByEmail(String userName);
}
