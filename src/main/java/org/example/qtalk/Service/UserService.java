package org.example.qtalk.Service;

import org.example.qtalk.Entity.UserEntity;
import org.example.qtalk.Dto.KakaoUserInfoResponseDto;
import org.example.qtalk.mapper.UserMapper;
import org.example.qtalk.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public boolean createUserFromKakao(KakaoUserInfoResponseDto kakaoUserInfo) {
        try {
            LOGGER.info("Saving user info to DB: {}", kakaoUserInfo);
            UserEntity userEntity = userMapper.toUserEntity(kakaoUserInfo);
            userRepository.save(userEntity);
            LOGGER.info("User info saved successfully");
            return true;
        } catch (Exception e) {
            LOGGER.error("Failed to save user info", e);
            return false;
        }

    }

}

