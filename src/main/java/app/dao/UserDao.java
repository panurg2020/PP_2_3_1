package app.dao;

import app.model.User;

import java.util.List;

public interface UserDao {

    User getById(long id);

    List<User> allUsers();

    void add(User user);

    void delete(User user);
}
