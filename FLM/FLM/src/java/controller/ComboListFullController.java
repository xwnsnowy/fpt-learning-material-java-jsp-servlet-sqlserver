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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import model.Curriculum;
import model.Group;
import model.User;

/**
 *
 * @author user
 */
public class ComboListFullController extends HttpServlet {

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
            out.println("<title>Servlet ComboListFullController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComboListFullController at " + request.getContextPath() + "</h1>");
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
        try {
            String curriculumid = request.getParameter("curriculumid");
            String action = request.getParameter("action");
            String comboid = request.getParameter("comboid");
            String contentgroupid = request.getParameter("contentgroupid");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumById(curriculumid);
            GroupDAO gDAO = new GroupDAO();
            if (action.equals("newcombo")) {
                request.getRequestDispatcher("addcombo.jsp").forward(request, response);
            }
            if (action.equals("list")) {
                request.setAttribute("listCombos", null);
                request.setAttribute("alert", "");
                request.setAttribute("thisPage", "0");
                request.getRequestDispatcher("combolistfull.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            System.out.println("error doGet ComboListFullController: " + e.getMessage());
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
        try {
            String action = request.getParameter("action");
            String btnAdd = request.getParameter("btnAdd");
            String btnAddInCurriculum = request.getParameter("btnAddInCurriculum");
            String btnUpdate = request.getParameter("btnUpdate");
            String btnSearch = request.getParameter("btnSearch");
            String comboid = request.getParameter("comboid");
            String searchBy = request.getParameter("selectFilter");
            String search = request.getParameter("search");
            String thisPage = request.getParameter("thisPage");
            ArrayList<Group> listCombos = new ArrayList<>();
            CurriculumDAO cDAO = new CurriculumDAO();
            GroupDAO gDAO = new GroupDAO();
            String curriculumidcombo = request.getParameter("curriculumid");
            if (action.equals("newcombo")) {
                if (btnAdd != null) {
                    String comboname = request.getParameter("groupname");
                    if (gDAO.checkDuplicateComboName(comboname) != null) {
                        request.setAttribute("groupNameNew", comboname);
                        request.setAttribute("op", "4");
                        request.setAttribute("alertError", "Group NAME is available. Please enter another NAME !");
                        request.getRequestDispatcher("addcombo.jsp").forward(request, response);
                    }
                    if (gDAO.checkDuplicateComboName(comboname) == null) {
                        int newId = Integer.parseInt(gDAO.getComboIdMax().getId()) + 1;
                        HttpSession session = request.getSession();
                        User user = (User) session.getAttribute("acc");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        gDAO.addCombo(String.valueOf(newId), comboname, dtf.format(now), user.getUserId());
                        session.setAttribute("toast", "true");
                        response.sendRedirect("/FLM_NEW/view/common/combolistfull?action=list");
                    }
                }
            }
            if (action.equals("list")) {
                String numberOfCombo = "0";
                numberOfCombo = gDAO.getNumberOfCombosForAdmin(search);
                int start = 0;
                int limit = 10;
                start = ((Integer.parseInt(thisPage) - 1) * limit);

                if (thisPage.equals("0")) {
                    request.setAttribute("thisPage", "1");
                    start = 0;
                } else {
                    request.setAttribute("thisPage", thisPage);
                }
                if (Integer.parseInt(numberOfCombo) < start) {
                    request.setAttribute("thisPage", Math.floor(Integer.parseInt(numberOfCombo) / 10) + 1);
                    start = (int) (Math.floor(Integer.parseInt(numberOfCombo) / 10)) * limit;
                }
                if (searchBy.equalsIgnoreCase("name")) {
                    listCombos = gDAO.getListCombosByName(search, String.valueOf(start), String.valueOf(limit));
                }

                request.setAttribute("selectFilter", searchBy);
                request.setAttribute("numberOfCombo", numberOfCombo);
                request.setAttribute("search", search);
                request.setAttribute("listCombos", listCombos);
//            request.setAttribute("c", c);
                request.setAttribute("op", "4");
                request.getRequestDispatcher("combolistfull.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("error doPost ComboListFullController: " + e.getMessage());
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
