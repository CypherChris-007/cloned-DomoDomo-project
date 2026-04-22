package edu.utsa.cs3443.marvels_domodomo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.transform.Scale;
import javafx.scene.layout.Pane;
import java.io.IOException;

public class DomoDomoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(
                DomoDomoApplication.class.getResource("Main-screen.fxml")
        );
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("DomoDomo!");
        stage.setScene(scene);
        stage.setMaximized(true);   // ← fills any laptop screen (hopefully)
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}