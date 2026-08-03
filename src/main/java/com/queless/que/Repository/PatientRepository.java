package com.queless.que.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.queless.que.Entity.PatientEntity;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer>{

}