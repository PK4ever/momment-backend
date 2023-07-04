package com.app.momment.children;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildrenRepository extends JpaRepository<Child, Long> {
    List<Child> findAllByUserId(long userId);
}
