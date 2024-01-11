package com.cybersoft.crm04.controller;

import com.cybersoft.crm04.entity.UsersEntity;
import com.cybersoft.crm04.repository.UsersRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Các bước cần làm cho một chức năng trong ứng dụng
 * Bước 1 : Phân tích yêu cầu của chức năng, tức là phần tích chức đó mình cần làm gì và kết quả mong muốn
 * là gì.
 * Bước 2 : Xác định được câu truy vấn ( query ) giành cho chức năng đó
 * Bước 3 : Từ câu truy vấn sẽ xác định được đường dẫn có nhận tham hay không và số số lượng tham số là
 * bao nhiêu.
 */

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsersRepository usersRepository;

    @GetMapping("")
    public String login(HttpServletRequest request, Model model){
//        List<UsersEntity> list = usersRepository.findByEmailAndPassword("nguyenvana@gmail.com","123456");
//        for(UsersEntity item : list){
//            System.out.println("Kiemtra " + item.getEmail());
//        }

        Cookie[] cookies = request.getCookies();
        String email  = "";
        String password = "";
        if(cookies != null && cookies.length > 0){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("email")){
                    email = cookie.getValue();
                }

                if(cookie.getName().equals("password")){
                    password = cookie.getValue();
                }

            }
        }

        model.addAttribute("email",email);
        model.addAttribute("password",password);

        return "login";
    }

    //Controller : Nơi định nghĩa link
    //Model : cho phép trả giá trị từ java ra file HTML (View)
    //View : Chính là file html
    @PostMapping("")
    public String progressLogin(HttpSession httpSession,HttpServletResponse response, Model model, @RequestParam String email, @RequestParam String password){
        /**
         * Hoàn thiện chức năng login
         * Bước 1 : Thế tham số người dùng truyền vào hàm findByEmailAndPassword
         *  - Làm cách nào lấy được tham số ?
         *  - Bên html làm cách nào có thể gọi được link /login với phương thức post ?
         *  - Làm cách nào truyền email và password ?
         * Bước 2 : kiểm tra xem list có dữ liệu hay không ?
         * Bước 3 : Nếu có thì trả ra chuyển qua trang dashboard ( Lưu tạo link /dashboard sử dụng page index.html ).
         * Bước 4 : Nếu thất bại thì xuất thông báo "Đăng nhập thất bại" ra màn hình login
         *   - Làm cách nào để trả giá trị của biến trong java ra file html ?
         *   - Làm cách nào lấy được giá trị mà java truyền ra file html ?
         * Lưu ý : Phương thức post => Chỉnh form data bên giao diện login
         */
        List<UsersEntity> listUser = usersRepository.findByEmailAndPassword(email,password);
        boolean isSuccess = false;
//        Kiểm tra xem danh sách users có giá trị hay không
        if(listUser.size() > 0){
            //Có giá trị => đăng nhập thành công
            isSuccess = true;

            httpSession.setAttribute("email",email);
            httpSession.setMaxInactiveInterval(8 * 60 * 60);

//            Cookie cookieEmail = new Cookie("email",email);
//            Cookie cookiePassword = new Cookie("password",password);
//
//            response.addCookie(cookieEmail);
//            response.addCookie(cookiePassword);

        }else{
            //Không có giá trị => đăng nhập thất bại
            isSuccess = false;
        }
//      Đẩy giá trị của biến isSuccess ra file html và đặt tên key (biến) là isSuccess
        model.addAttribute("isSuccess",isSuccess);
//        System.out.println("Kiemtra " + email + " - " + password);

        return "login";
    }

    /**
     * Khi đăng nhập thành công thì lưu email và mật khẩu vào Cookie
     * Khi người dùng vô lại link /login thì sẽ điền sẵn email và mật khẩu vào input
     */
}
