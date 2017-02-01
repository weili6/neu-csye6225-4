package team4.repository;

import team4.domain.User;

import java.util.List;

/**
 * Created by liren
 */
public interface UserRepository {

    void save(User user);

    User findByUsername(String username);

    public List<User> findAll();
}
