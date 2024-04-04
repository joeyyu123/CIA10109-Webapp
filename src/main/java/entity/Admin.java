package entity;

import javax.persistence.*;

@Entity
@Table(name = "admin", schema = "ezban")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no", nullable = false)
    private Integer adminno;

    @Column(name = "admin_account", length = 20)
    private String adminAccount;

    @Column(name = "admin_pwd", length = 20)
    private String adminPwd;

    @Column(name = "admin_mail", length = 50)
    private String adminMail;

    @Column(name = "admin_phone", length = 15)
    private String adminPhone;

    @Column(name = "admin_status")
    private Byte adminStatus;

    public Integer getAdminno() {
        return adminno;
    }

    public void setAdminno(Integer adminno) {
        this.adminno = adminno;
    }

    public String getAdminAccount() {
        return adminAccount;
    }

    public void setAdminAccount(String adminAccount) {
        this.adminAccount = adminAccount;
    }

    public String getAdminPwd() {
        return adminPwd;
    }

    public void setAdminPwd(String adminPwd) {
        this.adminPwd = adminPwd;
    }

    public String getAdminMail() {
        return adminMail;
    }

    public void setAdminMail(String adminMail) {
        this.adminMail = adminMail;
    }

    public String getAdminPhone() {
        return adminPhone;
    }

    public void setAdminPhone(String adminPhone) {
        this.adminPhone = adminPhone;
    }

    public Byte getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Byte adminStatus) {
        this.adminStatus = adminStatus;
    }

}