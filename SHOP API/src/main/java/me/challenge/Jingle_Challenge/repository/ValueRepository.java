package me.challenge.Jingle_Challenge.repository;

import me.challenge.Jingle_Challenge.entity.Store;
import me.challenge.Jingle_Challenge.entity.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ValueRepository extends JpaRepository<Value,Long> {
    Page<Value> findByAttributeId(Long attributeId, Pageable pageable);
    Optional<Value> findByIdAndAttributeId(Long id, Long attributeId);
}
