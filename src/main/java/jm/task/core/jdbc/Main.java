package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.util.List;


public class Main {

    private static UserDao userDao = new UserDaoJDBCImpl();

    public static void main(String[] args) {


        userDao.createUsersTable();


        List<User> userList = List.of(new User("Bob","Singer",(byte)25),
                new User("Kate","White",(byte)30),
                new User("Victory","Chance",(byte)35),
                new User("Tom","Pitt",(byte)20));
        for (User user : userList) {
            userDao.saveUser(user.getName(),user.getLastName(),user.getAge());
            System.out.println("User c именем " +user.getName()+" добавлен в базу данных");
        }


        List<User> userList2 = userDao.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }


        userDao.cleanUsersTable();


        userDao.dropUsersTable();
    }
}
