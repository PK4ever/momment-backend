package com.app.momment.users.controller;

import com.app.momment.emailSender.VerificationToken;
import com.app.momment.users.exceptionHandlers.UserAlreadyExistsException;
import com.app.momment.users.listeners.OnRegistrationCompleteEvent;
import com.app.momment.users.model.*;
import com.app.momment.users.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Calendar;
import java.util.Locale;

@RestController
//@CrossOrigin
@RequestMapping("api/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    MessageSource messageSource;

    @PostMapping("signup")
    public ResponseEntity<?> signUpUser(@RequestBody UserRequest userRequest, HttpServletRequest httpServletRequest){
        User registeredUser;
        try {
            registeredUser = userService.addUser(userRequest);
            String appUrl = httpServletRequest.getRequestURL().toString();
//            URI currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri()
//                    .build()
//                    .toUri();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registeredUser, httpServletRequest.getLocale(), appUrl.replace("signup","registrationConfirmation")));
        } catch (UserAlreadyExistsException uaeEx) {
            return new ResponseEntity<>("An account for that email already exists", HttpStatus.BAD_REQUEST);
        } catch (RuntimeException ex) {
            return new ResponseEntity<>("Email Error" + ex, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(new SignupResponseObj(registeredUser.getName(), registeredUser.getEmail(),  registeredUser.isEnabled()), HttpStatus.OK);
    }

    @GetMapping("/registrationConfirmation")
    public String confirmRegistration(WebRequest request, Model model,
                                      @RequestParam("token") String token) {
        Locale locale = request.getLocale();
        VerificationToken verificationToken = userService.getVerificationToken(token);
        if (verificationToken == null) {
            String message = this.messageSource.getMessage("auth.message.invalidToken", null, locale);
            model.addAttribute("message", message);
            return "redirect:/badUser.html?lang="+locale.getLanguage();
        }
        User user =  verificationToken.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() -
                cal.getTime().getTime() <= 0)) {
            String messageValue = messageSource.getMessage("auth.message.expired",  null, locale);
            model.addAttribute("message", messageValue);
            return "redirect:/badUser.html?lang=" + locale.getLanguage();
        }

        user.setEnabled(true);
        userService.saveRegisteredUser(user);
        return "redirect:/login.html?lang=" + request.getLocale().getLanguage();
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        LoginResponse  loginResponse = userService.login(loginRequest);
        return ResponseEntity.ok(loginResponse);
    }
}
