package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.poly.global.GlobalData;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(){
        GlobalData.cart.clear();
        return "login";
    }
}
