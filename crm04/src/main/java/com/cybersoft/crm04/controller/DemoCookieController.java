package com.cybersoft.crm04.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/cookie")
public class DemoCookieController {

    @GetMapping("")
    public String createCookie(HttpServletResponse response, HttpServletRequest request){
//        //Tạo cookie
//        Cookie cookie = new Cookie("hello","Emlacookiene");
//        Cookie cookie1 = new Cookie("username", URLEncoder.encode("Nguyễn Văn A", StandardCharsets.UTF_8));
//
//        //server bắt client tạo cookie
//        response.addCookie(cookie);
//        response.addCookie(cookie1);

//        Lấy toàn bộ cookie client truyền lên
        Cookie[] cookies = request.getCookies();

//       Duyệt qua từng cookie
        for(Cookie cookie : cookies){
//            Lấy tên cookie đang duyệt đến
            String name = cookie.getName();
//            Lấy giá trị cookie đang duyệt đến
            String value = cookie.getValue();

            if(name.equals("hello")){
                System.out.println("Kiem tra name " + name);
                System.out.println("Kiemtra value " + value);
            }
        }

        return "login";
    }

}
