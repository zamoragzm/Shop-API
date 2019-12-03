package me.challenge.Jingle_Challenge.repository;


import me.challenge.Jingle_Challenge.entity.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {
    Page<Store> findByProductId(Long productId, Pageable pageable);
    Optional<Store> findByIdAndProductId(Long id, Long productId);
}
