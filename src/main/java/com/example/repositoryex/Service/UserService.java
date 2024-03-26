package com.example.repositoryex.Service;

import com.example.repositoryex.ApiResponse.ApiException;
import com.example.repositoryex.Model.User;
import com.example.repositoryex.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public void updateUser(Integer id, User user){
        User u = userRepository.findUserById(id);
        if(u==null){
            throw new ApiException("could not find user");
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setAge(user.getAge());
        userRepository.save(u);
    }

    public User deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("could not find user");
        }
        userRepository.delete(user);
        return user;
    }

    public void checkLogin(String username, String password){
        User user = userRepository.findUserByUsername(username);
        if(user == null){
            throw new ApiException("user not found");
        }
        if(user.getUsername().equals(username)&&user.getPassword().equals(password)){
            return;
        }
        throw new ApiException("username and password doesn't match");
    }

    public User getUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if(user == null){
            throw new ApiException("could not find user by this email");
        }
        return user;
    }
    public List<User> getUsersByRole(String role){
        return userRepository.findUsersByRole(role);
    }

    public List<User> getUserByAgeGreaterThanAndEqual(Integer age){
        return userRepository.findUsersByAgeGreaterThanEqual(age);
    }
}
