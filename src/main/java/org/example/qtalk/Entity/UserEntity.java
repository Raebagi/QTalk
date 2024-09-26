package org.example.qtalk.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id; // 기본 키 (자동 생성)

    @Column(name = "kakao_id", nullable = false, unique = true)
    private Long kakaoId; // 카카오 회원 ID

    @Column(name = "email", nullable = true, length = 50)
    private String email; // 카카오 계정 이메일

    @Column(name = "profile_image_url", nullable = true, length = 255)
    private String profileImageUrl; // 프로필 이미지 URL

    @Column(name = "nickname", nullable = true, length = 30)
    private String nickName; // 닉네임

    @Column(name = "phone_number", nullable = true, length = 20)
    private String phoneNumber; // 휴대폰번호

    @Column(name = "ci", nullable = true, length = 255)
    private String ci; // CI (연계 정보)

}
