package org.example.qtalk.Controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.qtalk.Dto.KakaoUserInfoResponseDto;
import org.example.qtalk.Service.KakaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("")
public class KakaoLoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(KakaoLoginController.class);

    private final KakaoService kakaoService;

    @GetMapping("/getToken")
    public ResponseEntity<?> callback(@RequestParam("code") String code) {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        LOGGER.info("Access Token: {}", accessToken);

        KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

        // 사용자 정보를 DB에 저장하는 로직 실행 후 상태 확인
        boolean isUserSaved = kakaoService.saveUserInfo(userInfo);

        if (isUserSaved) {
            return new ResponseEntity<>(userInfo, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to save user info", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}