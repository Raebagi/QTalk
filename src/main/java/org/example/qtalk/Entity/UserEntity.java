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
    @Column(name = "userId", nullable = false)
    private Integer id; // 회원ID

    @Column(name = "password", nullable = false, length = 30)
    private String password; // 비밀번호

    @Column(name = "name", nullable = false, length = 20)
    private String name; // 이름

    @Column(name = "phone", nullable = false, length = 15)
    private String phone; // 휴대폰번호

}
