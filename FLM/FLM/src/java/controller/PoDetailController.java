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
import java.util.ArrayList;
import model.Curriculum;
import model.Po;

/**
 *
 * @author ADMIN
 */
public class PoDetailController extends HttpServlet {

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
            out.println("<title>Servlet PoDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PoDetailController at " + request.getContextPath() + "</h1>");
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
        String po_id = request.getParameter("po_id");
        PoDAO pDAO = new PoDAO();
        ArrayList<Po> ListPO = pDAO.getListPoByCode(Ccode);
        Po p = pDAO.getPObyPoID(po_id);
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        request.setAttribute("Ccode", Ccode);
        request.setAttribute("c", c);
        request.setAttribute("op", "4");
        request.setAttribute("oppo", "0");
        request.setAttribute("p", p);
        request.setAttribute("ListPO", ListPO);
        request.getRequestDispatcher("podetail.jsp").forward(request, response);
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
        PoDAO pDAO = new PoDAO();
        String Ccode = request.getParameter("code");
        String po_id = request.getParameter("id");
        String active = request.getParameter("active");
        String PoName = request.getParameter("name");
        String button = request.getParameter("btn");
        String description = request.getParameter("description");
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        active = active == null ? "0" : "1";
        Po p = new Po(po_id, PoName, description, active);
        //Bấm vào nút update
        if (button != null) {
            pDAO.updatePOs(p);
            ArrayList<Po> ListPO = pDAO.getListPoByCode(Ccode);
            request.setAttribute("Ccode", Ccode);
            request.setAttribute("p", p);
            request.setAttribute("op", "4");
            request.setAttribute("c", c);
            request.setAttribute("ListPO", ListPO);
            request.setAttribute("alert", "Update Success");
            request.getRequestDispatcher("/view/common/po.jsp").forward(request, response);
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
