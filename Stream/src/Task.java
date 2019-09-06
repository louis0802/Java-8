import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


public class Task {
    private final String title;
    private final TaskType type;
    private final LocalDate createdOn;
    private boolean done = false;
    private Set<String> tags = new HashSet<>();
    private LocalDate dueOn;
    Task(String title, TaskType taskType, LocalDate createdOn){

        this.title = title;
        this.type = taskType;
        this.createdOn = createdOn;
    }
    public Task addTag(String tag){
        this.tags.add(tag);
        return this;
    }

    public TaskType getType() {
        return type;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public String getTitle() {
        return title;
    }

    public Set<String> getTags() {
        return tags;
    }
}
