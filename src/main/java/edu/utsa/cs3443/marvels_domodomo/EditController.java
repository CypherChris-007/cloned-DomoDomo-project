package edu.utsa.cs3443.marvels_domodomo;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class EditController {

    @FXML private Button EditID;
    // @FXML optionsID, PetID, ToDoID is never used
    @FXML private Button addID;
    @FXML private Button editID;
    @FXML private javafx.scene.control.SplitMenuButton removeID; // Matches FXML SplitMenuButton type
    @FXML private TextArea textArea;

    private boolean isSelectionModeActive = false; // User hover selection
    private static final String FILE_PATH = "data/tasks.txt";

    @FXML
    public void initialize() {
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists())
            {boolean created = dataFolder.mkdir();
                if(!created) System.out.println("Error: Data file was not created.");
                }
            File taskFile = new File(FILE_PATH);
            if (!taskFile.exists()) {
                boolean created = taskFile.createNewFile();
                if(!created) System.out.println("Error: Task was not created.");
            }
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        loadFromFile();

        // Mouse click listener on TextArea for selection removal mode
        textArea.setOnMouseClicked(event -> {
            if (isSelectionModeActive) {
                javafx.application.Platform.runLater(this::handleTaskClickRemoval);
            }
        });
        // Force full transparency on all internal SplitMenuButton nodes
        javafx.application.Platform.runLater(() -> {
            removeID.lookupAll(".label-container, .arrow-button, *").forEach(node -> {
                node.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
            });
        });
    }

    // ── File Read ─────────────────────────────────────────────────

    private void loadFromFile() {
        ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
        if (tasks.isEmpty()) {
            textArea.setText("Type Here . . . ");
        } else {
            StringBuilder display = new StringBuilder();
            for (Task task : tasks) {
                display.append(task.getTaskName()).append("\n");
            }
            textArea.setText(display.toString().trim());
        }
        textArea.setEditable(false);
    }

    // ── File Write ────────────────────────────────────────────────

    private void saveToFile() {
        try {
            StringBuilder sb = new StringBuilder();
            for (Task task : TaskManager.getInstance().getTasks()) {
                sb.append(task.getTaskName()).append("\n");
            }
            Files.write(Paths.get(FILE_PATH), sb.toString().getBytes());
            System.out.println("Saved to " + FILE_PATH);
        } catch (Exception e) {
            System.out.println("Save failed: " + e.getMessage());
        }
    }

    // ── Tab Navigation ────────────────────────────────────────────

    @FXML
    protected void onOptionsClick() throws Exception {
        switchScene("Options-screen.fxml");
    }

    @FXML
    protected void onToDoClick() throws Exception {
        switchScene("Main-screen.fxml");
    }

    // Edit tab — already on this screen, just refresh
    @FXML
    protected void onEditTabClick() {
        loadFromFile();
    }

    @FXML
    protected void onPetClick() throws Exception {
        switchScene("Pet-screen.fxml");
    }

    // ── Add / Edit / Remove ───────────────────────────────────────

    // ADD
    @FXML
    protected void onAddClick() {
        if (addID.getText().equals("Add")) {
            textArea.setEditable(true);
            textArea.clear();
            textArea.requestFocus();
            addID.setText("Save");
        } else {
            String taskName = textArea.getText().trim();

            if (!taskName.isEmpty() && !taskName.equals("TYPE HERE...")) {
                Task newTask = new Task(taskName, 1, false);
                TaskManager.getInstance().addTask(newTask);
            }

            loadFromFile();
            textArea.setEditable(false);
            addID.setText("Add");
            showMessage(addID, "Saved!");
        }
    }

    // EDIT
    @FXML
    protected void onEditClick2() {
        if (editID.getText().equals("Edit")) {
            textArea.setEditable(true);
            textArea.requestFocus();
            editID.setText("Save");
        } else {
            String[] lines = textArea.getText().trim().split("\n");
            ArrayList<Task> currentTasks = TaskManager.getInstance().getTasks();

            for (int i = 0; i < lines.length && i < currentTasks.size(); i++) {
                String newName = lines[i].trim();
                if (!newName.isEmpty()) {
                    Task oldTask = currentTasks.get(i);
                    Task updatedTask = new Task(newName, oldTask.getPoints(), oldTask.isTaskIsCompleted());
                    TaskManager.getInstance().editTask(oldTask, updatedTask);
                }
            }

            loadFromFile();
            textArea.setEditable(false);
            editID.setText("Edit");
            showMessage(editID, "Edited!");
        }
    }

    // REMOVE — Selection mode
    @FXML
    protected void onRemoveSelectedClick() {
        isSelectionModeActive = true;
        textArea.setStyle("-fx-cursor: hand;");
        removeID.setStyle("-fx-background-color: #e0a800; -fx-text-fill: white;");
        removeID.setText("Click Task...");
    }

    private void handleTaskClickRemoval() {
        int caretPos = textArea.getCaretPosition();
        String fullText = textArea.getText();

        if (fullText == null || fullText.isEmpty()) return;

        int lineStart = fullText.lastIndexOf('\n', caretPos - 1) + 1;
        int lineEnd   = fullText.indexOf('\n', caretPos);
        if (lineEnd == -1) lineEnd = fullText.length();

        String taskName = fullText.substring(lineStart, lineEnd).trim();

        // Always reset mode BEFORE showMessage so it restores to "Remove" correctly
        isSelectionModeActive = false;
        textArea.setStyle("-fx-cursor: default;");
        removeID.setStyle("");
        removeID.setText("Remove");

        if (!taskName.isEmpty() && !taskName.equals("Type Here . . .")) {
            TaskManager.getInstance().removeTaskByName(taskName);
            saveToFile();
            loadFromFile();
            showMessage(removeID, "Removed: " + taskName);
        } else {
            showMessage(removeID, "No task on that line!");
        }
    }

    // REMOVE — Remove all
    @FXML
    protected void onRemoveAllClick() {
        TaskManager.getInstance().getTasks().clear();
        saveToFile();
        loadFromFile();
        showMessage(removeID, "All tasks cleared!");
    }

    // ── Message Helper ────────────────────────────────────────────

    // Accepts both Button and SplitMenuButton via shared parent ButtonBase
    private void showMessage(javafx.scene.control.ButtonBase button, String message) {
        String original = button.getText();
        button.setText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> button.setText(original));
        pause.play();
    }

    // ── Scene Switch ──────────────────────────────────────────────

    private void switchScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(fxml)
        );
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) EditID.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}