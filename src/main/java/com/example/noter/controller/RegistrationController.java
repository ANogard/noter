package com.example.noter.controller;

import com.example.noter.model.Role;
import com.example.noter.model.User;
import com.example.noter.repos.UserRepository;
import java.util.Collections;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

  private final UserRepository userRepository;

  public RegistrationController(UserRepository userRepository){
    this.userRepository = userRepository;
  }

  @GetMapping("/registration")
  public String registration(Authentication authentication){
    if(authentication == null){
      return "registration";
    }
    return "redirect:/";
  }

  @PostMapping("/registration")
  public String registerUser(@RequestParam String username, @RequestParam String password,
      @RequestParam String passwordRepeat, Model model, HttpServletRequest request){
    if(userRepository.findByUsername(username) != null){ //Не реализована проверка email by regex
      model.addAttribute("userExists", true);
      return "registration";
    }
    if(!password.equals(passwordRepeat)){ //Не реализована проверка на отсутствие пароль
      model.addAttribute("passwordError", true);
      return "registration";
    }
    User user = new User(username, password);
    user.setRoles(Collections.singleton(Role.USER));
    userRepository.save(user);
    try {
      request.login(username, password);
    } catch (ServletException e) {
      e.printStackTrace();
    }
    return "redirect:/";
  }

  @GetMapping("/login")
  public String login(Authentication authentication){
    if(authentication == null){
      return "login";
    }
    return "redirect:/";
  }
}
