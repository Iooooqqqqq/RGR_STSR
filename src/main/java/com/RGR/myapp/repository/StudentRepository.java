package com.RGR.myapp.repository;


import com.RGR.myapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT SUBSTRING(s.name, 1, 1) AS letter, COUNT(s) FROM Student s GROUP BY SUBSTRING(s.name, 1, 1)")
    List<Object[]> countStudentsByFirstLetter();


}