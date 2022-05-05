module parkingrush {
    requires javafx.controls;
    requires javafx.fxml;

    opens parkingrush to javafx.fxml;
    exports parkingrush;
}
