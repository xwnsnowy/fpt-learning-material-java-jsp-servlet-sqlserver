/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CloDAO;
import dao.CurriculumDAO;
import dao.MappingClo_PloDAO;
import dao.PloDAO;
import dao.SettingDAO;
import dao.SyllabusDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Clo;

import model.Curriculum;
import model.Group;
import model.Plo;
import model.Setting;
import model.Syllabus;
import model.User;

/**
 *
 * @author Admin
 */
@SuppressWarnings("unchecked")
public class CloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sylId = req.getParameter("sylId");
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        // Add CLOs
        if (action.equalsIgnoreCase("add")) {
            String clo_description = req.getParameter("cloDescription");
            String Syllabus_id = req.getParameter("sylId");
            String clo_name = req.getParameter("cloName");
            String active = req.getParameter("active");
            active = active == null ? "0" : "1";
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            String created_at = spf.format(date);
//            System.out.println(created_at);
            User accUsername = (User) session.getAttribute("acc");
            String create_by = accUsername.getUserId();
            CloDAO cloDAO = new CloDAO();
            CloDAO clo = new CloDAO();
            SyllabusDAO sllDAO = new SyllabusDAO();
            String mess = "";
//            String mess1 =  "Add CLOs successfully";
            Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
            String newClo_id = String.valueOf(cloDAO.getlastCloId() + 1);
            if (!cloDAO.checkNameExist(clo_name)) {
                
//                session.setAttribute("toast", "true");
                req.setAttribute("alert", mess);
                cloDAO.addNewCLOs(newClo_id, clo_name, clo_description, Syllabus_id, active, created_at, create_by);
            } else {
                mess = "CLO Name is already";
                req.setAttribute("alert", mess);
                req.setAttribute("clo_name", clo_name);
                req.setAttribute("syllabus", syllabus);
                req.setAttribute("active", active);
                req.setAttribute("dropDownS", 1);
                req.setAttribute("clo_description", clo_description);
//                req.setAttribute("alert", mess);
                req.getRequestDispatcher("/view/admin/addclos.jsp").forward(req, resp);
            }

            ArrayList<Clo> listCLO = clo.getListCloBySyllabusID(sylId);
            int page, numperpage = 5;
            int sizePage = listCLO.size();
            int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
            String xpage = req.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start;
            start = (page - 1) * numperpage;
            int endPage = Math.min(page * numperpage, sizePage);
            ArrayList<Clo> listPage = cloDAO.getListByPage(listCLO, start, Math.min(page * numperpage, endPage));
            session.setAttribute("toast", "true");
            req.setAttribute("sizePage", sizePage);
//            req.setAttribute("alert", mess1);
            req.setAttribute("page", page);
            req.setAttribute("numPage", numPage);
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("listCLO", listPage);

            req.setAttribute("dropDownS", 1);
            req.getRequestDispatcher("/view/common/clo.jsp").forward(req, resp);
        }
        //Update CLOs
        if (action.equalsIgnoreCase("details")) {
            
            String CloName = req.getParameter("cloName");
            String cDescription = req.getParameter("cDescription");
            String active = req.getParameter("active");
//            System.out.println("details 1 :" + active);
            String cloid = req.getParameter("cloid");
            active = active == null ? "0" : "1";
//            System.out.println("details 2 :" + active);
            CloDAO cloDAO = new CloDAO();
            SyllabusDAO sllDAO = new SyllabusDAO();
            Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
            Clo c = cloDAO.getCLObyCloID(sylId);

            Clo clo = new Clo(cloid, CloName, cDescription, sylId, active);
            //xử lý update 

            cloDAO.updateCLOs(clo);

            ArrayList<Clo> listCLO = cloDAO.getListCloBySyllabusID(sylId);
            //xử lý phân trang 89-102
            int page, numperpage = 5;
            int sizePage = listCLO.size();
            int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
            String xpage = req.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start;
            start = (page - 1) * numperpage;
            int endPage = Math.min(page * numperpage, sizePage);
            ArrayList<Clo> listPage = cloDAO.getListByPage(listCLO, start, Math.min(page * numperpage, endPage));
            session.setAttribute("toast", "true");
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("c", c);
            req.setAttribute("dropDownS", 1);
            req.setAttribute("sizePage", sizePage);
            req.setAttribute("page", page);
            session.setAttribute("toast", "true");
            req.setAttribute("active", active);
            req.setAttribute("numPage", numPage);
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("listCLO", listPage);
            req.getRequestDispatcher("/view/common/clo.jsp").forward(req, resp);
        }

        //Mapping PLOs_CLOs
        if (action.equalsIgnoreCase("mapping")) {
            Map<String, Boolean> mappingStatus = new HashMap<>();
            Map<String, Boolean> mappingStatusUpdate = new HashMap<>();
            String clo_id = req.getParameter("clo_id");
            CloDAO cloDAO = new CloDAO();
            PloDAO ploDAO = new PloDAO();
            SyllabusDAO sllDAO = new SyllabusDAO();
            Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
            ArrayList<Clo> listCLO = cloDAO.getListCloBySyllabusID(sylId);
            Clo c = cloDAO.getCLObyCloID(clo_id);
            CurriculumDAO crrDao = new CurriculumDAO();
            MappingClo_PloDAO mpCLDAO = new MappingClo_PloDAO();
            ArrayList<Curriculum> listC = crrDao.getCurriculumCodeBySubjectCode(syllabus.getSubject().getCode());

            ArrayList<ArrayList<Plo>> allListPLO = new ArrayList<>();
            for (Curriculum crrCode : listC) {
                ArrayList<Plo> listPLO = ploDAO.getListPloByCode(crrCode.getCode());
                allListPLO.add(listPLO);
            }
            String para = "";
            boolean check = false;
            if (req.getParameter("save") != null) {
                for (ArrayList<Plo> arrayList : allListPLO) {
                    for (Plo plo : arrayList) {
                        for (Clo clo : listCLO) {
                            para = "mapping" + plo.getPlo_id() + "_" + clo.getClo_id();
                            check = mpCLDAO.check(plo.getPlo_id(), clo.getClo_id());
                            mappingStatus.put(para, check);
                            if (req.getParameter(para) != null && check == false) {
                                mpCLDAO.addMappingPLO_CLO(plo.getPlo_id(), clo.getClo_id());

                            }
                            //clo.getClo_id(), plo.getPlo_id()
                            if (req.getParameter(para) == null && check == true) {
                                mpCLDAO.deleteMappingPLO_CLO(plo.getPlo_id(), clo.getClo_id());
                            }
                        }
                    }
                }
                for (ArrayList<Plo> arrayList : allListPLO) {
                    for (Plo plo : arrayList) {
                        for (Clo clo : listCLO) {
                            para = "mapping" + plo.getPlo_id() + "_" + clo.getClo_id();
                            check = mpCLDAO.check(plo.getPlo_id(), clo.getClo_id());
                            mappingStatusUpdate.put(para, check);
                        }
                    }
                }
            }
            //Lấy tên của các curriculum của môn đó 
            req.setAttribute("listC", listC);
            req.setAttribute("para", para);
            req.setAttribute("mappingStatus", mappingStatusUpdate);
            //List PLO của các curriculum của môn đó
            req.setAttribute("allListPLO", allListPLO);
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("listCLO", listCLO);
            session.setAttribute("toast", "true");
            req.setAttribute("c", c);
            req.setAttribute("dropDownS", 1);
            req.getRequestDispatcher("/view/common/mappingclo_plo.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("search")) {
            SyllabusDAO sllDAO = new SyllabusDAO();
            Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
            String selectFilter = req.getParameter("selectFilter");
            String search = req.getParameter("search");
            CloDAO cloDAO = new CloDAO();
            ArrayList listStatus = cloDAO.getListStatus();
            if (selectFilter.equals("")) {
                ArrayList<Clo> listCLOSearch = cloDAO.getListCLOBySearchNotActive(search, sylId);
                int page, numperpage = 5;
                int sizePage = listCLOSearch.size();
                int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
                String xpage = req.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start;
                start = (page - 1) * numperpage;
                int endPage = Math.min(page * numperpage, sizePage);
                ArrayList<Clo> listPage = cloDAO.getListByPage(listCLOSearch, start, Math.min(page * numperpage, endPage));
                req.setAttribute("sizePage", sizePage);

                req.setAttribute("page", page);
                req.setAttribute("numPage", numPage);
                req.setAttribute("listStatus", listStatus);
                req.setAttribute("listCLO", listPage);
                req.setAttribute("syllabus", syllabus);
                req.setAttribute("search", search);
                req.setAttribute("selectFilter", selectFilter);
                req.setAttribute("dropDownS", 1);
                req.getRequestDispatcher("/view/common/clo.jsp").forward(req, resp);
            } else {
                if (search == null) {
                    search = "";
                } else {
                    req.setAttribute("search", search);
                }
//            System.out.println("search " + selectFilter);
                ArrayList<Clo> listCLOSearch = cloDAO.getListCLOBySearch(search, sylId, selectFilter);
                int page, numperpage = 5;
                int sizePage = listCLOSearch.size();
                int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
                String xpage = req.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start;
                start = (page - 1) * numperpage;
                int endPage = Math.min(page * numperpage, sizePage);
                ArrayList<Clo> listPage = cloDAO.getListByPage(listCLOSearch, start, Math.min(page * numperpage, endPage));
                req.setAttribute("sizePage", sizePage);

                req.setAttribute("page", page);
                req.setAttribute("numPage", numPage);
//            System.out.println(listCLOSearch);
                req.setAttribute("syllabus", syllabus);
                req.setAttribute("selectFilter", selectFilter);
                req.setAttribute("search", search);
                req.setAttribute("listStatus", listStatus);
                req.setAttribute("listCLO", listPage);
                Clo c = cloDAO.getCLObyCloID(sylId);
                req.setAttribute("c", c);
                req.setAttribute("dropDownS", 1);
                req.getRequestDispatcher("/view/common/clo.jsp").forward(req, resp);
            }
        }
        if (action.equalsIgnoreCase("addlist")) {
            String mess1 =  "Import CLOs successfully";
            CloDAO cloDAO = new CloDAO();
            SyllabusDAO sllDAO = new SyllabusDAO();
            Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
            ArrayList<Clo> listCLO = cloDAO.getListCloBySyllabusID(sylId);
            int page, numperpage = 5;
            int sizePage = listCLO.size();
            int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
            String xpage = req.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start;
            start = (page - 1) * numperpage;
            int endPage = Math.min(page * numperpage, sizePage);
            ArrayList<Clo> listPage = cloDAO.getListByPage(listCLO, start, Math.min(page * numperpage, endPage));

            ArrayList<Clo> myList = (ArrayList<Clo>) session.getAttribute("validCloList");

            cloDAO.addListClo(myList);
            req.setAttribute("sizePage", sizePage);
            session.removeAttribute("validCloList");
            req.setAttribute("alert", mess1);
            req.setAttribute("page", page);
            req.setAttribute("numPage", numPage);
            req.setAttribute("dropDownS", 1);
            session.setAttribute("toast", "true");
//            req.setAttribute("syllabus", syllabus);
            req.setAttribute("listCLO", listPage);
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("dropDownS", 1);
            req.getRequestDispatcher("clo.jsp").forward(req, resp);
        }

        if (action.equals("changestatus")) {

            String[] activeValues = req.getParameterValues("active");
            String active = activeValues != null && activeValues.length > 0 ? activeValues[0] : "0";

            String Clo_id = req.getParameter("Clo_id");
            CloDAO cloDAO = new CloDAO();
            ArrayList listStatus = cloDAO.getListStatus();
            SyllabusDAO sllDAO = new SyllabusDAO();
            Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
            Clo c = cloDAO.getCLObyCloID(sylId);
            //xử lý update 
            if (active.equals("1")) {
                cloDAO.UpdateStatusClo("1", Clo_id);
            }
            if (active.equals("0")) {
                cloDAO.UpdateStatusClo("0", Clo_id);
            }
            ArrayList<Clo> listCLO = cloDAO.getListCloBySyllabusID(sylId);
            int page, numperpage = 5;
            int sizePage = listCLO.size();
            int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
            String xpage = req.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start;
            start = (page - 1) * numperpage;
            int endPage = Math.min(page * numperpage, sizePage);
            ArrayList<Clo> listPage = cloDAO.getListByPage(listCLO, start, Math.min(page * numperpage, endPage));
            req.setAttribute("listStatus", listStatus);
            req.setAttribute("c", c);
            req.setAttribute("dropDownS", 1);
            req.setAttribute("sizePage", sizePage);
            req.setAttribute("page", page);
            req.setAttribute("active", active);
            session.setAttribute("toast", "true");
            req.setAttribute("numPage", numPage);
            req.setAttribute("syllabus", syllabus);
            req.setAttribute("listCLO", listPage);
            req.getRequestDispatcher("/view/common/clo.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            String sylId = req.getParameter("sylId");
            String action = req.getParameter("action");
            HttpSession session = req.getSession();

            if (action.equalsIgnoreCase("add")) {
                SyllabusDAO sllDAO = new SyllabusDAO();
                Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);

                req.setAttribute("syllabus", syllabus);
                req.setAttribute("dropDownS", 1);
                req.getRequestDispatcher("/view/admin/addclos.jsp").forward(req, resp);
            }

            if (action.equalsIgnoreCase("details")) {
                String clo_id = req.getParameter("clo_id");
                CloDAO cloDAO = new CloDAO();
                SyllabusDAO sllDAO = new SyllabusDAO();
                Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
                ArrayList<Clo> listCLO = cloDAO.getListCloBySyllabusID(sylId);
                Clo c = cloDAO.getCLObyCloID(clo_id);

                req.setAttribute("syllabus", syllabus);
                req.setAttribute("listCLO", listCLO);
                req.setAttribute("c", c);
                req.setAttribute("dropDownS", 1);
                req.getRequestDispatcher("clodetails.jsp").forward(req, resp);
            }

            //maping plo_clo
            if (action.equalsIgnoreCase("mapping")) {
                Map<String, Boolean> mappingStatus = new HashMap<>();
                String clo_id = req.getParameter("clo_id");
                CloDAO cloDAO = new CloDAO();
                PloDAO ploDAO = new PloDAO();
                MappingClo_PloDAO mpCLDAO = new MappingClo_PloDAO();
                SyllabusDAO sllDAO = new SyllabusDAO();
                Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
                ArrayList<Clo> listCLO = cloDAO.getListCloBySyllabusID(sylId);
                Clo c = cloDAO.getCLObyCloID(clo_id);
                CurriculumDAO crr = new CurriculumDAO();
                ArrayList<Curriculum> listC = crr.getCurriculumCodeBySubjectCode(syllabus.getSubject().getCode());
                String para = "";
                boolean check = false;
                ArrayList<ArrayList<Plo>> allListPLO = new ArrayList<>();
                for (Curriculum crrCode : listC) {
                    ArrayList<Plo> listPLO = ploDAO.getListPloByCode(crrCode.getCode());
                    allListPLO.add(listPLO);
                }
                for (ArrayList<Plo> arrayList : allListPLO) {
                    for (Plo plo : arrayList) {
                        for (Clo clo : listCLO) {
                            para = "mapping" + plo.getPlo_id() + "_" + clo.getClo_id();
                            check = mpCLDAO.check(plo.getPlo_id(), clo.getClo_id());
                            mappingStatus.put(para, check);

                        }
                    }
                }
                //Lấy tên của các curriculum của môn đó 
                req.setAttribute("listC", listC);
                //List PLO của các curriculum của môn đó
                req.setAttribute("allListPLO", allListPLO);
                req.setAttribute("syllabus", syllabus);
                req.setAttribute("listCLO", listCLO);
                String toast = (String) session.getAttribute("toast");
                req.setAttribute("toast", toast);
                req.setAttribute("para", para);
                req.setAttribute("mappingStatus", mappingStatus);
                req.setAttribute("c", c);
                req.setAttribute("dropDownS", 1);
                req.getRequestDispatcher("/view/common/mappingclo_plo.jsp").forward(req, resp);
            }
            if (action.equals("list")) {
                CloDAO cloDAO = new CloDAO();
                SyllabusDAO sllDAO = new SyllabusDAO();
                Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
                ArrayList<Clo> listCLO = cloDAO.getListCloBySyllabusID(sylId);
                int page, numperpage = 5;
                int sizePage = listCLO.size();
                int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
                String xpage = req.getParameter("page");
                if (xpage == null) {
                    page = 1;
                } else {
                    page = Integer.parseInt(xpage);
                }
                int start;
                start = (page - 1) * numperpage;
                int endPage = Math.min(page * numperpage, sizePage);
                ArrayList<Clo> listPage = cloDAO.getListByPage(listCLO, start, Math.min(page * numperpage, endPage));
                req.setAttribute("sizePage", sizePage);
                ArrayList listStatus = cloDAO.getListStatus();
                req.setAttribute("page", page);
                req.setAttribute("numPage", numPage);
                req.setAttribute("syllabus", syllabus);
                String toast = (String) session.getAttribute("toast");
                req.setAttribute("toast", toast);                           
                req.setAttribute("listCLO", listPage);
                req.setAttribute("listStatus", listStatus);

                req.setAttribute("dropDownS", 1);
                req.getRequestDispatcher("clo.jsp").forward(req, resp);
            }
        
    }
}
