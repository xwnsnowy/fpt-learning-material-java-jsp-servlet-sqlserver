/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.SettingDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.EmailSend;

/**
 *
 * @author Admin
 */
public class AddUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullname");
        String userName = req.getParameter("username");
        String Email = req.getParameter("email");
        String Mobile = req.getParameter("mobile");
        String role = req.getParameter("role");
        String picture = "avata-default.jpg";
        SettingDAO stD = new SettingDAO();
        UserDAO ud = new UserDAO();
        if (req.getParameter("btn") != null && !ud.checkUserbyJustEmail(Email)
                && !ud.checkUserbyJustPhone(Mobile) && !ud.checkUserbyJustUserName(userName)) {
           
            EmailSend es = new EmailSend();
            String randomPassword = es.generateRandomPassword();
            try {
                    es.SendVerificationCode(Email, randomPassword);
                } catch (MessagingException ex) {
                    Logger.getLogger(RsPwByEmailController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(RsPwByEmailController.class.getName()).log(Level.SEVERE, null, ex);
                }
             // create new user id
            String newUserId = String.valueOf(ud.getlastUserId() + 1);
            // get role id of student
            String roleIdOfUser = ud.getRoleNameOfUser(role);
//            System.out.println("roleOfUser " + roleIdOfUser);
            // add user in DB
            ud.AddUserInDBForAdmin(roleIdOfUser, newUserId, fullName, Email,
                    randomPassword, Mobile, userName, "1", picture);
            ArrayList listRole = stD.getListRole();
            req.setAttribute("listRole", listRole);
            req.setAttribute("alert", "Add User Successfull !");
            req.getRequestDispatcher("adduser.jsp").forward(req, resp);
        } else if (ud.checkUserbyJustUserName(userName)) {
            req.setAttribute("alert", "The user name is exist");
            ArrayList listRole = stD.getListRole();
            req.setAttribute("email", Email);
            req.setAttribute("username", userName);
            req.setAttribute("fullName", fullName);
            req.setAttribute("listRole", listRole);
            req.getRequestDispatcher("adduser.jsp").forward(req, resp);
        } else if (ud.checkUserbyJustEmail(Email)) {
            req.setAttribute("alert", "The email is exist");
            ArrayList listRole = stD.getListRole();
            req.setAttribute("listRole", listRole);
            req.setAttribute("email", Email);
            req.setAttribute("username", userName);
            req.setAttribute("fullName", fullName);
            req.getRequestDispatcher("adduser.jsp").forward(req, resp);
//        req.getRequestDispatcher("adduser.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SettingDAO stD = new SettingDAO();
        ArrayList listRole = stD.getListRole();
        req.setAttribute("listRole", listRole);
        req.getRequestDispatcher("adduser.jsp").forward(req, resp);
    }

}
