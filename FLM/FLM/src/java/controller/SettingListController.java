/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.SettingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Setting;

/**
 *
 * @author ADMIN
 */
public class SettingListController extends HttpServlet {

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
            out.println("<title>Servlet SettingController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingController at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("action");
        if (action.equals("list")) {
            SettingDAO sDAO = new SettingDAO();
            ArrayList<Setting> listSetting;
            listSetting = sDAO.getListSetting();
            int page, numperpage = 6;
            int sizePage = listSetting.size();
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
            ArrayList<Setting> listPage = sDAO.getListSettingByPage(listSetting, start, Math.min(page * numperpage, endPage));
            request.setAttribute("numPage", numPage);
            request.setAttribute("sizePage", sizePage);
            request.setAttribute("page", page);
            request.setAttribute("listPage", listPage);
            request.getRequestDispatcher("settinglist.jsp").forward(request, response);
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
        String action = request.getParameter("action");
        SettingDAO sDAO = new SettingDAO();
        ArrayList<Setting> listSetting;
        if (action.equals("search")) {
            String search = request.getParameter("search");
            listSetting = sDAO.getListSettingForSearch(search);
            int page, numperpage = 6;
            int sizePage = listSetting.size();
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
            ArrayList<Setting> listPage = sDAO.getListSettingByPage(listSetting, start, Math.min(page * numperpage, endPage));
            request.setAttribute("numPage", numPage);
            request.setAttribute("sizePage", sizePage);
            request.setAttribute("page", page);
            request.setAttribute("search", search);
            request.setAttribute("listPage", listPage);
            request.getRequestDispatcher("settinglist.jsp").forward(request, response);
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
