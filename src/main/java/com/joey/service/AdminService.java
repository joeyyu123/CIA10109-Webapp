package com.joey.service;

import com.joey.AdminDao;
import com.joey.AdminDaoJDBCImpl;
import entity.Admin;

import java.util.List;

public class AdminService {
    private AdminDao dao;

    public AdminService() {
        dao = new AdminDaoJDBCImpl();
    }

    public Admin add(String adminAccount, String adminPwd, String adminMail,
                        String  adminPhone, Byte adminStatus) {

        Admin admin = new Admin();

        admin.setAdminAccount(adminAccount);
        admin.setAdminPwd(adminPwd);
        admin.setAdminMail(adminMail);
        admin.setAdminPhone(adminPhone);
        admin.setAdminStatus(adminStatus);
        dao.insert(admin);

        return admin;
    }

    public Admin update(Integer adminno,String adminAccount, String adminPwd, String adminMail,
                     String  adminPhone, Byte adminStatus) {

        Admin admin = new Admin();

        admin.setAdminNo(adminno);
        admin.setAdminAccount(adminAccount);
        admin.setAdminPwd(adminPwd);
        admin.setAdminMail(adminMail);
        admin.setAdminPhone(adminPhone);
        admin.setAdminStatus(adminStatus);
        dao.insert(admin);

        return admin;
    }

    public void delete(Integer adminno) {
        dao.delete(adminno);
    }

    public Admin findByPK(Integer adminno) {
        return dao.findByPK(adminno);
    }

    public List<Admin> getAll() {
        return dao.getAll();
    }

}
