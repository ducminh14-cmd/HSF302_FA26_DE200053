package com.example.data.repository;

import com.example.data.pojo.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // 2.2: Lấy tất cả sinh viên sắp xếp theo ID tăng dần
    List<Student> findAllByOrderByIdAsc();

    // 2.3: Tìm sinh viên theo email (không phân biệt hoa/thường)
    Optional<Student> findByEmailIgnoreCase(String email);

    // 2.4: Kiểm tra email đã tồn tại chưa (dùng khi Create)
    boolean existsByEmailIgnoreCase(String email);

    // 2.5: Kiểm tra email đã tồn tại cho sinh viên KHÁC chưa (dùng khi Update)
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);

    // 2.6: Tìm kiếm tên HOẶC email chứa keyword, không phân biệt hoa thường, ORDER BY id
    @Query("SELECT s FROM Student s " +
            "WHERE LOWER(s.name) LIKE LOWER(CONCAT('%', :kw, '%')) " +
            "   OR LOWER(s.email) LIKE LOWER(CONCAT('%', :kw, '%')) " +
            "ORDER BY s.id")
    List<Student> search(@Param("kw") String keyword);
}
