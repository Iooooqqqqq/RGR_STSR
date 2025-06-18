package com.RGR.myapp.service;

import com.RGR.myapp.exception.ResourceNotFoundException;
import com.RGR.myapp.model.Student;
import com.RGR.myapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Map<String, Long> countByFirstLetter() {
        List<Object[]> results = studentRepository.countStudentsByFirstLetter();
        Map<String, Long> resultMap = new TreeMap<>();
        for (Object[] row : results) {
            String letter = (String) row[0];
            Long count = (Long) row[1];
            resultMap.put(letter, count);
        }
        return resultMap;
    }



    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setName(studentDetails.getName());
        student.setEmail(studentDetails.getEmail());
        return studentRepository.save(student);
    }

    @Transactional
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        student.getCourses().clear(); // Удаляем связи перед удалением студента
        studentRepository.delete(student);
    }
}