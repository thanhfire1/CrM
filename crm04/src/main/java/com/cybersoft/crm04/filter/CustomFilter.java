package com.cybersoft.crm04.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        System.out.println("Kiem tra filter " + request.getServletPath());
        HttpSession session = request.getSession();

        //Kiểm tra xem session lưu trữ ở login lúc đăng nhập thành công có tồn tại không
        if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")){
            //Chuyển hướng về trang chủ
            response.sendRedirect("http://localhost:8080");
        }else{
            //Cho đi tiếp vào đường dẫn mà client đang gọi hoặc thoát ra khỏi filter và đi tiếp
            filterChain.doFilter(servletRequest,servletResponse);
        }

        //Lấy đường dẫn người dùng đang gọi mà kích hoạt filter
//        String path = request.getServletPath();

        //Cách 2 :
        // Kiểm tra xem session lưu trữ ở login lúc đăng nhập thành công có tồn tại không
//        if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")){
//            if(path.equals("/login")){
//                //Đăng nhập thành công chuyển hướng về trang chủ
//                response.sendRedirect("http://localhost:8080");
//            }else if(path.equals("/role")){
//                filterChain.doFilter(servletRequest,servletResponse);
//            }
//        }else{
//            //Cho đi tiếp vào đường dẫn mà client đang gọi hoặc thoát ra khỏi filter và đi tiếp
//            if(path.equals("/login")){
//                //Đăng nhập thành công chuyển hướng về trang chủ
//                filterChain.doFilter(servletRequest,servletResponse);
//            }else if(path.equals("/role")){
//                response.sendRedirect("http://localhost:8080/login");
//            }
//
//        }
//      Cách 1 :
//        if(path.equals("/login")){
//            //Kiểm tra xem session lưu trữ ở login lúc đăng nhập thành công có tồn tại không
//            if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")){
//                //Chuyển hướng về trang chủ
//                response.sendRedirect("http://localhost:8080");
//            }else{
//                //Cho đi tiếp vào đường dẫn mà client đang gọi hoặc thoát ra khỏi filter và đi tiếp
//                filterChain.doFilter(servletRequest,servletResponse);
//            }
//        }else if(path.equals("/role")){
//            if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")){
//                //Đã đăng nhập
//                filterChain.doFilter(servletRequest,servletResponse);
//            }else{
//                //Chưa đăng nhập
//                response.sendRedirect("http://localhost:8080/login");
//            }
//        }

    }

    /**
     * Nếu vào link là /role mà chưa đăng nhập thì sẽ chuyển về trang đăng nhập
     *
     */
}
