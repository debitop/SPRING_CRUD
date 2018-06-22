package crud.repository.impl;

import crud.model.User;
import crud.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(user);
    }

    @Override
    public User get(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id); // SELECT * FROM users WHERE id = 100;
    }

    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User").list(); // createCriteria / createSQLQuery
    }

    @Override
    public void removeUser(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.load(User.class, id);
        session.delete(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        User savedUser = session.get(User.class, user.getId());
        savedUser.setAdmin(user.isAdmin());
        savedUser.setAge(user.getAge());
        savedUser.setName(user.getName());
        session.update(savedUser);
    }
}
