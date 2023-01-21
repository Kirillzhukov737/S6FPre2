package model;

public class SubTask extends Task {
    private Long epicId;

    public SubTask(Long id, TaskType taskType, String name, TaskStatus status, String description, Long epicId) {
        super(id, taskType, name, status, description);
        this.epicId = epicId;
    }

    public Long getEpicId() {
        return epicId;
    }

    public void setEpicId(Long epicId) {
        this.epicId = epicId;
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