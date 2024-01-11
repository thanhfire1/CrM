package com.cybersoft.crm04.entity;

import jakarta.persistence.*;

/**
 * Để mapping khóa ngoại trong Entity
 * - Bước 1 : Xem khóa chính 2 bảng đang quan hệ với nhau có phải là tự động tăng hay không. Nếu
 * tự động tặng thì không là OneToOne => OneToMany
 * - Bước 2 : Nếu khóa chính không tự động tăng và vừa là khóa chính và khóa ngoại => OneToOne
 *
 *
 *  (*) : OneToMany : Entity nào giữa khóa ngoại thì sẽ có 2 Annotation sau đây
 *  - @ManyToOne và @JoinColumn
 *  Bảng được được tham chiếu khóa ngoại sẽ map ngược lại
 *  - @OneToMany
 */

@Entity(name = "users")
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "avatar")
    private String avatar;

//    @Column(name = "role_id")
//    private int roleId;

    @ManyToOne
    @JoinColumn(name = "role_id") //tên cột khóa ngoại trong database dùng để liên kết dữ liệu
    private RolesEntity rolesEntity; //Dựa vào chữ đằng sau OneToMany hay ManyToOne thì sẽ biết được
//    là một đối tượng hay một list đối tượng


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public RolesEntity getRolesEntity() {
        return rolesEntity;
    }

    public void setRolesEntity(RolesEntity rolesEntity) {
        this.rolesEntity = rolesEntity;
    }
}
