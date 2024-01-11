package com.cybersoft.crm04.controller;

import com.cybersoft.crm04.entity.RolesEntity;
import com.cybersoft.crm04.repository.RolesRepository;
import com.cybersoft.crm04.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    /**
     * Bước 1 : Xác định nghiệp vụ cho chức năng
     * Bước 2 : Xác định câu truy vấn
     * Bước 3 : Xác định số lượng tham số sẽ sử dụng cho controller ( đường dẫn đã khai báo )
     * -- Xử lý nghiệp vụ thông qua code
     * Bước 4 : Để thực hiện được các câu truy vấn liên quan tới bảng đã xác định ở bước 2 => tạo ra file repository
     * để quản lý các câu truy nếu chưa có.
     * Bước 5 : Xác định hàm tương ứng với lại câu truy vấn, bước 2 của JPA
     */

    @Autowired
    private RoleService roleService;

    /**
     * save() : Có 2 chức năng vừa là thêm mới dữ liệu, vừa là cập nhật dữ liệu
     *   - Thêm mới : khóa chính của class entity truyền vào hàm save() không có giá trị khóa trị
     *   - Cập nhật : khóa chính của class entity truyền vào hàm save() có giá trị
     */

    @GetMapping("")
    public String add(){
//        RolesEntity rolesEntity = new RolesEntity();
//        rolesEntity.setName("ROLE_TEST");
//        rolesEntity.setDescription("Hello test");
//
//        rolesRepository.save(rolesEntity);

        return "role-add.html";
    }

    @PostMapping("")
    public String processAdd(@RequestParam String roleName, @RequestParam String desc){

        roleService.insertRole(roleName,desc);

        return "role-add.html";
    }

    /**
     * Thực hiện chức năng xóa role.
     * Thực hiện chức năng thêm mới thành viên.
     */

    //Yêu cầu lấy toàn bộ danh sách role và hiển thị lên giao diện role-table.html
    @GetMapping("/show")
    public String showRole(Model model){

        List<RolesEntity> listRole = roleService.getAllRole();
        model.addAttribute("listRole",listRole);

        return "role-table.html";
    }

    @GetMapping("/update/{id}")
    public String update(Model model,@PathVariable int id){

        RolesEntity rolesEntity = roleService.getRoleById(id);
        model.addAttribute("roleEntity",rolesEntity);

        return "role-update.html";
    }

    @PostMapping("/update/{id}")
    public String progressUpdate(Model model,@PathVariable int id,
                                 @RequestParam String roleName, @RequestParam String desc){

        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setId(id);
        rolesEntity.setName(roleName);
        rolesEntity.setDescription(desc);

        roleService.updateRole(rolesEntity);

        model.addAttribute("roleEntity",rolesEntity);
        return "role-update.html";
    }



    /**
     * Bước 1 : Tạo đường dẫn load giao diện thêm mới quyền
     * Bước 2 : Khi người dùng click vào role muốn chỉnh sửa thì phải gán động id vào đường dẫn để biết được người dùng
     * đang click vào role nào muốn chỉnh sửa.
     */
}
