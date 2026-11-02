package com.example.ui.controller;

import com.example.data.exception.StudentNotFoundException;
import com.example.data.exception.ValidationException;
import com.example.data.pojo.Student;
import com.example.data.service.StudentService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

@Component
public class StudentController {

    @FXML private TableView<Student> tableView;
    @FXML private TextField searchField, idField, nameField, emailField, ageField;
    @FXML private Button btnUpdate, btnDelete;
    @FXML private Label messageLabel, statusLabel;

    private final StudentService studentService;
    private final ObservableList<Student> data = FXCollections.observableArrayList();

    // Constructor Injection Spring Bean
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ==========================================
    // TODO #5: LOAD / SEARCH / SELECT
    // ==========================================

    @FXML
    public void initialize() {
        // Gán danh sách quan sát vào TableView
        tableView.setItems(data);

        // Lắng nghe sự kiện chọn dòng trên bảng -> đổ dữ liệu vào Form
        tableView.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldSel, newSel) -> {
                    if (newSel != null) {
                        fillForm(newSel);
                    }
                });

        // Tự động disable nút Update và Delete khi chưa chọn dòng nào trên bảng
        var noSelection = tableView.getSelectionModel().selectedItemProperty().isNull();
        btnUpdate.disableProperty().bind(noSelection);
        btnDelete.disableProperty().bind(noSelection);

        // Tải dữ liệu lần đầu khi mở app
        loadData();
    }

    private void loadData() {
        try {
            data.setAll(studentService.findAll());
            statusLabel.setText("Loaded " + data.size() + " students");
        } catch (Exception e) {
            showError("Cannot load data: " + e.getMessage());
            statusLabel.setText("Database error");
        }
    }

    @FXML
    private void handleSearch() {
        try {
            data.setAll(studentService.search(searchField.getText()));
            statusLabel.setText("Found " + data.size() + " students");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    private void handleRefresh() {
        searchField.clear();
        loadData();
    }

    private void fillForm(Student s) {
        idField.setText(String.valueOf(s.getId()));
        nameField.setText(s.getName());
        emailField.setText(s.getEmail());
        ageField.setText(String.valueOf(s.getAge()));
    }

    private void showInfo(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: green;");
    }

    private void showError(String msg) {
        messageLabel.setText(msg);
        messageLabel.setStyle("-fx-text-fill: red;");
    }

    // ==========================================
    // TODO #6: ADD / UPDATE / DELETE / CLEAR
    // ==========================================

    private Student readForm() {
        String ageText = ageField.getText() == null ? "" : ageField.getText().trim();
        if (ageText.isEmpty()) {
            throw new ValidationException("Age is required");
        }
        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            throw new ValidationException("Age must be a number");
        }
        return new Student(nameField.getText(), emailField.getText(), age);
    }

    @FXML
    private void handleAdd() {
        try {
            Student saved = studentService.create(readForm());
            showInfo("Added: " + saved.getName());
            handleClear();
            loadData();
        } catch (ValidationException | StudentNotFoundException e) {
            showError(e.getMessage());
        } catch (Exception e) {
            showError("System error: " + e.getMessage());
        }
    }

    @FXML
    private void handleUpdate() {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a student");
            return;
        }
        try {
            // Không sửa trực tiếp object selected trên TableView
            Student edit = readForm();
            edit.setId(selected.getId());
            Student updated = studentService.update(edit);
            showInfo("Updated: " + updated.getName());
            handleClear();
            loadData();
        } catch (ValidationException | StudentNotFoundException e) {
            showError(e.getMessage());
        } catch (Exception e) {
            showError("System error: " + e.getMessage());
        }
    }

    @FXML
    private void handleDelete() {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Please select a student");
            return;
        }

        // Hộp thoại xác nhận xoá
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete student \"" + selected.getName() + "\"?",
                ButtonType.OK, ButtonType.CANCEL);
        confirm.setHeaderText("Confirm delete");
        confirm.initOwner(tableView.getScene().getWindow());

        confirm.showAndWait()
                .filter(rs -> rs == ButtonType.OK)
                .ifPresent(rs -> {
                    try {
                        studentService.delete(selected.getId());
                        showInfo("Deleted: " + selected.getName());
                        handleClear();
                        loadData();
                    } catch (StudentNotFoundException e) {
                        showError(e.getMessage());
                        loadData();
                    } catch (Exception e) {
                        showError("System error: " + e.getMessage());
                    }
                });
    }

    @FXML
    private void handleClear() {
        idField.clear();
        nameField.clear();
        emailField.clear();
        ageField.clear();
        tableView.getSelectionModel().clearSelection();
        nameField.requestFocus();
    }
}