package managers;

import managers.history.HistoryManager;
import managers.history.InMemoryHistoryManager;
import managers.task.FileBackedTasksManager;

public class Managers {

    public static HistoryManager getHistoryManager(){
        return  new InMemoryHistoryManager();
    }
    public static FileBackedTasksManager getFileBackendManager(){
        return new FileBackedTasksManager();
    }
}
