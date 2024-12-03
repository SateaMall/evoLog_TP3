package com.example.evoLog_TP3.controller;
@org.springframework.web.bind.annotation.RestController
@org.springframework.web.bind.annotation.RequestMapping("/api/users")
public class UserController {
    @org.springframework.beans.factory.annotation.Autowired
    private com.example.evoLog_TP3.service.UserService userService;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(com.example.evoLog_TP3.controller.UserController.class);

    @org.springframework.web.bind.annotation.PostMapping("/register")
    public org.springframework.http.ResponseEntity<?> registerUser(@org.springframework.validation.annotation.Validated
    @org.springframework.web.bind.annotation.RequestBody
    com.example.evoLog_TP3.model.User user) {
        try {
            com.example.evoLog_TP3.model.User createdUser = userService.registerUser(user);
            createdUser.setPassword(null);// Hide password in response

            com.example.evoLog_TP3.controller.UserController.logger.info("User registered successfully: {}", createdUser.getEmail());
            return new org.springframework.http.ResponseEntity<>(createdUser, org.springframework.http.HttpStatus.CREATED);
        } catch (java.lang.RuntimeException ex) {
            return new org.springframework.http.ResponseEntity<>(ex.getMessage(), org.springframework.http.HttpStatus.BAD_REQUEST);
        }
    }

    @org.springframework.web.bind.annotation.PostMapping("/login")
    public org.springframework.http.ResponseEntity<?> loginUser(@org.springframework.web.bind.annotation.RequestBody
    com.example.evoLog_TP3.model.User user) {
        try {
            com.example.evoLog_TP3.model.User authenticatedUser = userService.authenticateUser(user.getEmail(), user.getPassword());
            authenticatedUser.setPassword(null);// Hide password in response

            com.example.evoLog_TP3.controller.UserController.logger.info("User connected successfully: {}", authenticatedUser.getEmail());
            return new org.springframework.http.ResponseEntity<>(authenticatedUser, org.springframework.http.HttpStatus.OK);
        } catch (java.lang.RuntimeException ex) {
            return new org.springframework.http.ResponseEntity<>(ex.getMessage(), org.springframework.http.HttpStatus.UNAUTHORIZED);
        }
    }
}
