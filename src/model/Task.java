package model;

public class Task {
    protected Long id;
    protected String name;
    protected String description;
    protected TaskStatus status;
    protected TaskType taskType;

    public Task(Long id, TaskType taskType, String name, TaskStatus status, String description) {
        this.id = id;
        this.taskType = taskType;
        this.name = name;
        this.status = status;
        this.description = description;
    }

    //Task(id, type, name, status, description);
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + ","
                + taskType + ","
                + name + ","
                + status + ','
                + description;
    }
}