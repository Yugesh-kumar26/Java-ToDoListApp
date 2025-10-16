import java.io.*;
import java.util.ArrayList;

public class TaskManager {
    private ArrayList<Task> tasks;
    private final String FILE_NAME = "tasks.dat";

    public TaskManager() {
        tasks = loadTasks();
    }

    // Return current task list
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Add a new task and save
    public void addTask(Task task) {
        tasks.add(task);
        saveTasks();
    }

    // Remove task by index
    public void removeTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
            saveTasks();
        }
    }

    // Toggle completion status
    public void toggleTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).toggleCompleted();
            saveTasks();
        }
    }

    // Save tasks to file
    private void saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    // Load tasks from file
    @SuppressWarnings("unchecked")
    private ArrayList<Task> loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            Object obj = in.readObject();
            if (obj instanceof ArrayList<?>) {
                return (ArrayList<Task>) obj;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved tasks found. Starting fresh.");
        }
        return new ArrayList<>();
    }
}