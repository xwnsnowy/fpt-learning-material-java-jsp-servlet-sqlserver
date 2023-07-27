/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.PloDAO;
import dao.PoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import model.Curriculum;
import model.Plo;
import model.Po;

/**
 *
 * @author Admin
 */
public class PloDetailsController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Ccode = req.getParameter("Ccode");
        String plo_id = req.getParameter("Ploid");
        String active = req.getParameter("active");
        String PloName = req.getParameter("PloName");
        String button = req.getParameter("btn");
        HttpSession session = req.getSession();
        String pDescription = req.getParameter("pDescription");
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        PloDAO pDAO = new PloDAO();
        active = active == null ? "0" : "1";
        Plo p = new Plo(plo_id, PloName, pDescription, active);
        //Bấm vào nút update
        if (button != null) {
            //Gọi update trong database
//            System.out.println("123");
            pDAO.updatePLOs(p);
            ArrayList<Plo> ListPLO = pDAO.getListPloByCode(Ccode);
            req.setAttribute("Ccode", Ccode);
            req.setAttribute("p", p);
            req.setAttribute("op", "4");
//            req.setAttribute("opplo", "0");
            req.setAttribute("c", c);
            session.setAttribute("toast", "true");
            req.setAttribute("active", active);
            req.setAttribute("ListPLO", ListPLO);
         req.getRequestDispatcher("/view/common/plo.jsp").forward(req, resp);
            
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Ccode = req.getParameter("Ccode");
        String plo_id = req.getParameter("plo_id");
        HttpSession session = req.getSession();
        Plo p = new Plo();
        PloDAO pDAO = new PloDAO();
        ArrayList<Plo> ListPLO = pDAO.getListPloByCode(Ccode);
        p = pDAO.getPLObyPloID(plo_id);
//        System.out.println(p.getIs_Active());
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        req.setAttribute("Ccode", Ccode);
        req.setAttribute("c", c);
        req.setAttribute("op", "4");
        req.setAttribute("opplo", "0");
        req.setAttribute("p", p);
        String toast = (String) session.getAttribute("toast");
        req.setAttribute("toast", toast);
        req.setAttribute("ListPLO", ListPLO);
        req.getRequestDispatcher("plodetails.jsp").forward(req, resp);

    }

}
