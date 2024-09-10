package org.example.qtalk.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Qualifications")

public class QualificationEntity {

    @Id
    @Column(name = "qulifcationId", nullable = false, length = 10)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 2)
    private String type;

}
