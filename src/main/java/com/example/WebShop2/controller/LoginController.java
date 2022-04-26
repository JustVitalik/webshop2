package com.example.WebShop2.controller;

import com.example.WebShop2.model.Role;
import com.example.WebShop2.model.Users;
import com.example.WebShop2.repository.RoleRepository;
import com.example.WebShop2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    RoleRepository rolerepository;
    @Autowired
    UserRepository userRepository;


    @Autowired
    RoleRepository repository;

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }
    @GetMapping("/register")
    public String registerGet(){
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(@ModelAttribute("users")Users users, HttpServletRequest request) throws ServletException {
        String password= users.getPsssword();
        users.setPsssword(bCryptPasswordEncoder.encode(password));
        List<Role> roles = new ArrayList<>();
        roles.add(rolerepository.findById(2L).get());
        users.setRoles(roles);
        userRepository.save(users);
        request.login(users.getEmail(),password);
        return "redirect:/";
    }
}
