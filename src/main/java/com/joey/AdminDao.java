package com.joey;

import entity.Admin;

import java.util.List;

/**
 * 此介面定義對admin的相關存取抽象方法
 */

public interface AdminDao {


    int insert(Admin admin);
    int update(Admin admin);
    int delete(Integer adminno);
    Admin findByPK(Integer adminno);
    List<Admin> getAll();
}
