module com.example.idpaproject1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;




    opens com.example.idpaproject1 to javafx.fxml;
    exports com.example.idpaproject1;



}