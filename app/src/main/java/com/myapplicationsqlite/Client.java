package com.myapplicationsqlite;

public class Client {
    int id;
    String name;
    String Email;
    String Password;

    public Client() { }

    public Client(String name, String email, String password) {
        this.name = name;
        Email = email;
        Password = password;
    }

    public Client(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        Email = email;
        Password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                '}';
    }
}
