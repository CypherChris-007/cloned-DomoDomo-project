module edu.utsa.cs3443.marvels_domodomo {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.utsa.cs3443.marvels_domodomo to javafx.fxml;
    exports edu.utsa.cs3443.marvels_domodomo;
}