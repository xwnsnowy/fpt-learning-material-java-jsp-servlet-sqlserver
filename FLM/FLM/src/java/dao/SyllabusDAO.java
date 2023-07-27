/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Decision;
import model.Subject;
import model.Syllabus;

/**
 *
 * @author Admin
 */
public class SyllabusDAO extends BaseDAO {

    public ArrayList<Syllabus> getListSyllabusByName(String search, String start, String end) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {

            String strSelect
                    = "select sl.syllabus_id, s.subject_code, s.subject_name, sl.name, "
                    + "sl.is_active, sl.is_approved, d.decision_no,d.decision_ApprovedDate from flm_db.syllabus sl \n"
                    + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                    + "where sl.name like ? order by sl.syllabus_id asc limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            pstm.setInt(2, Integer.parseInt(start));
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                Decision d = new Decision();
                sl.setId(rs.getString(1));
                sl.setSubject(s);
                sl.setName(rs.getString(4));
                sl.setIsActive((rs.getString(5).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(6).equals("1")) ? "true" : "false");
                d.setDecision_no(rs.getString(7));
                d.setDecision_date(rs.getDate(8));
                sl.setDecision(d);
                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Syllabus> getListSyllabusByCode(String search, String start, String end) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect
                    = "select sl.syllabus_id, s.subject_code, s.subject_name, sl.name, sl.is_active, sl.is_approved, d.decision_no,d.decision_ApprovedDate from flm_db.syllabus sl \n"
                    + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                    + "where s.subject_code like ? order by sl.syllabus_id asc limit ?,?;";
            
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            pstm.setInt(2, Integer.parseInt(start));
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                Decision d = new Decision();
                sl.setId(rs.getString(1));
                sl.setSubject(s);
                sl.setName(rs.getString(4));
                sl.setIsActive((rs.getString(5).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(6).equals("1")) ? "true" : "false");
                d.setDecision_no(rs.getString(7));
                d.setDecision_date(rs.getDate(8));
                sl.setDecision(d);
                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusByCode: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }
//    public static void main(String[] args) {
//        SyllabusDAO sd = new SyllabusDAO();
//        Syllabus s = sd.getSyllabusbyId("4");
//        System.out.println(s.getId());
//    }

