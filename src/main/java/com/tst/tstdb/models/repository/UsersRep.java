package com.tst.tstdb.models.repository;

import com.tst.tstdb.models.Question;
import com.tst.tstdb.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRep extends CrudRepository<User, Integer> {
    User findByName(String name);
    List<User> findAll();
}
