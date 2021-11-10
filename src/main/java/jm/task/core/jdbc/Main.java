package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Petrov", (byte) 45);
        userService.saveUser("Anton", "Petrov", (byte) 20);
        userService.saveUser("Lida", "Petrova", (byte) 18);
        userService.saveUser("Sveta", "Petrova", (byte) 43);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
