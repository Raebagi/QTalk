package org.example.qtalk.Service;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.*;
import org.example.qtalk.Dto.KakaoResponseDto;
import org.example.qtalk.Dto.KakaoUserInfoResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@RequiredArgsConstructor
@Service
public class KakaoService {
    private static final Logger LOGGER = LoggerFactory.getLogger(KakaoService.class);

    private final String clientId;
    private final String KAUTH_TOKEN_URL_HOST;
    private final String KAUTH_USER_URL_HOST;
    private final UserService userService; // UserService를 주입받습니다.

    @Autowired
    public KakaoService(@Value("${kakao.client_id}") String clientId, UserService userService) {
        this.clientId = clientId;
        this.userService = userService; // UserService 주입
        KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
        KAUTH_USER_URL_HOST = "https://kapi.kakao.com";
    }

    // Access Token을 Kakao로부터 받아오는 메서드
    public String getAccessTokenFromKakao(String code) {
        KakaoResponseDto kakaoResponseDto = WebClient.create(KAUTH_TOKEN_URL_HOST).post()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/oauth/token")
                        .queryParam("grant_type", "authorization_code")
                        .queryParam("client_id", clientId)
                        .queryParam("code", code)
                        .build(true))
                .header(HttpHeaders.CONTENT_TYPE, HttpHeaderValues.APPLICATION_X_WWW_FORM_URLENCODED.toString())
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoResponseDto.class)
                .block();

        LOGGER.info(" [Kakao Service] Access Token ------> {}", kakaoResponseDto.getAccessToken());
        LOGGER.info(" [Kakao Service] Refresh Token ------> {}", kakaoResponseDto.getRefreshToken());

        return kakaoResponseDto.getAccessToken();
    }

    // 사용자 정보를 Kakao API로부터 받아오는 메서드
    public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
        KakaoUserInfoResponseDto userInfo = WebClient.create(KAUTH_USER_URL_HOST)
                .get()
                .uri(uriBuilder -> uriBuilder
                        .scheme("https")
                        .path("/v2/user/me")
                        .build(true))
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(KakaoUserInfoResponseDto.class)
                .block();

        LOGGER.info("Kakao User Info: {}", userInfo);

        return userInfo;
    }

    // 사용자 정보 저장 메서드 분리
    public boolean saveUserInfo(KakaoUserInfoResponseDto userInfo) {
        return userService.createUserFromKakao(userInfo);
    }
}
