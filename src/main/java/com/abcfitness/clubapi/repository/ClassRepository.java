package com.abcfitness.clubapi.repository;

import com.abcfitness.clubapi.entity.ClassEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ClassRepository {

    private final List<ClassEntity> classList = new ArrayList<>();

    public ClassEntity saveClass(ClassEntity classEntity) {
        classList.add(classEntity);
        return classEntity;
    }

    public List<ClassEntity> getAllClasses() {
        return new ArrayList<>(classList);
    }
}
