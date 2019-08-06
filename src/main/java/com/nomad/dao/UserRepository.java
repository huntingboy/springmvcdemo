package com.nomad.dao;

import com.nomad.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository {

    List<User> findUsers(long max, int count);

    User save(User unsaved);

    User findUserByName(String username);
}
