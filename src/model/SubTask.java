package model;

public class SubTask extends Task {
    private Long epicId;

    public SubTask(Long id, String name, String description, TaskStatus status, TaskType taskType, Long epicId) {
        super(id, name, description, status, taskType);
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