package com.yarikpavlin.tz.dao;

import com.yarikpavlin.tz.model.User;

import java.util.*;

/**
 * Created by Yaroslav Pavlinskiy on 07.06.2017.
 */

public class UserDao {

    public static User getUserById(Long id) {
        return ManageDataBase.getUserById(id);
    }

    public static User getUserByName(String name) {
        return ManageDataBase.getUserByName(name);
    }

    public static User addUser(User u) {
        return ManageDataBase.addNewUser(u);
    }

    public static User updateUser(User u) {
        return ManageDataBase.updateUser(u);
    }

    public static void deleteUser(Long id) {
        ManageDataBase.deleteUser(id);
    }

    public static List<User> getAllUsers() {
        Collection<User> c = ManageDataBase.getUsersList().values();
        List<User> list = new ArrayList<User>();
        list.addAll(c);
        return list;
    }


}
