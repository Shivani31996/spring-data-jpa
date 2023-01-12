package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long>{
    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findStudentsByEmail(String email);

    @Query("select s from Student s WHERE s.firstName = ?1 AND s.age > ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual
            (String firstName,
             Integer age);
}
