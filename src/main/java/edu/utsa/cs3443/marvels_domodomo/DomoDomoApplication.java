package edu.utsa.cs3443.marvels_domodomo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.transform.Scale;
import java.io.IOException;

public class DomoDomoApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DomoDomoApplication.class.getResource("Main-screen.fxml"));

        // Load the FXML into a Pane variable so we can reference it for binding and scaling
        Pane root = fxmlLoader.load();
        Scene scene = new Scene(root, 1280, 720);

        // Create a Scale transform starting at 1:1 (no scaling), anchored at the top-left corner (0,0)
        Scale scale = new Scale(1, 1, 0, 0);

        // Apply the scale transform to the root pane so everything inside it gets scaled together
        root.getTransforms().add(scale);

        // Whenever the window width changes, recalculate the horizontal scale
        // by dividing the new width by the original width (1280)
        scene.widthProperty().addListener((obs, oldVal, newVal) -> {
            scale.setX(newVal.doubleValue() / 1280.0);
        });

        // Whenever the window height changes, recalculate the vertical scale
        // by dividing the new height by the original height (720)
        scene.heightProperty().addListener((obs, oldVal, newVal) -> {
            scale.setY(newVal.doubleValue() / 720.0);
        });

        // Bind the root pane's preferred size to the scene size so it
        // always fills the window instead of leaving white space
        root.prefWidthProperty().bind(scene.widthProperty());
        root.prefHeightProperty().bind(scene.heightProperty());

        stage.setTitle("DomoDomo!");
        stage.setScene(scene);
        stage.show();
    }
}
