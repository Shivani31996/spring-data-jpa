package com.example.demo;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long>{
    @Query("SELECT s from Student s where s.email = ?1")
    Optional<Student> findStudentsByEmail(String email);

    @Query("select s from Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqual
            (String firstName,
             Integer age);

    @Query(value = "select * from Student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true)
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThanEqualNative
            (@Param("firstName") String firstName,
             @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(Long id);
}
