package com.abcfitness.clubapi.service;

import com.abcfitness.clubapi.entity.ClassEntity;
import com.abcfitness.clubapi.repository.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    public ClassEntity createClass(ClassEntity classEntity) {
        if (classEntity.getCapacity() < 1) {
            throw new IllegalArgumentException("Capacity must be at least 1.");
        }
        if (classEntity.getEndDate().isBefore(classEntity.getStartDate()) || classEntity.getEndDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("End date must be in the future.");
        }
        return classRepository.saveClass(classEntity);
    }

    public List<ClassEntity> getAllClasses() {
        return classRepository.getAllClasses();
    }
}

