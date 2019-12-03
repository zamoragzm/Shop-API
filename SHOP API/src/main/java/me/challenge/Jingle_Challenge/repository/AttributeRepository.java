package me.challenge.Jingle_Challenge.repository;

import me.challenge.Jingle_Challenge.entity.Attribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute,Long> {
    Page<Attribute> findByProductId(Long productId, Pageable pageable);
    Optional<Attribute> findByIdAndProductId(Long id, Long productId);
}
