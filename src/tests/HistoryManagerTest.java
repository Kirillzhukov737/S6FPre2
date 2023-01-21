package tests;

import model.TaskStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import managers.history.HistoryManager;
import model.Task;

import java.time.Instant;
import java.util.List;

public abstract class HistoryManagerTest<T extends HistoryManager> {

    private T historyManager;

    @BeforeEach
    public void init() {
    }

    @Test //проверяет, что список истории пуст после создания объекта HistoryManager
    public void getHistoryEmpty() {
        List<Task> history = historyManager.getHistory();
        assertTrue(history.isEmpty());
    }

    @Test // проверяет, что задача успешно добавляется в историю
    public void addHistory() {
        Task task = new Task("task 1", "task1 description", TaskStatus.NEW, Instant.now());
        historyManager.addHistory(task);
        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task, history.get(0));
    }

    @Test // проверяет, что задача успешно удаляется из истории в разных позициях списка
    public void remove() {
        Task task1 = new Task("task 1", "task1 description", TaskStatus.NEW, Instant.now());
        Task task2 = new Task("task 2", "task2 description", TaskStatus.IN_PROGRESS, Instant.now());
        Task task3 = new Task("task 3", "task3 description", TaskStatus.DONE, Instant.now());
        historyManager.addHistory(task1);
        historyManager.addHistory(task2);
        historyManager.addHistory(task3);

        // удаление из начала
        historyManager.remove(1);
        List<Task> history = historyManager.getHistory();
        assertEquals(2, history.size());
        assertEquals(task2, history.get(0));
        assertEquals(task3, history.get(1));

        // удаление из середины
        historyManager.remove(2);
        history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task3, history.get(0));

        // удаление из конца
        historyManager.remove(3);
        history = historyManager.getHistory();
        assertTrue(history.isEmpty());
    }

    @Test // если задача не найдена в истории выбрасывает исключение
    public void removeNonExisting() {
        Task task = new Task("task 1", "task1 description", TaskStatus.NEW, Instant.now());
        historyManager.addHistory(task);
        assertThrows(IllegalArgumentException.class, () -> historyManager.remove(2));
    }

    @Test //проверяет, что метод addHistory() выбрасывает исключение, если задача с таким же ID уже существует в истории
    public void duplicate() {
        Task task = new Task("task 1", "task1 description", TaskStatus.NEW, Instant.now());
        historyManager.addHistory(task);
        assertThrows(IllegalArgumentException.class, () -> historyManager.addHistory(task));
    }
}
