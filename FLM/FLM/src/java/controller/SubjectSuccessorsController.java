/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.DecisionDAO;
import dao.PrerequisiteDAO;
import dao.SubjectDAO;
import dao.SyllabusDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import model.Decision;
import model.Node;
import model.Prerequisite;
import model.Syllabus;

/**
 *
 * @author user
 */
public class SubjectSuccessorsController extends HttpServlet {

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
            out.println("<title>Servlet SubjectPredeccessorsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SubjectPredeccessorsController at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("subjectsuccessors.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            System.out.println("error doGet SubjectSuccessors: " + e.getMessage());
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
            String search = request.getParameter("search");
            String code = request.getParameter("code");
            SubjectDAO sDAO = new SubjectDAO();
            SyllabusDAO syDAO = new SyllabusDAO();
            PrerequisiteDAO pDAO = new PrerequisiteDAO();
            DecisionDAO dDAO = new DecisionDAO();
            boolean forwarded = false;
            if (search != null) {
                if (code == null || code.isEmpty()) {
                    String errorSearch = "Please enter subject code!";
                    request.setAttribute("errorSearch", errorSearch);
                    forwarded = true;
                }
                if (sDAO.checkSubjectCode(code)) {
                    ArrayList<Prerequisite> listPrerequisiteSubjectByCode = pDAO.getListSuccessorsSubjectByCode(code);
                    String subjectId = sDAO.getSubjectIdbySubjectCode(code).getId();
                    if (!pDAO.checkSuccessorsSubjectBySubjectId(subjectId)) {
                        Syllabus syllabus = syDAO.getSyllabusByCode(code);
                        Decision decision = dDAO.getDecisionBySubjectId(subjectId);
                        if (decision != null) {
                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            String date = formatter.format(decision.getDecision_date());
                            request.setAttribute("date", "dated " + date);
                        } else {
                            request.setAttribute("date", "");
                        }
                        request.setAttribute("codeNonePre", sDAO.getSubjectIdbySubjectCode(code).getCode());
                        request.setAttribute("nonePre", ":(No pre-requisite)");
                        request.setAttribute("returncode", code);
                        request.setAttribute("code", sDAO.getSubjectIdbySubjectCode(code).getCode());
                        request.setAttribute("syllabus", syllabus);
                        request.setAttribute("decision", decision);
                        forwarded = true;
                    }
                    if (listPrerequisiteSubjectByCode != null) {
                        Syllabus syllabus = syDAO.getSyllabusByCode(code);
                        Decision decision = dDAO.getDecisionBySubjectId(subjectId);
                        if (decision != null) {
                            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
                            String date = formatter.format(decision.getDecision_date());
                            request.setAttribute("date", "dated " + date);
                        } else {
                            request.setAttribute("date", "");
                        }
                        String list = "";
                        String listPreSubject = "";
                        List<String> testList = new ArrayList<>();
                        String[] array;
                        traverseSubjectPrerequisitesTree(code, testList);
                        List<String> listWithoutDuplicates = testList.stream().distinct().collect(Collectors.toList());
                        array = listWithoutDuplicates.toArray(new String[0]);
                        String listPreForEachSubject = "";
                        StringBuilder sb = new StringBuilder(listPreForEachSubject);
                        for (int i = 1; i < array.length; i++) {
                            if (i == array.length - 1) {
                                listPreSubject += array[i];
                            } else {
                                listPreSubject += array[i] + ", ";
                            }
                        }
                        ArrayList<String> arrayListPreForEachSubject = new ArrayList<String>();
                        List<String> listWithoutDuplicatesNew = testList.stream().distinct().collect(Collectors.toList());
                        // xoa di phan tu dau tien vi phan tu dau tien la mon hoc minh tim kiem
                        listWithoutDuplicatesNew.remove(0);
                        String[] arrayNew = listWithoutDuplicatesNew.toArray(new String[0]);
                        for (String subject : arrayNew) {
                            List<Prerequisite> prerequisites = pDAO.getListSuccessorsSubjectByCode(subject);
                            sb.append(subject).append(": ");
                            if (!prerequisites.isEmpty()) {
                                for (Prerequisite prerequisite : prerequisites) {
                                    String prerequisiteCode = sDAO.getSubjectCodeBySubjectId(prerequisite.getSubject_id()).getCode();
                                    sb.append(prerequisiteCode).append(", ");
                                }
                                sb.setLength(sb.length() - 2); //
                            } else {
                                sb.append("None");
                            }
                            listPreForEachSubject = sb.toString();
                            arrayListPreForEachSubject.add(listPreForEachSubject);
                            sb.setLength(0);
                        }
                        for (String subjectWithPrerequisites : arrayListPreForEachSubject) {
                            System.out.println(subjectWithPrerequisites);
                        }
                        request.setAttribute("returncode", code);
                        request.setAttribute("arrayListPreForEachSubject", arrayListPreForEachSubject);
                        request.setAttribute("listPreSubject", listPreSubject);
                        request.setAttribute("listPreForEachSubject", listPreForEachSubject);
                        request.setAttribute("code", sDAO.getSubjectIdbySubjectCode(code).getCode());
                        request.setAttribute("syllabus", syllabus);
                        request.setAttribute("decision", decision);
                        forwarded = true;
                    }
                }
                if (!forwarded) {
                    request.setAttribute("returncode", code);
                    request.setAttribute("errorNotFoundSearch", code);
                }
                request.getRequestDispatcher("subjectsuccessors.jsp").forward(request, response);
            }
        } catch (ServletException | IOException e) {
            System.out.println("error doPost SubjectSuccessors: " + e.getMessage());
        }
    }

    private void traverseSubjectPrerequisitesTree(String code, List<String> listPre) {
        PrerequisiteDAO pDAO = new PrerequisiteDAO();
        SubjectDAO sDAO = new SubjectDAO();
        Queue<Node> queue = new LinkedList<>();
        Node root = new Node(code);
        queue.add(root);
        //breadth-first traversal
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            List<Prerequisite> prerequisites = pDAO.getListSuccessorsSubjectByCode(currentNode.code);
            for (Prerequisite prerequisite : prerequisites) {
                String preCode = sDAO.getSubjectCodeBySubjectId(prerequisite.getSubject_id()).getCode();
                Node childNode = new Node(preCode);
                currentNode.addChild(childNode);
                queue.add(childNode);
            }
        }
        Queue<Node> traversalQueue = new LinkedList<>();
        traversalQueue.add(root);
        while (!traversalQueue.isEmpty()) {
            Node currentNode = traversalQueue.poll();
            listPre.add(currentNode.code);
            if (currentNode.left != null) {
                traversalQueue.add(currentNode.left);
            }
            if (currentNode.right != null) {
                traversalQueue.add(currentNode.right);
            }
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
