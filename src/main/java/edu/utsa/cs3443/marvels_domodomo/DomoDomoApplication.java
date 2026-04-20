package edu.utsa.cs3443.marvels_domodomo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

//public class DomoDomoApplication extends Application {
//    @Override
//    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(DomoDomoApplication.class.getResource("Edit-screen.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//    }
//}
public class DomoDomoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                DomoDomoApplication.class.getResource("Edit-screen.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 800, 600); // ← change from 320, 240
        stage.setTitle("Marvels DomoDomo");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}