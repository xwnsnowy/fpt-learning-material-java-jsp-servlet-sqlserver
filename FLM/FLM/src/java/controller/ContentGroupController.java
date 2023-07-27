package controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import java.util.Arrays;
import model.Curriculum;
import model.CurriculumSubject;
import model.Group;
import model.Subject;
import model.User;

/**
 *
 * @author user
 */
public class ContentGroupController extends HttpServlet {

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
            out.println("<title>Servlet ContentGroupController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContentGroupController at " + request.getContextPath() + "</h1>");
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
            String contentgroupid = request.getParameter("contentgroupid");
            String action = request.getParameter("action");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumById(curriculumid);
            GroupDAO gDAO = new GroupDAO();
            ArrayList<Group> listContents;
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("acc");
            if (action.equals("newcontentgroup")) {
                request.setAttribute("curriculumid", curriculumid);
                request.getRequestDispatcher("addcontent.jsp").forward(request, response);
            }
            if (action.equals("list")) {
                System.out.println(contentgroupid);
                String numberOfContent;
                String thisPageStr = request.getParameter("thisPage");
                int limit = 5;
                int totalPages;
                int thisPage;
                int start;
                if (user != null) {
                    if (user.getRoleName().equalsIgnoreCase("ADMIN")
                            || user.getRoleName().equalsIgnoreCase("CRDD STAFF")
                            || user.getRoleName().equalsIgnoreCase("CRDD HEAD")) {
                        numberOfContent = gDAO.getNumberOfAllContentForAdmin(curriculumid);
                        totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                        thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        start = (thisPage - 1) * limit;
                        listContents = gDAO.getListAllContentsForAdmin(curriculumid, String.valueOf(start), String.valueOf(limit));
                        request.setAttribute("numberOfContent", numberOfContent);
                        request.setAttribute("listContents", listContents);
                        request.setAttribute("c", c);
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("op", "4");
                        request.getRequestDispatcher("contentgrouplist.jsp").forward(request, response);
                    } else {
                        numberOfContent = gDAO.getNumberOfAllContent(curriculumid);
                        totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                        thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        start = (thisPage - 1) * limit;
                        listContents = gDAO.getListAllContents(curriculumid, String.valueOf(start), String.valueOf(limit));
                        request.setAttribute("numberOfContent", numberOfContent);
                        request.setAttribute("listContents", listContents);
                        request.setAttribute("c", c);
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("op", "4");
                        request.getRequestDispatcher("contentgrouplist.jsp").forward(request, response);
                    }
                } else {
                    numberOfContent = gDAO.getNumberOfAllContent(curriculumid);
                    totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                    thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    start = (thisPage - 1) * limit;
                    listContents = gDAO.getListAllContents(curriculumid, String.valueOf(start), String.valueOf(limit));
                    request.setAttribute("numberOfContent", numberOfContent);
                    request.setAttribute("listContents", listContents);
                    request.setAttribute("c", c);
                    request.setAttribute("thisPage", thisPage);
                    request.setAttribute("contentgroupid", contentgroupid);
                    request.setAttribute("curriculumid", curriculumid);
                    request.setAttribute("op", "4");
                    request.getRequestDispatcher("contentgrouplist.jsp").forward(request, response);
                }
            }
            if (action.equals("detail")) {
                if (user != null) {
                    if (user.getRoleName().equalsIgnoreCase("ADMIN")
                            || user.getRoleName().equalsIgnoreCase("CRDD STAFF")
                            || user.getRoleName().equalsIgnoreCase("CRDD HEAD")) {
                        Group g = gDAO.getContentGroupById(contentgroupid);
                        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
                        String numberOfSubjectContent;
                        String thisPageStr = request.getParameter("thisPage");
                        int limit = 5;
                        numberOfSubjectContent = gDAO.getNumberOfSubjectContentForAdmin(contentgroupid);
                        int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubjectContent) / (double) limit);
                        int thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        int start = (thisPage - 1) * limit;
                        ArrayList<CurriculumSubject> listSubjectContent = csDAO.
                                getSubjectContentForAdmin(contentgroupid, String.valueOf(start), String.valueOf(limit), curriculumid);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("numberOfSubjectContent", numberOfSubjectContent);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("c", c);
                        request.setAttribute("g", g);
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("op", "4");
                        request.setAttribute("contentgroupid", contentgroupid);
                        String toast = (String) session.getAttribute("toast");
                        request.setAttribute("toast", toast);
                        System.out.println("toast: " + toast);
                        request.getRequestDispatcher("contentgroupdetail.jsp").forward(request, response);
                    } else {
                        Group g = gDAO.getContentGroupById(contentgroupid);
                        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
                        String numberOfSubjectContent;
                        String thisPageStr = request.getParameter("thisPage");
                        int limit = 5;
                        numberOfSubjectContent = gDAO.getNumberOfSubjectContent(contentgroupid);
                        int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubjectContent) / (double) limit);
                        int thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        int start = (thisPage - 1) * limit;
                        ArrayList<CurriculumSubject> listSubjectContent
                                = csDAO.getSubjectContent(contentgroupid, String.valueOf(start), String.valueOf(limit), curriculumid);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("numberOfSubjectContent", numberOfSubjectContent);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("c", c);
                        request.setAttribute("g", g);
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("op", "4");
                        request.setAttribute("contentgroupid", contentgroupid);
                        String toast = (String) session.getAttribute("toast");
                        request.setAttribute("toast", toast);
                        System.out.println("toast: " + toast);
                        request.getRequestDispatcher("contentgroupdetail.jsp").forward(request, response);
                    }
                } else {
                    Group g = gDAO.getContentGroupById(contentgroupid);
                    CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
                    String numberOfSubjectContent;
                    String thisPageStr = request.getParameter("thisPage");
                    int limit = 5;
                    numberOfSubjectContent = gDAO.getNumberOfSubjectContent(contentgroupid);
                    int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubjectContent) / (double) limit);
                    int thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    int start = (thisPage - 1) * limit;
                    ArrayList<CurriculumSubject> listSubjectContent
                            = csDAO.getSubjectContent(contentgroupid, String.valueOf(start), String.valueOf(limit), curriculumid);
                    request.setAttribute("listSubjectContent", listSubjectContent);
                    request.setAttribute("numberOfSubjectContent", numberOfSubjectContent);
                    request.setAttribute("listSubjectContent", listSubjectContent);
                    request.setAttribute("contentgroupid", contentgroupid);
                    request.setAttribute("curriculumid", curriculumid);
                    request.setAttribute("c", c);
                    request.setAttribute("g", g);
                    request.setAttribute("thisPage", thisPage);
                    request.setAttribute("op", "4");
                    request.setAttribute("contentgroupid", contentgroupid);
                    String toast = (String) session.getAttribute("toast");
                    request.setAttribute("toast", toast);
                    System.out.println("toast: " + toast);
                    request.getRequestDispatcher("contentgroupdetail.jsp").forward(request, response);
                }
            }
            if (action.equals("newsubjectcontent")) {
                SubjectDAO sDAO = new SubjectDAO();
                String numberOfSubject;
                String thisPageStr = request.getParameter("thisPage");
                int limit = 10;
                numberOfSubject = sDAO.getNumberOfAllSubject();
                int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubject) / (double) limit);
                int thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                int start = (thisPage - 1) * limit;
                ArrayList<Subject> listSubject = sDAO.getListOfAllSubject(String.valueOf(start), String.valueOf(limit));
                request.setAttribute("numberOfSubject", numberOfSubject);
                request.setAttribute("listSubject", listSubject);
                request.setAttribute("c", c);
                request.setAttribute("thisPage", thisPage);
                request.setAttribute("op", "4");
                request.setAttribute("contentgroupid", contentgroupid);
                request.setAttribute("curriculumid", curriculumid);
                request.getRequestDispatcher("addsubjectcontent.jsp").forward(request, response);
            }
            if (action.equals("updatecontentgroup")) {
                request.setAttribute("curriculumid", curriculumid);
                request.setAttribute("contentgroupid", contentgroupid);
                request.setAttribute("contentgroupname", gDAO.getGroupNameByGroupId(contentgroupid).getName());
                request.getRequestDispatcher("updatecontent.jsp").forward(request, response);
            }
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("error doGet ContentGroupController: " + e.getMessage());
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
            String btnAddContentGroup = request.getParameter("btnAddContentGroup");
            String searchBy = request.getParameter("selectFilter");
            String search = request.getParameter("search");
            String curriculumid = request.getParameter("curriculumid");
            String contentgroupid = request.getParameter("contentgroupid");
            String contentgroupname = request.getParameter("contentgroupname");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumById(curriculumid);
            SubjectDAO sDAO = new SubjectDAO();
            CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
            ArrayList<Group> listContents;
            HttpSession session = request.getSession();
            GroupDAO gDAO = new GroupDAO();
            User user = (User) session.getAttribute("acc");
            if (action.equals("detail")) {
                if (user != null) {
                    if (user.getRoleName().equalsIgnoreCase("ADMIN")
                            || user.getRoleName().equalsIgnoreCase("CRDD STAFF")
                            || user.getRoleName().equalsIgnoreCase("CRDD HEAD")) {
                        Group g = gDAO.getContentGroupById(contentgroupid);
                        String numberOfSubjectContent;
                        String thisPageStr = request.getParameter("thisPage");
                        int limit = 5;
                        numberOfSubjectContent = gDAO.getNumberOfSubjectContentForAdmin(contentgroupid);
                        int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubjectContent) / (double) limit);
                        int thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        int start = (thisPage - 1) * limit;
                        ArrayList<CurriculumSubject> listSubjectContent = csDAO.
                                getSubjectContentForAdmin(contentgroupid, String.valueOf(start), String.valueOf(limit), curriculumid);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("numberOfSubjectContent", numberOfSubjectContent);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("c", c);
                        request.setAttribute("g", g);
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("op", "4");
                        request.setAttribute("contentgroupid", contentgroupid);
                        String toast = (String) session.getAttribute("toast");
                        request.setAttribute("toast", toast);
                        System.out.println("toast: " + toast);
                        request.getRequestDispatcher("contentgroupdetail.jsp").forward(request, response);
                    } else {
                        Group g = gDAO.getContentGroupById(contentgroupid);
                        String numberOfSubjectContent;
                        String thisPageStr = request.getParameter("thisPage");
                        int limit = 5;
                        numberOfSubjectContent = gDAO.getNumberOfSubjectContent(contentgroupid);
                        int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubjectContent) / (double) limit);
                        int thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        int start = (thisPage - 1) * limit;
                        ArrayList<CurriculumSubject> listSubjectContent
                                = csDAO.getSubjectContent(contentgroupid, String.valueOf(start), String.valueOf(limit), curriculumid);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("numberOfSubjectContent", numberOfSubjectContent);
                        request.setAttribute("listSubjectContent", listSubjectContent);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("c", c);
                        request.setAttribute("g", g);
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("op", "4");
                        request.setAttribute("contentgroupid", contentgroupid);
                        String toast = (String) session.getAttribute("toast");
                        request.setAttribute("toast", toast);
                        System.out.println("toast: " + toast);
                        request.getRequestDispatcher("contentgroupdetail.jsp").forward(request, response);
                    }
                } else {
                    Group g = gDAO.getContentGroupById(contentgroupid);
                    String numberOfSubjectContent;
                    String thisPageStr = request.getParameter("thisPage");
                    int limit = 5;
                    numberOfSubjectContent = gDAO.getNumberOfSubjectContent(contentgroupid);
                    int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubjectContent) / (double) limit);
                    int thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    int start = (thisPage - 1) * limit;
                    ArrayList<CurriculumSubject> listSubjectContent
                            = csDAO.getSubjectContent(contentgroupid, String.valueOf(start), String.valueOf(limit), curriculumid);
                    request.setAttribute("listSubjectContent", listSubjectContent);
                    request.setAttribute("numberOfSubjectContent", numberOfSubjectContent);
                    request.setAttribute("listSubjectContent", listSubjectContent);
                    request.setAttribute("contentgroupid", contentgroupid);
                    request.setAttribute("curriculumid", curriculumid);
                    request.setAttribute("c", c);
                    request.setAttribute("g", g);
                    request.setAttribute("thisPage", thisPage);
                    request.setAttribute("op", "4");
                    request.setAttribute("contentgroupid", contentgroupid);
                    String toast = (String) session.getAttribute("toast");
                    request.setAttribute("toast", toast);
                    System.out.println("toast: " + toast);
                    request.getRequestDispatcher("contentgroupdetail.jsp").forward(request, response);
                }
            }
            if (action.equals("newcontentgroup")) {
                if (btnAdd != null) {
                    String comboname = request.getParameter("groupname");
                    if (gDAO.checkDuplicateComboName(comboname) != null) {
                        request.setAttribute("groupNameNew", comboname);
                        request.setAttribute("op", "4");
                        request.setAttribute("alertError", "Group NAME is available. Please enter another NAME !");
                        request.getRequestDispatcher("addcontent.jsp").forward(request, response);
                    }
                    if (gDAO.checkDuplicateComboName(comboname) == null) {
                        int newId = Integer.parseInt(gDAO.getComboIdMax().getId()) + 1;
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        gDAO.addCombo(String.valueOf(newId), comboname, dtf.format(now), user.getUserId());
                        request.setAttribute("groupNameNew", comboname);
                        request.setAttribute("op", "4");
                        request.setAttribute("alert", "Successfully added new COMBO !");
                        request.getRequestDispatcher("/view/common/combolistfull.jsp").forward(request, response);
                    }
                }
            }
            if (action.equals("changestatus")) {
                String contentId = request.getParameter("contentgroupid");
                String curriId = request.getParameter("curriculumid");
                String hiddenIsActive = request.getParameter("hiddenIsActive");
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime now = LocalDateTime.now();
                System.out.println("Content ID: " + contentId);
                System.out.println("Curriculum ID: " + curriId);
                System.out.println("Active: " + hiddenIsActive);
                if (hiddenIsActive.equals("INACTIVE")) { // Thay đổi thành ACTIVE
                    System.out.println("Updating to active...");
                    gDAO.updateIsactiveContent(contentId, dtf.format(now), user.getUserId());
                } else if (hiddenIsActive.equals("ACTIVE")) {
                    System.out.println("Updating to inactive...");
                    gDAO.updateDeactiveContent(contentId, dtf.format(now), user.getUserId());
                }
                session.setAttribute("toast", "true");
                response.sendRedirect("/FLM_NEW/view/common/contentgroup?action=list&"
                        + "&curriculumid=" + curriId);
//                request.getRequestDispatcher("contentgrouplist.jsp").forward(request, response);
            }
            if (action.equals("list")) {
                if (user != null) {
                    if (user.getRoleName().equalsIgnoreCase("ADMIN")
                            || user.getRoleName().equalsIgnoreCase("CRDD STAFF")
                            || user.getRoleName().equalsIgnoreCase("CRDD HEAD")) {
                        String numberOfContent;
                        String thisPageStr = request.getParameter("thisPage");
                        int limit = 5;
                        int thisPage;
                        String[] isActiveList = request.getParameterValues("isActiveList");
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        if (isActiveList != null) {
                            for (String contentgroupId : isActiveList) {
                                String isActive = request.getParameter("isActiveValue_" + contentgroupId);
                                if (isActive.equals("TRUE")) {
                                    gDAO.updateIsactiveContent(contentgroupId, dtf.format(now), user.getUserId());
                                } else {
                                    gDAO.updateDeactiveContent(contentgroupId, dtf.format(now), user.getUserId());
                                }
                            }
                        }
                        if (searchBy.equalsIgnoreCase("name")) {
                            numberOfContent = gDAO.getNumberOfContentForAdmin(curriculumid, search);
                            int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                            thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                            int start = (thisPage - 1) * limit;
                            listContents = gDAO.getListContentsByNameForAdmin(curriculumid, search, String.valueOf(start), String.valueOf(limit));
                        } else if (searchBy.equalsIgnoreCase("active")) {
                            numberOfContent = gDAO.getNumberOfActiveContent(curriculumid);
                            int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                            thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                            int start = (thisPage - 1) * limit;
                            listContents = gDAO.getListActiveContents(curriculumid, String.valueOf(start), String.valueOf(limit));
                        } else if (searchBy.equalsIgnoreCase("deactive")) {
                            numberOfContent = gDAO.getNumberOfDeactiveContent(curriculumid);
                            int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                            thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                            int start = (thisPage - 1) * limit;
                            listContents = gDAO.getListDeactiveContents(curriculumid, String.valueOf(start), String.valueOf(limit));
                        } else {
                            numberOfContent = gDAO.getNumberOfAllContentForAdmin(curriculumid);
                            int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                            thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                            int start = (thisPage - 1) * limit;
                            listContents = gDAO.getListAllContentsForAdmin(curriculumid, String.valueOf(start), String.valueOf(limit));
                        }
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("numberOfContent", numberOfContent);
                        request.setAttribute("listContents", listContents);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("c", c);
                        request.setAttribute("op", "4");
                        request.setAttribute("selectFilter", searchBy);
                        request.setAttribute("search", search);
                        request.getRequestDispatcher("contentgrouplist.jsp").forward(request, response);
                    } else {
                        String numberOfContent;
                        String thisPageStr = request.getParameter("thisPage");
                        int limit = 5;
                        int thisPage;
                        if (searchBy.equalsIgnoreCase("name")) {
                            numberOfContent = gDAO.getNumberOfContent(curriculumid, search);
                            int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                            thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                            int start = (thisPage - 1) * limit;
                            listContents = gDAO.getListContentsByName(curriculumid, search, String.valueOf(start), String.valueOf(limit));
                        } else {
                            numberOfContent = gDAO.getNumberOfAllContent(curriculumid);
                            int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                            thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                            int start = (thisPage - 1) * limit;
                            listContents = gDAO.getListAllContents(curriculumid, String.valueOf(start), String.valueOf(limit));
                        }
                        request.setAttribute("thisPage", thisPage);
                        request.setAttribute("numberOfContent", numberOfContent);
                        request.setAttribute("listContents", listContents);
                        request.setAttribute("contentgroupid", contentgroupid);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("c", c);
                        request.setAttribute("op", "4");
                        request.setAttribute("selectFilter", searchBy);
                        request.setAttribute("search", search);
                        request.getRequestDispatcher("contentgrouplist.jsp").forward(request, response);
                    }
                } else {
                    String numberOfContent;
                    String thisPageStr = request.getParameter("thisPage");
                    int limit = 5;
                    int thisPage;
                    if (searchBy.equalsIgnoreCase("name")) {
                        numberOfContent = gDAO.getNumberOfContent(curriculumid, search);
                        int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                        thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        int start = (thisPage - 1) * limit;
                        listContents = gDAO.getListContentsByName(curriculumid, search, String.valueOf(start), String.valueOf(limit));
                    } else {
                        numberOfContent = gDAO.getNumberOfAllContent(curriculumid);
                        int totalPages = (int) Math.ceil(Integer.parseInt(numberOfContent) / (double) limit);
                        thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                        int start = (thisPage - 1) * limit;
                        listContents = gDAO.getListAllContents(curriculumid, String.valueOf(start), String.valueOf(limit));
                    }
                    request.setAttribute("thisPage", thisPage);
                    request.setAttribute("numberOfContent", numberOfContent);
                    request.setAttribute("listContents", listContents);
                    request.setAttribute("contentgroupid", contentgroupid);
                    request.setAttribute("curriculumid", curriculumid);
                    request.setAttribute("c", c);
                    request.setAttribute("op", "4");
                    request.setAttribute("selectFilter", searchBy);
                    request.setAttribute("search", search);
                    request.getRequestDispatcher("contentgrouplist.jsp").forward(request, response);
                }
            }
            if (action.equals("newsubjectcontent")) {
                String numberOfSubject;
                String thisPageStr = request.getParameter("thisPage");
                int limit = 10;
                int thisPage;
                ArrayList<Subject> listSubjects;
                if (searchBy.equalsIgnoreCase("code")) {
                    numberOfSubject = sDAO.getNumberOfSubjectByCode(search);
                    int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubject) / (double) limit);
                    thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    int start = (thisPage - 1) * limit;
                    listSubjects = sDAO.getListOfSubjectByCode(search, String.valueOf(start), String.valueOf(limit));
                } else if (searchBy.equalsIgnoreCase("name")) {
                    numberOfSubject = sDAO.getNumberOfSubjectByName(search);
                    int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubject) / (double) limit);
                    thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    int start = (thisPage - 1) * limit;
                    listSubjects = sDAO.getListSubjectContentsByName(search, String.valueOf(start), String.valueOf(limit));
                } else if (searchBy.equalsIgnoreCase("active")) {
                    numberOfSubject = sDAO.getNumberOfActiveSubjectContent();
                    int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubject) / (double) limit);
                    thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    int start = (thisPage - 1) * limit;
                    listSubjects = sDAO.getListActiveSubjectContents(String.valueOf(start), String.valueOf(limit));
                } else if (searchBy.equalsIgnoreCase("deactive")) {
                    numberOfSubject = sDAO.getNumberOfDeactiveSubjectContent();
                    int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubject) / (double) limit);
                    thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    int start = (thisPage - 1) * limit;
                    listSubjects = sDAO.getListDeactiveSubjectContents(String.valueOf(start), String.valueOf(limit));
                } else {
                    numberOfSubject = sDAO.getNumberOfAllSubject();
                    int totalPages = (int) Math.ceil(Integer.parseInt(numberOfSubject) / (double) limit);
                    thisPage = Math.min(Math.max((thisPageStr == null) ? 1 : Integer.parseInt(thisPageStr), 1), totalPages);
                    int start = (thisPage - 1) * limit;
                    listSubjects = sDAO.getListOfAllSubject(String.valueOf(start), String.valueOf(limit));
                }
                request.setAttribute("numberOfSubject", numberOfSubject);
                request.setAttribute("listSubject", listSubjects);
                request.setAttribute("c", c);
                request.setAttribute("thisPage", thisPage);
                request.setAttribute("op", "4");
                request.setAttribute("search", search);
                request.setAttribute("selectFilter", searchBy);
                request.setAttribute("contentgroupid", contentgroupid);
                request.setAttribute("curriculumid", curriculumid);
                request.getRequestDispatcher("addsubjectcontent.jsp").forward(request, response);
            }
            if (action.equals("add")) {
                String btnAddListSubject = request.getParameter("btnAddListSubject");
                if (btnAddListSubject != null) {
                    String[] subjectIds = request.getParameterValues("subjectId");
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    if (subjectIds != null) {
                        for (String subjectId : subjectIds) {
                            if (sDAO.checkSubjectInCurriculum(curriculumid, subjectId)) {
                                csDAO.updateContentInCurriculumSubject(contentgroupid, curriculumid, subjectId, dtf.format(now), user.getUserId());
                            } else {
                                csDAO.addSubjectAndContentToCurriculumSubject(contentgroupid, curriculumid, subjectId, dtf.format(now), user.getUserId());
                            }
                        }
                    }
                }
                session.setAttribute("toast", "true");
                response.sendRedirect("/FLM_NEW/view/common/contentgroup?action=detail&"
                        + "contentgroupid="
                        + contentgroupid
                        + "&curriculumid=" + curriculumid);
            }
            if (action.equals("newcontentgroup")) {
                if (btnAddContentGroup != null) {
                    if (gDAO.checkDuplicateContentName(contentgroupname) == null) {
                        int newId = Integer.parseInt(gDAO.getComboIdMax().getId()) + 1;
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        LocalDateTime now = LocalDateTime.now();
                        gDAO.addContentGroupInCurriculum(String.valueOf(newId), contentgroupname, curriculumid, dtf.format(now), user.getUserId());
                        session.setAttribute("toast", "true");
                        response.sendRedirect("/FLM_NEW/view/common/contentgroup?action=list&"
                                + "&curriculumid=" + curriculumid);
                    } else {
                        request.setAttribute("contentgroupname", contentgroupname);
                        request.setAttribute("curriculumid", curriculumid);
                        request.setAttribute("op", "4");
                        request.setAttribute("c", c);
                        request.setAttribute("alertError", "Content Group NAME is already available. Please enter another Content Group NAME !");
                        request.getRequestDispatcher("addcontent.jsp").forward(request, response);
                    }
                }
            }
            if (action.equals("deletesubjectcontent")) {
                String subjectId = request.getParameter("subjectcontentid");
                csDAO.deleteContentGroup(subjectId, curriculumid);
                session.setAttribute("toast", "true");
                response.sendRedirect("/FLM_NEW/view/common/contentgroup?action=detail&"
                        + "contentgroupid="
                        + contentgroupid
                        + "&curriculumid=" + curriculumid);
            }
            if (action.equals("updatecontentgroup")) {
                String id = request.getParameter("contentgroupid");
                String contentgroupnamenew = request.getParameter("contentgroupnamenew");
                String btnUpdateContentGroup = request.getParameter("btnUpdateContentGroup");
                if (btnUpdateContentGroup != null) {
                    gDAO.updateContentGroup(contentgroupnamenew, id);
                    request.setAttribute("curriculumid", curriculumid);
                    request.setAttribute("contentgroupid", contentgroupid);
                    request.setAttribute("contentgroupname", gDAO.getGroupNameByGroupId(contentgroupid).getName());
                    session.setAttribute("toast", "true");
                    response.sendRedirect("/FLM_NEW/view/common/contentgroup?action=list&"
                            + "&curriculumid=" + curriculumid);
                }

            }
        } catch (ServletException | IOException | NumberFormatException e) {
            System.out.println("error doPost ContentGroupController: " + e.getMessage());
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
