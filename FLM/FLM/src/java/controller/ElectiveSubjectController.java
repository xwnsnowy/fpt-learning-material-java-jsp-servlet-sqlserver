/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.GroupDAO;
import dao.SubjectDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Curriculum;
import model.Elective;
import model.Subject;

/**
 *
 * @author ADMIN
 */
public class ElectiveSubjectController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("electivedetail")) {
            String Ccode = request.getParameter("Ccode");
            String Electiveid = request.getParameter("id");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            GroupDAO gDAO = new GroupDAO();
            Elective ListElective = gDAO.getElectiveByElectiveId(Electiveid);
            request.setAttribute("e", ListElective);
            request.setAttribute("c", c);
            request.getRequestDispatcher("electivedetail.jsp").forward(request, response);
        }
        if (action.equals("editelective")) {
            String Ccode = request.getParameter("Ccode");
            String subjectid = request.getParameter("id");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            SubjectDAO sDAO = new SubjectDAO();
            Subject ListSubject = sDAO.getSubjectElectiveBySubjectId(subjectid);
            request.setAttribute("s", ListSubject);
            request.setAttribute("c", c);
            request.getRequestDispatcher("electivesubjectdetail.jsp").forward(request, response);
        }
        if (action.equals("add")) {
            String Ccode = request.getParameter("Ccode");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            request.setAttribute("c", c);
            SubjectDAO sDAO = new SubjectDAO();
            ArrayList<Subject> listname = sDAO.getAllSubject();
            request.setAttribute("listname", listname);
            request.getRequestDispatcher("addelective.jsp").forward(request, response);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("update")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String active = request.getParameter("active");
            String button = request.getParameter("btn");
            active = active == null ? "0" : "1";
            Elective e = new Elective(id, name, active);
            String Ccode = request.getParameter("Ccode");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            if (button != null) {
                GroupDAO gDAO = new GroupDAO();
                gDAO.updateElective(e);
                ArrayList<Elective> ListElective = gDAO.getListElectiveByCode(Ccode);
                int page, numperpage = 6;
                int sizePage = ListElective.size();
                int numPage = (sizePage % 6 == 0 ? (sizePage / 6) : ((sizePage / 6)) + 1);
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start;
                start = (page - 1) * numperpage;
                int endPage = Math.min(page * numperpage, sizePage);
                ArrayList<Elective> listPage = gDAO.getListElectiveByPage(ListElective, start, Math.min(page * numperpage, endPage));
                request.setAttribute("numPage", numPage);
                request.setAttribute("sizePage", sizePage);
                request.setAttribute("page", page);
                request.setAttribute("op", "4");
                request.setAttribute("listPage", listPage);
                request.setAttribute("c", c);
                request.setAttribute("e", e);
                request.setAttribute("MessUpdate", "Update success!");
                request.getRequestDispatcher("/view/crddstaff/electivedetail.jsp").forward(request, response);
            }
        }
        if (action.equals("updatesubject")) {
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String code = request.getParameter("code");
            String active = request.getParameter("active");
            String button = request.getParameter("btn");
            String Ccode = request.getParameter("Ccode");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            active = active == null ? "0" : "1";
            Subject s = new Subject(id, code, name, active);
            if (button != null) {
                SubjectDAO sDAO = new SubjectDAO();
                GroupDAO gDAO = new GroupDAO();
                sDAO.updateSubjectElectiveById(s);
                ArrayList<Subject> ListSubject = gDAO.getSubjectOfElectiveByElectiveId(id);
                int page, numperpage = 6;
                int sizePage = ListSubject.size();
                int numPage = (sizePage % 6 == 0 ? (sizePage / 6) : ((sizePage / 6)) + 1);
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start;
                start = (page - 1) * numperpage;
                int endPage = Math.min(page * numperpage, sizePage);
                ArrayList<Subject> listPage = gDAO.getListSubjectElectiveByPage(ListSubject, start, Math.min(page * numperpage, endPage));
                request.setAttribute("numPage", numPage);
                request.setAttribute("sizePage", sizePage);
                request.setAttribute("page", page);
                request.setAttribute("op", "4");
                request.setAttribute("c", c);
                request.setAttribute("s", s);
                request.setAttribute("listPage", listPage);
                request.setAttribute("MessUpdate", "Update success!");
                request.getRequestDispatcher("/view/crddstaff/electivesubjectdetail.jsp").forward(request, response);
            }
        }
        if (action.equals("addnewelective")) {
            String Ccode = request.getParameter("Ccode");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            String name = request.getParameter("name");
            int id = Integer.parseInt(request.getParameter("id"));
            String type = request.getParameter("type");
            String subject = request.getParameter("select");
            String active = request.getParameter("active");
            active = active == null ? "0" : "1";
            Elective e = new Elective(id, name, subject, active);
            GroupDAO gDAO = new GroupDAO();
            gDAO.addElective(e);
            SubjectDAO sDAO = new SubjectDAO();
            ArrayList<Subject> listname = sDAO.getAllSubject();
            request.setAttribute("listname", listname);
            request.setAttribute("c", c);
            request.setAttribute("MessUpdate", "Add success!");
            request.getRequestDispatcher("addelective.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
