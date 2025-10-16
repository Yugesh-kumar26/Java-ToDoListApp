import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

public class ToDoListApp extends JFrame {
    private TaskManager manager;
    private DefaultListModel<Task> taskModel;
    private JList<Task> taskList;
    private JTextField taskInput;
    private JTextField dateInput;

    public ToDoListApp() {
        setTitle("Advanced To-Do List");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        manager = new TaskManager();
        taskModel = new DefaultListModel<>();
        taskList = new JList<>(taskModel);
        taskList.setFont(new Font("Consolas", Font.PLAIN, 16));
        refreshTaskList();

        add(new JScrollPane(taskList), BorderLayout.CENTER);

        // Input panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 1));
        taskInput = new JTextField();
        dateInput = new JTextField("YYYY-MM-DD");
        inputPanel.add(taskInput);
        inputPanel.add(dateInput);
        add(inputPanel, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Task");
        JButton removeButton = new JButton("Remove Selected");
        JButton toggleButton = new JButton("Toggle Complete");

        addButton.addActionListener(e -> addTask());
        removeButton.addActionListener(e -> removeTask());
        toggleButton.addActionListener(e -> toggleTask());

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(toggleButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void refreshTaskList() {
        taskModel.clear();
        for (Task task : manager.getTasks()) {
            taskModel.addElement(task);
        }
    }

    private void addTask() {
        String desc = taskInput.getText().trim();
        String dateStr = dateInput.getText().trim();
        try {
            LocalDate date = LocalDate.parse(dateStr);
            if (!desc.isEmpty()) {
                manager.addTask(new Task(desc, date));
                refreshTaskList();
                taskInput.setText("");
                dateInput.setText("YYYY-MM-DD");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Invalid date format. Use YYYY-MM-DD.");
        }
    }

    private void removeTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            manager.removeTask(index);
            refreshTaskList();
        }
    }

    private void toggleTask() {
        int index = taskList.getSelectedIndex();
        if (index != -1) {
            manager.toggleTask(index);
            refreshTaskList();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListApp().setVisible(true));
    }
}