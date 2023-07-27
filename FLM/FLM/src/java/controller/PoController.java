/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.PoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Curriculum;
import model.Po;
import model.User;

/**
 *
 * @author ADMIN
 */
public class PoController extends HttpServlet {

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
            out.println("<title>Servlet PoController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PoController at " + request.getContextPath() + "</h1>");
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
        if (action.equals("polist")) {
            String Ccode = request.getParameter("Ccode");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            PoDAO pDAO = new PoDAO();
            ArrayList<Po> ListPO = pDAO.getListPoByCode(Ccode);
            int page, numperpage = 4;
            int sizePage = ListPO.size();
            int numPage = (sizePage % 4 == 0 ? (sizePage / 4) : ((sizePage / 4)) + 1);
            String xpage = request.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start;
            start = (page - 1) * numperpage;
            int endPage = Math.min(page * numperpage, sizePage);
            ArrayList<Po> listPage = pDAO.getListPoByPage(ListPO, start, Math.min(page * numperpage, endPage));
            request.setAttribute("numPage", numPage);
            request.setAttribute("sizePage", sizePage);
            request.setAttribute("page", page);
            request.setAttribute("op", "4");
            request.setAttribute("c", c);
            request.setAttribute("listPage", listPage);
            request.getRequestDispatcher("po.jsp").forward(request, response);
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
            PoDAO pDAO = new PoDAO();
            ArrayList<Po> ListPo;
            if (searchBy.equalsIgnoreCase("name")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    ListPo = pDAO.getListPoByNameForTeacher(Ccode, search);
                    int page, numperpage = 4;
                    int sizePage = ListPo.size();
                    int numPage = (sizePage % 4 == 0 ? (sizePage / 4) : ((sizePage / 4)) + 1);
                    String xpage = request.getParameter("page");
                    if (xpage == null) {
                        page = 1;
                    } else {
                        page = Integer.parseInt(xpage);
                    }
                    int start;
                    start = (page - 1) * numperpage;
                    int endPage = Math.min(page * numperpage, sizePage);
                    ArrayList<Po> listPage = pDAO.getListPoByPage(ListPo, start, Math.min(page * numperpage, endPage));
                    request.setAttribute("numPage", numPage);
                    request.setAttribute("sizePage", sizePage);
                    request.setAttribute("page", page);
                    request.setAttribute("selectFilter", searchBy);
                    request.setAttribute("search", search);
                    request.setAttribute("listPage", listPage);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("po.jsp").forward(request, response);
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff")||acc.getRoleName().equalsIgnoreCase("admin")) {
                        ListPo = pDAO.getListPoByNameForCrddStaff(Ccode, search);
                        int page, numperpage = 4;
                        int sizePage = ListPo.size();
                        int numPage = (sizePage % 4 == 0 ? (sizePage / 4) : ((sizePage / 4)) + 1);
                        String xpage = request.getParameter("page");
                        if (xpage == null) {
                            page = 1;
                        } else {
                            page = Integer.parseInt(xpage);
                        }
                        int start;
                        start = (page - 1) * numperpage;
                        int endPage = Math.min(page * numperpage, sizePage);
                        ArrayList<Po> listPage = pDAO.getListPoByPage(ListPo, start, Math.min(page * numperpage, endPage));
                        request.setAttribute("numPage", numPage);
                        request.setAttribute("sizePage", sizePage);
                        request.setAttribute("page", page);
                        request.setAttribute("selectFilter", searchBy);
                        request.setAttribute("search", search);
                        request.setAttribute("listPage", listPage);
                        request.setAttribute("c", c);
                        request.getRequestDispatcher("po.jsp").forward(request, response);
                    }
                }
            }
            if (searchBy.equalsIgnoreCase("description")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    ListPo = pDAO.getListPoByDescriptionForTeacher(Ccode, search);
                    int page, numperpage = 6;
                    int sizePage = ListPo.size();
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
                    ArrayList<Po> listPage = pDAO.getListPoByPage(ListPo, start, Math.min(page * numperpage, endPage));
                    request.setAttribute("numPage", numPage);
                    request.setAttribute("sizePage", sizePage);
                    request.setAttribute("page", page);
                    request.setAttribute("selectFilter", searchBy);
                    request.setAttribute("search", search);
                    request.setAttribute("listPage", listPage);
                    request.setAttribute("c", c);
                    request.getRequestDispatcher("po.jsp").forward(request, response);
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff")||acc.getRoleName().equalsIgnoreCase("admin")) {
                        ListPo = pDAO.getListPoByDescriptionForCrddStaff(Ccode, search);
                        int page, numperpage = 6;
                        int sizePage = ListPo.size();
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
                        ArrayList<Po> listPage = pDAO.getListPoByPage(ListPo, start, Math.min(page * numperpage, endPage));
                        request.setAttribute("numPage", numPage);
                        request.setAttribute("sizePage", sizePage);
                        request.setAttribute("page", page);
                        request.setAttribute("selectFilter", searchBy);
                        request.setAttribute("search", search);
                        request.setAttribute("listPage", listPage);
                        request.setAttribute("c", c);
                        request.getRequestDispatcher("po.jsp").forward(request, response);
                    }
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
