package com.example.repositoryex.Repository;

import com.example.repositoryex.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserById(Integer id);
    User findUserByUsername(String username);
    User findUserByEmail(String email);
    List<User> findUsersByRole(String role);

    List<User> findUsersByAgeGreaterThanEqual(Integer age);

    @Query("select u from User u where u.age>=?1")
    List<User> findUsersByAgeMoreThanX(Integer age);
}