    public Syllabus getSyllabusbyId(String syllabusId) {
        try {
            String strSelect
                    = "select sl.syllabus_id, sl.name, s.subject_code, sl.no_of_credit, st.setting_name, s.subject_description, sl.scoringScale, "
                    + "sl.minAvgMarkToPass, sl.note, sl.is_active, sl.is_approved, d.decision_no,d.decision_ApprovedDate from flm_db.syllabus sl \n"
                    + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                    + "join flm_db.setting st on st.setting_id = sl.degree_level\n"
                    + "where sl.syllabus_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, syllabusId);
            ResultSet rs = pstm.executeQuery();
            Syllabus sl = new Syllabus();
            while (rs.next()) {

                sl.setId(rs.getString(1));
                sl.setName(rs.getString(2));
                Subject s = new Subject();
                s.setCode(rs.getString(3));
                sl.setNoCredit(rs.getString(4));
                sl.setDegreeLevel(rs.getString(5));
                s.setDescription(rs.getString(6));
                sl.setScoringScale(rs.getString(7));
                sl.setMinAvgMarkToPass(rs.getString(8));
                if (rs.getString(9) != null) {
                    sl.setNote(rs.getString(9).replace("\n", "<br>"));
                } else {
                    sl.setNote(rs.getString(9));
                }
                sl.setSubject(s);
                Decision d = new Decision();
                sl.setIsActive((rs.getString(10).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(11).equals("1")) ? "true" : "false");
                d.setDecision_no(rs.getString(12));
                d.setDecision_date(rs.getDate(13));
                sl.setDecision(d);
            }
            return sl;
        } catch (Exception e) {
            System.out.println("getSyllabusbyId: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public Syllabus getSyllabusByCode(String code) {
        try {
            String strSelect = "SELECT syllabus.syllabus_id, syllabus.name, syllabus.no_of_credit, \n"
                    + "syllabus.is_active, syllabus.is_approved, syllabus.degree_level,syllabus.scoringScale, \n"
                    + "syllabus.minAvgMarkToPass, syllabus.note, \n"
                    + "syllabus.decision_id, syllabus.subject_id FROM flm_db.syllabus join flm_db.`subject` \n"
                    + "on `subject`.subject_id = syllabus.subject_id \n"
                    + "WHERE `subject`.subject_code = ? and `syllabus`.is_active = '1';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Syllabus(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6),
                        rs.getString(7), rs.getString(8),
                        rs.getString(9), rs.getString(10), rs.getString(11));
            }
        } catch (Exception e) {
            System.out.println("error getSyllabusByCode: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

//    public static void main(String[] args) {
//        SyllabusDAO sd = new SyllabusDAO();
//        Syllabus s = sd.getSyllabusByCode("SWP391");
//        System.out.println(s);
//    }
    public String getNumberOfSyllabusForAdmin(String code, String name) {
        try {
            String strSelect;
            if (!code.equals("")) {
                strSelect
                        = "select count(sl.syllabus_id) from flm_db.syllabus sl \n"
                        + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                        + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                        + "where s.subject_code like ?;";
            } else {
                strSelect
                        = "select count(sl.syllabus_id) from flm_db.syllabus sl \n"
                        + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                        + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                        + "where sl.name like ?;";
            }
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            if (!code.equals("")) {

                pstm.setString(1, "%" + code + "%");
            } else {
                pstm.setString(1, "%" + name + "%");
            }
            ResultSet rs = pstm.executeQuery();
            String nunber = "0";
            while (rs.next()) {
                nunber = rs.getString(1);

            }
            return nunber;
        } catch (Exception e) {
            System.out.println("getNumberOfSyllabusForAdmin: " + e.getMessage());
        }
        return null;
    }

    public int getLastId() {
        try {
            String strSelect = "select max(syllabus_id) from flm_db.syllabus;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            int id = -1;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        } catch (Exception e) {
            System.out.println("error getLastId: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return -1;
    }

    public void addSyllabus(int syllabusId, String subject, String name, String degree, String noCredit, String approved, String active, String createdBy, String createdAt, String decision, String desginer) {
        try {
            String strSelect = "INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `subject_id`, `created_by`, `created_at`, `decision_id`, `designer_id`) \n"
                    + "VALUES (?, ?, ?, b?, b?, ?,?, ?, ?, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setInt(1, syllabusId);
            pstm.setString(2, name);
            pstm.setString(3, noCredit);
            pstm.setString(4, active);
            pstm.setString(5, approved);
            pstm.setString(6, degree);
            pstm.setString(7, subject);
            pstm.setString(8, createdBy);
            pstm.setString(9, createdAt);
            pstm.setString(10, decision);
            if (desginer.isEmpty()) {
                pstm.setNull(11, java.sql.Types.VARCHAR);
            } else {
                pstm.setString(11, desginer);
            }
            pstm.execute();
        } catch (Exception e) {
            System.out.println("error addSyllabus: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public String getNumberOfSyllabusForTeacher(String code, String name) {
        try {
            String strSelect;
            if (!code.equals("")) {
                strSelect
                        = "select count(sl.syllabus_id) from flm_db.syllabus sl \n"
                        + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                        + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                        + "where s.subject_code like ? and sl.is_active = 1 and sl.is_approved = 1;";
            } else {
                strSelect
                        = "select count(sl.syllabus_id) from flm_db.syllabus sl \n"
                        + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                        + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                        + "where sl.name like ? and sl.is_active = 1 and sl.is_approved = 1;";
            }
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            if (!code.equals("")) {

                pstm.setString(1, "%" + code + "%");
            } else {
                pstm.setString(1, "%" + name + "%");
            }
            ResultSet rs = pstm.executeQuery();
            String nunber = "0";
            while (rs.next()) {
                nunber = rs.getString(1);

            }
            return nunber;
        } catch (Exception e) {
            System.out.println("getNumberOfSyllabusForTeacher: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Syllabus> getListSyllabusByCodeForTeacher(String search, String start, String end) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect
                    = "select sl.syllabus_id, s.subject_code, s.subject_name, sl.name, sl.is_active, sl.is_approved, d.decision_no,d.decision_ApprovedDate from flm_db.syllabus sl \n"
                    + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                    + "where s.subject_code like ? and sl.is_active = 1 and sl.is_approved = 1 order by sl.syllabus_id asc limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            pstm.setInt(2, Integer.parseInt(start));
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                Decision d = new Decision();
                sl.setId(rs.getString(1));
                sl.setSubject(s);
                sl.setName(rs.getString(4));
                sl.setIsActive((rs.getString(5).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(6).equals("1")) ? "true" : "false");
                d.setDecision_no(rs.getString(7));
                d.setDecision_date(rs.getDate(8));
                sl.setDecision(d);
                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusByCodeForTeacher: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;

    }

    public ArrayList<Syllabus> getListSyllabusByNameForTeacher(String search, String start, String end) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect
                    = "select sl.syllabus_id, s.subject_code, s.subject_name, sl.name, "
                    + "sl.is_active, sl.is_approved, d.decision_no,d.decision_ApprovedDate from flm_db.syllabus sl \n"
                    + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                    + "where sl.name like ? and sl.is_active = 1 and sl.is_approved = 1 order by sl.syllabus_id asc limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            pstm.setInt(2, Integer.parseInt(start));
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                Decision d = new Decision();
                sl.setId(rs.getString(1));
                sl.setSubject(s);
                sl.setName(rs.getString(4));
                sl.setIsActive((rs.getString(5).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(6).equals("1")) ? "true" : "false");
                d.setDecision_no(rs.getString(7));
                d.setDecision_date(rs.getDate(8));
                sl.setDecision(d);
                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusByNameForTeacher: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfSyllabusForCrddStaff(String id, String code, String name) {
        try {
            String strSelect;
            if (!code.equals("")) {
                strSelect
                        = "select count(sl.syllabus_id) from flm_db.syllabus sl \n"
                        + " join flm_db.subject s on s.subject_id = sl.subject_id \n"
                        + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id\n"
                        + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                        + "where s.subject_code like ? and cs.curriculum_id in (select distinct c.curriculum_id from flm_db.curriculum c where owner_id = ? or create_id = ?);";
            } else {
                strSelect
                        = "select count(sl.syllabus_id) from flm_db.syllabus sl \n"
                        + " join flm_db.subject s on s.subject_id = sl.subject_id \n"
                        + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id\n"
                        + "join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                        + "where sl.name like ? and cs.curriculum_id in (select distinct c.curriculum_id from flm_db.curriculum c where owner_id = ? or create_id = ?);";
            }
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            if (!code.equals("")) {

                pstm.setString(1, "%" + code + "%");
                pstm.setString(2, id);
                pstm.setString(3, id);
            } else {
                pstm.setString(1, "%" + name + "%");
                pstm.setString(2, id);
                pstm.setString(3, id);
            }
            ResultSet rs = pstm.executeQuery();
            String nunber = "0";
            while (rs.next()) {
                nunber = rs.getString(1);

            }
            return nunber;
        } catch (Exception e) {
            System.out.println("getNumberOfSyllabusForCrddStaff: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Syllabus> getListSyllabusByNameForCrddStaff(String id, String search, String start, String end) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect
                    = "select sl.syllabus_id, s.subject_code, s.subject_name, sl.name,sl.is_active, sl.is_approved, d.decision_no,d.decision_ApprovedDate from flm_db.syllabus sl \n"
                    + " join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + " join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                    + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id\n"
                    + "where sl.name like ? and cs.curriculum_id in (select distinct c.curriculum_id from flm_db.curriculum c where owner_id = ? or create_id = ?) order by sl.syllabus_id asc limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);

            pstm.setString(1, "%" + search + "%");
            pstm.setString(2, id);
            pstm.setString(3, id);
            pstm.setInt(4, Integer.parseInt(start));
            pstm.setInt(5, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                Decision d = new Decision();
                sl.setId(rs.getString(1));
                sl.setSubject(s);
                sl.setName(rs.getString(4));
                sl.setIsActive((rs.getString(5).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(6).equals("1")) ? "true" : "false");
                d.setDecision_no(rs.getString(7));
                d.setDecision_date(rs.getDate(8));
                sl.setDecision(d);
                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusByNameForCrddStaff: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;

    }

    public ArrayList<Syllabus> getListSyllabusByCodeForCrddStaff(String id, String search, String start, String end) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect
                    = "select sl.syllabus_id, s.subject_code, s.subject_name, sl.name,sl.is_active, sl.is_approved, d.decision_no,d.decision_ApprovedDate from flm_db.syllabus sl \n"
                    + " join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + " join flm_db.decision d  on d.decision_id = sl.decision_id\n"
                    + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id\n"
                    + "where s.subject_code like ? and cs.curriculum_id in (select distinct c.curriculum_id from flm_db.curriculum c where owner_id = ? or create_id = ?) order by sl.syllabus_id asc limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);

            pstm.setString(1, "%" + search + "%");
            pstm.setString(2, id);
            pstm.setString(3, id);
            pstm.setInt(4, Integer.parseInt(start));
            pstm.setInt(5, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                Decision d = new Decision();
                sl.setId(rs.getString(1));
                sl.setSubject(s);
                sl.setName(rs.getString(4));
                sl.setIsActive((rs.getString(5).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(6).equals("1")) ? "true" : "false");
                d.setDecision_no(rs.getString(7));
                d.setDecision_date(rs.getDate(8));
                sl.setDecision(d);
                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusByCodeForCrddStaff: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public void addListSyllabus(ArrayList<Syllabus> myList) {
        try {
            String strSelect = "INSERT INTO `flm_db`.`syllabus` (`syllabus_id`, `name`, `no_of_credit`, `is_active`, `is_approved`, `degree_level`, `subject_id`, `created_by`, `created_at`, `decision_id`, `designer_id`) \n"
                    + "VALUES (?, ?, ?, b?, b?, ?,?, ?, ?, ?, ?);";
            String active = "1";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            for (Syllabus s : myList) {

                pstm.setString(1, s.getId());
                pstm.setString(2, s.getName());
                pstm.setString(3, s.getNoCredit());
                pstm.setString(4, active);
                pstm.setString(5, s.getIsApproved());
                pstm.setString(6, s.getDegreeLevel());
                pstm.setString(7, s.getSubjectid());
                pstm.setString(8, s.getCreateBy());
                pstm.setString(9, s.getCreateAt());
                pstm.setString(10, s.getDecision_id());
                if (s.getDesginerId().isEmpty()) {
                    pstm.setNull(11, java.sql.Types.VARCHAR);
                } else {
                    pstm.setString(11, s.getDesginerId());
                }
                pstm.addBatch();

            }

            // execute the remaining queries
            pstm.executeBatch();

        } catch (Exception ex2) {
            System.out.println("addListSyllabus" + ex2.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public ArrayList<Syllabus> getListSyllabusBySubjectCode(String selectSubject) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT s.syllabus_id, s.name FROM flm_db.syllabus s \n"
                    + "where s.subject_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, selectSubject);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                sl.setId(rs.getString(1));
                sl.setName(rs.getString(2));
                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusBySubjectCode: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Syllabus> getListSyllabusBySubject(String cId, String sId, String type) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect = "select sl.syllabus_id, s.subject_id, s.subject_code, s.subject_name, sl.name,sl.is_active, sl.is_approved from flm_db.syllabus sl \n"
                    + "join flm_db.subject s on s.subject_id = sl.subject_id \n"
                    + "where s.subject_id in (select s.subject_id from flm_db.subject s \n"
                    + "left join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "left join flm_db.group_curriculum gc on gc.group_id = cs.group_id and cs.curriculum_id = gc.curriculum_id\n"
                    + "left join flm_db.group g on g.group_id = gc.group_id left join flm_db.syllabus sl on sl.syllabus_id = cs.syllabus_id\n"
                    + "where cs.curriculum_id = ? and (s.subject_parent_id = ? or cs.subject_id = ?) and \n"
                    + "s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = ?));";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, sId);
            pstm.setString(3, sId);
            pstm.setString(4, type);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setId(rs.getString(2));
                s.setCode(rs.getString(3));
                s.setName(rs.getString(4));
                sl.setId(rs.getString(1));
                sl.setSubject(s);
                sl.setName(rs.getString(5));
                sl.setIsActive((rs.getString(6).equals("1")) ? "true" : "false");
                sl.setIsApproved((rs.getString(7).equals("1")) ? "true" : "false");

                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSyllabusBySubject: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

}
