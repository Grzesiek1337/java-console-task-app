import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class TaskApp {

    private static final TaskService taskService = new TaskService();
    private static final List<Task> tasks = taskService.getTasksListFromCsvFile("tasks.csv");
    private static final TasksInputService tasksInputService = new TasksInputService();

    public void startApplication() {
        Scanner optionInput = new Scanner(System.in);
        boolean isApplicationWorking = true;
        while (isApplicationWorking) {
            System.out.println(ConsoleColors.BLUE + "Choose option: " + ConsoleColors.RESET);
            System.out.println("add");
            System.out.println("remove");
            System.out.println("list");
            System.out.println("exit");
            String choosedOption = optionInput.nextLine();
            switch (choosedOption) {
                case "add" -> addTask();
                case "remove" -> removeTask();
                case "list" -> printTasks();
                case "exit" -> {
                    taskService.saveTasksToCsvFile("tasks.csv", tasks);
                    isApplicationWorking = false;
                }
                default -> System.out.println(ConsoleColors.BLUE + "Please select a correct option." + ConsoleColors.RESET);
            }
        }
    }

    public static void addTask() {
        String taskDescription = tasksInputService.getTaskDescription();
        LocalDate taskDate = tasksInputService.getTaskDate();
        boolean isImportant = tasksInputService.getImportantStatus();
        tasks.add(new Task(taskDescription, taskDate, isImportant));
    }

    private static void removeTask() {
        int index = tasksInputService.getTaskIdToRemove();
        if (taskService.isTaskAvailableInList(index, tasks)) {
            tasks.remove(taskService.getTaskById(index, tasks));
        } else {
            System.out.println("Task with this index dont exists.");
        }
    }

    private static void printTasks() {
        tasks.forEach(System.out::println);
    }
}
