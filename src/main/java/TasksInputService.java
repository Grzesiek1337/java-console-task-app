import java.time.LocalDate;
import java.util.Scanner;

public class TasksInputService {

    private final Scanner scanner = new Scanner(System.in);

    public String getTaskDescription() {
        System.out.println(ConsoleColors.BLUE + "Please add task description" + ConsoleColors.RESET);
        return scanner.nextLine();
    }

    public LocalDate getTaskDate() {
        System.out.println(ConsoleColors.BLUE + "Please add a date to do a task" + ConsoleColors.RESET);
        String taskDate = scanner.nextLine();
        while (!InputRegexChecker.checkLocalDateRegex(taskDate)) {
            System.out.println(ConsoleColors.BLUE + "Incorrect date format, yyyy-mm-dd allowed." + ConsoleColors.RESET);
            taskDate = scanner.nextLine();
        }
        return LocalDate.parse(taskDate);
    }

    public boolean getImportantStatus() {
        System.out.println(ConsoleColors.BLUE + "Is task important? true/false" + ConsoleColors.RESET);
        String isImportant = scanner.nextLine();
        while (!isImportant.equals("true") && !isImportant.equals("false")) {
            System.out.println(ConsoleColors.BLUE + "Allowed only true/false and small case." + ConsoleColors.RESET);
            isImportant = scanner.nextLine();
        }
        return Boolean.parseBoolean(isImportant);
    }

    public int getTaskIdToRemove() {
        System.out.println(ConsoleColors.BLUE + "Task index to remove?" + ConsoleColors.RESET);
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.println(ConsoleColors.BLUE + "Incorrect index" + ConsoleColors.RESET);
        }
        return  scanner.nextInt();

    }
}
