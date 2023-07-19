package io.github.janekkodowanie.ezML.user;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository {

    public List<User> findAll();

}
