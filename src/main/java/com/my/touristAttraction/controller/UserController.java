package com.my.touristAttraction.controller;

import com.my.touristAttraction.Service.JoinService;
import com.my.touristAttraction.Service.UserService;
import com.my.touristAttraction.dto.JoinDto;
import com.my.touristAttraction.dto.UserDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final JoinService joinService;

    public UserController(UserService userService, JoinService joinService) {
        this.userService = userService;
        this.joinService = joinService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/user/login";
    }

    @GetMapping("/signup")
    public String signupForm(Model model) {
        model.addAttribute("user", new JoinDto());
        return "/user/signup";
    }


    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute("user") JoinDto joinDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/user/signup";  // 검증 실패하면 다시 회원가입 폼으로
        }

        // 회원가입 처리
        joinService.joinProcess(joinDto);
        return "redirect:/user/login";  // 회원가입 후 로그인 페이지로 이동
    }


    @GetMapping("list")
    public String usersList(Model model) {
        List<UserDto> list = userService.findAllUsers();
        model.addAttribute("list", list);
        return "user/usersList";
    }

    @PostMapping("delete")
    public String usersDelete(@RequestParam("email") String email) {
        userService.deleteUsers(email);
        return "redirect:/user/list";
    }

    @GetMapping("/update")
    public String updateUsersForm(@RequestParam("email") String email,
                                  Model model) {
        UserDto users = userService.findOneUsers(email);
        model.addAttribute("users", users);
        return "/user/usersUpdate";
    }

    @PostMapping("updateUsers")
    public String updateUsers(@ModelAttribute("users") UserDto users) {
        userService.saveUser(users);
        return "redirect:/user/list";
    }
/*
   @PostMapping("/login")
    public String login(UserDto dto, HttpSession session) {
      UserDto loginResult = userService.findOneUsers(dto.getEmail());
        if (ObjectUtils.isEmpty(loginResult)) {
           return "/user/login";
       } else if (loginResult.getEmail().equals(dto.getEmail())) {
            session.setAttribute("loginEmail", dto.getEmail());
            session.setMaxInactiveInterval(60 * 30);
            return "main";
        } else {
             return "user/login";
      }
    }
    */


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "main";
    }

    @GetMapping("muInfo")
    public String muInfo(HttpSession session, Model model) {
        String myEmail = session.getAttribute("loginEmail").toString();
        UserDto user = userService.findOneUsers(myEmail);
        model.addAttribute("user", user);
        return "/user/userUpdate";
    }

    @GetMapping("myPage")
    public String myPage() {
        return "/user/myPage";
    }
}

