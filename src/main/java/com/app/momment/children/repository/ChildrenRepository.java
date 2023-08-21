package com.app.momment.children.repository;

import com.app.momment.children.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenRepository extends JpaRepository<Child, Long> {
    List<Child> findAllByUserId(long userId);
}
