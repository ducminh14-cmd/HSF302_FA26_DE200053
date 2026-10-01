package com.hsf302.ch4_Ex2.repository;

import com.hsf302.ch4_Ex2.pojo.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // bổ sung dần từ TODO 7
}