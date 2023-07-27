/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CloDAO;
import dao.CurriculumDAO;
import jakarta.servlet.annotation.MultipartConfig;
import model.Clo;
import org.apache.poi.ss.usermodel.Workbook;

import dao.DecisionDAO;
import dao.PloDAO;
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
import model.Syllabus;
import model.User;
//import com.spire.xls.Workbook;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.util.List;
import model.Curriculum;
import model.Plo;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.DataFormatter;
//import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Admin
 */
@SuppressWarnings("unchecked")
@MultipartConfig
public class ImportFileController extends HttpServlet {

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
        if (action.equalsIgnoreCase("importSyllabus")) {
            ArrayList<Syllabus> validList = new ArrayList<>(); // Danh sách cho các giá trị hợp lệ
            List<String> invalidMessage = new ArrayList<>();
            List<Syllabus> invalidList = new ArrayList<>();
            InputStream inputStream = null;
            SyllabusDAO sD = new SyllabusDAO();
            SubjectDAO subDao = new SubjectDAO();
            SettingDAO settingDao = new SettingDAO();
            DecisionDAO deciD = new DecisionDAO();
            UserDAO uD = new UserDAO();
            HttpSession session = req.getSession();
            User acc = (User) session.getAttribute("acc");
            List<Integer> errorRows = new ArrayList<>();
            List<String> errorMessages = new ArrayList<>();
            try {
                inputStream = req.getPart("file").getInputStream();
                org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(inputStream);
                DataFormatter dataFormatter = new DataFormatter();

                // Duyệt qua các sheet và lấy nội dung các ô
                for (Sheet sheet : workbook) {

                    if (sheet.getSheetName().equals("syllabus")) {
                        int rowCount = 0; // Biến đếm số hàng
                        int id = sD.getLastId();
                        for (Row row : sheet) {
                            errorRows.clear();
                            errorMessages.clear();
                            Syllabus sl = new Syllabus();
                            if (rowCount == 0) { // Bỏ qua hàng đầu
                                rowCount++;
                                continue;
                            }
                            boolean hasError = false;

                            // Subject Id
                            Cell firstCell = row.getCell(0);
                            if (firstCell == null) {
                                hasError = true;
                                errorMessages.add("Missing value: Subject Id is required!");
                            } else {
                                String subjectId = dataFormatter.formatCellValue(firstCell);
                                if (acc.getRoleName().equalsIgnoreCase("crdd staff")) {
                                    if (!subDao.checkSubjectId(acc.getUserId(), subjectId)) {
                                        hasError = true;
                                        errorMessages.add("Invalid value: Subject Id does not exist!");
                                        sl.setSubjectid(dataFormatter.formatCellValue(firstCell));
                                    } else {
                                        sl.setSubjectid(dataFormatter.formatCellValue(firstCell));
                                    }
                                } else {
                                    if (!subDao.checkSubjectIdForHead(subjectId)) {
                                        hasError = true;
                                        errorMessages.add("Invalid value: Subject Id does not exist!");
                                        sl.setSubjectid(dataFormatter.formatCellValue(firstCell));
                                    } else {
                                        sl.setSubjectid(dataFormatter.formatCellValue(firstCell));
                                    }
                                }

                            }
                            // name
                            Cell secondCell = row.getCell(1);
                            if (secondCell == null) {
                                hasError = true;
                                errorMessages.add("Missing value: Syllabus name is required!");
                            } else {
                                sl.setName(dataFormatter.formatCellValue(secondCell));
                            }
                            // Degree Level
                            Cell Cell3 = row.getCell(2);
                            if (Cell3 == null) {
                                hasError = true;
                                errorMessages.add("Missing value: Degree Level is required!");
                            } else {
                                String degree = dataFormatter.formatCellValue(Cell3);
                                if (!settingDao.checkDegreeId(degree)) {
                                    hasError = true;
                                    errorMessages.add("Invalid value: Degree Level does not exist!");
                                    sl.setDegreeLevel(dataFormatter.formatCellValue(Cell3));
                                } else {
                                    sl.setDegreeLevel(dataFormatter.formatCellValue(Cell3));
                                }

                            }
                            // nocredit
                            Cell Cell4 = row.getCell(3);
                            if (Cell4 == null) {
                                hasError = true;
                                errorMessages.add("Missing value: NoCredit is required!");
                            } else {
                                String noCredit = dataFormatter.formatCellValue(Cell4);
                                boolean checkNoCredit = false;
                                try {
                                    int checkNocrredit = Integer.parseInt(noCredit);
                                    checkNoCredit = true;
                                    if (checkNocrredit < 1) {
                                        hasError = true;
                                        checkNoCredit = false;
                                        errorMessages.add("Invalid value: NoCredit must be integers greater than 0!");
                                        sl.setNoCredit(dataFormatter.formatCellValue(Cell4));
                                    }
                                } catch (NumberFormatException e) {
                                    hasError = true;
                                    checkNoCredit = false;
                                    errorMessages.add("Invalid value: NoCredit must be integers greater than 0!");
                                    sl.setNoCredit(dataFormatter.formatCellValue(Cell4));
                                }
                                if (checkNoCredit) {
                                    sl.setNoCredit(dataFormatter.formatCellValue(Cell4));
                                }
                            }
                            // Decision
                            Cell Cell5 = row.getCell(4);
                            if (Cell5 == null) {
                                hasError = true;
                                errorMessages.add("Missing value: Decision is required!");
                            } else {
                                String decision = dataFormatter.formatCellValue(Cell5);

                                if (!deciD.CheckDecisionE(decision)) {
                                    hasError = true;
                                    errorMessages.add("Invalid value: Decision does not exist!");
                                    sl.setDecision_id(dataFormatter.formatCellValue(Cell5));
                                } else {
                                    sl.setDecision_id(dataFormatter.formatCellValue(Cell5));
                                }
                            }

                            // IsApproved
                            Cell Cell6 = row.getCell(5);
                            if (Cell6 == null) {
                                hasError = true;
                                errorMessages.add("Missing value: IsApproved is required");
                            } else {
                                String IsApproved = dataFormatter.formatCellValue(Cell6);
                                if (!IsApproved.equals("0") && !IsApproved.equals("1")) {
                                    hasError = true;
                                    errorMessages.add("Invalid value: IsApproved must be either 0 or 1");
                                    sl.setIsApproved(dataFormatter.formatCellValue(Cell6));
                                } else {
                                    sl.setIsApproved(dataFormatter.formatCellValue(Cell6));
                                }
                            }
                            //Desginer
                            Cell Cell7 = row.getCell(6);
                            if (Cell7 == null) {
                                hasError = true;
                                errorMessages.add("Missing value: Desginer is required");
                            } else {
                                String desginer = dataFormatter.formatCellValue(Cell7);

                                if (!uD.checkUserE(desginer)) {
                                    hasError = true;
                                    errorMessages.add("Invalid value: Desginer does not exist!");
                                    sl.setDesginerId(dataFormatter.formatCellValue(Cell7));
                                } else {
                                    sl.setDesginerId(dataFormatter.formatCellValue(Cell7));
                                }
                            }

                            if (!hasError) {
                                id++;
                                sl.setId(String.valueOf(id));
                                String create_by = acc.getUserId();
                                sl.setCreateBy(create_by);
                                SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime dateTime = LocalDateTime.now();
                                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                                String created_at = spf.format(date);
                                sl.setCreateAt(created_at);
                                validList.add(sl);
                            } else {

                                sl.setId("#N/A");
                                String create_by = acc.getUserId();
                                sl.setCreateBy(create_by);
                                SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime dateTime = LocalDateTime.now();
                                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                                String created_at = spf.format(date);
                                sl.setCreateAt(created_at);
                                invalidList.add(sl);
                                invalidMessage.add("Error row " + rowCount + ": " + errorMessages);
                            }
                            rowCount++;

                        }
                    }
                }
                // Đóng workbook và FileInputStream
                workbook.close();

//                req.setAttribute("rowErrors", rowErrors);
            } catch (IOException e) {
                // Xử lý exception
            }

//        cDao.addListClo(validList);
//            System.out.println(validList);
//            System.out.println(invalidList);
//            System.out.println(invalidMessage);
            session.setAttribute("validList", validList);
            req.setAttribute("validList", validList);
            req.setAttribute("invalidMessage", invalidMessage);
            req.setAttribute("invalidList", invalidList);

            ArrayList<Syllabus> listSyllabus = new ArrayList<>();
            String numberOfSyllabus;
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

            req.setAttribute("listSyllabus", listSyllabus);
            req.setAttribute("alert", "");
            session.setAttribute("importTable", "true");
            req.getRequestDispatcher("syllabuslist.jsp").forward(req, resp);
//        }
        }

