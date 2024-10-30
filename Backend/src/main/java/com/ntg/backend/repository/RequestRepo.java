package com.ntg.backend.repository;

import com.ntg.backend.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepo extends JpaRepository<Request, Long> {
}
