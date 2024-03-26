package com.example.repositoryex.Controller;

import com.example.repositoryex.ApiResponse.ApiResponse;
import com.example.repositoryex.Model.User;
import com.example.repositoryex.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user-management")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/users")
    public ResponseEntity getAllUser(){
        return ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.ok(new ApiResponse("user added: " + user));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id,@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id,user);
        return ResponseEntity.ok(new ApiResponse("user updated: "+user));
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id){
        User user = userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("user deleted"+user));
    }


    @PostMapping("/login/{username}/{password}")
    public ResponseEntity checkLogin(@PathVariable String username, @PathVariable String password){
        userService.checkLogin(username,password);
        return ResponseEntity.ok(new ApiResponse("matches"));
    }

    @GetMapping("/user-by-email/{email}")
    public ResponseEntity getUserByEmail(@PathVariable String email){
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @GetMapping("/users-by-role/{role}")
    public ResponseEntity getUsersByRole(@PathVariable String role){
        return ResponseEntity.ok(userService.getUsersByRole(role));
    }

    @GetMapping("/users-by-age/{age}")
    public ResponseEntity getUserByAgeGreaterThanAndEqual(@PathVariable Integer age){
        return ResponseEntity.ok(userService.getUserByAgeGreaterThanAndEqual(age));
    }

}
