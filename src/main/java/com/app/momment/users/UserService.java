package com.app.momment.users;

import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<?> addUser(UserRequest userRequest);

    LoginResponse login(LoginRequest loginRequest);

}
