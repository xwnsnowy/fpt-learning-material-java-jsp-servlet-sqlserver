/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.CurriculumSubjectDAO;
import dao.CurriculumDAO;
import dao.GroupDAO;
import dao.SubjectDAO;
import dao.SyllabusDAO;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import model.CurriculumSubject;
import model.Curriculum;
import java.util.ArrayList;
import java.util.Date;
import model.Group;
import model.Subject;
import model.Syllabus;
import model.User;

/**
 *
 * @author Admin
 */
public class CurriculumSubjectController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String curriculumId = req.getParameter("cId");
        CurriculumSubjectDAO cSD = new CurriculumSubjectDAO();
        HttpSession session = req.getSession();
        User acc = (User) session.getAttribute("acc");
        if (action.equals("edit")) {
            String sId = req.getParameter("cSId");
            String cId = req.getParameter("cId");
            String isCombo = req.getParameter("isCombo");
            String semester = req.getParameter("semester");
            String noCredit = req.getParameter("noCredit");
            String isActive = req.getParameter("isActive");
            isActive = isActive == null ? "0" : "1";
            String[] listS = req.getParameterValues("listS");
            String[] listSl = req.getParameterValues("selectSyllabus");
            String[] listC = req.getParameterValues("content");

            String updateBy = acc.getUserId();
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            String updateAt = spf.format(date);
            session.setAttribute("toast", "true");

            if (isCombo.equalsIgnoreCase("true")) {
                cSD.updateCurriculumSubject(sId, cId, semester, noCredit, isActive, "", "", updateBy, updateAt);
                for (int i = 0; i < listS.length; i++) {
                    cSD.updateCurriculumSubject(listS[i], cId, semester, "", isActive, listSl[i], listC[i], updateBy, updateAt);
                }
            } else {
                cSD.updateCurriculumSubject(sId, cId, semester, noCredit, isActive, listSl[0], listC[0], updateBy, updateAt);
            }
            resp.sendRedirect("/FLM_NEW/view/common/curriculumsubject?action=list&cId=" + curriculumId + "&selectFilter=&search=");

        }

        if (action.equals("add")) {
            if (req.getParameter("btn") == null) {
                String selectSubject = req.getParameter("selectSubject");
                SyllabusDAO slD = new SyllabusDAO();
                ArrayList<Syllabus> listSyllabus = new ArrayList<>();

                listSyllabus = slD.getListSyllabusBySubjectCode(selectSubject);

                GroupDAO gD = new GroupDAO();
                SubjectDAO sD = new SubjectDAO();
                ArrayList<Subject> listSubject = sD.getListOfSubjectForCuriculum(curriculumId);

                CurriculumDAO cD = new CurriculumDAO();
                Curriculum curiculum = cD.getCurriculumById(curriculumId);

                ArrayList<Group> listContent = gD.getListAllContents(curriculumId, "0", gD.getNumberOfActiveContent(curriculumId));

                req.setAttribute("listContent", listContent);

                req.setAttribute("listSyllabus", listSyllabus);
                req.setAttribute("c", curiculum);
                req.setAttribute("selectSubject", selectSubject);
                req.setAttribute("listSubject", listSubject);
                req.setAttribute("alert", "");
                req.setAttribute("op", 4);
                req.getRequestDispatcher("addcurriculumsubject.jsp").forward(req, resp);
            } else {
                String cId = req.getParameter("cId");
                String selectSubject = req.getParameter("selectSubject");
                String selectSyllabus = req.getParameter("selectSyllabus");
                String content = req.getParameter("content");
                String semester = req.getParameter("semester");
                String noCredit = req.getParameter("noCredit");
                String isActive = req.getParameter("isActive");
                isActive = isActive == null ? "0" : "1";
                String alert = "";
                try {
                    int checkNocrredit = Integer.parseInt(noCredit);
                    if (checkNocrredit < 1) {
                        if (alert.isEmpty()) {
                            alert += "NoCredit must be integers greater than 0!";
                        } else {
                            alert += "<br> NoCredit must be integers greater than 0!";
                        }
                    }
                } catch (NumberFormatException e) {
                    if (alert.isEmpty()) {
                        alert += "NoCredit must be integers greater than 0!";
                    } else {
                        alert += "<br> NoCredit must be integers greater than 0!";
                    }

                }
                try {
                    int checkSemester = Integer.parseInt(semester);
                    if (checkSemester < 0) {
                        if (alert.isEmpty()) {
                            alert += "Semester must be integers equal or greater than 0!";
                        } else {
                            alert += "<br> Semester must be integers equal or greater than 0!";
                        }
                    }
                } catch (NumberFormatException e) {
                    if (alert.isEmpty()) {
                        alert += "Semester must be integers equal or greater than 0!";
                    } else {
                        alert += "<br> Semester must be integers equal or greater than 0!";
                    }
                }

                if (!alert.isEmpty()) {
                    SyllabusDAO slD = new SyllabusDAO();
                    ArrayList<Syllabus> listSyllabus = new ArrayList<>();

                    listSyllabus = slD.getListSyllabusBySubjectCode(selectSubject);

                    GroupDAO gD = new GroupDAO();
                    SubjectDAO sD = new SubjectDAO();
                    ArrayList<Subject> listSubject = sD.getListOfSubjectForCuriculum(curriculumId);

                    CurriculumDAO cD = new CurriculumDAO();
                    Curriculum curiculum = cD.getCurriculumById(curriculumId);

                    ArrayList<Group> listContent = gD.getListAllContents(curriculumId, "0", gD.getNumberOfActiveContent(curriculumId));

                    req.setAttribute("listContent", listContent);

                    req.setAttribute("listSyllabus", listSyllabus);
                    req.setAttribute("selectSubject", selectSubject);
                    req.setAttribute("listSubject", listSubject);
                    req.setAttribute("curiculum", curiculum);
                    req.setAttribute("c", curiculum);
                    req.setAttribute("selectSubject", selectSubject);
                    req.setAttribute("selectSyllabus", selectSyllabus);
                    req.setAttribute("selectContent", content);
                    req.setAttribute("semester", semester);
                    req.setAttribute("noCredit", noCredit);
                    req.setAttribute("isActive", isActive);
                    req.setAttribute("alert", alert);
                    req.setAttribute("op", 4);
                    req.getRequestDispatcher("addcurriculumsubject.jsp").forward(req, resp);
                } else {
                    String createdBy = acc.getUserId();
                    SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    LocalDateTime dateTime = LocalDateTime.now();
                    Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                    String createdAt = spf.format(date);
                    session.setAttribute("toast", "true");
                    cSD.addCurriculumSubject(cId, selectSubject, selectSyllabus, content, semester,
                            noCredit, isActive, createdBy, createdAt);
                    resp.sendRedirect("/FLM_NEW/view/common/curriculumsubject?action=list&cId=" + curriculumId + "&selectFilter=&search=");
                }

            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String curriculumId = req.getParameter("cId");
        String selectFilter = req.getParameter("selectFilter");
        String search = req.getParameter("search");

        if (action.equals("list")) {

            CurriculumSubjectDAO cSD = new CurriculumSubjectDAO();
            ArrayList<CurriculumSubject> listCSubject = cSD.getListSubject(curriculumId, selectFilter, search);
            ArrayList<CurriculumSubject> listSemester = cSD.getListSemester(curriculumId);
            CurriculumDAO cD = new CurriculumDAO();
            Curriculum curiculum = cD.getCurriculumById(curriculumId);

            req.setAttribute("selectFilter", selectFilter);
            req.setAttribute("search", search);
            req.setAttribute("listSemester", listSemester);
            req.setAttribute("listCSubject", listCSubject);
            req.setAttribute("c", curiculum);
            req.setAttribute("alert", "");
            req.setAttribute("op", 4);
            req.getRequestDispatcher("curriculumsubjectlist.jsp").forward(req, resp);
        }

        if (action.equals("add")) {
            GroupDAO gD = new GroupDAO();
            SubjectDAO sD = new SubjectDAO();
            ArrayList<Subject> listSubject = sD.getListOfSubjectForCuriculum(curriculumId);

            CurriculumDAO cD = new CurriculumDAO();
            Curriculum curiculum = cD.getCurriculumById(curriculumId);

            ArrayList<Group> listContent = gD.getListAllContents(curriculumId, "0", gD.getNumberOfActiveContent(curriculumId));

            req.setAttribute("listContent", listContent);
            req.setAttribute("listSubject", listSubject);
            req.setAttribute("c", curiculum);
            req.setAttribute("alert", "");
            req.setAttribute("op", 4);
            req.getRequestDispatcher("addcurriculumsubject.jsp").forward(req, resp);
        }

        if (action.equals("edit")) {
            String sId = req.getParameter("cSId");
            String cId = req.getParameter("cId");
            CurriculumSubjectDAO csD = new CurriculumSubjectDAO();
            boolean checkSTypeIsCombo = csD.checkSubjectTypeIsCombo(sId);
            CurriculumSubject cs = csD.getCurriculumSubject(cId, sId);
            if (checkSTypeIsCombo) {
                req.setAttribute("isCombo", "true");
            }
            ArrayList<Syllabus> listSOCS = csD.getListSubjectOfCombo(cId, sId, "default");
            SyllabusDAO slD = new SyllabusDAO();
            ArrayList<Syllabus> listSl = slD.getListSyllabusBySubject(cId, sId, "default");
            req.setAttribute("listSOCS", listSOCS);
            req.setAttribute("listSl", listSl);

            GroupDAO gD = new GroupDAO();
            SubjectDAO sD = new SubjectDAO();
            ArrayList<Subject> listSubject = sD.getListOfSubjectForCuriculum(curriculumId);
            CurriculumDAO cD = new CurriculumDAO();
            Curriculum curiculum = cD.getCurriculumById(curriculumId);

            ArrayList<Group> listContent = gD.getListAllContents(curriculumId, "0", gD.getNumberOfActiveContent(curriculumId));
            req.setAttribute("listContent", listContent);
            req.setAttribute("listSubject", listSubject);
            req.setAttribute("cSId", sId);
            req.setAttribute("c", curiculum);
            req.setAttribute("cs", cs);
            req.setAttribute("alert", "");
            req.setAttribute("op", 4);
            req.getRequestDispatcher("curriculumsubjectdetails.jsp").forward(req, resp);
        }
    }

}
