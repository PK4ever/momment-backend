package com.app.momment.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<?> addUser(UserRequest userRequest) {
        User user = userRepository.findByEmail(userRequest.getEmail());
        if (user != null) {
            return new ResponseEntity<>("Email already In use", HttpStatus.BAD_REQUEST);
        }
        User userData = new User();
        userData.setEmail(userRequest.getEmail());
        userData.setName(userRequest.getName());
        userData.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        userRepository.save(userData);
        return new ResponseEntity<>("Sign Up Completed Successfully!", HttpStatus.OK);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null)   {
            String password = loginRequest.getPassword();
            String encodedPassword = user.getPassword();
            boolean isRightPassword = passwordEncoder.matches(password, encodedPassword);
            if (isRightPassword) {
                Optional<User> optionalUser = userRepository.findOneByEmailAndPassword(loginRequest.getEmail(), encodedPassword);
                if (optionalUser.isPresent()) {
                    return new LoginResponse("Login Success", true);
                } else {
                    return new LoginResponse("Login failed", false);
                }
            } else {
                return new LoginResponse("Incorrect Password",  false);
            }
        } else {
            return new LoginResponse("Incorrect email", false);
        }
    }
}
