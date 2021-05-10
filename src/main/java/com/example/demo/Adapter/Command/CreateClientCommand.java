package com.example.demo.Adapter.Command;

public class CreateClientCommand {

    private String name;

    private String firstName;

    public CreateClientCommand(String name, String firstName) {
        this.name = name;
        this.firstName = firstName;
    }

    public String getName() {
        return name;
    }

    public String getFirstName() {
        return firstName;
    }
}
