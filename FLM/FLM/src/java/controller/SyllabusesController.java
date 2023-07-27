/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.CurriculumSubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;
import model.CurriculumSubject;
import model.Syllabus;

/**
 *
 * @author Admin
 */
public class SyllabusesController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cId = req.getParameter("cId");
        String subject = req.getParameter("subjectId");
        CurriculumSubjectDAO cSD = new CurriculumSubjectDAO();
        CurriculumSubject cs = cSD.getCurriculumSubject(cId, subject);
        CurriculumDAO cD = new CurriculumDAO();
        Curriculum curiculum = cD.getCurriculumById(cId);
        String typeSubject="default";
        ArrayList<CurriculumSubject> listCombo = cSD.getListCombo(cId, subject, typeSubject);
        ArrayList<Syllabus> listSubjectOfCombo = cSD.getListSubjectOfCombo(cId, subject, typeSubject);
        

        req.setAttribute("alert", "");
        req.setAttribute("op", 4);
        req.setAttribute("listCombo", listCombo);
        req.setAttribute("listSubjectOfCombo", listSubjectOfCombo);
        req.setAttribute("cs", cs);
        req.setAttribute("c", curiculum);
        req.getRequestDispatcher("syllabuseslist.jsp").forward(req, resp);

    }

}
