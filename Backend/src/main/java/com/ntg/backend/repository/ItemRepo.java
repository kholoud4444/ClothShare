package com.ntg.backend.repository;

import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {



}
