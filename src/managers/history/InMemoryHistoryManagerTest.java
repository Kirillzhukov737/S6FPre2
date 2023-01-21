package managers.history;

import model.SubTask;
import model.Task;
import model.TaskStatus;
import model.TaskType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

public abstract class InMemoryHistoryManagerTest<T extends HistoryManager> {

    protected T historyManager;
    @BeforeEach
    public void init(){
    }


    @Test
    void getHistory() {

    }

//    @Test
//    void addHistory() {
//        Task task1 = new Task(1L ,"Task 1", "Task1 description", TaskStatus.NEW, TaskType.TASK );
//        historyManager.addHistory(task1);
//
//        List<Task> history = historyManager.getHistory();
//        assertEquals(1, history.size());
//        assertEquals(task1, history.get(0));
//
//        // добавленный дубликат Таски с тем же ID удаляет предыдущий
//        Task task2 = new Task(1, "Task 2");
//        historyManager.addHistory(task2);
//
//        history = historyManager.getHistory();
//        assertEquals(1, history.size());
//        assertEquals(task2, history.get(0));
//    }

    @Test
    void remove() {

    }
}