package com.ntg.backend.repository;

import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Request;
import com.ntg.backend.entity.Volunteer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> , JpaSpecificationExecutor<Item> {


    Page<Item> findByVolunteer(Volunteer volunteer, Pageable pageable);


    Page<Item> findAll(Specification<Item> spec, Pageable pageable);
}
