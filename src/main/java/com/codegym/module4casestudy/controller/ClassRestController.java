package com.codegym.module4casestudy.controller;

import com.codegym.module4casestudy.dto.ClassDto;
import com.codegym.module4casestudy.model.Class;
import com.codegym.module4casestudy.service.IClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClassRestController {

    @Autowired
    private IClassService classService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<List<Class>> getAllClasses() {
        List<Class> classes = classService.findAllActive();
        return ResponseEntity.ok(classes);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<Class> getClassById(@PathVariable Long id) {
        Optional<Class> class_ = classService.findById(id);
        return class_.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
    public ResponseEntity<List<Class>> searchClasses(@RequestParam String keyword) {
        List<Class> classes = classService.searchByKeyword(keyword);
        return ResponseEntity.ok(classes);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Class> createClass(@Validated @RequestBody ClassDto classDto) {
        if (classService.existsByClassName(classDto.getClassName())) {
            return ResponseEntity.badRequest().build();
        }
        Class savedClass = classService.save(classDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClass);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Class> updateClass(@PathVariable Long id, @Validated @RequestBody ClassDto classDto) {
        if (classService.existsByClassNameAndIdNot(classDto.getClassName(), id)) {
            return ResponseEntity.badRequest().build();
        }
        try {
            Class updatedClass = classService.update(id, classDto);
            return ResponseEntity.ok(updatedClass);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClass(@PathVariable Long id) {
        try {
            classService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
} 