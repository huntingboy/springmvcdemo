package com.nomad.dao;

import com.nomad.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    private static Map<String, User> users = new HashMap<>();

    static {
        for (int i = 0; i < 100; i++) {
            users.put("user" + i, new User("user" + i, 'm', 20));
        }
    }

    @Override
    public List<User> findUsers(long max, int count) {
        return createUserList(count);
    }

    @Override
    public User save(User unsaved) {
        String username = unsaved.getUsername();
        users.put(username, unsaved);
        return unsaved;
    }

    @Override
    public User findUserByName(String username) {
        return users.get(username);
    }

    private List<User> createUserList(int count) {
        List<User> usersList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
//            users.add(new User("user" + i, 'm', 20));
            usersList.add(users.get("user" + i));
        }
        return usersList;
    }
}
