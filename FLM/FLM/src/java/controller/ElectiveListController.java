/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.GroupDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Curriculum;
import model.Elective;
import model.Subject;
import model.User;

/**
 *
 * @author ADMIN
 */
public class ElectiveListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ElectiveListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ElectiveListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("electivelist")) {
            String Ccode = request.getParameter("Ccode");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            GroupDAO gDAO = new GroupDAO();
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
            request.setAttribute("c", c);
            request.setAttribute("listPage", listPage);
            request.getRequestDispatcher("elective.jsp").forward(request, response);
        }
        if (action.equals("electivesubject")) {
            String Ccode = request.getParameter("Ccode");
            String Electiveid = request.getParameter("id");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            GroupDAO gDAO = new GroupDAO();
            Elective e = gDAO.getElectiveByElectiveId(Electiveid);
            ArrayList<Subject> SubjectOfElective = gDAO.getSubjectOfElectiveByElectiveId(Electiveid);
            int page, numperpage = 6;
            int sizePage = SubjectOfElective.size();
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
            ArrayList<Subject> listPage = gDAO.getListSubjectElectiveByPage(SubjectOfElective, start, Math.min(page * numperpage, endPage));
            request.setAttribute("numPage", numPage);
            request.setAttribute("sizePage", sizePage);
            request.setAttribute("page", page);
            request.setAttribute("op", "4");
            request.setAttribute("c", c);
            request.setAttribute("e", e);
            request.setAttribute("Ccode", Ccode);
            request.setAttribute("listPage", listPage);
            request.setAttribute("SubjectOfElective", SubjectOfElective);
            request.getRequestDispatcher("electivesubject.jsp").forward(request, response);
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
        if (action.equals("search")) {
            String searchBy = request.getParameter("selectFilter");
            String search = request.getParameter("search");
            String Ccode = request.getParameter("Ccode");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            HttpSession session = request.getSession();
            User acc = (User) session.getAttribute("acc");
            GroupDAO gDAO = new GroupDAO();
            ArrayList<Elective> ListElective;
            if (searchBy.equalsIgnoreCase("name")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    ListElective = gDAO.getListElectiveByNameForTeacher(Ccode, search);
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
                    request.setAttribute("selectFilter", searchBy);
                    request.setAttribute("search", search);
                    request.setAttribute("listPage", listPage);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("elective.jsp").forward(request, response);
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff") || acc.getRoleName().equalsIgnoreCase("admin")) {
                        ListElective = gDAO.getListElectiveByNameForCrddStaff(Ccode, search);
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
                        request.setAttribute("selectFilter", searchBy);
                        request.setAttribute("search", search);
                        request.setAttribute("listPage", listPage);
                        request.setAttribute("c", c);
                        request.getRequestDispatcher("elective.jsp").forward(request, response);
                    }
                }
            }
            if (searchBy.equalsIgnoreCase("id")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    ListElective = gDAO.getListElectiveByIdForTeacher(Ccode, search);
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
                    request.setAttribute("selectFilter", searchBy);
                    request.setAttribute("search", search);
                    request.setAttribute("listPage", listPage);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("elective.jsp").forward(request, response);
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff") || acc.getRoleName().equalsIgnoreCase("admin")) {
                        ListElective = gDAO.getListElectiveByIdForCrddStaff(Ccode, search);
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
                        request.setAttribute("selectFilter", searchBy);
                        request.setAttribute("search", search);
                        request.setAttribute("listPage", listPage);
                        request.setAttribute("c", c);
                        request.getRequestDispatcher("elective.jsp").forward(request, response);
                    }
                }
            }
        }
        if (action.equals("searchsubject")) {
            String search = request.getParameter("search");
            String Ccode = request.getParameter("Ccode");
            int electiveId = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String active = request.getParameter("active");
            active = active == null ? "0" : "1";
            Elective e = new Elective(electiveId, name, active);
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            HttpSession session = request.getSession();
            User acc = (User) session.getAttribute("acc");
            ArrayList<Subject> ListSubject;
            GroupDAO gDAO = new GroupDAO();
            if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                ListSubject = gDAO.getListSubjectElectiveByNameForTeacher(electiveId, search);
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
                request.setAttribute("search", search);
                request.setAttribute("listPage", listPage);
                request.setAttribute("c", c);
                request.setAttribute("e", e);
                request.getRequestDispatcher("electivesubject.jsp").forward(request, response);
            } else {
                if (acc.getRoleName().equalsIgnoreCase("crdd staff") || acc.getRoleName().equalsIgnoreCase("admin")) {
                    ListSubject = gDAO.getListSubjectElectiveByNameForCrddStaff(electiveId, search);
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
                    request.setAttribute("search", search);
                    request.setAttribute("listPage", listPage);
                    request.setAttribute("c", c);
                    request.setAttribute("e", e);
                    request.getRequestDispatcher("electivesubject.jsp").forward(request, response);
                }
            }
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
