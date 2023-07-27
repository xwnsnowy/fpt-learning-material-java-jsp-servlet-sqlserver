/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.CurriculumSubject;
import model.Decision;
import model.Subject;
import model.Syllabus;

/**
 *
 * @author user
 */
public class CurriculumSubjectDAO extends BaseDAO {

    public ArrayList<CurriculumSubject> getSubjectCombo(String comboid) {
        ArrayList<CurriculumSubject> listComboDetail = new ArrayList<>();
        try {
            String strSelect = "SELECT cs.semester, s.subject_id, s.subject_code, s.subject_name, s.subject_description\n"
                    + "FROM curriculum_subject as cs join `subject` as s ON \n"
                    + "cs.subject_id = s.subject_id\n"
                    + "WHERE group_id = ? and curriculum_subject_is_active='1';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, comboid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listComboDetail.add(new CurriculumSubject(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5)));
            }
            return listComboDetail;
        } catch (Exception e) {
            System.out.println("error getComboDetailByUser: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<CurriculumSubject> getSubjectContentForAdmin(String contentgroupid, String start, String limit, String cid) {
        ArrayList<CurriculumSubject> listComboDetail = new ArrayList<>();
        try {
            String strSelect = "SELECT cs.curriculum_subject_is_active, cs.semester, \n"
                    + "s.subject_id, s.subject_code, s.subject_name, s.subject_description \n"
                    + "FROM curriculum_subject as cs join `subject` as s ON \n"
                    + "cs.subject_id = s.subject_id\n"
                    + "WHERE content_id = ? and cs.curriculum_id = ? limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, contentgroupid);
            pstm.setString(2, cid);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(3, startInt);
            pstm.setInt(4, Integer.parseInt(limit));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listComboDetail.add(new CurriculumSubject(rs.getString(1).equals("1") ? "TRUE" : "FALSE", rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return listComboDetail;
        } catch (Exception e) {
            System.out.println("error getSubjectContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<CurriculumSubject> getSubjectContent(String contentgroupid, String start, String limit, String cid) {
        ArrayList<CurriculumSubject> listComboDetail = new ArrayList<>();
        try {
            String strSelect = "SELECT cs.curriculum_subject_is_active, cs.semester, s.subject_id, s.subject_code, s.subject_name, s.subject_description \n"
                    + "FROM curriculum_subject as cs join `subject` as s ON \n"
                    + "cs.subject_id = s.subject_id\n"
                    + "WHERE content_id = ? and curriculum_subject_is_active='1' and cs.curriculum_id = ? limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, contentgroupid);
            pstm.setString(2, cid);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(3, startInt);
            pstm.setInt(4, Integer.parseInt(limit));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listComboDetail.add(new CurriculumSubject(rs.getString(1).equals("1") ? "TRUE" : "FALSE", rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
            return listComboDetail;
        } catch (Exception e) {
            System.out.println("error getSubjectContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
//    public static void main(String[] args) {
//        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
//
//        ArrayList<CurriculumSubject> listComboDetail = csDAO.getSubjectComboByAdmin("268");
//        for (CurriculumSubject curriculumSubject : listComboDetail) {
//            System.out.println("id" + curriculumSubject.getSubjectId());
//            System.out.println("code" + curriculumSubject.getSubjectCode());
//            System.out.println("name" + curriculumSubject.getSubjectName());
//
//        }
////        System.out.println(sd.getParentCodeByParentId("3"));
//    }
//-------------Trung------------------//

    public ArrayList<CurriculumSubject> getListSubject(String curriculumId, String semester, String search) {
        ArrayList<CurriculumSubject> listCSubject = new ArrayList<>();
        try {
            String strSelect = "select s.subject_id, s.subject_code, s.subject_name, cs.semester, cs.no_credit, cs.curriculum_subject_is_active from flm_db.subject s \n"
                    + "left join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "left join flm_db.group_curriculum gc on gc.group_id = cs.group_id and cs.curriculum_id = gc.curriculum_id\n"
                    + "left join flm_db.group g on g.group_id = gc.group_id\n"
                    + "where s.subject_parent_id is null and cs.curriculum_id = ?\n"
                    + "and cs.semester like ?\n"
                    + "and (s.subject_code like ?\n"
                    + "or s.subject_name like ?)\n"
                    + "order by cs.semester asc;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumId);
            pstm.setString(2, "%" + semester + "%");
            pstm.setString(3, "%" + search + "%");
            pstm.setString(4, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CurriculumSubject cS = new CurriculumSubject();
                cS.setSubjectId(rs.getString(1));
                cS.setSubjectCode(rs.getString(2));
                cS.setSubjectName(rs.getString(3));
                cS.setCurriculumSubjectSemester(rs.getString(4));
                cS.setCurriculumSubjectNoCredit(rs.getString(5));
                cS.setCurriculumSubjectIsActive((rs.getString(6).equals("1")) ? "true" : "false");
                listCSubject.add(cS);

            }
            return listCSubject;
        } catch (Exception e) {
            System.out.println("getListSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<CurriculumSubject> getListSemester(String curriculumId) {
        ArrayList<CurriculumSubject> list = new ArrayList<>();
        try {
            String strSelect = "select distinct semester from flm_db.curriculum_subject \n"
                    + "where curriculum_id = ? and semester is not null;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CurriculumSubject cS = new CurriculumSubject();
                cS.setCurriculumSubjectSemester(rs.getString(1));
                list.add(cS);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSemester: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updateCurriculumSubject(String subjectId, String curriculumId, String semester, String noCredit, String active, String syllabus, String content, String updateBy, String UpdateAt) {
        try {
            String strSelect = "update flm_db.curriculum_subject\n"
                    + "set semester = ?, \n"
                    + "no_credit = ?, \n"
                    + "curriculum_subject_is_active = b?,\n"
                    + "updated_by = ?,\n"
                    + "updated_at =?,\n"
                    + "syllabus_id=?,\n"
                    + "content_id=?\n"
                    + "where subject_id = ? and curriculum_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, semester);
            if (noCredit.isEmpty()) {
                pstm.setNull(2, java.sql.Types.INTEGER);
            } else {
                pstm.setString(2, noCredit);
            }
            pstm.setString(3, active);
            pstm.setString(4, updateBy);
            pstm.setString(5, UpdateAt);
            pstm.setString(8, subjectId);
            pstm.setString(9, curriculumId);
            if (syllabus.isEmpty()) {
                pstm.setNull(6, java.sql.Types.INTEGER);
            } else {
                pstm.setString(6, syllabus);
            }
            if (content.isEmpty()) {
                pstm.setNull(7, java.sql.Types.INTEGER);
            } else {
                pstm.setString(7, content);
            }

            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateCurriculumSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public CurriculumSubject getCurriculumSubject(String cId, String subject) {
        try {
            String strSelect = "select s.subject_code, s.subject_name, c.code, c.name, cs.semester, cs.no_credit, cs.curriculum_subject_is_active from flm_db.curriculum_subject cs\n"
                    + "join flm_db.curriculum c on cs.curriculum_id = c.curriculum_id\n"
                    + "join flm_db.subject s on s.subject_id = cs.subject_id\n"
                    + "where cs.curriculum_id = ? and cs.subject_id = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, subject);
            ResultSet rs = pstm.executeQuery();
            CurriculumSubject cS = new CurriculumSubject();
            while (rs.next()) {
                cS.setSubjectCode(rs.getString(1));
                cS.setSubjectName(rs.getString(2));
                cS.setCurriculumCode(rs.getString(3));
                cS.setCurriculumName(rs.getString(4));
                cS.setCurriculumSubjectSemester(rs.getString(5));
                cS.setCurriculumSubjectNoCredit(rs.getString(6));
                cS.setCurriculumSubjectIsActive(rs.getString(7));

            }
            return cS;
        } catch (Exception e) {
            System.out.println("getCurriculumSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
//    public static void main(String[] args) {
//        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
//
//        CurriculumSubject cs = csDAO.getCurriculumSubject("2","3");
//        System.out.println(cs.getCurriculumName());
////        System.out.println(sd.getParentCodeByParentId("3"));
//    }

    public ArrayList<CurriculumSubject> getListCombo(String cId, String subject, String typeSubject) {
        ArrayList<CurriculumSubject> list = new ArrayList<>();
        try {
            String strSelect = "select distinct cs.group_id, g.group_name from flm_db.subject s \n"
                    + "left join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "left join flm_db.group_curriculum gc on gc.group_id = cs.group_id and cs.curriculum_id = gc.curriculum_id\n"
                    + "left join flm_db.group g on g.group_id = gc.group_id\n"
                    + "where cs.curriculum_id = ? and (s.subject_parent_id = ? or cs.subject_id = ?) and \n"
                    + "s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, subject);
            pstm.setString(3, subject);
            pstm.setString(4, typeSubject);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CurriculumSubject cS = new CurriculumSubject();
                cS.setCurriculumSubjectGroupId(rs.getString(1));
                cS.setCurriculumName(rs.getString(2));
                list.add(cS);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListCombo: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Syllabus> getListSubjectOfCombo(String cId, String subject, String typeSubject) {
        ArrayList<Syllabus> list = new ArrayList<>();
        try {
            String strSelect = "select s.subject_code, s.subject_name, g.group_id, sl.syllabus_id, sl.name, sl.is_active, sl.is_approved, d.decision_no, d.decision_ApprovedDate, cs.content_id,s.subject_id from flm_db.subject s \n"
                    + "left join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "left join flm_db.group_curriculum gc on gc.group_id = cs.group_id and cs.curriculum_id = gc.curriculum_id\n"
                    + "left join flm_db.group g on g.group_id = gc.group_id left join flm_db.syllabus sl on sl.syllabus_id = cs.syllabus_id\n"
                    + "left join flm_db.decision d on d.decision_id = sl.decision_id\n"
                    + "where cs.curriculum_id = ? and (s.subject_parent_id = ? or cs.subject_id = ?) and \n"
                    + "s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, subject);
            pstm.setString(3, subject);
            pstm.setString(4, typeSubject);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Syllabus sl = new Syllabus();
                Subject s = new Subject();
                s.setCode(rs.getString(1));
                s.setName(rs.getString(2));
                s.setGroupId(rs.getString(3));
                s.setCurriculumId(rs.getString(10));
                s.setId(rs.getString(11));
                sl.setSubject(s);
                sl.setId(rs.getString(4));
                sl.setName(rs.getString(5));
                if (rs.getString(6) != null) {
                    sl.setIsActive((rs.getString(6).equals("1")) ? "true" : "false");
                } else {
                    sl.setIsActive(rs.getString(6));
                }
                if (rs.getString(7) != null) {
                    sl.setIsApproved((rs.getString(7).equals("1")) ? "true" : "false");
                } else {
                    sl.setIsApproved(rs.getString(7));
                }
                Decision d = new Decision();
                d.setDecision_no(rs.getString(8));
                d.setDecision_date(rs.getDate(9));
                sl.setDecision(d);

                list.add(sl);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSubjectOfCombo: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void addCurriculumSubject(String cId, String selectSubject, String selectSyllabus, String content, String semester, String noCredit, String active, String createBy, String createAt) {
        try {
            String strSelect = "INSERT INTO `flm_db`.`curriculum_subject` (`curriculum_id`, `subject_id`, \n"
                    + "                    `syllabus_id`, `content_id`, `semester`, `no_credit`, `curriculum_subject_is_active`, created_by, created_at)\n"
                    + "                    VALUES (?, ?, ?, ?, ?, ?, b?, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, selectSubject);

            if (selectSyllabus.isEmpty()) {
                pstm.setNull(3, java.sql.Types.INTEGER);
            } else {
                pstm.setString(3, selectSyllabus);
            }
            if (content.isEmpty()) {
                pstm.setNull(4, java.sql.Types.INTEGER);
            } else {
                pstm.setString(4, content);
            }
            pstm.setString(5, semester);
            pstm.setString(6, noCredit);
            pstm.setString(7, active);
            pstm.setString(8, createBy);
            pstm.setString(9, createAt);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("addCurriculumSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public ArrayList<CurriculumSubject> getListByPage(ArrayList<CurriculumSubject> listSubjectCombo, int start, int end) {
        ArrayList<CurriculumSubject> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(listSubjectCombo.get(i));
        }
        return arr;
    }

    //-------------Trung------------------//
    //-------------Linh-------------------//
    public ArrayList<CurriculumSubject> getListSubjectByCcode(String Ccode) {
        ArrayList<CurriculumSubject> listCSubject = new ArrayList<>();
        try {
            String settingName1 = "default";
            String settingName2 = "combo";
            String strSelect = "select  s.subject_code , s.subject_id from flm_db.subject s \n"
                    + "                    left join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "                    left join flm_db.group_curriculum gc on gc.group_id = cs.group_id and cs.curriculum_id = gc.curriculum_id\n"
                    + "                    left join flm_db.group g on g.group_id = gc.group_id\n"
                    + "                    where s.subject_type_id in (\n"
                    + "                    (select st.setting_id from flm_db.setting as st \n"
                    + "                     where st.setting_name = ? or st.setting_name = ?))\n"
                    + "                    and cs.curriculum_id = (select cc.curriculum_id from flm_db.curriculum as cc where cc.code = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, settingName1);
            pstm.setString(2, settingName2);
            pstm.setString(3, Ccode);

            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                CurriculumSubject cS = new CurriculumSubject();
                cS.setSubjectCode(rs.getString(1));
                cS.setSubjectId(rs.getString(2));
                listCSubject.add(cS);

            }
            return listCSubject;
        } catch (Exception e) {
            System.out.println("getListSubjectByCcode: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
//     public static void main(String[] args) {
//        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
//        ArrayList<CurriculumSubject> list = csDAO. getListSubjectByCcode("BIT_SE_K17B");
//         for (CurriculumSubject curriculumSubject : list) {
//             System.out.println(curriculumSubject.toString());
//         }
//    }

    public void updateContentInCurriculumSubject(String contentgroupid,
            String curriculumid, String subjectId, String updateAt, String updateBy) {
        try {
            String strUpdateCombo = "UPDATE `flm_db`.`curriculum_subject` "
                    + "SET `content_id` = ?, "
                    + "`updated_at` = ?, "
                    + "`updated_by` = ? "
                    + "WHERE (`curriculum_id` = ?) "
                    + "and (`subject_id` = ?);";
            connection = getConnection();

            PreparedStatement pstm = connection.prepareStatement(strUpdateCombo);
            pstm.setString(1, contentgroupid);
            pstm.setString(2, updateAt);
            pstm.setString(3, updateBy);
            pstm.setString(4, curriculumid);
            pstm.setString(5, subjectId);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error updateContentInCurriculumSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

//    public static void main(String[] args) {
//        CurriculumSubjectDAO csDAO = new CurriculumSubjectDAO();
//        csDAO.updateContentInCurriculumSubject("1111", "2", "1", "2023-06-25 02:08:37", "1");
//        csDAO.addSubjectAndContentToCurriculumSubject("1111", "2", "15", "2023-06-25 02:08:37", "1");
//    }
    public void addSubjectAndContentToCurriculumSubject(String contentgroupid, String curriculumid,
            String subjectId, String createAt, String createBy) {
        try {
            String strUpdateCombo = "INSERT INTO `flm_db`.`curriculum_subject` "
                    + "(`curriculum_id`, `subject_id`, "
                    + "`content_id`, `curriculum_subject_is_active`,"
                    + "`created_at`,`created_by`) "
                    + "VALUES (?, ?, ?, b'1', ?, ?);";
            connection = getConnection();

            PreparedStatement pstm = connection.prepareStatement(strUpdateCombo);
            pstm.setString(1, curriculumid);
            pstm.setString(2, subjectId);
            pstm.setString(3, contentgroupid);
            pstm.setString(4, createAt);
            pstm.setString(5, createBy);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addSubjectAndContentToCurriculumSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void deleteContentGroup(String subjectid, String curriculumid) {
        try {
            String strUpdate = "UPDATE `flm_db`.`curriculum_subject` "
                    + "SET `content_id` = NULL WHERE "
                    + "(`curriculum_id` = ?) and (`subject_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, curriculumid);
            pstm.setString(2, subjectid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error deleteContentGroup: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public boolean checkSubjectTypeIsCombo(String sId) {
        String type = "";
        try {
            String strSelect = "select cs.subject_id, st.setting_name from flm_db.curriculum_subject cs\n"
                    + "join flm_db.subject s on s.subject_id = cs.subject_id \n"
                    + "join flm_db.setting st on st.setting_id = s.subject_type_id\n"
                    + "where cs.subject_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, sId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                type = rs.getString(2);
            }
            if (type.equalsIgnoreCase("combo")) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkSubjectTypeIsCombo" + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

}
