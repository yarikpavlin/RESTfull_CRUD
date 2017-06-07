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
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://127.0.0.1/restchi?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final Logger log = Logger.getLogger(ManageDataBase.class);
    private static Statement statement;
    private static Connection connection;

    static {
        statement = null;
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            log.info("Connection successfully get!");
        } catch (SQLException e) {
            log.error("Something wrong with connection!");
            e.printStackTrace();
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
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
