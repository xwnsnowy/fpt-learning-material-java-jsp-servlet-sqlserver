/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.GroupDAO;
import dao.SettingDAO;
import dao.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Group;
import model.Setting;
import model.Subject;

/**
 *
 * @author Admin
 */
public class SubjectController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            String search = req.getParameter("search");
            SubjectDAO sd = new SubjectDAO();
            String btn = req.getParameter("btn");
            GroupDAO gd = new GroupDAO();
            SettingDAO stD = new SettingDAO();
            HttpSession session = req.getSession();
            if (action.equals("add")) {
                String name = req.getParameter("name");
                String code = req.getParameter("code");
                String parentid = req.getParameter("parentid");
                String typeid = req.getParameter("typeid");
                String groupid = req.getParameter("groupid");
                String isActive = req.getParameter("isActive");
                isActive = isActive == null ? "0" : "1";
                String description = req.getParameter("description");
                if (btn != null) {
                    if (sd.checkDuplicateCodeSubject(code) != null) {
                        req.setAttribute("nameNew", name);
                        req.setAttribute("codeNew", code);
                        req.setAttribute("descriptionNew", description);
                        ArrayList<Subject> listParentCode = sd.getListCodeIdSubject();
                        req.setAttribute("typeIdNew", typeid);
                        req.setAttribute("listParentCode", listParentCode);
                        ArrayList<Group> listGroup = gd.getListGroup();
                        req.setAttribute("typeNameNew", stD.getSettingNameBySettingId(typeid).getName());
                        if ("".equals(parentid)) {
                            req.setAttribute("parentIdNull", parentid);
                            req.setAttribute("parentNameNull", "None");
                        }
                        {
                            req.setAttribute("parentIdNew", parentid);
                            req.setAttribute("parentCodeNew", sd.getParentCodeByParentId(parentid).getCode());
                        }
                        ArrayList<Setting> listType = stD.getListSubjectType();
                        req.setAttribute("listGroup", listGroup);
                        req.setAttribute("listType", listType);
                        req.setAttribute("alertError", "Subject CODE is available. Please enter another CODE !");
                        req.getRequestDispatcher("addsubject.jsp").forward(req, resp);
                    } else if (sd.checkDuplicateNameSubject(name) != null) {
                        req.setAttribute("nameNew", name);
                        req.setAttribute("codeNew", code);
                        req.setAttribute("descriptionNew", description);
                        req.setAttribute("parentIdNew", parentid);
                        ArrayList<Subject> listParentCode = sd.getListCodeIdSubject();
                        req.setAttribute("typeIdNew", typeid);
                        if ("".equals(parentid)) {
                            req.setAttribute("parentIdNull", parentid);
                            req.setAttribute("parentNameNull", "None");
                        }
                        req.setAttribute("listParentCode", listParentCode);
                        ArrayList<Group> listGroup = gd.getListGroup();
                        req.setAttribute("typeNameNew", stD.getSettingNameBySettingId(typeid).getName());
                        ArrayList<Setting> listType = stD.getListSubjectType();
                        req.setAttribute("listGroup", listGroup);
                        req.setAttribute("listType", listType);
                        req.setAttribute("alertError", "Subject NAME is available. Please enter another NAME !");
                        req.getRequestDispatcher("addsubject.jsp").forward(req, resp);
                    } else {
                        int newId = Integer.parseInt(sd.getSubjectIdMax().getId()) + 1;
                        if (!"".equals(groupid) && !"".equals(parentid)) {
                            sd.addSubjectNoneGroup(String.valueOf(newId), code, name, typeid, isActive, description, parentid);
                        } else {
                            sd.addSubjectNoneParentAndNoneGroup(String.valueOf(newId), code, name, typeid, isActive, description);
                        }
                        session.setAttribute("toast", "true");
                        resp.sendRedirect("/FLM_NEW/view/admin/subject?action=list");
//                        req.getRequestDispatcher("subjectList.jsp").forward(req, resp);
                    }
                }
            }

            if (action.equals("edit")) {
                String id = req.getParameter("idValue");
                String name = req.getParameter("name");
                String code = req.getParameter("code");
                String type = req.getParameter("selectType");
                String isActive = req.getParameter("isActive");
                isActive = isActive == null ? "0" : "1";
                String description = req.getParameter("description");
                String parentSubjectId = req.getParameter("selectSubject");
                SubjectDAO sD = new SubjectDAO();
                sD.updateSubjectById(id, name, code, type, isActive, parentSubjectId, description);
                ArrayList<Setting> listType = stD.getListSubjectType();
                req.setAttribute("listType", listType);
                req.setAttribute("search", search);
                ArrayList<Subject> listSubject = sD.getListOfSubject(search);
                req.setAttribute("listSubject", listSubject);
                req.setAttribute("alert", "");
                req.getRequestDispatcher("subjectList.jsp").forward(req, resp);
            }

        } catch (ServletException | IOException e) {
            System.out.println("error doPost SubjectController: " + e.getMessage());
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String action = req.getParameter("action");
            String search = req.getParameter("search");
            SubjectDAO sd = new SubjectDAO();
            HttpSession session = req.getSession();
            GroupDAO gd = new GroupDAO();
            if (action.equals("list")) {
                //first run
                if (search == null) {
                    search = "";
                } else {
                    req.setAttribute("search", search);
                }
                SubjectDAO sD = new SubjectDAO();
                ArrayList<Subject> listSubject = sD.getListOfSubject(search);
                SettingDAO stD = new SettingDAO();
                ArrayList<Setting> listType = stD.getListSubjectType();
                req.setAttribute("listSubject", listSubject);
                req.setAttribute("listType", listType);
                req.setAttribute("alert", "");
                String toast = (String) session.getAttribute("toast");
                req.setAttribute("toast", toast);
                req.getRequestDispatcher("subjectList.jsp").forward(req, resp);
            }
            if (action.equals("add")) {
                ArrayList<Subject> listParentCode = sd.getListCodeIdSubject();
                ArrayList<Group> listGroup = gd.getListGroup();
                SettingDAO stD = new SettingDAO();
                ArrayList<Setting> listType = stD.getListSubjectType();
                req.setAttribute("listGroup", listGroup);
                req.setAttribute("listParentCode", listParentCode);
                req.setAttribute("listType", listType);
                req.getRequestDispatcher("addsubject.jsp").forward(req, resp);
            }
        } catch (ServletException | IOException e) {
            System.out.println("error doGet SubjectController: " + e.getMessage());
        }

    }

}
