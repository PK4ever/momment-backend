package com.app.momment.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserRequest userRequest){
          return userService.addUser(userRequest);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse  loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