        if (action.equalsIgnoreCase("importClo")) {
            ArrayList<Clo> validCloList = new ArrayList<>(); // Danh sách cho các giá trị hợp lệ
            List<String> invalidCloMessage = new ArrayList<>();
            List<Clo> invalidCloList = new ArrayList<>();
            InputStream inputStream = null;
            String sylId = req.getParameter("sylId");
            SyllabusDAO sllDAO = new SyllabusDAO();
            Syllabus syllabus = sllDAO.getSyllabusbyId(sylId);
            CloDAO cDao = new CloDAO();

            try {
                inputStream = req.getPart("file").getInputStream();
                org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(inputStream);
                DataFormatter dataFormatter = new DataFormatter();

                // Duyệt qua các sheet và lấy nội dung các ô
                for (Sheet sheet : workbook) {

                    if (sheet.getSheetName().equals("CloList")) {
                        int rowCount = 0; // Biến đếm số hàng
                        int id = cDao.getlastCloId();

                        for (Row row : sheet) {
                            List<Integer> errorRows = new ArrayList<>();
                            List<String> errorMessages = new ArrayList<>();
                            Clo clo = new Clo();
                            if (rowCount == 0) { // Bỏ qua hàng đầu
                                rowCount++;
                                continue;
                            }
                            boolean hasError = false;

                            Cell firstCell = row.getCell(0);
                            if (firstCell == null) {
                                hasError = true;
                                errorMessages.add("Missing value: PO name is required");
                            } else {
                                String Name = dataFormatter.formatCellValue(firstCell);
                                // Kiểm tra Name trong cơ sở dữ liệu
                                if (cDao.checkNameExist(Name)) {
                                    hasError = true;
//                                System.out.println("Name "+ Name);
//                                System.out.println(cDao.checkNameExist(Name));
                                    errorMessages.add("Duplicate Name");
                                    clo.setClo_name(dataFormatter.formatCellValue(firstCell));
                                } else {
                                    clo.setClo_name(dataFormatter.formatCellValue(firstCell));
                                }
                            }

                            Cell secondCell = row.getCell(1);
                            if (secondCell != null) {
                                clo.setClo_description(dataFormatter.formatCellValue(secondCell));
                            }
                            Cell fourthCell = row.getCell(2);
                            if (fourthCell == null) {
                                hasError = true;
                                errorMessages.add("Missing value: isActive is required");
                            } else {
                                String cellValue = dataFormatter.formatCellValue(fourthCell);
                                if (!cellValue.equals("0") && !cellValue.equals("1")) {
                                    hasError = true;
                                    errorMessages.add("Invalid value: isActive must be either 0 or 1");
                                    clo.setIs_active(cellValue);
                                } else {
                                    clo.setIs_active(cellValue);
                                }
                            }
                            if (!hasError) {
                                if (cDao.getlastCloId() == 0) {
                                    id = 1;
                                } else {
                                    id++;
                                }
                                clo.setClo_id(String.valueOf(id));
                                HttpSession session = req.getSession();
                                User accUsername = (User) session.getAttribute("acc");
                                String create_by = accUsername.getUserId();
                                clo.setCreated_by(create_by);
                                clo.setSyllabus_id(sylId);
                                SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime dateTime = LocalDateTime.now();
                                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                                String created_at = spf.format(date);
                                clo.setCreated_at(created_at);
                                validCloList.add(clo);
                            } else {

                                clo.setClo_id("N/A");
                                HttpSession session = req.getSession();
                                User accUsername = (User) session.getAttribute("acc");
                                String create_by = accUsername.getUserId();
                                clo.setCreated_by(create_by);
                                clo.setSyllabus_id(sylId);
                                SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime dateTime = LocalDateTime.now();
                                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                                String created_at = spf.format(date);
                                clo.setCreated_at(created_at);
                                invalidCloList.add(clo);
                                invalidCloMessage.add("Error row " + rowCount + ": " + errorMessages);
                            }
                            rowCount++;
                            List<String> rowErrors = new ArrayList<>();
                            if (!errorRows.isEmpty()) {
                                for (int i = 0; i < errorRows.size(); i++) {
                                    int errorRow = errorRows.get(i);
                                    String errorMessage = errorMessages.get(i);
                                    String rowError = "Error row " + errorRow + ": " + errorMessage;
                                    System.out.println(rowErrors);
                                    rowErrors.add(rowError);
                                }
                            }
                        }
                    }
                }
                // Đóng workbook và FileInputStream
                workbook.close();

                // In ra các thông báo lỗi chi tiết
            } catch (IOException e) {
                // Xử lý exception
            }

            req.setAttribute("syllabus", syllabus);
//          System.out.println(syllabus);
            System.out.println("valid " + validCloList);
            System.out.println("Invalid " + invalidCloList);
            HttpSession session = req.getSession();
            session.setAttribute("validCloList", validCloList);
            req.setAttribute("validCloList", validCloList);
            req.setAttribute("invalidCloMessage", invalidCloMessage);
            req.setAttribute("invalidCloList", invalidCloList);

//        response.sendRedirect(Constants.URL_PROJECT + Constants.URL_POLIST + "?idCurrent=" + idCurrent);
            req.getRequestDispatcher("/view/common/viewListPo.jsp").forward(req, resp);
//        }
        }
        if (action.equalsIgnoreCase("importPlo")) {
            ArrayList<Plo> validPloList = new ArrayList<>(); // Danh sách cho các giá trị hợp lệ
            List<String> invalidPloMessage = new ArrayList<>();
            List<Plo> invalidPloList = new ArrayList<>();
            InputStream inputStream = null;
            String Ccode = req.getParameter("Ccode");
            String Cid = req.getParameter("Cid");
            CurriculumDAO cDAO = new CurriculumDAO();
            Curriculum c = cDAO.getCurriculumOverview(Ccode);
            PloDAO pDao = new PloDAO();

            try {
                inputStream = req.getPart("file").getInputStream();
                org.apache.poi.ss.usermodel.Workbook workbook = new XSSFWorkbook(inputStream);
                DataFormatter dataFormatter = new DataFormatter();

                // Duyệt qua các sheet và lấy nội dung các ô
                for (Sheet sheet : workbook) {

                    if (sheet.getSheetName().equals("PloList")) {
                        int rowCount = 0; // Biến đếm số hàng
                        int id = pDao.getlastPloId();

                        for (Row row : sheet) {
                            List<Integer> errorRows = new ArrayList<>();
                            List<String> errorMessages = new ArrayList<>();
                            Plo plo = new Plo();
                            if (rowCount == 0) { // Bỏ qua hàng đầu
                                rowCount++;
                                continue;
                            }
                            boolean hasError = false;

                            Cell firstCell = row.getCell(0);
                            if (firstCell == null) {
                                hasError = true;
                                errorMessages.add("Missing value: PO name is required");
                            } else {
                                String Name = dataFormatter.formatCellValue(firstCell);
                                // Kiểm tra Name trong cơ sở dữ liệu
                                if (pDao.checkNameExist(Name, Cid)) {
                                    hasError = true;
//                                System.out.println("Name "+ Name);
//                                System.out.println(cDao.checkNameExist(Name));
                                    errorMessages.add("Duplicate Name");
                                    plo.setPlo_name(dataFormatter.formatCellValue(firstCell));
                                } else {
                                    plo.setPlo_name(dataFormatter.formatCellValue(firstCell));
                                }
                            }

                            Cell secondCell = row.getCell(1);
                            if (secondCell != null) {
                                plo.setPlo_description(dataFormatter.formatCellValue(secondCell));
                            }
                            Cell fourthCell = row.getCell(2);
                            if (fourthCell == null) {
                                hasError = true;
                                errorMessages.add("Missing value: isActive is required");
                            } else {
                                String cellValue = dataFormatter.formatCellValue(fourthCell);
                                if (!cellValue.equals("0") && !cellValue.equals("1")) {
                                    hasError = true;
                                    errorMessages.add("Invalid value: isActive must be either 0 or 1");
                                    plo.setIs_Active(cellValue);
                                } else {
                                    plo.setIs_Active(cellValue);
                                }
                            }
                            if (!hasError) {
                                if (pDao.getlastPloId() == 0) {
                                    id = 1;
                                } else {
                                    id++;
                                }
                                plo.setPlo_id(String.valueOf(id));
                                HttpSession session = req.getSession();
                                User accUsername = (User) session.getAttribute("acc");
                                String create_by = accUsername.getUserId();
                                plo.setCreated_by(create_by);
                                plo.setCurriculum_code(Ccode);
                                plo.setCurriculum_id(Cid);
                                SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime dateTime = LocalDateTime.now();
                                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                                String created_at = spf.format(date);
                                plo.setCreated_at(created_at);
                                validPloList.add(plo);
                            } else {

                                plo.setPlo_id("N/A");
                                HttpSession session = req.getSession();
                                User accUsername = (User) session.getAttribute("acc");
                                String create_by = accUsername.getUserId();
                                plo.setCreated_by(create_by);
                                plo.setCurriculum_code(Ccode);
                                plo.setCurriculum_id(Cid);
                                SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime dateTime = LocalDateTime.now();
                                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                                String created_at = spf.format(date);
                                plo.setCreated_at(created_at);
                                invalidPloList.add(plo);
                                invalidPloMessage.add("Error row " + rowCount + ": " + errorMessages);
                            }
                            rowCount++;
                            List<String> rowErrors = new ArrayList<>();
                            if (!errorRows.isEmpty()) {
                                for (int i = 0; i < errorRows.size(); i++) {
                                    int errorRow = errorRows.get(i);
                                    String errorMessage = errorMessages.get(i);
                                    String rowError = "Error row " + errorRow + ": " + errorMessage;
                                    System.out.println(rowErrors);
                                    rowErrors.add(rowError);
                                }
                            }
                        }
                    }
                }
                // Đóng workbook và FileInputStream
                workbook.close();

                // In ra các thông báo lỗi chi tiết
            } catch (IOException e) {
                // Xử lý exception
            }

//          System.out.println(syllabus);
            System.out.println("valid " + validPloList);
            System.out.println("Invalid " + invalidPloList);
            HttpSession session = req.getSession();
            session.setAttribute("validPloList", validPloList);
            req.setAttribute("validPloList", validPloList);
            System.out.println("Import: " + Ccode);
            req.setAttribute("Ccode", Ccode);
            req.setAttribute("c", c);
            req.setAttribute("invalidPloMessage", invalidPloMessage);
            req.setAttribute("invalidPloList", invalidPloList);

//        response.sendRedirect(Constants.URL_PROJECT + Constants.URL_POLIST + "?idCurrent=" + idCurrent);
            req.getRequestDispatcher("/view/admin/viewListPlo.jsp").forward(req, resp);
//        }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("viewListPo.jsp").forward(req, resp);
    }
}
