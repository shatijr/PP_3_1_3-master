package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller()
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RoleService roleService;


    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/")
    public String findAll(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user", user);
        model.addAttribute("allRoles", roleService.findAll());
        return "admin";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam Long id, Model model) {
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("user", userService.getById(id));
        return "admin";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/";
    }
}
