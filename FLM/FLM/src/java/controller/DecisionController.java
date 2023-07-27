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
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Curriculum;
import model.Decision;
import model.User;

/**
 *
 * @author hp
 */
public class DecisionController extends HttpServlet {

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
            out.println("<title>Servlet DecisionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DecisionController at " + request.getContextPath() + "</h1>");
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
        if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
            HttpSession session = request.getSession();
            User user_session = (User) session.getAttribute("acc");
            if (user_session != null) {
                String decision_no = request.getParameter("decision_no");
                DecisionDAO dDAO = new DecisionDAO();
                Decision d = dDAO.getDecisionByNo(decision_no);
                request.setAttribute("d", d);
                request.getRequestDispatcher("overviewdecision.jsp").forward(request, response);
            }
//            else {
//                request.getRequestDispatcher("").forward(request, response);
//            }

        } else if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
            String decision_no = request.getParameter("decision_no");
            DecisionDAO dDAO = new DecisionDAO();
            Decision d = dDAO.getDecisionByNo(decision_no);
            CurriculumDAO cDAO = new CurriculumDAO();
            ArrayList<Curriculum> List = cDAO.getListCurriculumNullDecision();
            request.setAttribute("List", List);
            request.setAttribute("d", d);
            HttpSession session = request.getSession();
            String toast = (String) session.getAttribute("toast");
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("decisiondetail.jsp").forward(request, response);
        } else if (request.getParameter("btn-New") != null && request.getParameter("btn-New").equals("new")) {
            LocalDate currentDate = LocalDate.now();
            request.setAttribute("date", currentDate);
            request.getRequestDispatcher("newdecision.jsp").forward(request, response);
        } else {
            HttpSession session = request.getSession();
            String toast = (String) session.getAttribute("toast");
            request.setAttribute("toast", toast);
            request.getRequestDispatcher("listdecision.jsp").forward(request, response);

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

        //Search by code => list
        if (request.getParameter("btn-search") != null && request.getParameter("optionSearch").equals("1")) {
            String searchString = request.getParameter("searchString").trim() == null ? "" : request.getParameter("searchString");
            if (searchString.length() > 50) {
                request.setAttribute("err", "Decision_no khong qua 50 ky tu");
                request.setAttribute("searchString", searchString);
                request.getRequestDispatcher("listdecision.jsp").forward(request, response);
            }
            String optionSearch = request.getParameter("optionSearch");
            DecisionDAO dDAO = new DecisionDAO();
            ArrayList<Decision> List = new ArrayList<>();
            List = dDAO.SearchByNo(searchString);
            request.setAttribute("List", List);
            request.setAttribute("searchString", searchString);
            request.setAttribute("optionSearch", optionSearch);
            request.getRequestDispatcher("listdecision.jsp").forward(request, response);
        }

        //Search by name => list
        if (request.getParameter("btn-search") != null && request.getParameter("optionSearch").equals("2")) {
            String searchString = request.getParameter("searchString").trim() == null ? "" : request.getParameter("searchString");
            if (searchString.length() > 100) {
                request.setAttribute("err", "Decision_name khong qua 100 ky tu");
                request.setAttribute("searchString", searchString);
                request.getRequestDispatcher("listdecision.jsp").forward(request, response);
            }
            String optionSearch = request.getParameter("optionSearch");
            DecisionDAO dDAO = new DecisionDAO();
            ArrayList<Decision> List = new ArrayList<>();
            List = dDAO.SearchByName(searchString);
            request.setAttribute("List", List);
            request.setAttribute("searchString", searchString);
            request.setAttribute("optionSearch", optionSearch);
            request.getRequestDispatcher("listdecision.jsp").forward(request, response);
        }

        //Edit
        if (request.getParameter("edit") != null && request.getParameter("edit").equals("Update")) {
            HttpSession session = request.getSession();
            String decision_id = request.getParameter("id");
            String decision_no = request.getParameter("decision_no");
            String decision_name = request.getParameter("decision_name");
            String decision_approve = request.getParameter("decision_approve");
            String decision_create = request.getParameter("decision_create");
            String notes = request.getParameter("notes");
            String curriculum = request.getParameter("curriculum");

            DecisionDAO dDAO = new DecisionDAO();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date decision_createDate = null;
            Date decision_date = null;
            try {
                decision_createDate = dateFormat.parse(decision_create);
                decision_date = dateFormat.parse(decision_approve);
            } catch (ParseException ex) {
                Logger.getLogger(DecisionController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Decision d1 = new Decision(decision_id, decision_no, decision_name, decision_date, notes);

            dDAO.updateDecision(d1);
            Decision d = dDAO.getDecisionByNo(decision_no);

            if (!curriculum.equals("0")) {
                CurriculumDAO cDAO = new CurriculumDAO();
                cDAO.updateDecisionForCurriculum(curriculum, d.getDecision_id());
            }

            CurriculumDAO cDAO = new CurriculumDAO();
            ArrayList<Curriculum> List = cDAO.getListCurriculumNullDecision();
            request.setAttribute("List", List);
            // Loi decision NO
            if (decision_no.length() < 2 || decision_no.length() > 50) {
                Decision d2 = new Decision(decision_id, decision_no, decision_name, decision_date, decision_createDate, decision_id, notes);
                request.setAttribute("err", "Decision no must be length than 2 character and less than 50 character!");
                request.setAttribute("d2", d2);
                request.setAttribute("dateA", decision_approve);
                request.setAttribute("dateC", decision_create);
                request.getRequestDispatcher("decisiondetail.jsp").forward(request, response);
            }
            //Loi decision Name
            if (decision_name.length() < 10 || decision_name.length() > 100) {
                Decision d2 = new Decision(decision_id, decision_no, decision_name, decision_date, decision_createDate, decision_id, notes);
                request.setAttribute("err", "Decision name must be length than 10 character and less than 100 character!");
                request.setAttribute("d2", d2);
                request.setAttribute("dateA", decision_approve);
                request.setAttribute("dateC", decision_create);
                request.getRequestDispatcher("decisiondetail.jsp").forward(request, response);
            }
            //Loi Appvore Date
            if (decision_date.before(decision_createDate)) {
                Decision d2 = new Decision(decision_id, decision_no, decision_name, decision_date, decision_createDate, decision_id, notes);
                request.setAttribute("err", "Decision Approve Date Error");
                request.setAttribute("d2", d2);
                request.setAttribute("dateA", decision_approve);
                request.setAttribute("dateC", decision_create);
                request.getRequestDispatcher("decisiondetail.jsp").forward(request, response);
            }

            request.setAttribute("d", d);
            session.setAttribute("toast", "true");
//            request.setAttribute("done", "Update Decision Thanh Cong");
            request.getRequestDispatcher("decisiondetail.jsp").forward(request, response);
        }

        //Add
        if (request.getParameter("add") != null && request.getParameter("add").equals("Add new")) {
            String decision_no = request.getParameter("decision_no");
            String decision_name = request.getParameter("decision_name");
            String notes = request.getParameter("notes");
            HttpSession session = request.getSession();
            User user_session = (User) session.getAttribute("acc");
            String user_id = user_session.getUserId();
            String create_date = request.getParameter("decision_create");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            DecisionDAO dDAO = new DecisionDAO();
            int id = dDAO.getDecisionIdNext() + 1;
            String decision_id = String.valueOf(id);
            Decision d = new Decision(decision_id, decision_no, decision_name, user_id, notes);

            if (decision_no.length() <= 2 || decision_no.length() > 50) {
                System.out.println(decision_no);
                LocalDate currentDate = LocalDate.now();
                request.setAttribute("date", currentDate);
                request.setAttribute("err", "Decision no must be length than 2 character and less than 50 character!");
                request.setAttribute("d", d);
                request.getRequestDispatcher("newdecision.jsp").forward(request, response);
            } else if (decision_name.length() < 10 || decision_name.length() > 100) {
                LocalDate currentDate = LocalDate.now();
                request.setAttribute("date", currentDate);
                request.setAttribute("err", "Decision name must be length than 10 character and less than 100 character!");
                request.setAttribute("d", d);
                request.getRequestDispatcher("newdecision.jsp").forward(request, response);
            } else if (dDAO.getDecisionByNo(decision_no) == null) {
                System.out.println(create_date);
                dDAO.AddNewDecision(d, create_date);
//                request.setAttribute("done", "Add Decision Thanh Cong");
                session.setAttribute("toast", "true");
                request.getRequestDispatcher("listdecision.jsp").forward(request, response);
            } else {
                LocalDate currentDate = LocalDate.now();
                request.setAttribute("date", currentDate);
                request.setAttribute("err", "Decision đã tồn tại.");
                request.setAttribute("d", d);
                request.getRequestDispatcher("newdecision.jsp").forward(request, response);
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
