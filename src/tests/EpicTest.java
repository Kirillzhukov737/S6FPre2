package tests;

import model.Epic;
import model.SubTask;
import model.TaskStatus;
import model.TaskType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class EpicTest {

    @Test
    void calculateStatusEmptySubTaskList() {
        Epic epic = new Epic(1L, "Epic 1", "Description", TaskStatus.NEW, TaskType.EPIC);
        epic.setSubTaskList(Collections.emptyList());
        assertEquals(TaskStatus.NEW, epic.getStatus());
    }

    @Test
    void calculateStatusAllSubTasksNew() {
        Epic epic = new Epic(1L, "Epic 1", "Description", TaskStatus.NEW, TaskType.EPIC);
        List<SubTask> subTasks = Arrays.asList(
                new SubTask(1L, "SubTask 1", "Description", TaskStatus.NEW,TaskType.TASK, epic.getId()),
                new SubTask(2L, "SubTask 2", "Description", TaskStatus.NEW,TaskType.TASK, epic.getId())
        );
        epic.setSubTaskList(subTasks);
        assertEquals(TaskStatus.NEW, epic.getStatus());
    }

    @Test
    void calculateStatusAllSubTasksDone() {
        Epic epic = new Epic(1L, "Epic 1", "Description", TaskStatus.NEW, TaskType.EPIC);
        List<SubTask> subTasks = Arrays.asList(
                new SubTask(1L, "SubTask 1", "Description", TaskStatus.DONE, TaskType.SUBTASK,epic.getId()),
                new SubTask(2L, "SubTask 2", "Description", TaskStatus.DONE,TaskType.SUBTASK,epic.getId())
        );
        epic.setSubTaskList(subTasks);
        assertEquals(TaskStatus.DONE, epic.getStatus());
    }

    @Test
    void calculateStatusSubTasksNewAndDone() {
        Epic epic = new Epic(1L, "Epic 1", "Description", TaskStatus.NEW, TaskType.EPIC);
        List<SubTask> subTasks = Arrays.asList(
                new SubTask(1L, "SubTask 1", "Description", TaskStatus.NEW,TaskType.SUBTASK, epic.getId()),
                new SubTask(2L, "SubTask 2", "Description", TaskStatus.DONE,TaskType.SUBTASK, epic.getId())
        );
        epic.setSubTaskList(subTasks);
        assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }

    @Test
    void calculateStatusSubTasksInProgress() {
        Epic epic = new Epic(1L, "Epic 1", "Description", TaskStatus.IN_PROGRESS, TaskType.EPIC);
        List<SubTask> subTasks = Arrays.asList(
                new SubTask(1L, "SubTask 1", "Description", TaskStatus.IN_PROGRESS,TaskType.TASK, epic.getId()),
                new SubTask(2L, "SubTask 2", "Description", TaskStatus.IN_PROGRESS,TaskType.TASK, epic.getId())
        );
        epic.setSubTaskList(subTasks);
        assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());
    }
}


//class EpicTest {
//
//    @Test
//    void getSubTaskList() {
//    }
//
//    @Test
//    void setSubTaskList() {
//    }
//
//    @Test
//    void testToString() {
//    }
