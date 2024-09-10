package org.example.qtalk.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Entity
@Table(name = "Dibs")
@Getter
@Setter
public class DibsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DibsId", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user; // 회원ID (외래키)

    @ManyToOne
    @JoinColumn(name = "qualificationsId", nullable = false)
    private QualificationEntity qualification; // 자격증ID (외래키)

    @Column(name = "implSeq", nullable = false)
    private Integer implSeq; // 시험회차

    @Column(name = "docRegStartDt", nullable = false)
    private Date docRegStartDt; // 필기 원서 접수 시작 일자

    @Column(name = "docRegEndDt", nullable = false)
    private Date docRegEndDt; // 필기 원서 접수 종료 일자

    @Column(name = "docExamStartDt", nullable = false)
    private Date docExamStartDt; // 필기 시험 시작 일자

    @Column(name = "docExamEndDt", nullable = false)
    private Date docExamEndDt; // 필기 시험 종료 일자

    @Column(name = "docPassDt", nullable = false)
    private Date docPassDt; // 필기 시험 합격자 발표 일자

    @Column(name = "pracRegStartDt", nullable = false)
    private Date pracRegStartDt; // 실기 시험 원서 접수 시작 일자

    @Column(name = "pracRegEndDt", nullable = false)
    private Date pracRegEndDt; // 실기 시험 원서 접수 종료 일자

    @Column(name = "pracExamStartDt", nullable = false)
    private Date pracExamStartDt; // 실기 시험 시작 일자

    @Column(name = "pracExamEndDt", nullable = false)
    private Date pracExamEndDt; // 실기 시험 종료 일자

    @Column(name = "pracPassDt", nullable = false)
    private Date pracPassDt; // 실기 시험 합격자 발표 일자

}
