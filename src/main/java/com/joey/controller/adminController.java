package com.joey.controller;

import com.joey.service.AdminService;
import entity.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/admin/admin.do")
public class adminController extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        doPost(req, res);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");

        if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            String str = req.getParameter("adminno");
            if (str == null || (str.trim()).length() == 0) {
                errorMsgs.add("請輸入管理員編號");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/admin/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            Integer adminno = null;
            try {
                adminno = Integer.valueOf(str);
            } catch (Exception e) {
                errorMsgs.add("管理員編號不正確");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/admin/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************2.開始查詢資料*****************************************/
            AdminService adminService = new AdminService();
            Admin admin = adminService.findByPK(adminno);
            if (admin == null) {
                errorMsgs.add("查無資料");
            }
            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/admin/select_page.jsp");
                failureView.forward(req, res);
                return;//程式中斷
            }

            /***************************3.查詢完成,準備轉交(Send the Success view)*************/
            req.setAttribute("admin", admin); // 資料庫取出的empVO物件,存入req
            String url = "/admin/listOneAdmin.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
            successView.forward(req, res);
        }

        if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數****************************************/
            Integer adminno = Integer.valueOf(req.getParameter("adminno"));

            /***************************2.開始查詢資料****************************************/
            AdminService adminSvc = new AdminService();
            Admin admin = adminSvc.findByPK(adminno);

            /***************************3.查詢完成,準備轉交(Send the Success view)************/
            req.setAttribute("admin", admin);         // 資料庫取出的empVO物件,存入req
            String url = "/admin/update_admin_input.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_admin_input.jsp
            successView.forward(req, res);
        }

        if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
            Integer adminno = Integer.valueOf(req.getParameter("adminno").trim());

            String adminAccount = req.getParameter("adminAccount");
            String accountReg = "^[(a-zA-Z0-9_)]{2,10}$";
            if ( adminAccount.trim().isEmpty()) {
                errorMsgs.add("員工帳號: 請勿空白");
            } else if (!adminAccount.trim().matches(accountReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String adminPwd = req.getParameter("adminPwd").trim();
            if ( adminPwd.trim().isEmpty()) {
                errorMsgs.add("密碼請勿空白");
            }

            String adminMail = req.getParameter("adminMail").trim();
            if ( adminMail.trim().isEmpty()) {
                errorMsgs.add("信箱請勿空白");
            }

            String adminPhone = req.getParameter("adminPhone");
            String phoneReg = "^[(0-9_)]{10}$";
            if (adminPhone == null || adminPhone.trim().isEmpty()) {
                errorMsgs.add("員工電話: 請勿空白");
            } else if (!adminPhone.trim().matches(phoneReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工電話: 只能是數字 , 且長度10");
            }

            byte status;
            try {
                status = Byte.parseByte(req.getParameter("adminStatus").trim());
            } catch (NumberFormatException e) {
                status = 0;
                errorMsgs.add("狀態請填數字.");
            }


            Admin admin = new Admin();
            admin.setAdminNo(adminno);
            admin.setAdminAccount(adminAccount);
            admin.setAdminPwd(adminPwd);
            admin.setAdminMail(adminMail);
            admin.setAdminPhone(adminPhone);
            admin.setAdminStatus(status);


            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("admin", admin); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/admin/update_admin_input.jsp");
                failureView.forward(req, res);
                return; //程式中斷
            }

            /***************************2.開始修改資料*****************************************/
            AdminService adminSvc = new AdminService();

            admin = adminSvc.update(adminno, adminAccount, adminPwd, adminMail, adminPhone, status);

            /***************************3.修改完成,準備轉交(Send the Success view)*************/
            req.setAttribute("admin", admin); // 資料庫update成功後,正確的的empVO物件,存入req
            String url = "/admin/listOneAdmin.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
            successView.forward(req, res);
        }


        if ("delete".equals(action)) { // 來自listAllEmp.jsp

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***************************1.接收請求參數***************************************/
            Integer adminno = Integer.valueOf(req.getParameter("adminno"));

            /***************************2.開始刪除資料***************************************/
            AdminService adminSvc = new AdminService();
            adminSvc.delete(adminno);

            /***************************3.刪除完成,準備轉交(Send the Success view)***********/
            String url = "/admin/listAllAdmin.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
            successView.forward(req, res);
        }

        if ("insert".equals(action)) { // 來自addEmp.jsp的請求

            List<String> errorMsgs = new LinkedList<String>();
            // Store this set in the request scope, in case we need to
            // send the ErrorPage view.
            req.setAttribute("errorMsgs", errorMsgs);

            /***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
            String adminAccount = req.getParameter("adminAccount");
            String accountReg = "^[(a-zA-Z0-9_)]{2,10}$";
            if ( adminAccount.trim().isEmpty()) {
                errorMsgs.add("員工帳號: 請勿空白");
            } else if (!adminAccount.trim().matches(accountReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工帳號: 只能是英文字母、數字和_ , 且長度必需在2到10之間");
            }

            String adminPwd = req.getParameter("adminPwd").trim();
            if ( adminPwd.trim().isEmpty()) {
                errorMsgs.add("密碼請勿空白");
            }

            String adminMail = req.getParameter("adminMail").trim();
            if ( adminMail.trim().isEmpty()) {
                errorMsgs.add("信箱請勿空白");
            }

            String adminPhone = req.getParameter("adminPhone");
            String phoneReg = "^[(0-9_)]{10}$";
            if (adminPhone == null || adminPhone.trim().isEmpty()) {
                errorMsgs.add("員工電話: 請勿空白");
            } else if (!adminPhone.trim().matches(phoneReg)) { //以下練習正則(規)表示式(regular-expression)
                errorMsgs.add("員工電話: 只能是數字 , 且長度10");
            }

            byte status;
            try {
                status = Byte.parseByte(req.getParameter("adminStatus").trim());
            } catch (NumberFormatException e) {
                status = 0;
                errorMsgs.add("狀態請填數字.");
            }


            Admin admin = new Admin();
            admin.setAdminAccount(adminAccount);
            admin.setAdminPwd(adminPwd);
            admin.setAdminMail(adminMail);
            admin.setAdminPhone(adminPhone);
            admin.setAdminStatus(status);

            // Send the use back to the form, if there were errors
            if (!errorMsgs.isEmpty()) {
                req.setAttribute("admin", admin); // 含有輸入格式錯誤的empVO物件,也存入req
                RequestDispatcher failureView = req
                        .getRequestDispatcher("/admin/addAdmin.jsp");
                failureView.forward(req, res);
                return;
            }

            /***************************2.開始新增資料***************************************/
            AdminService adminSvc = new AdminService();
            admin = adminSvc.add(adminAccount, adminPwd, adminMail, adminPhone, status);

            /***************************3.新增完成,準備轉交(Send the Success view)***********/
            String url = "/admin/listAllAdmin.jsp";
            RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
            successView.forward(req, res);
        }
    }

}

