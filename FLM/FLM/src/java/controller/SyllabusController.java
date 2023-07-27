/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DecisionDAO;
import dao.SettingDAO;
import dao.SubjectDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import dao.SyllabusDAO;
import dao.UserDAO;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import model.Decision;
import model.Setting;
import model.Subject;
import model.Syllabus;
import model.User;
import com.spire.data.table.DataTable;
import com.spire.data.table.common.JdbcAdapter;
import com.spire.xls.ExcelVersion;
import com.spire.xls.Workbook;
import com.spire.xls.Worksheet;
import jakarta.servlet.http.Part;
import java.sql.ResultSet;



/**
 *
 * @author Admin
 */
@SuppressWarnings("unchecked")
public class SyllabusController extends HttpServlet {
    private static final long serialVersionUID = 1L;
//     Phương thức hỗ trợ để lấy tên file từ Part

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf("=") + 1).trim().replace("\"", "");
            }
        }
        return "";
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String searchBy = req.getParameter("selectFilter");
        String search = req.getParameter("search");
        String thisPage = req.getParameter("thisPage");
        String numberOfSyllabus = "0";
        
        SyllabusDAO sD = new SyllabusDAO();
        SubjectDAO subDao = new SubjectDAO();
        SettingDAO settingDao = new SettingDAO();

        HttpSession session = req.getSession();
        User acc = (User) session.getAttribute("acc");
        if (action.equals("list")) {
            ArrayList<Syllabus> listSyllabus = new ArrayList<>();
            if (searchBy.equalsIgnoreCase("name")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    numberOfSyllabus = sD.getNumberOfSyllabusForTeacher("", search);
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                        numberOfSyllabus = sD.getNumberOfSyllabusForCrddStaff(acc.getUserId(), "", search);
                    } else {
                        numberOfSyllabus = sD.getNumberOfSyllabusForAdmin("", search);
                    }
                }
            }
            if (searchBy.equalsIgnoreCase("code")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    numberOfSyllabus = sD.getNumberOfSyllabusForTeacher(search, "");
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                        numberOfSyllabus = sD.getNumberOfSyllabusForCrddStaff(acc.getUserId(), search, "");
                    } else {
                        numberOfSyllabus = sD.getNumberOfSyllabusForAdmin(search, "");
                    }
                }

            }
            int start = 0;
            int limit = 10;

            start = ((Integer.parseInt(thisPage) - 1) * limit);
            if (thisPage.equals("0")) {
                req.setAttribute("thisPage", "1");
                start = 0;
            } else {
                req.setAttribute("thisPage", thisPage);
            }

            if (Integer.parseInt(numberOfSyllabus) < start) {
                req.setAttribute("thisPage", Math.floor(Integer.parseInt(numberOfSyllabus) / limit) + 1);
                start = (int) (Math.floor(Integer.parseInt(numberOfSyllabus) / limit)) * limit;
            }

            if (searchBy.equalsIgnoreCase("name")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    listSyllabus = sD.getListSyllabusByNameForTeacher(search, String.valueOf(start), String.valueOf(limit));
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                        listSyllabus = sD.getListSyllabusByNameForCrddStaff(acc.getUserId(), search, String.valueOf(start), String.valueOf(limit));
                    } else {
                        listSyllabus = sD.getListSyllabusByName(search, String.valueOf(start), String.valueOf(limit));
                    }
                }

            }
            if (searchBy.equalsIgnoreCase("code")) {
                if (acc.getRoleName().equalsIgnoreCase("student") || acc.getRoleName().equalsIgnoreCase("teacher")) {
                    listSyllabus = sD.getListSyllabusByCodeForTeacher(search, String.valueOf(start), String.valueOf(limit));
                } else {
                    if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                        listSyllabus = sD.getListSyllabusByCodeForCrddStaff(acc.getUserId(), search, String.valueOf(start), String.valueOf(limit));
                    } else {
                        listSyllabus = sD.getListSyllabusByCode(search, String.valueOf(start), String.valueOf(limit));
                    }
                }

            }
            req.setAttribute("selectFilter", searchBy);
            req.setAttribute("numSyllabus", numberOfSyllabus);
            req.setAttribute("search", search);
            req.setAttribute("listSyllabus", listSyllabus);
            req.setAttribute("alert", "");
            req.getRequestDispatcher("syllabuslist.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("add")) {
            String subject = req.getParameter("selectSubject");
            String name = req.getParameter("sName").trim();
            String degree = req.getParameter("selectDegree");
            String decision = req.getParameter("selectDecision");
            String noCredit = req.getParameter("noCredit");
            String isApproved = req.getParameter("isApproved");
            String desginer = req.getParameter("selectDesginer");
            isApproved = isApproved == null ? "0" : "1";

            String alert = "";
            if (name.isEmpty()) {
                alert = "Name not be empty!";
            }
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

            if (!alert.isEmpty()) {

                ArrayList<Subject> listSubject = new ArrayList<>();
                listSubject = subDao.getListOfSubjectForHead();
                String type = "Degree Level";
                ArrayList<Setting> listDegree = settingDao.getListSettingByType(type.toLowerCase());
                DecisionDAO dD = new DecisionDAO();
                ArrayList<Decision> listDecision = dD.getListDecision();
                UserDAO uD = new UserDAO();
                ArrayList<User> listUser = uD.getListAccount("syllabus designer", "");

                req.setAttribute("listSubject", listSubject);
                req.setAttribute("listUser", listUser);
                req.setAttribute("listDecision", listDecision);
                req.setAttribute("listDegree", listDegree);

                req.setAttribute("subjectR", subject);
                req.setAttribute("name", name);
                req.setAttribute("degreeR", degree);
                req.setAttribute("decisionR", decision);
                req.setAttribute("noCredit", noCredit);
                req.setAttribute("isApproved", isApproved);
                req.setAttribute("desginer", desginer);
                req.setAttribute("alert", alert);
                req.getRequestDispatcher("addsyllabus.jsp").forward(req, resp);
                return;
            }
            
            SyllabusDAO slDao = new SyllabusDAO();
            int syllabusId = slDao.getLastId() + 1;
            String isActive = "1";
            String createdBy = acc.getUserId();
            SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.now();
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
            String createdAt = spf.format(date);

            slDao.addSyllabus(syllabusId, subject, name, degree, noCredit, isApproved, isActive, createdBy, createdAt, decision, desginer);
            session.setAttribute("toast", "true");
//            req.setAttribute("toast", "true");
            resp.sendRedirect("/FLM_NEW/view/common/syllabus?action=list");

        }
        if (action.equalsIgnoreCase("import")){
            
            ArrayList<Syllabus> myList = (ArrayList<Syllabus>) session.getAttribute("validList");
            sD.addListSyllabus(myList);
            session.setAttribute("toast", "true");
            resp.sendRedirect("/FLM_NEW/view/common/syllabus?action=list");
        }
           
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User acc = (User) session.getAttribute("acc");
        SubjectDAO subDao = new SubjectDAO();
        SettingDAO settingDao = new SettingDAO();
        if (action.equals("list")) {

            ArrayList<Syllabus> listSyllabus = new ArrayList<>();
            if (session.getAttribute("toast") == "true") {
                String numberOfSyllabus;
                SyllabusDAO sD = new SyllabusDAO();
                if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                    numberOfSyllabus = sD.getNumberOfSyllabusForCrddStaff(acc.getUserId(), "", "");
                } else {
                    numberOfSyllabus = sD.getNumberOfSyllabusForAdmin("", "");
                }
                int start = 0;
                int limit = 10;
                req.setAttribute("thisPage", "1");
                if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                    listSyllabus = sD.getListSyllabusByCodeForCrddStaff(acc.getUserId(), "", String.valueOf(start), String.valueOf(limit));
                } else {
                    listSyllabus = sD.getListSyllabusByCode("", String.valueOf(start), String.valueOf(limit));
                }
