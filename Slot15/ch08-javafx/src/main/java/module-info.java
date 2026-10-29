module com.fu.ch08 {
    requires javafx.controls;
    requires javafx.fxml;

    // Cho phép FXMLLoader can thiệp vào các biến @FXML private của Controller
    opens com.fu.ch08.b4 to javafx.fxml;

    // Cho phép JavaFX khởi chạy DrinkOrderApp ở package b1
    exports com.fu.ch08.b1;
    exports com.fu.ch08.b2;
    exports com.fu.ch08.b3;
    exports com.fu.ch08.b4;

    // Cho phép các bài sau dùng được AlertUtil ở package common
    exports com.fu.ch08.common;
}