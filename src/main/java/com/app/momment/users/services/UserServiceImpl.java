package com.app.momment.users.services;

import com.app.momment.emailSender.VerificationToken;
import com.app.momment.emailSender.VerificationTokenRepository;
import com.app.momment.users.exceptionHandlers.UserAlreadyExistsException;
import com.app.momment.users.model.*;
import com.app.momment.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    VerificationTokenRepository tokenRepository;

    @Override
    public User addUser(UserRequest userRequest) throws UserAlreadyExistsException {
//        User user = userRepository.findByEmail(userRequest.getEmail());
//        if (user != null) {
//            return new ResponseEntity<>("Email already In use", HttpStatus.BAD_REQUEST);
//        }
//        User userData = new User();
//        userData.setEmail(userRequest.getEmail());
//        userData.setName(userRequest.getName());
////        String randomCode =
//        userData.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
//        userRepository.save(userData);
//        return new ResponseEntity<>("Sign Up Completed Successfully!", HttpStatus.OK);
        if (emailExists(userRequest.getEmail())){
            throw new UserAlreadyExistsException("There is an account with that email address: "
                    + userRequest.getEmail());
        }
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(this.passwordEncoder.encode(userRequest.getPassword()));
        return userRepository.save(user);
    }

    private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }

    @Override
    public User getUser(String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

    @Override
    public VerificationToken getVerificationToken(String verificationToken) {
        return tokenRepository.findByToken(verificationToken);
    }

    @Override
    public void saveRegisteredUser(User user) {
        userRepository.save(user);
    }

//    @Override
//    public void sendVerificationEmail(User user,  String url) {
//
//    }

    @Override
    public void createVerificationToken(User user, String token){
        VerificationToken tokenObj = new VerificationToken(token, user);
        tokenObj.setExpiryDate(120);
        tokenRepository.save(tokenObj);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null)   {
            String password = loginRequest.getPassword();
            String encodedPassword = user.getPassword();
            boolean isRightPassword = passwordEncoder.matches(password, encodedPassword);
            if (isRightPassword) {
                if (!user.isEnabled()) {
                    UserResponse response = new UserResponse(user.getId(),  user.getName(), user.getEmail());
                    response.setEnabled(user.isEnabled());
                    return new LoginResponse("User no verified", false, response);
                }
                Optional<User> optionalUser = userRepository.findOneByEmailAndPassword(loginRequest.getEmail(), encodedPassword);

                return optionalUser.map(value -> new LoginResponse("Login Success", true, new UserResponse(value.getId(), value.getName(), value.getEmail())))
                        .orElseGet(() -> new LoginResponse("Login failed", false, null));
            } else {
                return new LoginResponse("Incorrect Password",  false, null);
            }
        } else {
            return new LoginResponse("Incorrect email", false, null);
        }
    }
}
