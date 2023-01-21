package time;

import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class TaskTime {

    public void timeTaskAndAddToMap(Map<String, LocalTime> tasksMap) {
        // Получаем название задачи от пользователя
        System.out.print("Введите название задачи: ");
        String taskName = System.console().readLine();

        // Получаем время начала задачи от пользователя
        System.out.print("Введите время начала задачи (в формате ЧЧ:ММ:СС): ");
        LocalTime startTime = LocalTime.parse(System.console().readLine());

        // Получаем продолжительность задачи от пользователя
        System.out.print("Введите продолжительность задачи (в минутах): ");
        int durationMinutes = Integer.parseInt(System.console().readLine());

        // Вычисляем время окончания задачи
        LocalTime endTime = startTime.plusMinutes(durationMinutes);

        // Добавляем задачу в мапу
        tasksMap.put(taskName, startTime);

        // Выводим результат на экран
        System.out.println("Задача '" + taskName + "' начинается в " + startTime + " и заканчивается в " + endTime);
    }

}
