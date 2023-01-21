package utility;

import managers.task.FileBackedTasksManager;
import model.*;
import managers.history.HistoryManager;
import managers.history.InMemoryHistoryManager;
import managers.task.TaskManager;

import java.util.ArrayList;
import java.util.List;

public final class UtilityManagers {

    public static Task taskFromString(String value) {
        String[] values = value.split(",");
        Long id = Long.parseLong(values[0]);
        TaskType type = TaskType.valueOf(values[1]);
        String name = values[2];
        TaskStatus status = TaskStatus.valueOf(values[3]);
        String description = values[4];
        Long epicId = Long.valueOf(values[5]);
        if (type.equals(TaskType.TASK)) {
            return new Task(id, type, name, status, description);
        }
        if (type.equals((TaskType.SUBTASK))) {
            return new SubTask(id, type, name, status, description, epicId);
        }
        if (type.equals((TaskType.EPIC))) {
            return new SubTask(id, type, name, status, description, epicId);
        }
        return null;
    }

    public static String historyToString(HistoryManager manager) {
        StringBuilder sb = new StringBuilder();
        for (Task task : manager.getHistory()) {
            sb.append(task.getId()).append(",");
        }
        return sb.toString();
    }

    public static  String tasksToString(TaskManager taskManager){
        List<Task> tasks = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        tasks.addAll(taskManager.getAllTask());
        tasks.addAll(taskManager.getAllEpic());
        tasks.addAll(taskManager.getAllSubTask());
        for(Task task: tasks){
            sb.append(task.toString()).append("\n");
        }
        return sb.toString();
    }

    public static List<Integer> historyFromString(String value) {
        List<Integer> history = new ArrayList<>();
        for (String a : value.split(",")) {
            history.add(Integer.parseInt(a));
        }
        return history;
    }
}