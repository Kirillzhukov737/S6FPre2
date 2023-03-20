package managers.task;

import managers.Managers;
import managers.history.HistoryManager;
import managers.task.TaskManager;
import model.*;

import java.util.*;

public class InMemoryTaskManager implements TaskManager, Comparator<Task> {

    private Long taskMaxId = 0L;
    private Set<Task> priorityTasks = new TreeSet<>(this);
    protected static HistoryManager historyManager = Managers.getHistoryManager();
    private final Map<Long, Epic> epics = new HashMap<>(); // Создаю мап для эпиков: ключ(id), значение(эпик)
    private final Map<Long, Task> tasks = new HashMap<>();
    private final Map<Long, SubTask> subTasks = new HashMap<>();

    private void addPrioritiesTask(Task task){
        checkIntersections();
        priorityTasks.add(task);
    }
    public List<Task> getPrioritizedTasks(){
        return new ArrayList<>(priorityTasks);
    }

    public void checkIntersections(){
        var priorityTasks = getPrioritizedTasks();
        for(int i = 1; i < priorityTasks.size(); i++){
            if(priorityTasks.get(i).getStartTime().isBefore(priorityTasks.get(i-1).getStartTime())){
                throw new IntersectionsException("Найдено пересечение между"
                        +priorityTasks.get(i) + " и "+priorityTasks.get(i-1));
            }
        }
    }
    @Override
    public List<Epic> getAllEpic() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public void clearEpic() {
        clearSubtask();
        epics.clear();
    }

    @Override
    public Epic getEpicById(Long id) {
        Epic epic = epics.get(id);
        historyManager.addHistory(epic);
        return epic;
    }

    @Override
    public Epic addEpic(Epic epic) {
        epic.setId(taskMaxId++);
        if (epic != null && !epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
        }
        addPrioritiesTask(epic);
        return epic;
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) { // проверят наличие эпика в мапе и если он есть, обноваляет его
            epics.put(epic.getId(), epic);
        }
    }

    @Override
    public Epic removeEpicById(Long id) {
        for (SubTask subTask : epics.get(id).getSubTaskList()) {
            subTasks.remove(subTask.getId());
        }
        return epics.remove(id);
    }

    @Override
    public List<SubTask> getAllSubTask() {
        return (List<SubTask>) subTasks.values();
    }

    @Override
    public void clearSubtask() {
        for (Epic epic : epics.values()) {
            epic.getSubTaskList().clear();
            epic.setStatus(TaskStatus.NEW);
        }
        subTasks.clear();
    }

    @Override
    public SubTask getSubTaskById(Long id) {
        SubTask subTask = subTasks.get(id);
        historyManager.addHistory(subTask);
        return subTask;
    }

    @Override
    public SubTask addSubTask(SubTask subTask) {
        subTask.setId(taskMaxId++);//добавлению новый сабтаск в мапу и возвращаю добавленный сабтаск
        if (!subTasks.containsKey(subTask.getId())) {
            subTasks.put(subTask.getId(), subTask);
        }
        addPrioritiesTask(subTask);
        return subTask;
    }

    @Override
    public SubTask removeSubTaskById(Long id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = epics.get(subTask.getEpicId());
        if (epic.getSubTaskList().size() == 1) {
            epic.setStatus(TaskStatus.DONE); //если закончились сабтаски, значит эпик готов
        }
        epic.getSubTaskList().remove(subTask);
        return subTasks.remove(id);
    }

    @Override
    public SubTask updateSubTask(SubTask subTask) {
        Epic epic = epics.get(subTask.getEpicId());
        boolean isDone = false;
        for (SubTask subTask1 : epic.getSubTaskList()) {
            if (!subTask1.getStatus().equals(TaskStatus.DONE)) {
                isDone = true;
                break;
            }
        }
        if (!isDone && subTask.getStatus().equals(TaskStatus.DONE)) {
            updateStatusEpic(epic, TaskStatus.DONE);
        }
        return subTasks.put(subTask.getId(), subTask);
    }

    @Override
    public List<Task> getAllTask() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public void clearTask() {
        tasks.clear();
    }

    @Override
    public Task getTaskById(Long id) {
        Task task = tasks.get(id);
        historyManager.addHistory(task);
        return task;
    }

    @Override
    public void updateStatusEpic(Epic epic, TaskStatus taskStatus) {
        epic.setStatus(taskStatus);
    }

    @Override
    public Task addTask(Task task) {
        task.setId(taskMaxId++);//добавлению новый таск в мапу и возвращаю добавленный сабтаск
        tasks.put(task.getId(), task);
        addPrioritiesTask(task);
        return task;
    }

    @Override
    public Task removeTaskById(Long id) {
        return tasks.remove(id);
    }

    @Override
    public void updateTask(Task task) {
        if (tasks.containsKey(task.getId())) {
            tasks.put(task.getId(), task);
        }
    }

    @Override
    public List<SubTask> getAllSubtaskByEpic(Epic epic) {
        return epic.getSubTaskList();
    }

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getStartTime().compareTo(o2.getStartTime());
    }
}