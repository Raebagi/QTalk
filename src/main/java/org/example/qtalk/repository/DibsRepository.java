package org.example.qtalk.repository;

import org.example.qtalk.Entity.DibsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DibsRepository extends JpaRepository<DibsEntity, Long> {

}
