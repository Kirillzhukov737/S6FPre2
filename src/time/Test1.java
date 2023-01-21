package time;

import model.Task;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Test1 {

    public void scheduleTask(Task task) {
        System.out.print("Введите время начала задачи (в формате ЧЧ:ММ): ");
        LocalTime startTime = LocalTime.parse(System.console().readLine());
        System.out.print("Отлично, теперь введите продолжительность задачи в минутах: ");
        int durationMinutes = Integer.parseInt(System.console().readLine());
        LocalTime getEndTime = startTime.plusMinutes(durationMinutes);
        System.out.println("Задача " + task.getName() + "начинается в: " + startTime + "заканчивается в " + getEndTime);
        Map<String, Integer> TaskMap = new HashMap<>();
        TaskMap.put(task.getName(), durationMinutes);
    }

    //    public boolean overlaps(Task otherTask){
//        if(this.taskStartTime.before(otherTask.taskEndTime) && otherTask.taskStartTime.before(this.taskEndTime)){
//            return true;
//        } else{
//            return false;
//        }
//    }
}
