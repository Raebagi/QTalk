package org.example.qtalk.mapper;

import org.example.qtalk.Entity.UserEntity;
import org.example.qtalk.Dto.KakaoUserInfoResponseDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toUserEntity(KakaoUserInfoResponseDto kakaoUserInfo) {
        UserEntity userEntity = new UserEntity();

        // Kakao ID
        userEntity.setKakaoId(kakaoUserInfo.getId());

        // Email
        if (kakaoUserInfo.getKakaoAccount().getIsEmailAgree()) {
            userEntity.setEmail(kakaoUserInfo.getKakaoAccount().getEmail());
        }

        // Profile Image
        if (kakaoUserInfo.getKakaoAccount().getProfile() != null) {
            userEntity.setProfileImageUrl(kakaoUserInfo.getKakaoAccount().getProfile().getProfileImageUrl());
            userEntity.setNickName(kakaoUserInfo.getKakaoAccount().getProfile().getNickName());
        }

        // Phone Number
        if (kakaoUserInfo.getKakaoAccount().getIsPhoneNumberAgree()) {
            userEntity.setPhoneNumber(kakaoUserInfo.getKakaoAccount().getPhoneNumber());
        }

        // CI
        if (kakaoUserInfo.getKakaoAccount().getIsCIAgree()) {
            userEntity.setCi(kakaoUserInfo.getKakaoAccount().getCi());
        }

        return userEntity;
    }
}
