package com.joey;

import entity.Admin;
import util.JDBCUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdminDaoJDBCImpl implements AdminDao {

    private static final String INSERT_STMT = "INSERT INTO admin(admin_account, admin_pwd, admin_mail, admin_phone, admin_status) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_STMT = "UPDATE admin SET admin_account = ?, admin_pwd = ?, admin_mail = ?, admin_phone = ?, admin_status = ? WHERE admin_no = ?";
    private static final String DELETE_STMT = "DELETE FROM admin WHERE admin_no = ?";
    private static final String FIND_BY_PK = "SELECT * FROM admin WHERE admin_no = ?";
    private static final String GET_ALL = "SELECT * FROM admin";


    static {
        try {
            Class.forName(JDBCUtil.DRIVER);
        } catch (ClassNotFoundException ce) {
            ce.printStackTrace();
        }
    }

    @Override
    public int insert(Admin admin) {


        try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = con.prepareStatement(INSERT_STMT)) {

            ps.setString(1, admin.getAdminAccount());
            ps.setString(2, admin.getAdminPwd());
            ps.setString(3, admin.getAdminMail());
            ps.setString(4, admin.getAdminPhone());
            ps.setByte(5, admin.getAdminStatus());
            return ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int update(Admin admin) {
        try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = con.prepareStatement(UPDATE_STMT)) {
            ps.setString(1, admin.getAdminAccount());
            ps.setString(2, admin.getAdminPwd());
            ps.setString(3, admin.getAdminMail());
            ps.setString(4, admin.getAdminPhone());
            ps.setByte(5, admin.getAdminStatus());
            ps.setInt(6, admin.getAdminno());

            return ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public int delete(Integer adminno) {
        try (Connection con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
             PreparedStatement ps = con.prepareStatement(DELETE_STMT)) {
            ps.setInt(1, adminno);

            return ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Admin findByPK(Integer adminno) {
        Admin admin = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
            ps = con.prepareStatement(FIND_BY_PK);

            ps.setInt(1, adminno);
            rs = ps.executeQuery();

            while (rs.next()) {
                admin = new Admin();
                admin.setAdminno(rs.getInt("admin_no"));
                admin.setAdminAccount(rs.getString("admin_account"));
                admin.setAdminPwd(rs.getString("admin_pwd"));
                admin.setAdminMail(rs.getString("admin_mail"));
                admin.setAdminPhone(rs.getString("admin_phone"));
                admin.setAdminStatus(rs.getByte("admin_status"));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } finally {
            closeResources(con, ps, rs);
        }
        return admin;
    }

    @Override
    public List<Admin> getAll() {
        List<Admin> admins = new ArrayList<>();
        Admin admin = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = DriverManager.getConnection(JDBCUtil.URL, JDBCUtil.USER, JDBCUtil.PASSWORD);
            ps = con.prepareStatement(GET_ALL);
            rs = ps.executeQuery();

            while (rs.next()) {
                admin = new Admin();
                admin.setAdminno(rs.getInt("admin_no"));
                admin.setAdminAccount(rs.getString("admin_account"));
                admin.setAdminPwd(rs.getString("admin_pwd"));
                admin.setAdminMail(rs.getString("admin_mail"));
                admin.setAdminPhone(rs.getString("admin_phone"));
                admin.setAdminStatus(rs.getByte("admin_status"));

                admins.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources(con,ps,rs);
        }

        return admins;
    }

    private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException se) {
                se.printStackTrace(System.err);
            }
        }
        if (con != null) {
            try {
                con.close();
            } catch (Exception e) {
                e.printStackTrace(System.err);
            }
        }
    }
}
