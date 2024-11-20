package com.ntg.backend.repository;

import com.ntg.backend.entity.Item;
import com.ntg.backend.entity.Needy;
import com.ntg.backend.entity.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RequestRepo extends JpaRepository<Request, Long> {
    Optional<Request> findByNeedyAndItem(Needy needy, Item item);

    Page<Request> findByNeedy(Needy needyUser, Pageable pageable);
    Page<Request> findByItem_ItemId(Long itemId, Pageable pageable);
    Page<Request> findByItem(Item item, Pageable pageable);

}