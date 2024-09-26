package org.example.qtalk.repository;

import org.example.qtalk.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    // 추가적으로 카카오 ID로 사용자 찾는 메소드
    Optional<UserEntity> findByKakaoId(Long kakaoId);
}
