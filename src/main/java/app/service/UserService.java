package app.service;

import app.model.User;

import java.util.List;

public interface UserService {

    User getById(long id);

    List<User> allUsers();

    void add(User user);

    void delete(User user);
}
