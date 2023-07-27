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
public class SettingDetailController extends HttpServlet {

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
            out.println("<title>Servlet SettingDetailController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SettingDetailController at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            SettingDAO st = new SettingDAO();
            if (request.getParameter("mod") != null && request.getParameter("mod").equals("1")) {
                String id = request.getParameter("id");
                Setting data = st.getSettingBySettingID(id);
                request.setAttribute("data", data);
                ArrayList<Setting> data2 = st.getAllType();
                request.setAttribute("type", data2);
                request.getRequestDispatcher("addsetting.jsp").forward(request, response);
            } else if (request.getParameter("mod") != null && request.getParameter("mod").equals("2")) {
                String id = request.getParameter("id");
                Setting data = st.getSettingBySettingID(id);
                request.setAttribute("data", data);
                ArrayList<Setting> data2 = st.getAllType();
                request.setAttribute("type", data2);
                request.getRequestDispatcher("settingdetail.jsp").forward(request, response);
            }
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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            SettingDAO st = new SettingDAO();
            // update setting list
            String id = request.getParameter("id");
            String name = request.getParameter("name");
            String type = request.getParameter("type");
            String value = request.getParameter("value");
            String displayorder = request.getParameter("displayOrder");
            String description = request.getParameter("Description");
            String status = request.getParameter("active");
            status = status == null ? "0" : "1";
            Setting s = new Setting(id, name, type, value, displayorder, description, status);
            st.UpdateSetting(s);
            ArrayList<Setting> data = st.getListSetting();
            request.setAttribute("data", data);
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
