package ru.netologia.qamid.services;

public class Epic extends Task {

    protected String[] subtasks;

    public Epic(int id, String[] subtasks) {
        super(id); // вызов родительского конструктора
        this.subtasks = subtasks;// заполнение своих полей
    }

    public String[] getSubtasks() {
        return subtasks;
    }

    @Override
    public boolean matches(String query) {
        // Если массив subtasks пустой или null, сразу возвращаем false
        if (subtasks == null || subtasks.length == 0) {
            return false;
        }

        // Проверяем каждую подзадачу на наличие query
        for (String subtask : subtasks) {
            if (subtask != null && subtask.contains(query)) {
                return true; // Возвращаем true, если найдено совпадение
            }
        }

        // Если ни одна подзадача не содержит query, возвращаем false
        return false;
    }
}
