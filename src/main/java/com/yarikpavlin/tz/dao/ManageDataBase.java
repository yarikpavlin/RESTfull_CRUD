package com.yarikpavlin.tz.dao;

import com.yarikpavlin.tz.model.User;
import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import java.sql.*;
import java.util.*;

/**
 * Created by Yaroslav Pavlinskiy on 07.06.2017.
 */
public class ManageDataBase {
    private static final String CREATE_QUERY = "CREATE TABLE USER(" +
            "Id int(16) not null primary key,name varchar(255) not null,phone varchar(255) not null)";
    private static final Logger log = Logger.getLogger(ManageDataBase.class);
    private static Statement statement;
    private static Connection connection;

    static {
        try {
            DriverManager.registerDriver(new org.h2.Driver());
            connection = DriverManager.getConnection("jdbc:h2:mem:test");
            statement = connection.createStatement();
            statement.execute(CREATE_QUERY);
            log.info("Table successfully created!");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Some problems with creating table USER!");
        }
    }

    public static Map<Long, User> getUsersList() {
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER");
            Map<Long, User> userMap = new HashMap<Long, User>();
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
                userMap.put(resultSet.getLong("Id"), user);
            }

            return userMap;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static User getUserById(Long id) {
        try {
            statement = connection.createStatement();
            User user = new User();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM USER WHERE id =" + id);
            while (resultSet.next()) {
                user.setId(resultSet.getLong("Id"));
                user.setName(resultSet.getString("name"));
                user.setPhone(resultSet.getString("phone"));
            }
            log.info("User successfully find by Id");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Some problems with find user by ID");
        }

        return null;
    }

    public static User getUserByName(String name) {
        Map<Long, User> userMap = null;
        userMap = getUsersList();
        Iterator it = userMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, User> pair = (Map.Entry<Long, User>) it.next();
            if (name.equals(pair.getValue().getName())) {
                log.info("User successfully find by Name!");
                return pair.getValue();
            }
        }
        return new User();
    }

    public static User addNewUser(User user) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format("INSERT INTO user (Id,name,phone) " +
                    "VALUES ('%s','%s','%s')", user.getId(), user.getName(), user.getPhone()));
            log.info("New User successfully added to DataBase!");
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Some problems with adding User to DataBase!");
        }
        return null;

    }

    public static User updateUser(User user) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate(String.format("UPDATE user SET Id=%s,name='%s',phone='%s' WHERE id=%s"
                    , user.getId(), user.getName(), user.getPhone(), user.getId()));
            log.info("User successfully updated!");
        } catch (SQLException e) {
            e.printStackTrace();
            log.error("Some problems with updating User!");
        }
        return new User();
    }

    public static Response deleteUser(Long id) {
        try {
            statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM user WHERE id=" + id);
            log.info("User successfully deleted from DataBase!");
            return Response.status(200).build();
        } catch (SQLException e) {
            log.error("Some problems with deleting User!");
            e.printStackTrace();
        }
        return null;
    }
}
