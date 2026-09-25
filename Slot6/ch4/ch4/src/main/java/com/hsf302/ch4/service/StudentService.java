package com.hsf302.ch4.service;

import com.hsf302.ch4.pojo.Student;

import java.util.*;

public interface StudentService {
    long count();                                   // TODO 6
    Optional<Student> findById(Long id);
    // Các method được bổ sung dần từ TODO 6
}
