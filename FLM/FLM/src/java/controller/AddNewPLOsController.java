/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.PloDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import model.Curriculum;
import model.Plo;
import model.User;

/**
 *
 * @author Admin
 */
public class AddNewPLOsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ploName = req.getParameter("ploName");
        String ploDescription = req.getParameter("ploDescription");
        String Ccode = req.getParameter("Ccode");
        String Cid = req.getParameter("cid");
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();
        Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
        String created_at = spf.format(date);
        HttpSession session = req.getSession();
        User accUsername = (User) session.getAttribute("acc");
        String create_by = accUsername.getUserId();
        String setting = accUsername.getRoleName();
        PloDAO pDAO = new PloDAO();
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        String newClo_id = String.valueOf(pDAO.getlastPloId() + 1);
        String active = req.getParameter("active");
        active = active == null ? "0" : "1";
        if (!pDAO.checkNameExist(ploName, Cid)) {
            pDAO.addPloByCcode(newClo_id, ploName, ploDescription, Ccode, active, created_at, create_by);
            ArrayList<Plo> ListPLO = pDAO.getListPloByCode(Ccode);
            req.setAttribute("alert", "Add PLO Successfull!");
            req.setAttribute("op", "4");
            req.setAttribute("Ccode", Ccode);
            req.setAttribute("ListPLO", ListPLO);
            req.setAttribute("c", c);
            req.getRequestDispatcher("/view/common/plo.jsp").forward(req, resp);
        } else {
            req.setAttribute("name", ploName);
            req.setAttribute("discirption", ploDescription);
            req.setAttribute("active", active);
            req.setAttribute("alert", "PLO Name is exsit");
            req.setAttribute("op", "4");
            req.setAttribute("Ccode", Ccode);
            req.setAttribute("c", c);
            req.getRequestDispatcher("addplos.jsp").forward(req, resp);

        }
//        req.getRequestDispatcher("/view/common/plo.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String create_by = session.getId();
        String Ccode = req.getParameter("Ccode");
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        req.setAttribute("c", c);
        req.setAttribute("op", "4");
        req.getRequestDispatcher("addplos.jsp").forward(req, resp);
    }

}
