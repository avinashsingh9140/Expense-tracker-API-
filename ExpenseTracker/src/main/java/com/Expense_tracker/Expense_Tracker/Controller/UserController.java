package com.Expense_tracker.Expense_Tracker.Controller;

import com.Expense_tracker.Expense_Tracker.Model.DTO.AuthInput;
import com.Expense_tracker.Expense_Tracker.Model.DTO.SignIn_Authentication;
import com.Expense_tracker.Expense_Tracker.Model.User;
import com.Expense_tracker.Expense_Tracker.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //   sign in
    @PostMapping("/SignUp")
    private String addUser(@Valid @RequestBody User newUser){
       return userService.addUser(newUser);
    }

    //    sign in

    @PostMapping("signIn")
    public String userSignIn(@Valid @RequestBody SignIn_Authentication signInInput)
    {
        return userService.userSignIn(signInInput);
    }

    //sign out
    @DeleteMapping("signOut")
    public String userSignOut(@Valid @RequestBody AuthInput authInput)
    {
        return userService.userSignOut(authInput);
    }
}
