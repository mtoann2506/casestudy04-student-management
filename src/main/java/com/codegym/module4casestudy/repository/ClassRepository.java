package com.codegym.module4casestudy.repository;

import com.codegym.module4casestudy.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClassRepository extends JpaRepository<Class, Long> {
    
    Optional<Class> findByClassName(String className);
    
    List<Class> findByActive(boolean active);
    
    @Query("SELECT c FROM Class c WHERE c.className LIKE %:keyword% OR c.description LIKE %:keyword%")
    List<Class> searchByKeyword(@Param("keyword") String keyword);
    
    boolean existsByClassName(String className);
    
    boolean existsByClassNameAndIdNot(String className, Long id);
} 