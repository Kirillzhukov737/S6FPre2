package managers.history;

import collection.CustomLinkedList;
import collection.Node;
import managers.history.HistoryManager;
import model.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    private final CustomLinkedList historyManager = new CustomLinkedList();
    private final Map<Long, Node> history = new HashMap<>();

    @Override
    public List<Task> getHistory() {
        return historyManager.getTasks();
    }

    @Override
    public void addHistory(Task task) {
       Node node = historyManager.linkLast(task);
       if(history.containsKey(task.getId())){
           historyManager.removeNode(history.get(task.getId()));
       }
        history.put(task.getId(), node);
    }

    @Override
    public void remove(int id) {
        historyManager.removeNode(history.get(id));
        history.remove(id);
    }
}