package com.nomad.dao;

import com.nomad.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Override
    public List<User> findUsers(long max, int count) {
        return createUserList(count);
    }

    private List<User> createUserList(int count) {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            users.add(new User("user" + i, 'm', 20));
        }
        return users;
    }
}
