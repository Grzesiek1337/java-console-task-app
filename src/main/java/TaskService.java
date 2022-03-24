import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskService {

    public boolean isTaskAvailableInList(int index, List<Task> tasks) {
        for (Task task : tasks) {
            if (task.getTaskID() == index) {
                return true;
            }
        }
        return false;
    }

    public Task getTaskById(int index, List<Task> tasks) {
        return tasks.stream()
                .filter(t -> t.getTaskID() == index)
                .findAny().get();
    }

    public List<Task> getTasksListFromCsvFile(String fileName) {
        Path path = Paths.get(fileName);
        File file = new File(fileName);
        List<Task> tasks = new ArrayList<>();
        try (Scanner tasksFile = new Scanner(file)) {
            while (tasksFile.hasNextLine()) {
                String line = tasksFile.nextLine();
                String[] strArray = line.split(",");
                Task task = new Task(strArray[0], LocalDate.parse(strArray[1]), Boolean.parseBoolean(strArray[2]));
                tasks.add(task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void saveTasksToCsvFile(String fileName, List<Task> tasks) {
        Path path = Paths.get(fileName);
        String[] taskSimpleTable = new String[tasks.size()];
        int index = 0;
        for (Task task : tasks) {
            taskSimpleTable[index] = task.getTaskDescription() + "," + task.getTaskDate() + "," + task.isTaskImportant();
            index += 1;
        }
        try {
            Files.write(path, Arrays.asList(taskSimpleTable));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
