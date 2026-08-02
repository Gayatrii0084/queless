package com.queless.que.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.queless.que.Entity.QueueEntity;

public interface QueueRepository extends JpaRepository<QueueEntity, Integer> {


}