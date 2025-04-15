module org.example.csc311mod6assignment {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.csc311mod6assignment to javafx.fxml;
    exports org.example.csc311mod6assignment;
}