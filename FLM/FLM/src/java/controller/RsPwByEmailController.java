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
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import model.EmailSend;

/**
 *
 * @author Admin
 */
public class RsPwByEmailController extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       UserDAO ud = new UserDAO();
        HttpSession session = req.getSession();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String passwordhs = BaseDAO.getMD5Hash(password);
        String confirmPassword = req.getParameter("confirmPassword");
        String codeV = req.getParameter("codeV");
        boolean checkaccount = ud.checkUserbyJustEmail(email);
        
        // if you press the button "send verification code"
        if (req.getParameter("btn") != null ) {
            if(!checkaccount && req.getParameter("btn") != null){
            System.out.println(email);
            System.out.println(checkaccount);
            req.setAttribute("alert1", "Email does not exist");
            req.setAttribute("email", email);
            req.setAttribute("sendCode", '0');
            req.setAttribute("ChangeSuccess", "0");
            req.getRequestDispatcher("forgotpasswordbyemail.jsp").forward(req, resp);
            }
            else if (!email.isEmpty()) {
                int code = (int) Math.floor(((Math.random() * 899999) + 100000));
                EmailSend es = new EmailSend();
                try {
                    es.SendVerificationCode(email, String.valueOf(code));
                } catch (MessagingException ex) {
                    Logger.getLogger(RsPwByEmailController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedEncodingException ex) {
                    Logger.getLogger(RsPwByEmailController.class.getName()).log(Level.SEVERE, null, ex);
                }
                HttpSession VCode = req.getSession();
                VCode.setAttribute("checkRegisterCode", String.valueOf(code));
                VCode.setAttribute("checkRegisterEmail", email);
                System.out.println(String.valueOf(code));
                System.out.println(String.valueOf(email));
                // start count down to resend verification Code
                req.setAttribute("sendCode", "1");
            } else {
                req.setAttribute("alert", "Email not be empty!");
                // do not count down to resend verification Code
                req.setAttribute("sendCode", "0");
            }
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("confirmPassword", confirmPassword);
            req.setAttribute("ChangeSuccess", "0");
            req.getRequestDispatcher("forgotpasswordbyemail.jsp").forward(req, resp);
        } else {
            String VCodeInput = req.getParameter("codeV");
            HttpSession VCode = req.getSession();
             // check verification code input
            if (VCodeInput.equals(String.valueOf((VCode.getAttribute("checkRegisterCode"))))
                    && email.equals(String.valueOf(VCode.getAttribute("checkRegisterEmail")))) {
                VCode.removeAttribute("checkRegisterCode");
                VCode.removeAttribute("checkRegisterEmail");
                
                ud.updatePasswordByEmmail(passwordhs, email);
                //change successfull           
                session.setAttribute("toast", "true");
                req.setAttribute("ChangeSuccess", "1");
                req.getRequestDispatcher("forgotpasswordbyemail.jsp").forward(req, resp);                
            } else {
                req.setAttribute("email", email);
                req.setAttribute("password", password);
                req.setAttribute("confirmPassword", confirmPassword);
                req.setAttribute("codeV", codeV);
                System.out.println(confirmPassword);
                System.out.println(password);
                req.setAttribute("ChangeSuccess", "0");
                req.setAttribute("alertCode", "Your authentication code is incorrect. ");
                req.getRequestDispatcher("forgotpasswordbyemail.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("ChangeSuccess", "0");
        req.setAttribute("sendCode", "0");
        String toast = (String) session.getAttribute("toast");
        req.setAttribute("toast", toast);
        req.getRequestDispatcher("forgotpasswordbyemail.jsp").forward(req, resp);
    }

    
}
