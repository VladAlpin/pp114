package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static final jm.task.core.jdbc.model.User user1 = new jm.task.core.jdbc.model.User("Name1", "LastName1", (byte) 30);
    private static final jm.task.core.jdbc.model.User user2 = new jm.task.core.jdbc.model.User("Name2", "LastName2", (byte) 25);
    private static final jm.task.core.jdbc.model.User user3 = new jm.task.core.jdbc.model.User("Name3", "LastName3", (byte) 31);
    private static final jm.task.core.jdbc.model.User user4 = new jm.task.core.jdbc.model.User("Name4", "LastName4", (byte) 38);

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());
        System.out.println("User c именем " + user1.getName() + " добавлен в базу данных");

        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());
        System.out.println("User c именем " + user2.getName() + " добавлен в базу данных");

        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());
        System.out.println("User c именем " + user3.getName() + " добавлен в базу данных");

        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());
        System.out.println("User c именем " + user4.getName() + " добавлен в базу данных");

        userService.removeUserById(1);
        userService.getAllUsers();
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
