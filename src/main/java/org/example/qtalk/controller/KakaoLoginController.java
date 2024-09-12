/*package org.example.qtalk.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.qtalk.service.KakaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class KakaoLoginController {

    private final KakaoService kakaoService;

    @GetMapping("/signin")
    public ResponseEntity<?> getAccessToken(@RequestParam(value = "code", required = false) String code) {
        if (code == null) {
            return new ResponseEntity<>("Code parameter is missing", HttpStatus.BAD_REQUEST);
        }
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        return new ResponseEntity<>(accessToken, HttpStatus.OK);
    }
}*/


