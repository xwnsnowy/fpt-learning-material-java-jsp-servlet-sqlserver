/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CurriculumDAO;
import dao.CurriculumSubjectDAO;
import dao.GroupDAO;
import dao.SubjectDAO;
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
import model.CurriculumSubject;
import model.Group;
import model.Subject;
import model.User;

/**
 *
 * @author user
 */
public class GroupController extends HttpServlet {

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
            out.println("<title>Servlet ComboController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ComboController at " + request.getContextPath() + "</h1>");
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
            if (action.equals("combolist")) {
                ArrayList<Group> listCombo = gDAO.getListComboByCurriculumId(c.getCurriculum_id());
                int page, numperpage = 6;
                int sizePage = listCombo.size();
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
                ArrayList<Group> listPage = gDAO.getListByPage(listCombo, start, Math.min(page * numperpage, endPage));
                request.setAttribute("sizePage", sizePage);
                request.setAttribute("listPage", listPage);
                request.setAttribute("page", page);
                request.setAttribute("numPage", numPage);
                request.setAttribute("c", c);
                request.setAttribute("op", "4");
                request.getRequestDispatcher("combolist.jsp").forward(request, response);
            }
            
            if (action.equals("combodetail")) {
                Group g = gDAO.getComboByComboIdAdmin(comboid);
                CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
                ArrayList<CurriculumSubject> listSubjectCombo = csDAO.getSubjectCombo(comboid);
                int page, numperpage = 6;
                int sizePage = listSubjectCombo.size();
                int numPage = (sizePage % 6 == 0 ? (sizePage / 6) : ((sizePage / 6)) + 1);
                String xpage = request.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start;
                int endPage = Math.min(page * numperpage, sizePage);
                start = (page - 1) * numperpage;
                ArrayList<CurriculumSubject> listPage = csDAO.getListByPage(listSubjectCombo, start, endPage);
                request.setAttribute("listPage", listPage);
                request.setAttribute("sizePage", sizePage);
                request.setAttribute("page", page);
                request.setAttribute("numPage", numPage);
                request.setAttribute("g", g);
                request.setAttribute("comboid", comboid);
                request.setAttribute("c", c);
                request.setAttribute("op", "4");
                request.getRequestDispatcher("combodetail.jsp").forward(request, response);
            }
            if (action.equals("newcombo")) {
                request.setAttribute("curriculumid", curriculumid);
                request.getRequestDispatcher("addcombo.jsp").forward(request, response);
            }
            if (action.equals("updatecomboincurriculu")) {
                Group gAdmin = gDAO.getComboByComboIdAdmin(comboid);
                ArrayList<Curriculum> listCurriculum = cDAO.getListCurriculumWhereNot(curriculumid);
                request.setAttribute("listCurriculum", listCurriculum);
                String combo_subject = gAdmin.getCombo_subject();
                for (Curriculum curriculum : listCurriculum) {
                    System.out.println(curriculum);
                }
                combo_subject = "1".equals(combo_subject) ? "TRUE" : "FALSE";
                request.setAttribute("combo_subject", combo_subject);
                System.out.println("combo_subject: " + combo_subject);
                System.out.println(c);
                request.setAttribute("combo_subject", combo_subject);
                request.setAttribute("comboid", comboid);
                request.setAttribute("g", gAdmin);
                request.setAttribute("gId", gAdmin.getId());
                request.setAttribute("c", c);
                request.setAttribute("op", "4");
                request.getRequestDispatcher("updatecombo.jsp").forward(request, response);
            }
            if (action.equals("updatecombo")) {
                Group gAdmin = gDAO.getComboByComboIdAdmin(comboid);
                ArrayList<Curriculum> listCurriculum = cDAO.getListCurriculumWhereNot(curriculumid);
                request.setAttribute("listCurriculum", listCurriculum);
                String combo_subject = gAdmin.getCombo_subject();
                for (Curriculum curriculum : listCurriculum) {
                    System.out.println(curriculum);
                }
                combo_subject = "1".equals(combo_subject) ? "TRUE" : "FALSE";
                request.setAttribute("combo_subject", combo_subject);
                System.out.println("combo_subject: " + combo_subject);
                System.out.println(c);
                request.setAttribute("combo_subject", combo_subject);
                request.setAttribute("comboid", comboid);
                request.setAttribute("g", gAdmin);
                request.setAttribute("gId", gAdmin.getId());
                request.setAttribute("c", c);
                request.setAttribute("op", "4");
                request.getRequestDispatcher("updatecombo.jsp").forward(request, response);
            }
         
        } catch (ServletException | IOException e) {
            System.out.println("loi doGet ComboController: " + e.getMessage());
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
          
            if (btnAddInCurriculum != null) {
                String isActive = request.getParameter("isActive");
                isActive = isActive == null ? "0" : "1";
                String comboname = request.getParameter("groupname");
                if (gDAO.checkDuplicateComboName(comboname) != null) {
                    request.setAttribute("groupNameNew", comboname);
                    request.setAttribute("curriculumIdNew", curriculumidcombo);
                    ArrayList<Curriculum> listCurriculum = cDAO.getListCurriculum();
                    request.setAttribute("listCurriculum", listCurriculum);
                    request.setAttribute("op", "4");
                    isActive = "1".equals(isActive) ? "TRUE" : "FALSE";
                    request.setAttribute("isActive", isActive);
                    request.setAttribute("alertError", "Group NAME is available. Please enter another NAME !");
                    request.getRequestDispatcher("addcombo.jsp").forward(request, response);
                }
                if (gDAO.checkDuplicateComboName(comboname) == null) {
                    int newId = Integer.parseInt(gDAO.getComboIdMax().getId()) + 1;
                    HttpSession session = request.getSession();
                    User user = (User) session.getAttribute("acc");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("YYYY-MM-DD HH:MI:SS");
                    LocalDateTime now = LocalDateTime.now();
                    gDAO.addCombo(String.valueOf(newId), comboname, dtf.format(now), user.getRoleName());
                    gDAO.addComboToGroupCurriculum(String.valueOf(newId), curriculumidcombo, dtf.format(now), user.getRoleName());
                    request.setAttribute("groupNameNew", comboname);
                    request.setAttribute("curriculumIdNew", curriculumidcombo);
                    ArrayList<Curriculum> listCurriculum = cDAO.getListCurriculum();
                    request.setAttribute("curriculumCodeNew", cDAO.getCurriculumCodeByCurriculumId(curriculumidcombo).getCode());
                    request.setAttribute("op", "4");
                    isActive = "1".equals(isActive) ? "TRUE" : "FALSE";
                    request.setAttribute("isActive", isActive);
                    request.setAttribute("listCurriculum", listCurriculum);
                    request.setAttribute("alert", "Successfully added new COMBO !");
                    request.getRequestDispatcher("addcombo.jsp").forward(request, response);
                }
            }
            if (btnUpdate != null) {
                String groupname = request.getParameter("groupname");
                String curriculumid = request.getParameter("curriculumid");
                String isActive = request.getParameter("isActive");
                String gId = request.getParameter("gId");
                System.out.println(isActive);
                System.out.println("----");
                isActive = "true".equals(isActive) ? "1" : "0";
                gDAO.updateCombo(groupname, isActive, gId, curriculumid);
                //return values
                ArrayList<Curriculum> listCurriculum = cDAO.getListCurriculumWhereNot(curriculumid);
                request.setAttribute("gId", gId);
                request.setAttribute("gname", groupname);
                request.setAttribute("curriculumCodeNew", cDAO.getCurriculumCodeByCurriculumId(curriculumidcombo).getCode());
                request.setAttribute("comboid", comboid);
                request.setAttribute("curriculumid", curriculumid);
                isActive = "1".equals(isActive) ? "TRUE" : "FALSE";
                request.setAttribute("combo_subject", isActive);
                request.setAttribute("op", "4");
                request.setAttribute("listCurriculum", listCurriculum);
                request.setAttribute("alert", "Successfully update COMBO !");
                request.getRequestDispatcher("updatecombo.jsp").forward(request, response);
            }

        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("error Group Controller: " + e.getMessage());
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
