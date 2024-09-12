package org.example.qtalk.service;

import io.netty.handler.codec.http.HttpHeaderValues;
import lombok.extern.slf4j.Slf4j;
import org.example.qtalk.dto.KakaoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class KakaoService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String clientId;
    private final String KAUTH_TOKEN_URL_HOST = "https://kauth.kakao.com";
    private final String KAUTH_USER_URL_HOST = "https://kapi.kakao.com";

    public KakaoService(@Value("${kakao.client_id}") String clientId) {
        this.clientId = clientId;
    }

    public String getAccessTokenFromKakao(String code) {
        KakaoResponseDTO kakaoResponseDTO = WebClient.create(KAUTH_TOKEN_URL_HOST)
                .post()
                .uri("/oauth/token")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .bodyValue("grant_type=authorization_code&client_id=" + clientId + "&code=" + code)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new RuntimeException("Invalid Parameter")))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new RuntimeException("Internal Server Error")))
                .bodyToMono(KakaoResponseDTO.class)
                .block();

        logger.info(" [Kakao Service] Access Token ------> {}", kakaoResponseDTO.getAccessToken());
        logger.info(" [Kakao Service] Refresh Token ------> {}", kakaoResponseDTO.getRefreshToken());
        logger.info(" [Kakao Service] Id Token ------> {}", kakaoResponseDTO.getIdToken());
        logger.info(" [Kakao Service] Scope ------> {}", kakaoResponseDTO.getScope());

        return kakaoResponseDTO.getAccessToken();
    }
}
