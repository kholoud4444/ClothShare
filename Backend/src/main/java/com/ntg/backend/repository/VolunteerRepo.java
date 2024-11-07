package com.ntg.backend.repository;

import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VolunteerRepo extends JpaRepository<Volunteer, Long> {



}
