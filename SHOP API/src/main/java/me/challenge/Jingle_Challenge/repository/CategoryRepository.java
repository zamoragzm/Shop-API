package me.challenge.Jingle_Challenge.repository;

import me.challenge.Jingle_Challenge.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Page<Category> findByProductId(Long productId, Pageable pageable);
    Optional<Category> findByIdAndProductId(Long id, Long productId);

}
