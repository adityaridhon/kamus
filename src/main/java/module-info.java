module com.example.sumpah {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.sumpah to javafx.fxml;
    exports com.example.sumpah;
}