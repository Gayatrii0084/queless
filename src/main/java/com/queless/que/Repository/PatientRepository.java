package com.queless.que.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.queless.que.Entity.PatientEntity;
import com.queless.que.Entity.PatientStatus;

public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {

    // Find maximum token number in a queue
    @Query("SELECT MAX(p.tokenNumber) FROM PatientEntity p WHERE p.queue.id = :queueId")
    Integer findMaxTokenNumberByQueueId(@Param("queueId") Integer queueId);

    // Find first waiting patient
    Optional<PatientEntity> findFirstByQueueIdAndStatusOrderByTokenNumberAsc(
            Integer queueId,
            PatientStatus status);

	Optional<PatientEntity> findByQueueIdAndStatus(Integer queueId, PatientStatus inConsultation);

}