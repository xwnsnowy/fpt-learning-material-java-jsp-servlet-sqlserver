/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BaseDAO;
import dao.UserDAO;
import model.EmailSend;
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

/**
 *
 * @author Admin
 */
public class RegisterController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        String password = req.getParameter("password");
        String passwordhs = BaseDAO.getMD5Hash(password);
        String confirmPassword = req.getParameter("confirmPassword");
        String verificationCode = req.getParameter("verificationCode");
        // if you press the button "send verification code"
        if (req.getParameter("sendCodeBT") != null) {
            UserDAO uD = new UserDAO();
            // check User Already Exist By Email
            if (uD.checkUserAlreadyExistByEmail(email)) {
                req.setAttribute("alartForSendCode", "Account already exist!");
                // do not count down to resend verification Code
                req.setAttribute("sendCode", "0");
            } else {
                if (!email.isEmpty()) {
                    // random code with 6 number
                    int code = (int) Math.floor(((Math.random() * 899999) + 100000));
                    EmailSend es = new EmailSend();
                    // send code to email
                    try {
                        es.SendVerificationCode(email, String.valueOf(code));
                    } catch (MessagingException ex) {
                        Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (UnsupportedEncodingException ex) {
                        Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    // add VCode in Check register
                    HttpSession VCode = req.getSession();
                    VCode.setAttribute("checkRegisterCode", String.valueOf(code));
                    VCode.setAttribute("checkRegisterEmail", email);
                    // start count down to resend verification Code
                    req.setAttribute("sendCode", "1");
                } else {
                    req.setAttribute("alartForSendCode", "Email not be empty!");
                    // do not count down to resend verification Code
                    req.setAttribute("sendCode", "0");
                }
            }
            req.setAttribute("fullName", fullName);
            req.setAttribute("email", email);
            req.setAttribute("password", password);
            req.setAttribute("confirmPassword", confirmPassword);
            // unregistered
            req.setAttribute("registerSuccess", "0");

            req.getRequestDispatcher("register.jsp").forward(req, resp);

        } else {

            // if you press the button "register"
            // if register by mobile
            if (!mobile.isEmpty()) {
                UserDAO uD = new UserDAO();
                // check User Already Exist By mobile
                if (uD.checkUserAlreadyExistByMobile(mobile)) {
                    req.setAttribute("alartForSendCodeForMobile", "Account already exist!");
                    // do not count down to resend verification Code
                    req.setAttribute("sendCode", "0");
                    // unregistered
                    req.setAttribute("registerSuccess", "0");
                    req.setAttribute("fullName", fullName);
                    req.setAttribute("mobile", mobile);
                    req.setAttribute("password", password);
                    req.setAttribute("confirmPassword", confirmPassword);  
                    req.setAttribute("verificationCode", verificationCode);
                } else {

                    // create new user id
                    String newUserId = String.valueOf(uD.getlastUserId() + 1);
                    // get role id of guest
                    String roleIdOfGuest = uD.getRoleIdOfGuest();
                    // add user in DB
                    uD.addUserInDBForRegistrationByMobile(roleIdOfGuest, "avata-default.jpg",newUserId, fullName, mobile, passwordhs);
                   
                    // register Success
                    req.setAttribute("registerSuccess", "1");
                }
                req.getRequestDispatcher("register.jsp").forward(req, resp);
            } else {
                // if register by email
                String VCodeInput = req.getParameter("verificationCode");
                HttpSession VCode = req.getSession();
                // check verification code input
                if (VCodeInput.equals(String.valueOf((VCode.getAttribute("checkRegisterCode"))))
                        && email.equals(String.valueOf(VCode.getAttribute("checkRegisterEmail")))) {
                    // remove VCode in Check register
                    VCode.removeAttribute("checkRegisterCode");
                    VCode.removeAttribute("checkRegisterEmail");

                    UserDAO uD = new UserDAO();
                    // create new user id
                    String newUserId = String.valueOf(uD.getlastUserId() + 1);
                    // get role id of guest
                    String roleIdOfGuest = uD.getRoleIdOfGuest();
                    // add user in DB
                    uD.addUserInDBForRegistration(roleIdOfGuest, "avata-default.jpg",newUserId, fullName, email, passwordhs);

                    // register Success
                    req.setAttribute("registerSuccess", "1");
                    req.getRequestDispatcher("register.jsp").forward(req, resp);

                } else {
                    req.setAttribute("fullName", fullName);
                    req.setAttribute("email", email);
                    req.setAttribute("password", password);
                    req.setAttribute("confirmPassword", confirmPassword);
                    req.setAttribute("verificationCode", verificationCode);
                    // do not count down to resend verification Code
                    req.setAttribute("sendCode", "0");
                    // unregistered
                    req.setAttribute("registerSuccess", "0");
                    req.setAttribute("alart", "Check your email or verification code!");
                    req.getRequestDispatcher("register.jsp").forward(req, resp);
                }
            }

        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // unregistered
        req.setAttribute("registerSuccess", "0");
        // do not count down to resend verification Code
        req.setAttribute("sendCode", "0");
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

}
