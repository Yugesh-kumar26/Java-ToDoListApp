import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Serializable {
    private String description;
    private LocalDate dueDate;
    private boolean completed;

    public Task(String description, LocalDate dueDate) {
        this.description = description;
        this.dueDate = dueDate;
        this.completed = false;
    }

    public String getDescription() { return description; }
    public LocalDate getDueDate() { return dueDate; }
    public boolean isCompleted() { return completed; }

    public void toggleCompleted() { this.completed = !this.completed; }

    @Override
    public String toString() {
        return (completed ? "[✔] " : "[ ] ") + description + " (Due: " + dueDate + ")";
    }
}