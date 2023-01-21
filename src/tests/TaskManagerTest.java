package tests;

import managers.task.InMemoryTaskManager;
import managers.task.TaskManager;
import model.*;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static model.TaskStatus.DONE;
import static model.TaskStatus.NEW;
import static org.junit.jupiter.api.Assertions.*;

public abstract class TaskManagerTest<T extends TaskManager> {

    protected T manager;

    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", NEW, Instant.now());
        final Long taskId = manager.addTask(task).getId();

        final Task savedTask = manager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final List<Task> tasks = manager.getAllTask();

        assertNotNull(tasks, "Задачи на возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(0), "Задачи не совпадают.");
    }

//    @Test
//    void removeTaskTest() {
//        Task task = new Task("Test removeTask", "Test removeTask", DONE);
//        final Long taskId = manager.removeTaskById(1L).getId();
//
//        final

    @Test
    void taskIsEmpty(){
        Epic epic = new Epic(1L,"Test addNewTask", "Test addNewTask description", NEW, TaskType.TASK);
        epic.setSubTaskList(new ArrayList<>());
        assertEquals(TaskStatus.NEW, epic.getStatus());
    }
    @Test
    public void allSubtaskDone(){
        Instant now = Instant.now();
        Epic epic = new Epic(1L,"Test addNewTask", "Test addNewTask description", NEW, TaskType.TASK);
        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(new SubTask(1L, "SubTask1", "Description", TaskStatus.DONE, TaskType.SUBTASK, 40L));
        subTaskList.add(new SubTask(2L, "SubTask2", "Description", TaskStatus.DONE, TaskType.SUBTASK, 10L));
        epic.setSubTaskList(subTaskList);
        assertEquals(TaskStatus.DONE, epic.getStatus());
    }
    @Test
    public void allSubtaskNew(){
        Instant now = Instant.now();
        Epic epic = new Epic(1L, "Epic1", "Description", TaskStatus.NEW, TaskType.EPIC);
        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(new SubTask(1L, "SubTask1", "Description", TaskStatus.NEW, TaskType.SUBTASK, 11L));
        subTaskList.add(new SubTask(2L, "SubTask2", "Description", TaskStatus.NEW, TaskType.SUBTASK, 12L));
        epic.setSubTaskList(subTaskList);
        assertEquals(TaskStatus.NEW, epic.getStatus());
    }
    @Test
    public void allSubTasksInProgress() {
        Instant now = Instant.now();
        Epic epic = new Epic(1L, "Epic1", "Description", TaskStatus.IN_PROGRESS, TaskType.EPIC);
        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(new SubTask(1L, "SubTask1", "Description", TaskStatus.IN_PROGRESS, TaskType.SUBTASK, 10L));
        subTaskList.add(new SubTask(2L, "SubTask2", "Description", TaskStatus.IN_PROGRESS, TaskType.SUBTASK, 20L));
        epic.setSubTaskList(subTaskList);
        assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }

    }

