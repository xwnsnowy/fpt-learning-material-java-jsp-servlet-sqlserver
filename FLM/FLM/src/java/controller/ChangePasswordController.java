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
import model.User;

/**
 *
 * @author Admin
 */
public class ChangePasswordController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO ud = new UserDAO();
        //Lấy account và password từ session
        HttpSession session = req.getSession();
        User accUsername = (User) session.getAttribute("acc");
        String password = accUsername.getPassword();
        String account = accUsername.getUsername();
        String gmail = accUsername.getEmail();
        String mobile = accUsername.getMobile();
        String currentPassword = req.getParameter("currentpassword");
        String newPassword = req.getParameter("newpassword");
        String confirmPassword = req.getParameter("confirmpassword");
        String currentPasswordhs = BaseDAO.getMD5Hash(currentPassword);
        // Nếu bấm vào nút changepassword
        if (req.getParameter("submit") != null && currentPasswordhs.equals(password)
                && confirmPassword.equals(newPassword)) {
            if (accUsername.getUsername() != null) {
                req.setAttribute("alertchangepassword", "Change password successfull !");
                ud.changePassword(account, confirmPassword);
                req.getRequestDispatcher("/view/user/userprofile.jsp").forward(req, resp);
            }
            else if (accUsername.getEmail() != null) {
                req.setAttribute("alertchangepassword", "Change password successfull !");
                ud.updatePasswordByEmmail(confirmPassword, gmail);
                req.getRequestDispatcher("/view/user/userprofile.jsp").forward(req, resp);
            }
            else if (accUsername.getMobile() != null) {
                req.setAttribute("alertchangepassword", "Change password successfull !");
                ud.updatePasswordByMobile(confirmPassword, mobile);
                req.getRequestDispatcher("/view/user/userprofile.jsp").forward(req, resp);
            }
//            req.setAttribute("alertchangepassword", "Change password successfull !");
//            ud.changePassword(account, newPassword);
//            req.getRequestDispatcher("userprofile.jsp").forward(req, resp);

        } else if (!currentPasswordhs.equals(password)) {
            req.setAttribute("confirmPassword", confirmPassword);
            req.setAttribute("newPassword", newPassword);
            req.setAttribute("currentPassword", currentPassword);
            req.setAttribute("alertchangepassword", "Current Password incorrect!");
            req.getRequestDispatcher("/view/user/userprofile.jsp").forward(req, resp);
        } else if (!confirmPassword.equals(newPassword)) {
            req.setAttribute("confirmPassword", confirmPassword);
            req.setAttribute("newPassword", newPassword);
            req.setAttribute("currentPassword", currentPassword);
            req.setAttribute("alertchangepassword", "Password not match !");
            req.getRequestDispatcher("/view/user/userprofile.jsp").forward(req, resp);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("changepassword.jsp").forward(req, resp);
    }

}
