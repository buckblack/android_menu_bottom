package com.example.buckb.menubottom;

public class ban_class {
    private int id;
    private String status;

    public ban_class(int id, String status) {
        this.setId(id);
        this.setStatus(status);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
