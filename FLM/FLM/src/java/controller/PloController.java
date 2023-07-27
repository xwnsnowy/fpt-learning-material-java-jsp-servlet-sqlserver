/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CloDAO;
import dao.CurriculumDAO;
import dao.PloDAO;
import dao.SyllabusDAO;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import model.Curriculum;
import model.Plo;

/**
 *
 * @author Admin
 */
@SuppressWarnings("unchecked")
public class PloController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Ccode = req.getParameter("Ccode");
        PloDAO pDao = new PloDAO();
        HttpSession session = req.getSession();
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        String action = req.getParameter("action");

        if (req.getParameter("mod") != null && req.getParameter("mod").equals("2")) {
            String idPlo = req.getParameter("IdPlo");
//            System.out.println(idPlo);
            pDao.deletePloById(idPlo);
            req.setAttribute("alert", "");
            ArrayList<Plo> ListPLO = pDao.getListPloByCode(Ccode);
            req.setAttribute("op", "4");
            req.setAttribute("c", c);
            req.setAttribute("ListPLO", ListPLO);
            req.getRequestDispatcher("plo.jsp").forward(req, resp);
        }

        if (action.equalsIgnoreCase("changestatus")) {
            String[] activeValues = req.getParameterValues("active");
            String active = activeValues != null && activeValues.length > 0 ? activeValues[0] : "0";
            System.out.println("Ccode" + Ccode);
            String Plo_id = req.getParameter("Plo_id");
            PloDAO ploDAO = new PloDAO();
            //xử lý update 
            if (active.equals("1")) {

                ploDAO.UpdateStatusPlo("1", Plo_id);
            }
            if (active.equals("0")) {
                ploDAO.UpdateStatusPlo("0", Plo_id);
            }
            ArrayList<Plo> ListPLO = ploDAO.getListPloByCode(Ccode);
            int page, numberpage = 5;
            int sizePage = ListPLO.size();
            int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
            String xpage = req.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start;
            start = (page - 1) * numberpage;
            int endPage = Math.min(page * numberpage, sizePage);
            ArrayList<Plo> listPage = ploDAO.getListByPage(ListPLO, start, endPage);
            req.setAttribute("op", "4");
//           req.setAttribute("opplo", "1");
            req.setAttribute("c", c);
            session.setAttribute("toast", "true");
            req.setAttribute("page", page);
            req.setAttribute("sizePage", sizePage);
            req.setAttribute("numPage", numPage);
            req.setAttribute("active", active);
            req.setAttribute("ListPLO", listPage);
            req.getRequestDispatcher("/view/common/plo.jsp").forward(req, resp);
        }
        if (action.equals("search")) {
            String searchFilter = req.getParameter("selectFilter");
            String Cid = req.getParameter("Cid");
            System.out.println(Ccode);
            System.out.println("Filter :" + searchFilter);
            String search = req.getParameter("search");
            System.out.println("search :" + search);

            if (search == null) {
                search = "";
            } else {
                req.setAttribute("search", search);
            }
            ArrayList<Plo> listPLOSearch = pDao.getListPLOBySearch(search, Cid);
            int page, numberpage = 5;
            int sizePage = listPLOSearch.size();
            int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
            String xpage = req.getParameter("page");
            if (xpage == null) {
                page = 1;
            } else {
                page = Integer.parseInt(xpage);
            }
            int start;
            start = (page - 1) * numberpage;
            int endPage = Math.min(page * numberpage, sizePage);
            ArrayList<Plo> listPage = pDao.getListByPage(listPLOSearch, start, endPage);
            req.setAttribute("op", "4");
//           req.setAttribute("opplo", "1");
            req.setAttribute("c", c);
            req.setAttribute("page", page);
            req.setAttribute("sizePage", sizePage);
            req.setAttribute("numPage", numPage);
            req.setAttribute("ListPLO", listPage);
            req.getRequestDispatcher("/view/common/plo.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("addlist")) {
            String mess1 = "Import CLOs successfully";
            String Ccode1 = req.getParameter("Ccode");
            System.out.println("add list " + Ccode1);
            ArrayList<Plo> listPLO = pDao.getListPloByCode(Ccode1);
            int page, numperpage = 5;
            int sizePage = listPLO.size();
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
            ArrayList<Plo> listPage = pDao.getListByPage(listPLO, start, Math.min(page * numperpage, endPage));
            ArrayList<Plo> myList = (ArrayList<Plo>) session.getAttribute("validPloList");
//            System.out.println(myList);
            pDao.addListPlo(myList);
            session.removeAttribute("validPloList");
            req.setAttribute("alert", mess1);
            req.setAttribute("c", c);
            req.setAttribute("page", page);
            session.setAttribute("toast", "true");
            req.setAttribute("page", page);
            req.setAttribute("sizePage", sizePage);
            req.setAttribute("numPage", numPage);
            req.setAttribute("ListPLO", listPage);
            req.getRequestDispatcher("plo.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String Ccode = req.getParameter("Ccode");
        HttpSession session = req.getSession();
        CurriculumDAO cDAO = new CurriculumDAO();
        Curriculum c = cDAO.getCurriculumOverview(Ccode);
        PloDAO pDao = new PloDAO();

        ArrayList<Plo> ListPLO = pDao.getListPloByCode(Ccode);
        int page, numberpage = 5;
        int sizePage = ListPLO.size();
        int numPage = (sizePage % 5 == 0 ? (sizePage / 5) : ((sizePage / 5)) + 1);
        String xpage = req.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start;
        start = (page - 1) * numberpage;
        int endPage = Math.min(page * numberpage, sizePage);
        ArrayList<Plo> listPage = pDao.getListByPage(ListPLO, start, endPage);
        req.setAttribute("op", "4");
//        req.setAttribute("opplo", "1");
        req.setAttribute("page", page);
        String toast = (String) session.getAttribute("toast");
        req.setAttribute("toast", toast);    
        req.setAttribute("sizePage", sizePage);
        req.setAttribute("numPage", numPage);
        req.setAttribute("c", c);
        req.setAttribute("ListPLO", listPage);
        req.getRequestDispatcher("plo.jsp").forward(req, resp);
    }

}
