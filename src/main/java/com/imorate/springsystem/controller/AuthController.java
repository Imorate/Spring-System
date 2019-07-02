package com.imorate.springsystem.controller;

import com.imorate.springsystem.configuration.AuthExceptionHandler;
import com.imorate.springsystem.configuration.validator.StudentValidator;
import com.imorate.springsystem.configuration.validator.UserProfileValidator;
import com.imorate.springsystem.configuration.validator.UserValidator;
import com.imorate.springsystem.model.auth.User;
import com.imorate.springsystem.service.auth.UserService;
import com.imorate.springsystem.service.common.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    private final UserService userService;
    private final AuthExceptionHandler authExceptionHandler;
    private final UserValidator userValidator;
    private final StudentValidator studentValidator;
    private final DepartmentService departmentService;
    private final UserProfileValidator userProfileValidator;

    public AuthController(UserService userService, AuthExceptionHandler authExceptionHandler, UserValidator userValidator, DepartmentService departmentService, StudentValidator studentValidator, UserProfileValidator userProfileValidator) {
        this.userService = userService;
        this.authExceptionHandler = authExceptionHandler;
        this.userValidator = userValidator;
        this.departmentService = departmentService;
        this.studentValidator = studentValidator;
        this.userProfileValidator = userProfileValidator;
    }

    @GetMapping("/signup")
    public String registration(Model model) {
        model.addAttribute("formUser", new User());
        model.addAttribute("departmentList", departmentService.findAll());
        return "auth/signup";
    }

    @PostMapping("/signup")
    public String registration(@ModelAttribute("formUser") User formUser, BindingResult bindingResult) {
        userValidator.validate(formUser, bindingResult);
        studentValidator.validate(formUser.getStudent(),bindingResult);
        userProfileValidator.validate(formUser.getUserProfile(),bindingResult);

        if (bindingResult.hasErrors()) {
            return "auth/signup";
        }
        userService.save(formUser);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(Model model, @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            switch (authExceptionHandler.getAuthErrorMessage()) {
                case 0:
                    model.addAttribute("errMessage", "نام کاربری یا رمز عبور اشتباه است");
                    break;
                case 1:
                    model.addAttribute("errMessage",
                            "حساب کاربری شما به دلایلی قفل شده است. لطفا برای حل مشکل با مدیریت سامانه تماس بگیرید");
                    break;
                case 2:
                    model.addAttribute("errMessage", "در فرایند به خاطر سپردن ورود مشکلی پیش آمده است");
                    break;
                case 3:
                    model.addAttribute("errMessage",
                            "حساب کاربری شما منقضی شده است. لطفا برای حل مشکل با مدیریت سامانه تماس بگیرید");
                    break;
                case 4:
                    model.addAttribute("errMessage",
                            "اعتبار حساب کاربری شما به اتمام رسیده است. لطفا برای حل مشکل با مدیریت سامانه تماس بگیرید");
                    break;
                case 5:
                    model.addAttribute("errMessage",
                            "برای حساب کاربری شما مشکلی به آمده است. برای حساب کاربری شما مشکلی به وجود آمده است. لطفا برای حل مشکل با مدیریت سامانه تماس بگیرید");
                    break;
            }
        }
        return "auth/login";
    }

}
