package managers.task;

import managers.Managers;
import model.*;
import utility.UtilityManagers;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileBackedTasksManager extends InMemoryTaskManager implements TaskManager {

    private static final Path FILE_PATH = Path.of("src/resources/result.csv");

    @Override
    public List<Epic> getAllEpic() {
        List<Epic> epics = super.getAllEpic();
        save();
        return epics;
    }

    @Override
    public void clearEpic() {
        super.clearEpic();
        save();
    }

    @Override
    public Epic getEpicById(Long id) {
        Epic epic = super.getEpicById(id);
        save();
        return epic;
    }

    @Override
    public Epic addEpic(Epic epic) {
        Epic newEpic = super.addEpic(epic);
        save();
        return newEpic;
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public Epic removeEpicById(Long id) {
        Epic epic = super.removeEpicById(id);
        save();
        return epic;
    }

    @Override
    public List<SubTask> getAllSubTask() {
        List<SubTask> subtasks = super.getAllSubTask();
        save();
        return subtasks;
    }

    @Override
    public void clearSubtask() {
        super.clearSubtask();
        save();
    }

    @Override
    public SubTask getSubTaskById(Long id) {
        SubTask newGetSubTaskById = super.getSubTaskById(id);
        save();
        return newGetSubTaskById;
    }

    @Override
    public SubTask addSubTask(SubTask subTask) {
        SubTask newAddSubTask = super.addSubTask(subTask);
        save();
        return newAddSubTask;
    }

    @Override
    public SubTask removeSubTaskById(Long id) {
        SubTask newRemoveSubTaskById = super.removeSubTaskById(id);
        save();
        return newRemoveSubTaskById;
    }

    @Override
    public SubTask updateSubTask(SubTask subTask) {
        SubTask newUpdateSubTask = super.updateSubTask(subTask);
        save();
        return newUpdateSubTask;
    }

    @Override
    public List<Task> getAllTask() {
        List<Task> newGetAllTask = super.getAllTask();
        save();
        return newGetAllTask;
    }

    @Override
    public void clearTask() {
        super.clearTask();
        save();
    }

    @Override
    public Task getTaskById(Long id) {
        Task newGetTaskById = super.getTaskById(id);
        save();
        return newGetTaskById;
    }

    @Override
    public void updateStatusEpic(Epic epic, TaskStatus taskStatus) {
        super.updateStatusEpic(epic, taskStatus);
        save();
    }

    @Override
    public Task addTask(Task task) {
        Task newAddTask = super.addTask(task);
        save();
        return newAddTask;
    }

    @Override
    public Task removeTaskById(Long id) {
        Task newRemoveTaskById = super.removeTaskById(id);
        save();
        return newRemoveTaskById;
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public List<SubTask> getAllSubtaskByEpic(Epic epic) {
        List<SubTask> newGetAllSubtaskByEpic = super.getAllSubtaskByEpic(epic);
        save();
        return newGetAllSubtaskByEpic;
    }

    public void save() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("result.csv"));
             BufferedReader br = new BufferedReader(new FileReader("result.csv"))) {
            if (br.readLine() == null) {
                String header = "id,type,name,status,description,epic";
                bw.write(header);
            }
            String results = UtilityManagers.tasksToString(this) + "\n" + UtilityManagers.historyToString(UtilityManagers.getHistoryManager());
            bw.write(results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static FileBackedTasksManager loadFromFile(File file) {

        FileBackedTasksManager fileBackendTasksManager = Managers.getFileBackendManager();
        long initID = 0;
        try {
            String fileName = Files.readString(FILE_PATH);
            String[] lines = fileName.split("\n");

            for (int i = 1; i < lines.length - 2; i++) {
                Task task = UtilityManagers.taskFromString(lines[i]);
                String type = lines[i].split(",")[1];

                if (task.getId() > initID) {
                    initID = task.getId();
                }

                if (TaskType.valueOf(type).equals(TaskType.TASK)) {
                    fileBackendTasksManager.addTask(task);
                    historyManager.addHistory(fileBackendTasksManager.getTaskById(task.getId()));
                }

                if (TaskType.valueOf(type).equals(TaskType.EPIC)) {

                    Epic epic = (Epic) task;
                    fileBackendTasksManager.addEpic(epic);
                    historyManager.addHistory(fileBackendTasksManager.getEpicById(epic.getId()));
                }

                if (TaskType.valueOf(type).equals(TaskType.SUBTASK)) {
                    SubTask subtask = (SubTask) task;
                    fileBackendTasksManager.addSubTask(subtask);
                    historyManager.addHistory(fileBackendTasksManager.getSubTaskById(subtask.getId()));
                }
            }
        } catch (IOException e) {
            throw new ManagerSaveException(e.getMessage());
        }
        return fileBackendTasksManager; //
    }
}