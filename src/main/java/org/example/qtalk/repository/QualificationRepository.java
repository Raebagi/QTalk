package org.example.qtalk.repository;

import org.example.qtalk.Entity.QualificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QualificationRepository extends JpaRepository<QualificationEntity, Long> {
}
