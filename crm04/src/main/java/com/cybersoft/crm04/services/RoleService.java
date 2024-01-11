package com.cybersoft.crm04.services;

import com.cybersoft.crm04.entity.RolesEntity;
import com.cybersoft.crm04.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//Lưu ý tên service sẽ giống tên controller. Bởi vì Service là nơi xử lý logic code cho controller
@Service
public class RoleService {

    @Autowired
    private RolesRepository rolesRepository;

    public List<RolesEntity> getAllRole(){

        return rolesRepository.findAll();
    }

    public void insertRole(String nameRole, String desc){
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setName(nameRole);
        rolesEntity.setDescription(desc);

        try{
            rolesRepository.save(rolesEntity);
        }catch (Exception e){
            //Code bên trong catch chỉ được chạy khi các đoạn code bên trong try bị lỗi liên quan tới Runtime Error
            System.out.println("Lỗi thêm dữ liệu " + e.getMessage());
        }
    }

    public RolesEntity getRoleById(int idRole){
//        Optional : Có hoặc không có cũng được.
//        Optional chứa các hàm hỗ trợ sẵn giúp kiểm tra giá trị có null hay không để tránh bị lỗi null dữ liệu trong
//        quá trình xử lý
        RolesEntity dataRole = null;
        Optional<RolesEntity> rolesEntity = rolesRepository.findById(idRole);
//       isPresent : Kiểm tra xem biến có giá trị hay không nếu là true tức biến có giá trị, nếu là false thì sẽ không có
//        giá trị
        if(rolesEntity.isPresent()){
            // .get() : Giúp hủy optional đi trả về kiểu dữ liệu thực của biến
            dataRole = rolesEntity.get();
        }

        return dataRole;

    }

    public void updateRole(RolesEntity rolesEntity){
        rolesRepository.save(rolesEntity);
    }

}
