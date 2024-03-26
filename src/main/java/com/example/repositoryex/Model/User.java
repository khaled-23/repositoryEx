package com.example.repositoryex.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor
@Entity@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "name should not be empty")
    @Size(min = 4, message = "name should be at least 4 characters")
    @Pattern(regexp = "^[A-Za-z]+$")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String name;
    @NotEmpty(message = "username should not be empty")
    @Size(min = 4, max = 20, message = "username should be at least 4 characters max 20")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL UNIQUE")
    private String username;
    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, max = 20,message = "password should be at least 8 characters max 20")
    @Column(columnDefinition = "VARCHAR(20) NOT NULL")
    private String password;
    @NotEmpty(message = "email should not be empty")
    @Email(message = "provide a valid email")
    @Column(columnDefinition = "VARCHAR(30) NOT NULL UNIQUE")
    private String email;
    @NotEmpty(message = "role should not be empty")
    @Pattern(regexp = "^(user|admin)$", message = "role should be admin or user")
    @Column(columnDefinition = "VARCHAR(5) NOT NULL CHECK(role='user' OR role='admin')")
    private String role;
    @Min(value = 13, message = "user minimum age is 13")
    @Column(columnDefinition = "INT NOT NULL CHECK(AGE>=13)")
    private Integer age;

}
