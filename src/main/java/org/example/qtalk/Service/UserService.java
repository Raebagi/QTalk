package org.example.qtalk.Service;

import org.example.qtalk.Entity.UserEntity;
import org.example.qtalk.dto.KakaoUserInfoResponseDto;
import org.example.qtalk.mapper.UserMapper;
import org.example.qtalk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserEntity createUserFromKakao(KakaoUserInfoResponseDto kakaoUserInfo) {
        UserEntity userEntity = userMapper.toUserEntity(kakaoUserInfo);
        return userRepository.save(userEntity); // 여기서 save 호출
    }
}
