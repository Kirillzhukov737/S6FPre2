package managers.task;

import model.Epic;
import model.SubTask;
import model.Task;
import model.TaskStatus;

import java.util.List;

public interface TaskManager {

    List<Epic> getAllEpic();

    void clearEpic();

    Epic getEpicById(Long id);

    Epic addEpic(Epic epic);

    void updateEpic(Epic epic);

    Epic removeEpicById(Long id);

    List<SubTask> getAllSubTask();

    void clearSubtask();

    SubTask getSubTaskById(Long id);

    SubTask addSubTask(SubTask subTask);

    SubTask removeSubTaskById(Long id);

    SubTask updateSubTask(SubTask subTask);

    List<Task> getAllTask();

    void clearTask();

    Task getTaskById(Long id);

    void updateStatusEpic(Epic epic, TaskStatus taskStatus);

    Task addTask(Task task);

    Task removeTaskById(Long id);

    void updateTask(Task task);

    List<SubTask> getAllSubtaskByEpic(Epic epic);
}