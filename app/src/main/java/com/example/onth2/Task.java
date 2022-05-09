package com.example.onth2;

public class Task {
    private String name;
    private String id;
    private String status;

    public Task(String id, String name, String status) {
        this.name = name;
        this.id = id;
        this.status = status;
    }

    public Task() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
