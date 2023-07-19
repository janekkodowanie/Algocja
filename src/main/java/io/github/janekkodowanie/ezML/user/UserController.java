package io.github.janekkodowanie.ezML.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/users")
public class UserController {

    @GetMapping
    public ResponseEntity<User> getUsers() {
        return null;
    }

}
