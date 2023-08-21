package com.app.momment.users.services;

import com.app.momment.emailSender.VerificationToken;
import com.app.momment.users.exceptionHandlers.UserAlreadyExistsException;
import com.app.momment.users.model.LoginRequest;
import com.app.momment.users.model.LoginResponse;
import com.app.momment.users.model.User;
import com.app.momment.users.model.UserRequest;

public interface UserService {

    User addUser(UserRequest userRequest) throws UserAlreadyExistsException;

    LoginResponse login(LoginRequest loginRequest);

//    void sendVerificationEmail(User use, String siteUrl);

    User getUser(String verificationToken);

    void saveRegisteredUser(User user);

    void createVerificationToken(User user, String token);

    VerificationToken getVerificationToken(String verificationToken);

}
