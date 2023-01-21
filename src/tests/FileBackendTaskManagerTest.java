package tests;

import managers.Managers;
import managers.task.FileBackedTasksManager;
import managers.task.InMemoryTaskManager;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FileBackendTaskManagerTest extends TaskManagerTest{

    @BeforeEach
    public void init(){
        manager = Managers.getFileBackendManager();
    }

    private static final String TEST_FILE = "test.csv";
    private FileBackedTasksManager manager;

//    @BeforeEach
//    public void setUp() {
//        manager = new FileBackedTasksManager(TEST_FILE);
//    }

    @AfterEach
    public void tearDown() {
        new File(TEST_FILE).delete();
    }

    @Test
    public void emptyList() {
        List<Task> tasks = manager.getAllTask();
        assertNotNull(tasks);
        assertEquals(0, tasks.size());
    }

    @Test
    public void addEpicWithoutSubTasks() {
        Epic epic = new Epic(1L, "epic1", "description", TaskStatus.NEW, TaskType.SUBTASK);
        epic.setName("Test epic");
        epic.setDescription("Test description");
        Epic result = manager.addEpic(epic);
        assertNotNull(result);
        assertNotNull(result.getId());
        assertEquals(epic.getName(), result.getName());
        assertEquals(epic.getDescription(), result.getDescription());
        List<SubTask> subTasks = manager.getAllSubtaskByEpic(result);
        assertNotNull(subTasks);
        assertEquals(0, subTasks.size());
    }

    @Test
    public void emptyHistory() {
        List<Task> history = (List<Task>) manager;
        assertNotNull(history);
        assertEquals(0, history.size());
    }
}
