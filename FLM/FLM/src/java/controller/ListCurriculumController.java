/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Curriculum;
import model.User;

/**
 *
 * @author hp
 */
public class ListCurriculumController extends HttpServlet {

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
            request.getRequestDispatcher("/view/curriculum/listcurriculum.jsp").forward(request, response);
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
        session.setAttribute("role", "guest");
        request.setAttribute("op", 1);
        String toast = (String) session.getAttribute("toast");
        request.setAttribute("toast", toast);

        request.getRequestDispatcher("listcurriculum.jsp").forward(request, response);

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

        String optionSearch = request.getParameter("optionSearch");

        try {

            //Search by code
            if (request.getParameter("btn-search") != null && optionSearch.equals("1")) {
                CurriculumDAO c = new CurriculumDAO();

                String code = request.getParameter("code").trim() == null ? "" : request.getParameter("code").trim();
                //check code contrain 
                if (code.length() > 20) {
                    throw new Exception();
                } else {
                    //List curriculum != staff

                    ArrayList<Curriculum> List = new ArrayList<>();
                    String count = "";

                    //Check session is null
                    if (user_session == null) {
                        List = c.searchListCurriculum(code);
                        count = c.NumberOfRow(code);
                    } else {
                        //check session is staff
                        if (user_session.getRoleName().equals("CRDD STAFF")) {

                            List = c.searchListCurriculum_1(code, user_session.getUserId());
                            count = c.NumberOfRow1(code, user_session.getUserId());

                        } else if (user_session.getRoleName().equals("CRDD HEAD") || user_session.getRoleName().equals("ADMIN")) {

                            List = c.AllCurriculum(code);
                            count = c.Number(code);

                        } else {
                            List = c.searchListCurriculum(code);
                            count = c.NumberOfRow(code);
                        }
                    }

                    c.getTotalCredit(code);
                    request.setAttribute("total", c);
                    request.setAttribute("select", request.getParameter("optionSearch"));
                    request.setAttribute("count", count);
                    request.setAttribute("op", 1);
                    request.setAttribute("code", code);
                    request.setAttribute("List", List);
                    request.getRequestDispatcher("listcurriculum.jsp").forward(request, response);
                }

            } else {

                //Search by name
                if (request.getParameter("btn-search") != null && optionSearch.equals("2")) {
                    String name = request.getParameter("code").trim() == null ? "" : request.getParameter("code").trim();
                    //check code contrain 
                    if (name.length() > 20) {
                        throw new Exception();
                    } else {
                        //List curriculum != staff
                        CurriculumDAO c = new CurriculumDAO();
                        ArrayList<Curriculum> List = new ArrayList<Curriculum>();
                        String count = "";
                        if (user_session == null) {
                            List = c.searchListCurriculumByName(name);
                            count = c.NumberOfRowName(name);
                        } else {
                            if (user_session.getRoleName().equals("CRDD STAFF")) {
                                System.out.println(user_session.getRoleName());
                                List = c.searchListCurriculumByName_1(name, user_session.getUserId());
                                count = c.NumberOfRowName1(name, user_session.getUserId());
                            } else if (user_session.getRoleName().equals("CRDD HEAD") || user_session.getRoleName().equals("ADMIN")) {

                                List = c.AllCurriculum_2(name);
                                count = c.Number_2(name);

                            } else {
                                List = c.searchListCurriculumByName(name);
                                count = c.NumberOfRowName(name);
                            }
                        }

                        System.out.println(count);
                        request.setAttribute("select", request.getParameter("optionSearch"));
                        request.setAttribute("count", count);
                        request.setAttribute("op", 1);
                        request.setAttribute("code", name);
                        request.setAttribute("List", List);
                        request.getRequestDispatcher("listcurriculum.jsp").forward(request, response);
                    }
                }
            }

        } catch (Exception e) {
            String code = request.getParameter("code").trim() == null ? "" : request.getParameter("code").trim();
            request.setAttribute("code", code);
            request.setAttribute("err", "Code must be less than 20 characters");
            request.getRequestDispatcher("listcurriculum.jsp").forward(request, response);
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
