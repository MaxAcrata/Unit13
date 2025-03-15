package ru.netologia.qamid.services;

public class Meeting extends Task {

    protected String topic;
    protected String project;
    protected String start;

    public Meeting(int id, String topic, String date, String project) {
        super(id);
        this.topic = topic;
        this.project = project;
        this.start = date;
    }

    public String getTopic() {
        return topic;
    }

    public String getProject() {
        return project;
    }

    public String getStart() {return start;
    }

    @Override
    public boolean matches(String query) {
        // Проверяем, содержится ли запрос в topic, project или start
        if (topic.contains(query) || project.contains(query) || start.contains(query)) {
            return true;
        }
        return false;
    }
}

