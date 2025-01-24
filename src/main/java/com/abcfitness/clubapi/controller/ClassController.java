package com.abcfitness.clubapi.controller;

import com.abcfitness.clubapi.entity.ClassEntity;
import com.abcfitness.clubapi.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    /**
     * API to create a class.
     * Example Request Body:
     * {
     *   "name": "Pilates",
     *   "startDate": "2025-01-01",
     *   "endDate": "2025-01-20",
     *   "startTime": "14:00",
     *   "duration": 60,
     *   "capacity": 10
     * }
     */
    @PostMapping
    public ResponseEntity<ClassEntity> createClass(@RequestBody ClassEntity classEntity) {
        return ResponseEntity.ok(classService.createClass(classEntity));
    }

    /**
     * API to get all classes.
     */
    @GetMapping
    public ResponseEntity<List<ClassEntity>> getAllClasses() {
        return ResponseEntity.ok(classService.getAllClasses());
    }
}
