package com.queless.que.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.queless.que.Entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

//    User findByEmail(String email);
//
//    boolean existsByEmail(String email);
//
//    boolean existsByPhone(String phone);
}