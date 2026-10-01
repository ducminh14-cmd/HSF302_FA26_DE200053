package com.hsf302.ch4_Ex2.service;

import com.hsf302.ch4_Ex2.dto.CourseStatDTO;
import com.hsf302.ch4_Ex2.pojo.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    long count();
    List<Course> findAllOrderByCode();
    Optional<Course> findById(Long id);
    Optional<Course> findByCode(String code);
    List<Course> findBySemester(String semester);
    long countBySemester(String semester);
    List<Course> findCoursesOfStudent(String studentCode);
    List<Course> findCoursesOfDepartment(String deptCode, boolean distinct);

    // CourseService
    List<Course> findCoursesWithoutStudents();
    List<CourseStatDTO> getStatistics();

    // CourseService
    List<Course> findFullCourses();
    // CourseService
    Course getWithStudents(String code);
}
