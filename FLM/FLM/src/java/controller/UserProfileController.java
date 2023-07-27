/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import model.User;

/**
 *
 * @author hp
 */
@MultipartConfig()
public class UserProfileController extends HttpServlet {

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
//          request.getRequestDispatcher("view/userprofile.jsp").forward(request, response);
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
//        processRequest(request, response);
//            String avatar = request.getParameter("picture");

        try {
            HttpSession session = request.getSession();
            User user_session = (User) session.getAttribute("acc");
            String user_id = user_session.getUserId();

            UserDAO u = new UserDAO();
            User user = u.getUserProfile(user_id);
            request.setAttribute("u", user);
            request.getRequestDispatcher("userprofile.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendRedirect("/FLM_NEW/index.jsp");
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
//        processRequest(request, response);

//        Part avatar = request.getPart("avatar");
//        System.out.println(avatar);
//        String realPart = "D:/Chuyen Nganh Ky 5/SWP391/g2/FLM_NEW/web/assets/images/doctors";
//        String fileName = getFileName(avatar);
//
//        if (request.getPart("avatar") != null) {
//            String filePath = realPart + File.separator + fileName;
//            OutputStream out = null;
//            InputStream fileContent = null;
//            try {
//                out = new FileOutputStream(new File(filePath));
//                fileContent = avatar.getInputStream();
//
//                int read;
//                byte[] buffer = new byte[4096];
//
//                while ((read = fileContent.read(buffer)) != -1) {
//                    out.write(buffer, 0, read);
//                }
//                request.setAttribute("avatar", filePath);
//            } catch (Exception e) {
//                request.setAttribute("err1", "File upload failed!");
//                e.printStackTrace();
//            } finally {
//                if (out != null) {
//                    out.close();
//                }
//                if (fileContent != null) {
//                    fileContent.close();
//                }
//            }
//            System.out.println(fileName);
//        }
        String btn_save = request.getParameter("save-profile");
        try {
            if (btn_save != null) {
                HttpSession session = request.getSession();
                User user_session = (User) session.getAttribute("acc");
                String user_id = user_session.getUserId();
                String full_name = request.getParameter("full_name");
                System.out.println(full_name);
                String email = request.getParameter("email");
                String user_name = request.getParameter("user_name");
                String mobile = request.getParameter("mobile");
                String veriCode = request.getParameter("vericode");
//        String picture = fileName;
                String picture = request.getParameter("avatar");
                
                User u = new User(user_id, full_name, email, mobile, user_name, picture);

                UserDAO uDAO = new UserDAO();
                uDAO.UpdateProfile(u);                
                User user = uDAO.getUserProfile(user_id);
                request.setAttribute("u", user);
                request.getRequestDispatcher("userprofile.jsp").forward(request, response);

            }

        } catch (Exception e) {
            HttpSession session = request.getSession();
            User user_session = (User) session.getAttribute("acc");
            String user_id = user_session.getUserId();
            UserDAO u1 = new UserDAO();
            User user = u1.getUserProfile(user_id);
            request.setAttribute("u", user);
            request.getRequestDispatcher("userprofile.jsp").forward(request, response);
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

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

}
