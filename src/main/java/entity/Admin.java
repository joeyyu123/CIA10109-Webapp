package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "admin", schema = "ezban")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "admin_no", nullable = false)
    private Integer adminNo;

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

    public Admin() {
    }

    public Admin(Integer adminNo, String adminAccount, String adminPwd, String adminMail, String adminPhone, Byte adminStatus) {
        this.adminNo = adminNo;
        this.adminAccount = adminAccount;
        this.adminPwd = adminPwd;
        this.adminMail = adminMail;
        this.adminPhone = adminPhone;
        this.adminStatus = adminStatus;
    }

    public Integer getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(Integer adminNo) {
        this.adminNo = adminNo;
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


    @Override
    public String toString() {
        return "Admin{" +
                "adminno=" + adminNo +
                ", adminAccount='" + adminAccount + '\'' +
                ", adminPwd='" + adminPwd + '\'' +
                ", adminMail='" + adminMail + '\'' +
                ", adminPhone='" + adminPhone + '\'' +
                ", adminStatus=" + adminStatus +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(adminNo, admin.adminNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminNo);
    }
}