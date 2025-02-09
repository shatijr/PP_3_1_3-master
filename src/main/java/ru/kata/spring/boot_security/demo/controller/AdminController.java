package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
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
    public String findAll(Model model){
        model.addAttribute("users", userService.getAll());
        return "index";
    }

    @GetMapping("/new")
    public String newUser (Model model){
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("user", new User());
        return "user-create";
    }

    @PostMapping("/new")
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/admin/";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam Long id) {
        userService.delete(id);
        return "redirect:/admin/";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("allRoles", roleService.findAll());
        model.addAttribute("user", user);
        return "update";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user) {
        userService.update(user);
        return "redirect:/admin/";
    }
}
