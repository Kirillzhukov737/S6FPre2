package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {

    private List<SubTask> subTaskList;
    protected Epic EpicType;

    public Epic(Long id, String name, String description, TaskStatus status, TaskType taskType) {
        super(id, name, description, status, taskType);
        subTaskList = new ArrayList<>();
    }

    public List<SubTask> getSubTaskList() {
        return subTaskList;
    }

    public void setSubTaskList(List<SubTask> subTaskList) {
        this.subTaskList = subTaskList;
    }

    @Override
    public String toString() {
        return id + ","
                + taskType + ","
                + name + ","
                + status + ','
                + description + "+"
                + EpicType;
    }
}

