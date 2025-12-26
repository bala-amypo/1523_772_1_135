// // package com.example.demo.controller;


// // import com.example.demo.entity.User;
// // import com.example.demo.service.UserService;
// // import org.springframework.web.bind.annotation.RestController;
// // import org.springframework.web.bind.annotation.RequestMapping;
// // import org.springframework.web.bind.annotation.PostMapping;
// // import org.springframework.web.bind.annotation.GetMapping;
// // import org.springframework.web.bind.annotation.PathVariable;
// // import org.springframework.web.bind.annotation.RequestBody;

// // import java.util.List;

// // @RestController
// // @RequestMapping("/api/users")
// // public class UserController {

// //     private final UserService userService;

// //     public UserController(UserService userService) {
// //         this.userService = userService;
// //     }

// //     @PostMapping("/register")
// //     public User register(@RequestBody User user) {
// //         return userService.registerUser(user);
// //     }

// //     @GetMapping("/{id}")
// //     public User getUser(@PathVariable Long id) {
// //         return userService.getUserById(id);
// //     }

// //     @GetMapping
// //     public List<User> getAllUsers() {
// //         return userService.getAllUsers();
// //     }
// // }
// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.service.UserService;

// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/users")
// public class UserController {

//     private final UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     // ✅ FIXED: registerUser → register
//     @PostMapping
//     public User register(@RequestBody User user) {
//         return userService.register(user);
//     }

//     // ✅ FIXED: getUserById → getById
//     @GetMapping("/{id}")
//     public User getById(@PathVariable Long id) {
//         return userService.getById(id);
//     }

//     @GetMapping
//     public List<User> getAllUsers() {
//         return userService.getAllUsers();
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User register(@RequestBody User user) {
        return userService.register(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
