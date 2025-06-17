package com.RGR.myapp.controller;


import com.RGR.myapp.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping
    public void enrollStudentToCourse(
            @RequestParam Long studentId,
            @RequestParam Long courseId
    ) {
        enrollmentService.enrollStudentToCourse(studentId, courseId);
    }
}
