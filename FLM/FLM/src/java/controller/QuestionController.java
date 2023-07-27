/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.QuestionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Question;

/**
 *
 * @author hp
 */
public class QuestionController extends HttpServlet {

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
            out.println("<title>Servlet QuestionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionController at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("action") != null && request.getParameter("action").equals("1")) {
            QuestionDAO q = new QuestionDAO();
            ArrayList<Question> List = q.getListQuestion();
            request.setAttribute("List", List);
            HttpSession session = request.getSession();
            String toast = (String) session.getAttribute("toast");
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("/view/question/questionlist.jsp").forward(request, response);
        } else if (request.getParameter("edit") != null){
            String id = request.getParameter("id");
            
            
            QuestionDAO qDAO = new QuestionDAO();
            Question q = qDAO.getQuestion(id);
            request.setAttribute("q", q);
            HttpSession session = request.getSession();
            String toast = (String) session.getAttribute("toast");
            request.setAttribute("toast", toast);
            
            request.getRequestDispatcher("questiondetail.jsp").forward(request, response);
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
        //Search
        if(request.getParameter("btn-search")!= null){
            String searchString = request.getParameter("searchString");
            QuestionDAO q = new QuestionDAO();
            ArrayList<Question> List = q.getListQuestionSearch(searchString);
            request.setAttribute("List", List);
            request.setAttribute("searchString", searchString);
            request.getRequestDispatcher("/view/question/questionlist.jsp").forward(request, response);
        } else if (request.getParameter("edit")!= null){
            String name = request.getParameter("name");
            String detail = request.getParameter("detail");
            int id = Integer.parseInt(request.getParameter("id"));
            Question q = new Question(id, name, detail);
            QuestionDAO qDAO = new QuestionDAO();
            qDAO.UpdateQuestion(q);
            request.setAttribute("q", q);
            HttpSession session = request.getSession();
            session.setAttribute("toast", "true");
            
            request.getRequestDispatcher("questiondetail.jsp").forward(request, response);
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
