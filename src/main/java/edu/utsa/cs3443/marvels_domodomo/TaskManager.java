/**
 * @author: Chris Jimenez
 *
 * @purpose: TaskManager manages the ArrayList<Task>.
 * Implemented as a singleton so all controllers share the same task list.
 * Handles add, edit, remove, load from file, and save to file.
 */
package edu.utsa.cs3443.marvels_domodomo;

import java.io.*;
import java.util.ArrayList;

public class TaskManager {

    // ── Singleton ────────────────────────────────────────────────
    private static TaskManager instance;
    // This is so we can keep track of the points for the To-Do hearts.
    private int points = 0;

    public int getPoints() { return points; }
    public void addPoints(int p) { points += p; }
    public void resetPoints() { points = 0; }

    /** Call this everywhere instead of new TaskManager() */
    public static TaskManager getInstance() {
        if (instance == null) {
            instance = new TaskManager();
            instance.loadTasks();   // auto-load from file on first access
        }
        return instance;
    }

    // Private constructor — use getInstance()
    private TaskManager() {
        this.tasks = new ArrayList<>();
    }

    // ── Data ─────────────────────────────────────────────────────
    private ArrayList<Task> tasks;

    private static final String FILE_PATH = "data/tasks.txt";

    // ── Getters & Setters ─────────────────────────────────────────
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void removeTaskByName(String taskName) {
        // We use an Iterator or a loop to safely remove items from an ArrayList
        ArrayList<Task> tasks = getTasks();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getTaskName().equals(taskName)) {
                tasks.remove(i);
                // Assuming you have a method to save changes to your .txt file
                saveTasks();
                break; // Stop once we've found and removed the match
            }
        }
    }

    public void removeTaskById(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    // ── CRUD ──────────────────────────────────────────────────────
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    /** Replaces the task at the same index — fixes the old duplicate bug */
    public void editTask(Task oldTask, Task updatedTask) {
        int index = tasks.indexOf(oldTask);
        if (index != -1) {
            tasks.set(index, updatedTask);  // set() replaces; add() duplicated
            saveTasks();
        }
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        saveTasks();
    }

    public void toggleComplete(Task task) {
        task.setTaskIsCompleted(!task.isTaskIsCompleted());
        saveTasks();
    }

    // ── Completion Stats (used by PetController) ──────────────────
    /**
     * Returns the percentage of tasks that are completed (0.0 – 100.0).
     * Returns 0 if the list is empty.
     */
    public double getCompletionPercent() {
        if (tasks.isEmpty()) return 0.0;
        long completedCount = tasks.stream()
                .filter(Task::isTaskIsCompleted)
                .count();
        return (completedCount / (double) tasks.size()) * 100.0;
    }

    // ── File I/O ──────────────────────────────────────────────────
    /**
     * Reads tasks from data/tasks.txt.
     * Each line format: taskName,points,true/false
     * Skips blank lines and malformed lines silently.
     */
    // Ask in meeting: Are we deleting data or just removing from the list. - Gio
    // We are deleting the whole list, but I plan to have an option to delet all or indidual - Chris - 4/24/2026
    public void loadTasks() {
        tasks.clear();
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                // taskName, 1-3 points, True or False
                String[] parts = line.split(",", 3);  // max 3 parts
                if (parts.length < 3) continue;       // skip malformed lines

                String name = parts[0].trim();
                int points;
                try {
                    points = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    continue;  // skip line if points aren't a number
                }
                boolean completed = Boolean.parseBoolean(parts[2].trim());

                tasks.add(new Task(name, points, completed));
            }
        } catch (IOException e) {
            System.err.println("Could not load tasks: " + e.getMessage());
        }
    }

    /**
     * Writes all tasks to data/tasks.txt.
     * Each line format: taskName,points,true/false
     */
    public void saveTasks() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs();  // create data/ dir if it doesn't exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task task : tasks) {
                writer.write(task.toString());  // uses Task's toString()
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Could not save tasks: " + e.getMessage());
        }
    }

    // ── toString ──────────────────────────────────────────────────
    @Override
    public String toString() {
        return "TaskManager{tasks=" + tasks + "}";
    }
}