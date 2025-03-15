package ru.netologia.qamid.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TodosTest {
    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");

        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(55, subtasks);

        Meeting meeting = new Meeting(
                555,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );

        Todos todos = new Todos();

        todos.add(simpleTask);
        todos.add(epic);
        todos.add(meeting);

        Task[] expected = {simpleTask, epic, meeting};
        Task[] actual = todos.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    /**
     * Тест метода matches в классе SimpleTask на корректность работы: */
    public void testSimpleTaskMatches() {
        SimpleTask simpleTask = new SimpleTask(1, "Купить молоко");
        Todos todos = new Todos();
        todos.add(simpleTask);

        // Ожидаемый результат: массив с одной задачей, если запрос совпадает
        Task[] expected = {simpleTask};
        Task[] actual = todos.search("молоко");
        Assertions.assertArrayEquals(expected, actual);

        // Ожидаемый результат: пустой массив, если запрос не совпадает
        Task[] expectedEmpty = new Task[0];
        Task[] actualEmpty = todos.search("хлеб");
        Assertions.assertArrayEquals(expectedEmpty, actualEmpty);
    }

    @Test
    /**
     * Тест метода matches в классе Epic на корректность работы:*/
    public void testEpicMatches() {
        String[] subtasks = {"Молоко", "Яйца", "Хлеб"};
        Epic epic = new Epic(1, subtasks);
        Todos todos = new Todos();
        todos.add(epic);

        // Ожидаемый результат: массив с одной задачей, если запрос совпадает с одной из подзадач
        Task[] expected = {epic};
        Task[] actual = todos.search("Молоко");
        Assertions.assertArrayEquals(expected, actual);

        // Ожидаемый результат: пустой массив, если запрос не совпадает
        Task[] expectedEmpty = new Task[0];
        Task[] actualEmpty = todos.search("Сок");
        Assertions.assertArrayEquals(expectedEmpty, actualEmpty);
    }

    @Test
    /**
     * Тест метода matches в классе Meeting на корректность работы:*/
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(
                1,
                "Выкатка 3й версии приложения",
                "Приложение НетоБанка",
                "Во вторник после обеда"
        );
        Todos todos = new Todos();
        todos.add(meeting);

        // Ожидаемый результат: массив с одной задачей, если запрос совпадает с topic, project или start
        Task[] expected = {meeting};
        Task[] actualTopic = todos.search("Выкатка");
        Assertions.assertArrayEquals(expected, actualTopic);

        Task[] actualProject = todos.search("НетоБанка");
        Assertions.assertArrayEquals(expected, actualProject);

        Task[] actualStart = todos.search("вторник");
        Assertions.assertArrayEquals(expected, actualStart);

        // Ожидаемый результат: пустой массив, если запрос не совпадает
        Task[] expectedEmpty = new Task[0];
        Task[] actualEmpty = todos.search("понедельник");
        Assertions.assertArrayEquals(expectedEmpty, actualEmpty);
    }

}
