package com.hsf302.ch4_Ex2.service;

import com.hsf302.ch4_Ex2.dto.StudentCreditDTO;
import com.hsf302.ch4_Ex2.pojo.Course;
import com.hsf302.ch4_Ex2.pojo.Student;

import java.util.List;

public interface EnrollmentService {
    List<Course> getCoursesOfStudent(String studentCode);
    List<Student> getStudentsOfCourse(String courseCode);
    List<Student> findStudentsInCourse(String courseCode);
    long countStudentsInCourse(String courseCode);
    List<Student> findActiveStudentsInCourse(String courseCode);

    // EnrollmentService
    List<Student> findStudentsWithoutCourses();
    boolean isEnrolled(String studentCode, String courseCode);

    List<Student> findGoodStudentsInCourse(String courseCode, double minGpa);

    List<StudentCreditDTO> getCreditSummary(int minCredits);
    // EnrollmentService
    List<Student> findStudentsWithMoreThan(int n);
    // EnrollmentService
    Student getStudentWithCourses(String studentCode);

}
