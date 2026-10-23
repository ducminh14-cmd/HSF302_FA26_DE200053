package com.hsf302.student.service;

import com.hsf302.student.entity.Student;
import com.hsf302.student.repository.StudentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    /** Lấy toàn bộ danh sách sinh viên, sắp xếp theo ID tăng dần */
    public List<Student> findAll() {
        return studentRepository.findAll(Sort.by("id"));
    }

    /** Tìm kiếm sinh viên theo từ khóa (mã SV hoặc họ tên chứa từ khóa) */
    public List<Student> search(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return findAll();
        }
        String kw = keyword.trim();
        return studentRepository
                .findByStudentCodeContainingIgnoreCaseOrFullNameContainingIgnoreCaseOrderByIdAsc(kw, kw);
    }

    /** Tìm sinh viên theo ID */
    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }

    /** id == null -> INSERT, id != null -> UPDATE */
    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    /** Xóa sinh viên theo ID */
    @Transactional
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    /** Kiểm tra trùng mã SV, bỏ qua chính sinh viên đang sửa (excludeId) */
    public boolean isStudentCodeDuplicated(String code, Long excludeId) {
        if (code == null || code.isBlank()) return false;
        String c = code.trim();
        return excludeId == null
                ? studentRepository.existsByStudentCodeIgnoreCase(c)
                : studentRepository.existsByStudentCodeIgnoreCaseAndIdNot(c, excludeId);
    }

    /** Tính GPA trung bình của danh sách */
    public double averageGpa(List<Student> list) {
        return list.stream()
                .map(Student::getGpa)
                .filter(Objects::nonNull)
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }
}
