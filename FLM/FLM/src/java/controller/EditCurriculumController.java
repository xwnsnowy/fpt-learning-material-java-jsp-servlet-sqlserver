/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.DecisionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintWriter;
import model.Curriculum;
import model.Decision;

/**
 *
 * @author hp
 */
public class EditCurriculumController extends HttpServlet {

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
            out.println("<title>Servlet EditCurriculumController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditCurriculumController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        String c_id = request.getParameter("C_id");
        System.out.println(c_id);
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumById(c_id);
        request.setAttribute("c", c);
        request.setAttribute("c_active", c.getIs_active());
        String toast = (String) session.getAttribute("toast");
        request.setAttribute("toast", toast);

        request.getRequestDispatcher("editcurriculum.jsp").forward(request, response);
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
//        processRequest(request, response);
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String ename = request.getParameter("ename");

        String description = request.getParameter("description");
        String active = request.getParameter("is_active");

        if (request.getParameter("update").equals("Update")) {
            boolean is_active;
            if (active.equals("1")) {
                is_active = true;
            } else {
                is_active = false;
            }
            if (code.trim().length() <= 2 || code.trim().length() > 20) {
                request.setAttribute("errCode", "Error curriculum code !");
                request.setAttribute("id", id);
                request.setAttribute("code", code);
                request.setAttribute("name", name);
                request.setAttribute("ename", ename);
                request.setAttribute("is_active", is_active);
                request.setAttribute("description", description);

                request.getRequestDispatcher("editcurriculum.jsp").forward(request, response);
            } else {

                CurriculumDAO cDAO = new CurriculumDAO();
                int Cid = Integer.parseInt(id);
                Curriculum cnew = new Curriculum(id, code, name, description, is_active, ename);

//                if (cDAO.CheckCurriculumByID(code) != null) {
//                    request.setAttribute("errCode", "Curriculum existed system");
//                } else {
//                    cDAO.updateCurriculum(cnew);
//                    HttpSession session = request.getSession();
//                    session.setAttribute("toast", "true");
//                }
                cDAO.updateCurriculum(cnew);
                    HttpSession session = request.getSession();
                    session.setAttribute("toast", "true");
                request.setAttribute("c", cnew);
                request.setAttribute("c_active", cnew.getIs_active());
                //            request.setAttribute("Done", "Update Suscessfully");

                request.getRequestDispatcher("editcurriculum.jsp").forward(request, response);
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
