package edu.utsa.cs3443.marvels_domodomo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class DomoDomoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DomoDomoApplication.class.getResource("Main-screen.fxml"));

        Scene scene = SceneUtils.createScaledScene(fxmlLoader.load(), stage);
        stage.setTitle("DomoDomo!");
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setMaximized(false);
        stage.show();
    }

}