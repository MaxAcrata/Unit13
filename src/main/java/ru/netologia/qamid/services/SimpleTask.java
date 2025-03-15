package ru.netologia.qamid.services;

public class SimpleTask extends Task {
    protected String title;

    public SimpleTask(int id, String title) {
        super(id); // вызов родительского конструктора
        this.title = title; // заполнение своих полей
    }

    public String getTitle() {
        return title;
    }
    /** Метод String.contains(query)  возвращает  (true или false)*/
    @Override
    public boolean matches(String query) {
        return title.contains(query);
    }

}
