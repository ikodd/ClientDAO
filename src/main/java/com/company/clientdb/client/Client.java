package com.company.clientdb.client;

import com.company.clientdb.dao.*;

public class Client {
    @Id
    private Long id;
    private String name;
    private long phone;

    public Client(String name, long phone) {
        this.name = name;
        this.phone = phone;
    }

    public Client() {
    }

    public Client(Long id, String name, long phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setAge(long phone) {
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }
}
