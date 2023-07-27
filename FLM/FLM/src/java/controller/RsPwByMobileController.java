/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BaseDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

/**
 *
 * @author Admin
 */
public class RsPwByMobileController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserDAO ud = new UserDAO();
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");
        System.out.println("password chua md5 : " + password);
        String passwordhs = BaseDAO.getMD5Hash(password);
        System.out.println("da md5 "+passwordhs);
        ud.updatePasswordByMobile(passwordhs, mobile);
        req.setAttribute("ChangeSuccess", "1");
        session.setAttribute("toast", "true");
        req.getRequestDispatcher("login.jsp").forward(req, resp);
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("ChangeSuccess", "0");
        req.setAttribute("sendCode", "0"); String toast = (String) session.getAttribute("toast");
        req.setAttribute("toast", toast);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

}
