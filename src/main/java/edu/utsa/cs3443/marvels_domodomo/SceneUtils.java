package edu.utsa.cs3443.marvels_domodomo;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneUtils {

    public static final double BASE_WIDTH = 1280.0;
    public static final double BASE_HEIGHT = 720.0;

    private SceneUtils() {}

    public static Scene createScaledScene(Parent fxmlRoot, Stage stage) {
        Group group = new Group(fxmlRoot);
        Scene scene = new Scene(group, BASE_WIDTH, BASE_HEIGHT);

        scene.widthProperty().addListener((obs, oldVal, newVal) -> rescale(group, scene));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> rescale(group, scene));

        // Apply scale immediately based on the stage's CURRENT size, since
        // if the window is already maximized when switching tabs, no future
        // resize event will fire to trigger the listeners above.
        javafx.application.Platform.runLater(() -> rescale(group, scene));

        return scene;
    }

    private static void rescale(Group group, Scene scene) {
        double scaleX = scene.getWidth() / BASE_WIDTH;
        double scaleY = scene.getHeight() / BASE_HEIGHT;
        double scale = Math.max(scaleX, scaleY);

        // JavaFX scaleX/scaleY pivot around the node's LOCAL BOUNDS CENTER
        // by default, not (0,0). That's why content was shifting off-screen
        // to the top-left as scale grew. We compensate by translating the
        // node back so the visual top-left corner stays pinned at (0,0).
        double centerX = BASE_WIDTH / 2.0;
        double centerY = BASE_HEIGHT / 2.0;

        group.setScaleX(scale);
        group.setScaleY(scale);
        group.setTranslateX((scale - 1) * centerX);
        group.setTranslateY((scale - 1) * centerY);

    }
}
