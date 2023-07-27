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
import java.util.ArrayList;
import model.Setting;
import model.User;

/**
 *
 * @author Admin
 */
public class AccountController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String selectFilter = req.getParameter("selectFilter");
        String search = req.getParameter("search");
        // update user profile
        if (action.equals("1")) {
            String id = req.getParameter("idValue");
            String role = req.getParameter("role");
            String preRole = req.getParameter("preRole");
            String isActive = req.getParameter("isActive");
            String status = req.getParameter("status");
            isActive = isActive == null ? "0" : "1";
            status = status == null ? "0" : "1";
            UserDAO u = new UserDAO();
            boolean checkUpdate = u.updateUser(id, preRole, role, isActive, status);
            req.setAttribute("alert", "");
            // if This role already exists with the user you want to update
            if (!checkUpdate) {
                req.setAttribute("alert", "This role already exists with the user you want to update!");
            }
        }
        // delete user
        if(action.equals("2")){
            String daleteUserId = req.getParameter("daleteUserId");
            UserDAO uDForDelete = new UserDAO();
            uDForDelete.deleteUserByUserId(daleteUserId);
            req.setAttribute("alert", "");
            
        }
            UserDAO uD = new UserDAO();
            SettingDAO s = new SettingDAO();
            ArrayList<Setting> listRole = s.getListRole();
            ArrayList<User> listAccount = uD.getListAccount(selectFilter, search);
            req.setAttribute("numberOfAcc", listAccount.size());
            req.setAttribute("selectFilter", selectFilter);
            req.setAttribute("search", search);
            req.setAttribute("listRole", listRole);
            req.setAttribute("listAccount", listAccount);
            req.getRequestDispatcher("account.jsp").forward(req, resp);
        
        
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectFilter = req.getParameter("selectFilter");
        String search = req.getParameter("search");
        //first run
        if (selectFilter == null) {
            selectFilter = "";
        } else {
            req.setAttribute("selectFilter", selectFilter);
        }
        //first run
        if (search == null) {
            search = "";
        } else {
            req.setAttribute("search", search);
        }
        UserDAO uD = new UserDAO();
        SettingDAO s = new SettingDAO();
        ArrayList<Setting> listRole = s.getListRole();
        ArrayList<User> listAccount = uD.getListAccount(selectFilter, search);
        req.setAttribute("alert", "");
        req.setAttribute("numberOfAcc", listAccount.size());
        req.setAttribute("listRole", listRole);
        req.setAttribute("listAccount", listAccount);
        req.getRequestDispatcher("account.jsp").forward(req, resp);
    }

}
