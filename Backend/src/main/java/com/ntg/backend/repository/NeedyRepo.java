package com.ntg.backend.repository;

import com.ntg.backend.entity.Needy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedyRepo extends JpaRepository<Needy, Long> {
}
