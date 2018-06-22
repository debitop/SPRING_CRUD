package crud.repository;

import crud.model.User;

import java.util.List;

public interface UserRepository {

    void addUser(User user);

    User get(Integer id);

    List getUsers();

    void removeUser(Integer id);

    void updateUser(User user);
}
