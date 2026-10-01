package com.hsf302.ch4_Ex2.service;

import com.hsf302.ch4_Ex2.repository.CourseRepository;
import com.hsf302.ch4_Ex2.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EnrollmentServiceImpl implements EnrollmentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    // cài đặt dần từ TODO 7
}