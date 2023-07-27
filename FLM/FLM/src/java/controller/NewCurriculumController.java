/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.DecisionDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.Curriculum;
import model.Decision;
import model.User;

/**
 *
 * @author hp
 */
public class NewCurriculumController extends HttpServlet {

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
            out.println("<title>Servlet NewCurriculumController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewCurriculumController at " + request.getContextPath() + "</h1>");
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
        User user_session = (User) session.getAttribute("acc");

        if (user_session.getRoleName().equals("CRDD HEAD") || user_session.getRoleName().equals("ADMIN")) {
            System.out.println(user_session.getRoleName());
            UserDAO uDAO = new UserDAO();
            List<User> ListStaff = uDAO.ListStaff();
            request.setAttribute("ListStaff", ListStaff);
            System.out.println("chec");

            request.getRequestDispatcher("newcurriculum.jsp").forward(request, response);

        } else {

            request.getRequestDispatcher("newcurriculum.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        User user_session = (User) session.getAttribute("acc");
        String user_id = user_session.getUserId();
        String id = request.getParameter("id");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String ename = request.getParameter("ename");
        boolean is_active = false;
        String description = request.getParameter("description");
        String staff = request.getParameter("staff");

        try {
            if (code.trim().length() <= 2 || code.trim().length() > 20) {
                request.setAttribute("errCode", "Error curriculum code !");
                throw new Error();
            }

            //Check curriculum
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.CheckCurriculumByID(code);
            int id_next = cDAO.getMaxCurriculumId() + 1;
            String c_id = String.valueOf(id_next);

            // check curriculum co ton tai hay khong
            if (c == null) {
                ArrayList<Curriculum> List = new ArrayList<>();
                String count = "";
                if (user_session.getRoleName().equals("CRDD STAFF")) {

                    Curriculum cnew = new Curriculum(c_id, code, name, description, user_id, is_active, ename, user_id);
                    cDAO.InsertCurriculum(cnew);
                    
                    List = cDAO.searchListCurriculum_1(code, user_session.getUserId());
                    count = cDAO.NumberOfRow1(code, user_session.getUserId());
                    
                } else {
                    
                    Curriculum cnew = new Curriculum(c_id, code, name, description, staff, is_active, ename, user_id);
                    cDAO.AddCurriculumForStaff(cnew);
                    System.out.println(code);
                    List = cDAO.AllCurriculum(code);
                    count = cDAO.Number(code);
                }
                session.setAttribute("toast", "true");
//                request.setAttribute("Done", "Add suscessfully");
                request.setAttribute("code", code);
                request.setAttribute("List", List);
                request.setAttribute("count", count);
                request.getRequestDispatcher("listcurriculum.jsp").forward(request, response);

            } else {
                //errr
                request.setAttribute("CurriculumId", "Curriculum already exist in database!  ");
                //infor
                request.setAttribute("code", code);
                request.setAttribute("name", name);
                request.setAttribute("ename", ename);
                request.setAttribute("is_active", is_active);
                request.setAttribute("description", description);
                request.getRequestDispatcher("newcurriculum.jsp").forward(request, response);
            }

        } catch (Exception e) {
            // re-up input
            request.setAttribute("code", code);
            request.setAttribute("name", name);
            request.setAttribute("ename", ename);
            request.setAttribute("is_active", is_active);
            request.setAttribute("description", description);
            // Mess error
            request.getRequestDispatcher("newcurriculum.jsp").forward(request, response);
        } catch (Error ex) {
            // re-up input
            request.setAttribute("code", code);
            request.setAttribute("name", name);
            request.setAttribute("ename", ename);
            request.setAttribute("is_active", is_active);
            request.setAttribute("description", description);
            request.getRequestDispatcher("newcurriculum.jsp").forward(request, response);
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