//                session.removeAttribute("toast");
//                req.setAttribute("toast", "true");
                req.setAttribute("numSyllabus", numberOfSyllabus);
            } else {

                listSyllabus = null;
                req.setAttribute("thisPage", "0");
            }

            req.setAttribute("listSyllabus", listSyllabus);
            req.setAttribute("alert", "");

            req.getRequestDispatcher("syllabuslist.jsp").forward(req, resp);
            
        }
        if (action.equalsIgnoreCase("overview")) {
            SyllabusDAO sD = new SyllabusDAO();
            String syllabusId = req.getParameter("sylId");
            Syllabus sl = sD.getSyllabusbyId(syllabusId);
            req.setAttribute("syllabus", sl);
            req.setAttribute("dropDownS", 1);
            req.getRequestDispatcher("syllabusoverview.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("add")) {

            ArrayList<Subject> listSubject = new ArrayList<>();
            if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                listSubject = subDao.getListOfSubjectForStaff(acc.getUserId());
            } else {
                listSubject = subDao.getListOfSubjectForHead();
            }

            String type = "Degree Level";
            ArrayList<Setting> listDegree = settingDao.getListSettingByType(type.toLowerCase());
            DecisionDAO dD = new DecisionDAO();
            ArrayList<Decision> listDecision = dD.getListDecision();
            UserDAO uD = new UserDAO();
            ArrayList<User> listUser = uD.getListAccount("syllabus designer", "");

            req.setAttribute("listSubject", listSubject);
            req.setAttribute("listUser", listUser);
            req.setAttribute("listDecision", listDecision);
            req.setAttribute("listDegree", listDegree);
            req.getRequestDispatcher("addsyllabus.jsp").forward(req, resp);
        }
        if (action.equalsIgnoreCase("import")) {
            UserDAO uD = new UserDAO();
            DecisionDAO dD = new DecisionDAO();
            String type = "Degree Level";
            String toFile = "‪‪tem\\Import Syllabus.xlsx";
            //Create a Workbook object
            Workbook wb = new Workbook();
            //destop
//            wb.loadFromFile("C:\\Users\\Admin\\Desktop\\S5\\SWP\\g2\\FLM_NEW\\web\\assets\\templateFile\\Import Syllabus.xlsx");
            //laptop
            wb.loadFromFile("C:\\Users\\trung\\OneDrive\\Máy tính\\My_FLM\\g2\\FLM_NEW\\web\\assets\\templateFile\\Import Syllabus.xlsx");
            //Get the first worksheet
            Worksheet sheet = wb.getWorksheets().get(1);

            //Create a DataTable object
            DataTable dataTable = new DataTable();
            ResultSet resultSet;
            try {

                if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                    resultSet = subDao.getResultSetOfSubjectForStaff(acc.getUserId());
                } else {
                    resultSet = subDao.getResultSetOfSubjectForHead();
                }
                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                //Export data from database to datatable
                jdbcAdapter.fillDataTable(dataTable, resultSet);
            } catch (Exception e) {
                e.printStackTrace();
            }

            //Write datatable to the worksheet
            sheet.insertDataTable(dataTable, true, 1, 1);
            //Auto fit column width
            sheet.getAllocatedRange().autoFitColumns();
            wb.saveToFile(toFile, ExcelVersion.Version2016);

            DataTable dataTable1 = new DataTable();
            sheet = wb.getWorksheets().get(2);

            try {

                resultSet = settingDao.getResultSetSettingByType(type.toLowerCase());

                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                //Export data from database to datatable
                jdbcAdapter.fillDataTable(dataTable1, resultSet);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Write datatable to the worksheet
            sheet.insertDataTable(dataTable1, true, 1, 1);
            //Auto fit column width
            sheet.getAllocatedRange().autoFitColumns();

            //Save to an Excel file
            wb.saveToFile(toFile, ExcelVersion.Version2016);

            DataTable dataTable2 = new DataTable();
            sheet = wb.getWorksheets().get(3);

            try {

                resultSet = dD.getResultSetDecision();

                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                //Export data from database to datatable
                jdbcAdapter.fillDataTable(dataTable2, resultSet);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Write datatable to the worksheet
            sheet.insertDataTable(dataTable2, true, 1, 1);
            //Auto fit column width
            sheet.getAllocatedRange().autoFitColumns();

            //Save to an Excel file
            wb.saveToFile(toFile, ExcelVersion.Version2016);

            DataTable dataTable3 = new DataTable();
            sheet = wb.getWorksheets().get(4);

            try {

                resultSet = uD.getResultSetAccount("syllabus designer", "");

                JdbcAdapter jdbcAdapter = new JdbcAdapter();
                //Export data from database to datatable
                jdbcAdapter.fillDataTable(dataTable3, resultSet);

            } catch (Exception e) {
                e.printStackTrace();
            }

            //Write datatable to the worksheet
            sheet.insertDataTable(dataTable3, true, 1, 1);
            //Auto fit column width
            sheet.getAllocatedRange().autoFitColumns();

            //Save to an Excel file
            wb.saveToFile(toFile, ExcelVersion.Version2016);

            ArrayList<Syllabus> listSyllabus = new ArrayList<>();
            String numberOfSyllabus;
            SyllabusDAO sD = new SyllabusDAO();
            if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                numberOfSyllabus = sD.getNumberOfSyllabusForCrddStaff(acc.getUserId(), "", "");
            } else {
                numberOfSyllabus = sD.getNumberOfSyllabusForAdmin("", "");
            }
            int start = 0;
            int limit = 10;
            req.setAttribute("thisPage", "1");
            if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                listSyllabus = sD.getListSyllabusByCodeForCrddStaff(acc.getUserId(), "", String.valueOf(start), String.valueOf(limit));
            } else {
                listSyllabus = sD.getListSyllabusByCode("", String.valueOf(start), String.valueOf(limit));
            }
            req.setAttribute("numSyllabus", numberOfSyllabus);

            listSyllabus = null;
            req.setAttribute("thisPage", "0");

            req.setAttribute("listSyllabus", listSyllabus);

            req.setAttribute("alert", "");
            req.setAttribute("importData", "true");

            req.getRequestDispatcher("syllabuslist.jsp").forward(req, resp);
        }

    }

}
