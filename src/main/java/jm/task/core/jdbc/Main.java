package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;


public class Main {

    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {


        userService.createUsersTable();


        List<User> userList = List.of(new User("Bob", "Singer", (byte) 25),
                new User("Kate", "White", (byte) 30),
                new User("Victory", "Chance", (byte) 35),
                new User("Tom", "Pitt", (byte) 20));
        for (User user : userList) {
            userService.saveUser(user.getName(), user.getLastName(), user.getAge());
            System.out.println("User c именем " + user.getName() + " добавлен в базу данных");
        }


        List<User> userList2 = userService.getAllUsers();
        for (User user : userList2) {
            System.out.println(user);
        }


        userService.cleanUsersTable();


        userService.dropUsersTable();


        Util.closeConnection();
    }
}
