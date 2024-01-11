package com.cybersoft.crm04.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        //Kiểm tra xem session lưu trữ ở login lúc đăng nhập thành công có tồn tại không
        if(session != null && session.getAttribute("email") != null && !session.getAttribute("email").equals("")){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            response.sendRedirect("http://localhost:8080/login");
        }
    }

    /**
     * Khi người dùng gọi link /role nếu có quyền ADMIN thì mới cho vào
     * còn không có quyền ADMIN thì chuyển về trang 404.html
     */
}
