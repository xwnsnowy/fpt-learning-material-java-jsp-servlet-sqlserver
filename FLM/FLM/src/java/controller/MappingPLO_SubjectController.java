/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.CurriculumSubjectDAO;
import dao.PloDAO;
import dao.Plo_SubjectDAO;
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
import model.CurriculumSubject;
import model.Plo;
import model.Po;

/**
 *
 * @author Admin
 */
public class MappingPLO_SubjectController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Ccode = req.getParameter("Ccode");
        HttpSession session = req.getSession();
        Map<String, Boolean> mappingStatusUpdate = new HashMap<>();
        Map<String, Boolean> mappingStatus = new HashMap<>();
//        System.out.println(Ccode);
        CurriculumDAO cDAO = new CurriculumDAO();
        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        PloDAO pDao = new PloDAO();
        Plo_SubjectDAO plosbj = new Plo_SubjectDAO();
        ArrayList<CurriculumSubject> listSubjectCode = csDAO.getListSubjectByCcode(Ccode);
        ArrayList<Plo> ListPLO = pDao.getListPloByCode(Ccode);
        String para = "";
        boolean check = false;
        if (req.getParameter("save") != null) {
            for (Plo plo : ListPLO) {
                for (CurriculumSubject ccs : listSubjectCode) {
                    para = "mapping" + plo.getPlo_id() + "_" + ccs.getSubjectId() + "";
//                System.out.println(para);
                    check = plosbj.check(plo.getPlo_id(), ccs.getSubjectId());
                    mappingStatus.put(para, check);
                    if (req.getParameter(para) != null && check == false) {
                        plosbj.addMappingPLO_Subject(plo.getPlo_id(), ccs.getSubjectId(), Ccode);
                    }
                    if (req.getParameter(para) == null && check == true) {
                        plosbj.deleteMappingPLO_Subject(plo.getPlo_id(), Ccode, ccs.getSubjectId());
                    }
                }
            }
        }
        for (Plo plo : ListPLO) {
            for (CurriculumSubject ccs : listSubjectCode) {
                para = "mapping" + plo.getPlo_id() + "_" + ccs.getSubjectId() + "";
                check = plosbj.check(plo.getPlo_id(), ccs.getSubjectId());
                mappingStatusUpdate.put(para, check);
//                System.out.println(mappingStatusUpdate);
            }
        }
        req.setAttribute("op", "4");
        req.setAttribute("c", c);
        session.setAttribute("toast", "true");
        req.setAttribute("para", para);
        req.setAttribute("ListPLO", ListPLO);
        req.setAttribute("mappingStatus", mappingStatusUpdate);
        req.setAttribute("listSubjectCode", listSubjectCode);
        req.getRequestDispatcher("mappingplo_subject.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String Ccode = req.getParameter("Ccode");
        Map<String, Boolean> mappingStatus = new HashMap<>();
//        System.out.println(Ccode);
        CurriculumDAO cDAO = new CurriculumDAO();
        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        PloDAO pDao = new PloDAO();
        Plo_SubjectDAO plosbj = new Plo_SubjectDAO();
        ArrayList<CurriculumSubject> listSubjectCode = csDAO.getListSubjectByCcode(Ccode);
        ArrayList<Plo> ListPLO = pDao.getListPloByCode(Ccode);
        String para = "";
        boolean check = false;
        for (Plo plo : ListPLO) {
            for (CurriculumSubject ccs : listSubjectCode) {
                para = "mapping" + plo.getPlo_id() + "_" + ccs.getSubjectId() + "";
                check = plosbj.check(plo.getPlo_id(), ccs.getSubjectId());
                mappingStatus.put(para, check);
            }
        }
        req.setAttribute("op", "4");
        req.setAttribute("c", c);
        req.setAttribute("para", para);
        req.setAttribute("ListPLO", ListPLO);
        req.setAttribute("mappingStatus", mappingStatus);
        String toast = (String) session.getAttribute("toast");
        req.setAttribute("toast", toast);
        req.setAttribute("listSubjectCode", listSubjectCode);
        req.getRequestDispatcher("mappingplo_subject.jsp").forward(req, resp);
    }

}
