package com.app.momment.highlight.repository;

import com.app.momment.highlight.model.Highlight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HighlightRepository extends JpaRepository<Highlight, Long> {
    List<Highlight> findAllByChildId(long childId);
}
