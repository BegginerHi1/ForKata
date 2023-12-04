package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Util util = new Util();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastname` VARCHAR(45) NULL,\n" +
                "  `age` INT NULL,\n" +
                "  PRIMARY KEY (`id`));\n";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        String sql = "drop table if exists user";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "insert into user(name,lastname,age) values(?,?,?)";
        try (PreparedStatement statement = util.getConnection().prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setInt(3, age);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        String sql = "delete from user where id=?";
        try (PreparedStatement preparedStatement = util.getConnection().prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String sql = "select * from user";
        List<User> list = new ArrayList<>();
        try (Statement statement = util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge(resultSet.getByte(4));
                list.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        String sql = "delete from user";
        try (Statement statement = util.getConnection().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
