import lombok.*;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Task {

    private static final AtomicInteger count = new AtomicInteger(0);
    private int taskID;
    private String taskDescription;
    private LocalDate taskDate;
    private boolean isTaskImportant;

    public Task(String taskDescription, LocalDate taskDate, boolean isTaskImportant) {
        this.taskID = count.incrementAndGet();
        this.taskDescription = taskDescription;
        this.taskDate = taskDate;
        this.isTaskImportant = isTaskImportant;
    }
}
