package com.houser.devtrac002.Controller;
import java.util.List;

import com.houser.devtrac002.model.Role;
import com.houser.devtrac002.model.User;
import com.houser.devtrac002.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/umgr")
public class UserManagerController {
    private UserService userService;
    public UserManagerController(UserService userService) {
        super();
        this.userService = userService;
    }
    // display list of users
    @GetMapping("/list")
    public String viewHomePage(Model model) {
        model.addAttribute("listUsers", userService.getAllUsers());
        return "newuser";
    }
    @GetMapping("/adduser")
    public String showAddUser(Model model) {
        User user = new User(); // create model attribute to bind form data
        model.addAttribute("user", user);
        return "newuser";
    }
    @GetMapping("/UserUpdate/{id}")
    public String UpdateUser(@PathVariable(value = "id") long id, Model model) {
        User user=userService.getUserById(id);
        List<Role>listRoles=userService.listRoles();
        model.addAttribute("user",user);
        model.addAttribute("listRoles",listRoles);

        return "updateuser";
    }
    @PostMapping("/addUser")
    public String addUserDetails(@ModelAttribute("user") User user) {
        userService.save(user); // save user details to database
        return "redirect:/umgr/list";
    }
    @PostMapping("/saveUserdets")
    public String saveUserPrivileges(@ModelAttribute("user") User user) {
        //userService.SaveUser(user); // save user details to database
        return "redirect:/umgr/list";
    }
    @GetMapping("/deleteUser/{id}")
    public String removeUser(@PathVariable(value = "id") long id) {
        this.userService.deleteUserById(id); // call delete user method
        return "redirect:/umgr/list";
    }
    @ModelAttribute("user")
    public User userRegistrationDto() {
        return new User();
    }
    @GetMapping
    public String showRegistrationForm() {
        return "registration";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") User registrationDto) {
        userService.save(registrationDto);
        return "redirect:/registration?success";
    }
}
