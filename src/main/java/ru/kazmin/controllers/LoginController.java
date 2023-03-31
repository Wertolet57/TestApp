package ru.kazmin.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginPage() {
        System.out.println("login");
        return "login";
    }

    @GetMapping("/user/1")
    public String printUsers(Model model) {
        System.out.println("user");
        model.addAttribute("user", "user");
        return "index";
    }

    @GetMapping("/admin/1")
    public String printUserss(Model model) {
        System.out.println("admin");
        model.addAttribute("user", "admin");
        return "index";
    }
}
