package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {
    private UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
        userService.addUser(new User("Johnny", "Silverhand", "john@gmail.com"));
        userService.addUser(new User("Alt", "Cunningham", "altier@gmail.com"));
        userService.addUser(new User("Saburo", "Arasaka", "saburo_arasaka@gmail.com"));
        userService.addUser(new User("Lucy", "Kushinada", "lucy123@gmail.com"));
        userService.addUser(new User("Judy", "Alvarez", "judy_alvarez_2077@gmail.com"));
    }

    @GetMapping(value = "/")
    public String showUsers(Model model) {
        model.addAttribute("usersToShow", userService.getUsers());
        return "users";
    }

    @GetMapping(value = "/new")
    public String newUser(Model model) {
        model.addAttribute("newUser", new User());
        return "new";
    }

    @PostMapping
    public String createUser(@ModelAttribute("newUser") User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam Long id, Model model) {
        model.addAttribute("userToEdit", userService.getUserById(id));
        return "edit";
    }

    @PostMapping(value = "/edit")
    public String updateUser(@RequestParam Long id, @ModelAttribute("userToEdit") User user) {
        userService.updateUser(user);
        return "redirect:/";
    }

    @GetMapping(value = "/delete")
    public String removeUser(@RequestParam Long id, Model model) {
        model.addAttribute("userToRemove", userService.getUserById(id));
        return "delete";
    }

    @PostMapping(value = "/delete")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
