package com.ntg.backend.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ntg.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);
    Page<User> findByRole(String role, Pageable pageable);

}
