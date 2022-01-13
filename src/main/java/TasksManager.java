import org.apache.commons.lang3.ArrayUtils;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class TasksManager {

    static String[][] tasks;

    public static void main(String[] args) {
        tasks = getDataFromFile("tasks.csv");
        menuOptions();
    }

    public static void menuOptions() {
        Scanner optionInput = new Scanner(System.in);
        boolean programWorking = true;
        while (programWorking) {
            System.out.println(ConsoleColors.BLUE + "Choose a option: " + ConsoleColors.RESET);
            System.out.println("add");
            System.out.println("remove");
            System.out.println("list");
            System.out.println("exit");
            String choosedOption = optionInput.nextLine();
            switch (choosedOption) {
                case "add" -> addTask();
                case "remove" -> removeTask();
                case "list" -> showTasks();
                case "exit" -> {
                    saveDataToFile("tasks.csv");
                    programWorking = false;
                }
                default -> System.out.println(ConsoleColors.BLUE + "Please select a correct option." + ConsoleColors.RESET);
            }
        }
    }

    public static String[][] getDataFromFile(String fileName) {
        Path path = Paths.get(fileName);
        File file = new File(fileName);
        StringBuilder input = new StringBuilder();
        int rows = 0;
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                input.append(line).append(",");
                rows += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String[] strArray = input.toString().split(",");
        String[][] arrayToTasks = new String[rows][3];
        int strArrayLenght = 0;
        for (int i = 0; i < arrayToTasks.length; i++) {
            for (int j = 0; j < arrayToTasks[i].length; j++) {
                arrayToTasks[i][j] = strArray[strArrayLenght];
                strArrayLenght++;
            }
            System.out.println();
        }
        return arrayToTasks;
    }


    private static void showTasks() {
        for (int i = 0; i < tasks.length; i++) {
            System.out.print(i + ":");
            for (int j = 0; j < tasks[i].length; j++) {
                System.out.print(tasks[i][j]);
            }
            System.out.println();
        }
    }

    private static void addTask() {
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Please add task description" + ConsoleColors.RESET);
        String desc = scan.nextLine() + ",";
        System.out.println(ConsoleColors.BLUE + "Please add a date to do a task" + ConsoleColors.RESET);
        String dateOfTask = scan.nextLine() + ",";
        System.out.println(ConsoleColors.BLUE + "Is task important? true/false" + ConsoleColors.RESET);
        String isImportant;
        do {
            System.out.println(ConsoleColors.BLUE + "Allowed only true/false (only small case)" + ConsoleColors.RESET);
            isImportant = scan.nextLine();
        } while (!isImportant.equals("true") && !isImportant.equals("false"));
        String[][] newArr = Arrays.copyOf(tasks, tasks.length + 1);
        newArr[newArr.length - 1] = new String[3];
        newArr[newArr.length - 1][0] = desc + " ";
        newArr[newArr.length - 1][1] = dateOfTask + " ";
        newArr[newArr.length - 1][2] = isImportant;
        tasks = newArr;

    }

    private static String[][] removeTask() {
        showTasks();
        Scanner scan = new Scanner(System.in);
        System.out.println(ConsoleColors.BLUE + "Number of index to remove?" + ConsoleColors.RESET);
        while (!scan.hasNextInt()) {
            scan.nextLine();
            System.out.println(ConsoleColors.BLUE + "Incorrect index" + ConsoleColors.RESET);
        }
        int index = scan.nextInt();
        while ((index < 0 || index > tasks.length - 1)) {
            System.out.println(ConsoleColors.BLUE + "Incorrect number of index" + ConsoleColors.RESET);
            index = scan.nextInt();
        }
        boolean decisionYesOrNot = false;
        while (!decisionYesOrNot) {
            String decision = scan.nextLine();
            switch (decision) {
                case "y" -> {
                    tasks = ArrayUtils.remove(tasks, index);
                    decisionYesOrNot = true;
                }
                case "n" -> decisionYesOrNot = true;
                default -> System.out.println(ConsoleColors.BLUE + "Only y/n allowed to continue." + ConsoleColors.RESET);
            }
            System.out.println(ConsoleColors.BLUE + "Are u sure to remove task from index " + index + "? y/n" + ConsoleColors.RESET);
        }
        return tasks;
    }

    private static void saveDataToFile(String fileName) {
        Path path = Paths.get(fileName);
        String[] tabRow = new String[tasks.length];
        for (int i = 0; i < tasks.length; i++) {
            tabRow[i] = String.join(",", tasks[i]);
        }
        try {
            Files.write(path, Arrays.asList(tabRow));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

