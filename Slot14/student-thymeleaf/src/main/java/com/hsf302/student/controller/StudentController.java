package com.hsf302.student.controller;

import com.hsf302.student.entity.Student;
import com.hsf302.student.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * Tự động đưa danh sách ngành học vào Model của TẤT CẢ các request trong Controller này
     * Ở Thymeleaf truy cập bằng: ${majors}
     */
    @ModelAttribute("majors")
    public Map<String, String> majors() {
        Map<String, String> majors = new LinkedHashMap<>();
        majors.put("SE", "Kỹ thuật phần mềm");
        majors.put("AI", "Trí tuệ nhân tạo");
        majors.put("IA", "An toàn thông tin");
        majors.put("GD", "Thiết kế đồ họa");
        return majors;
    }

    // TODO 4: GET /students?keyword=...
    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        List<Student> students = studentService.search(keyword);
        model.addAttribute("students", students);
        model.addAttribute("keyword", keyword); // Giữ lại từ khóa trên ô tìm kiếm
        model.addAttribute("totalStudents", students.size());
        model.addAttribute("avgGpa", studentService.averageGpa(students));
        model.addAttribute("activeMenu", "list"); // Để tô sáng menu "Danh sách" trên navbar
        return "students/list";
    }

    // TODO 5: GET /students/{id}
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Optional<Student> opt = studentService.findById(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy sinh viên có id = " + id);
            return "redirect:/students";
        }
        model.addAttribute("student", opt.get());
        model.addAttribute("activeMenu", "list");
        return "students/detail";
    }

    // TODO 6: Hiển thị form Thêm mới và Cập nhật
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("activeMenu", "new"); // Tô sáng menu "Thêm mới" trên navbar
        return "students/form";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes ra) {
        Optional<Student> opt = studentService.findById(id);
        if (opt.isEmpty()) {
            ra.addFlashAttribute("errorMessage", "Không tìm thấy sinh viên có id = " + id);
            return "redirect:/students";
        }
        model.addAttribute("student", opt.get());
        model.addAttribute("activeMenu", "list");
        return "students/form";
    }

    // TODO 7: POST /students/save (Validation & Lưu dữ liệu)
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("student") Student student,
                       BindingResult result,
                       Model model,
                       RedirectAttributes ra) {

        // 1. Kiểm tra nghiệp vụ: Trùng mã sinh viên
        if (studentService.isStudentCodeDuplicated(student.getStudentCode(), student.getId())) {
            result.rejectValue("studentCode", "duplicate", "Mã sinh viên đã tồn tại");
        }

        // 2. Nếu có lỗi validation hoặc trùng mã -> Trả về form (KHÔNG redirect)
        if (result.hasErrors()) {
            model.addAttribute("activeMenu", student.getId() == null ? "new" : "list");
            return "students/form";
        }

        // 3. Hợp lệ: Thực hiện lưu và chuyển hướng (PRG Pattern)
        boolean isNew = student.getId() == null;
        studentService.save(student);
        ra.addFlashAttribute("successMessage",
                (isNew ? "Thêm" : "Cập nhật") + " sinh viên " + student.getFullName() + " thành công!");
        return "redirect:/students";
    }

    // Xóa sinh viên
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes ra) {
        studentService.deleteById(id);
        ra.addFlashAttribute("successMessage", "Đã xóa sinh viên có id = " + id);
        return "redirect:/students";
    }
}
