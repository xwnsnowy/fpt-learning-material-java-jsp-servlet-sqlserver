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
import model.Curriculum;

/**
 *
 * @author ADMIN
 */
public class AddPosController extends HttpServlet {

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
            out.println("<title>Servlet AddPosController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddPosController at " + request.getContextPath() + "</h1>");
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
        String Ccode = request.getParameter("Ccode");
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        PoDAO pdao = new PoDAO();
        int idmax = pdao.getMaxPoId();
        request.setAttribute("idmax", idmax);
        request.setAttribute("c", c);
        request.setAttribute("op", "4");
        request.getRequestDispatcher("addpos.jsp").forward(request, response);
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
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String Description = request.getParameter("Description");
        String Ccode = request.getParameter("Ccode");
        String active = request.getParameter("active");
        active = active == null ? "0" : "1";
        PoDAO pDAO = new PoDAO();
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        if (request.getParameter("btn") != null) {
            pDAO.addPoByCcode(id, name, Description, Ccode, active);
            request.setAttribute("alert", "Add PO Successfull!");
            request.setAttribute("op", "4");
            request.setAttribute("Ccode", Ccode);
            request.setAttribute("c", c);
             request.getRequestDispatcher("addpos.jsp").forward(request, response);
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
