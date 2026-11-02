package com.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Đặt ở package gốc com.example để Spring quét được cả:
 * - com.example.data (Entity, Repository, Service)
 * - com.example.ui (Controller)
 */
@SpringBootApplication
public class StudentManagementApplication {
}
