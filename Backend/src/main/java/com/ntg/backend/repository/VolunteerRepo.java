package com.ntg.backend.repository;

import com.ntg.backend.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepo extends JpaRepository<Volunteer, Long> {
}
