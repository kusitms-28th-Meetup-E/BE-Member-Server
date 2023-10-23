package com.example.memberservice.service;

import com.example.memberservice.dto.MemberDto;
import com.example.memberservice.entity.MemberEntity;
import com.example.memberservice.repository.MemberRepository;
import com.example.memberservice.vo.ResponseOrder;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    @Override
    public MemberDto createUser(MemberDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MemberEntity memberEntity = mapper.map(userDto, MemberEntity.class);
        memberEntity.setEncryptedPwd(passwordEncoder.encode(userDto.getPwd()));
        userRepository.save(memberEntity);

        return mapper.map(memberEntity, MemberDto.class);
    }

    @Override
    public MemberDto getUserByUserId(String userId) {
        MemberEntity memberEntity = userRepository.findByUserId(userId);
        if (memberEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }

        MemberDto userDto = new ModelMapper().map(memberEntity, MemberDto.class);
        List<ResponseOrder> orders = new ArrayList<>();
        userDto.setOrders(orders);
        return userDto;
    }

    @Override
    public Iterable<MemberEntity> getUserByAll() {
        return userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberEntity memberEntity = userRepository.findByEmail(username);

        if (memberEntity == null) {
            throw new UsernameNotFoundException(username);
        }

        return new User(memberEntity.getEmail(), memberEntity.getEncryptedPwd(), true, true,
                true, true, new ArrayList<>());
    }

    @Override
    public MemberDto getUserDetailsByEmail(String email) {
        MemberEntity memberEntity = userRepository.findByEmail(email);

        if (memberEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new ModelMapper().map(memberEntity, MemberDto.class);
    }
}
