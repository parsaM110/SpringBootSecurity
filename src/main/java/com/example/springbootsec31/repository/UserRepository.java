package com.example.springbootsec31.repository;

import com.example.springbootsec31.entity.Role;
import com.example.springbootsec31.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email );
   User findByRole(Role role );


}
