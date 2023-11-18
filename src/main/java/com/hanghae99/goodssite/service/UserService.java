package com.hanghae99.goodssite.service;

import com.hanghae99.goodssite.dto.user.SignUpRequestDto;
import com.hanghae99.goodssite.entity.User;
import com.hanghae99.goodssite.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class  UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void signup(SignUpRequestDto requestDto) {
        String encodedPwd = passwordEncoder.encode(requestDto.getPassword());
        User user = new User(requestDto);
        user.setPassword(encodedPwd);
        userRepository.save(user);
    }

}
