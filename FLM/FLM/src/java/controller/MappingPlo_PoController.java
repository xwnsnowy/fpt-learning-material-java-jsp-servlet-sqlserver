/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.PloDAO;
import dao.Plo_PoDAO;
import dao.PoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Curriculum;
import model.Plo;
import model.Plo_Po;
import model.Po;

/**
 *
 * @author Admin
 */
public class MappingPlo_PoController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plo_PoDAO plopoDAO = new Plo_PoDAO();
        String Ccode = req.getParameter("Ccode");
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        Map<String, Boolean> mappingStatus = new HashMap<>();
        Map<String, Boolean> mappingStatusUpdate = new HashMap<>();
//        System.out.println("Code Curriculum :" + Ccode);
        HttpSession session = req.getSession();
        PloDAO pDao = new PloDAO();
        PoDAO poDAO = new PoDAO();
        ArrayList<Po> ListPo = poDAO.getListPoByCode(Ccode);
        ArrayList<Plo> ListPLO = pDao.getListPloByCode(Ccode);
        
        String para = "";
        boolean check = false;

        // duyệt tất cả các phần tử trong list plo
        if (req.getParameter("save") != null) {
            for (Plo plo : ListPLO) {
                // duyệt tất cả các phần tử trong list po
                for (Po po : ListPo) {
                    para = "mapping" + plo.getPlo_id() + "_" + po.getPo_id() + "";
                    check = plopoDAO.check(plo.getPlo_id(), po.getPo_id());
                    mappingStatus.put(para, check);
                    if (req.getParameter(para) != null && check == false) {
                        plopoDAO.addMappingPLO_PO(plo.getPlo_id(), po.getPo_id());

                    }
                    if (req.getParameter(para) == null && check == true) {
                        plopoDAO.deleteMappingPO_PLO(plo.getPlo_id(), po.getPo_id());

                    }
                }
            }
        }
        for (Plo plo : ListPLO) {
            for (Po po : ListPo) {
                para = "mapping" + plo.getPlo_id() + "_" + po.getPo_id() + "";
                check = plopoDAO.check(plo.getPlo_id(), po.getPo_id());
                mappingStatusUpdate.put(para, check);
            }
        }
        req.setAttribute("mappingStatus", mappingStatusUpdate);
        req.setAttribute("op", "4");
        req.setAttribute("c", c);
        req.setAttribute("para", para);
        session.setAttribute("toast", "true");
        req.setAttribute("ListPo", ListPo);
        req.setAttribute("ListPlo", ListPLO);
//        req.setAttribute("ListPlo_PO", ListPlo_PO);
        req.getRequestDispatcher("mappingplo_po.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Plo_PoDAO plopoDAO = new Plo_PoDAO();
        String Ccode = req.getParameter("Ccode");
        Map<String, Boolean> mappingStatus = new HashMap<>();
        HttpSession session = req.getSession();
//        System.out.println(Ccode);
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        PloDAO pDao = new PloDAO();
        PoDAO poDAO = new PoDAO();
        ArrayList<Po> ListPo = poDAO.getListPoByCode(Ccode);
        ArrayList<Plo> ListPLO = pDao.getListPloByCode(Ccode);
//        ArrayList<Plo_Po> ListPlo_PO = plopoDAO.getMappings(Ccode);
        String para = "";
        boolean check = false;
        for (Plo plo : ListPLO) {
            for (Po po : ListPo) {
                para = "mapping" + plo.getPlo_id() + "_" + po.getPo_id() + "";
                check = plopoDAO.check(plo.getPlo_id(), po.getPo_id());
                mappingStatus.put(para, check);
            }
        }
        req.setAttribute("op", "4");
        req.setAttribute("c", c);
        req.setAttribute("para", para);
        req.setAttribute("ListPo", ListPo);
        req.setAttribute("ListPlo", ListPLO);
        String toast = (String) session.getAttribute("toast");
        req.setAttribute("toast", toast);
        req.setAttribute("mappingStatus", mappingStatus);
        req.getRequestDispatcher("mappingplo_po.jsp").forward(req, resp);
    }
}
