package com.yarikpavlin.tz.service;

import com.yarikpavlin.tz.dao.UserDao;
import com.yarikpavlin.tz.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by Yaroslav Pavlinskiy on 07.06.2017.
 */
@Path("/users")
public class RestService {


    // URI:localhost:8080/rest/users
    // From help this method we can see all users
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getUsers() {
        List<User> listOfUser = UserDao.getAllUsers();
        return listOfUser;
    }


    // URI:localhost:8080/rest/users/{id}
    //From help this method we can find user by Id
    @GET
    @Path("/id={id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser(@PathParam("id") Long id) {
        return UserDao.getUserById(id);
    }


    // URI:localhost:8080/rest/users/name={name}
    // From help this method we can find user by Name
    @GET
    @Path("/name={name}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserForName(@PathParam("name") String name) {
        return UserDao.getUserByName(name);
    }


    // URI:localhost:8080/rest/users
    // From help this method we can add new User
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUser(User u) {
        UserDao.addUser(u);
        return Response.status(200).build();
    }


    // URI:localhost:8080/rest/users
    //From help this method we can update User by Id
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public User updateUser(User u) {
        UserDao.updateUser(u);
        return u;
    }


    // URI:localhost:8080/rest/users/delete={id}
    // From help this method we can delete user by id
    @DELETE
    @Path("/delete={id}")
    public void deleteUser(@PathParam("id") Long id) {
        UserDao.deleteUser(id);
    }
}
