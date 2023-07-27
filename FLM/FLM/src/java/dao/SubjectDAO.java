/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Subject;

/**
 *
 * @author Admin
 */
public class SubjectDAO extends BaseDAO {

    public ArrayList<Subject> getListOfSubject(String search) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect
                    = "select s.subject_id, s.subject_code, s.subject_name, st.setting_name ,s.subject_is_active, s.subject_description, s.subject_parent_id, p.subject_code as parent, s.subject_group_id  from flm_db.subject s\n"
                    + "left join flm_db.subject p on s.subject_parent_id = p.subject_id  \n"
                    + "join flm_db.setting st on st.setting_id = s.subject_type_id \n"
                    + "where s.subject_code like ? or \n"
                    + "s.subject_name like ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            pstm.setString(2, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getString(1));
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                s.setType(rs.getString(4));
                s.setIsActive((rs.getString(5).equals("1")) ? "true" : "false");
                s.setDescription(rs.getString(6).replaceAll("\n", "<br>"));
                s.setParentId(rs.getString(7));
                s.setParentCode(rs.getString(8));
                s.setGroupId(rs.getString(9));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListOfSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updateSubjectById(String id, String name, String code, String type, String active, String parentSubjectId, String description) {
        try {
            String strSelect = "update flm_db.subject\n"
                    + "set subject.subject_code = ?,\n"
                    + "subject.subject_name = ?,\n"
                    + "subject.subject_is_active = b?,\n"
                    + "subject.subject_parent_id = ?,\n"
                    + "subject.subject_type_id = ?,\n"
                    + "subject.subject_description =?\n"
                    + "where subject.subject_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, code);
            pstm.setString(2, name);
            pstm.setString(3, active);
            if (parentSubjectId.isEmpty()) {
                pstm.setNull(4, java.sql.Types.VARCHAR);
            } else {
                pstm.setString(4, parentSubjectId);
            }
            pstm.setString(5, type);
            pstm.setString(6, description);
            pstm.setString(7, id);

            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateSubjectById: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

//    public void deleteSubjectById(String id) {
//        try {
//            String strSelect
//                    = "delete from flm_db.user_role \n"
//                    + "where user_id = ?;";
//            connection = getConnection();                        PreparedStatement pstm = connection.prepareStatement(strSelect);
//            pstm.setString(1, id);
//            pstm.execute();
//            strSelect = "delete from flm_db.user\n"
//                    + "where user_id = ?;";
//            pstm = connection.prepareStatement(strSelect);
//            pstm.setString(1, id);
//            pstm.execute();
//        } catch (Exception e) {
//            System.out.println("deleteUserByUserId: " + e.getMessage());
//        }
//    }
    public void addSubjectNoneParent(String id, String name, String code, String isActive, String description) {
        try {
            String strIns = "INSERT INTO flm_db.`subject` "
                    + "(subject_id, subject_code, subject_name, subject_is_active, subject_description) \n"
                    + "VALUES (?, ?, ?, b?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, id);
            pstm.setString(2, code);
            pstm.setString(3, name);
            pstm.setString(4, isActive);
            pstm.setString(5, description);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("loi addSubjectNoneParent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public Subject checkDuplicateIdSubject(String id) {
        try {
            String strSelect = "SELECT * FROM flm_db.subject where subject_id=?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("loi checkDuplicateIdSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Subject getSubjectIdMax() {
        try {
            String strSelect = "SELECT MAX(subject_id) from flm_db.subject;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("loi getSubjectIdMax: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

//    public static void main(String[] args) {
//        SubjectDAO sd = new SubjectDAO();
//        System.out.println(sd.getSubjectIdMax().getId());
//    }
    public Subject getParentCodeByParentId(String subject_id) {
        try {
            String strSelect = "SELECT * from flm_db.subject WHERE subject_id =?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subject_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("error getParentCodeByParentId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Subject checkDuplicateCodeSubject(String code) {
        try {
            String strSelect = "SELECT * FROM flm_db.subject where subject_code=?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("loi checkDuplicateCodeSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Subject checkDuplicateNameSubject(String name) {
        try {
            String strSelect = "SELECT * FROM flm_db.subject where subject_name=?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("error checkDuplicateNameSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListCodeIdSubject() {
        ArrayList<Subject> listCodeIdSubject = new ArrayList<>();
        try {
            String strSelect = "SELECT subject_id, subject_code FROM flm_db.subject;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listCodeIdSubject.add(new Subject(rs.getString(1), rs.getString(2)));
            }
            return listCodeIdSubject;
        } catch (Exception e) {
            System.out.println("error getListCodeIdSubject " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void addSubject(String id, String code, String name, String typeid, String active, String description, String parentid, String groupid) {
        try {
            String strIns = "INSERT INTO `flm_db`.`subject`"
                    + "(`subject_id`, `subject_code`, `subject_name`, "
                    + "`subject_type_id`, `subject_is_active`, `subject_description`, `subject_parent_id`, `subject_group_id`) "
                    + "VALUES (?, ?, ?, ?, b?, ?, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, id);
            pstm.setString(2, code);
            pstm.setString(3, name);
            pstm.setString(4, typeid);
            pstm.setString(5, active);
            pstm.setString(6, description);
            pstm.setString(7, parentid);
            pstm.setString(8, groupid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addSubjectNoneParent(String id, String code, String name, String typeid, String active, String description, String groupid) {
        try {
            String strIns = "INSERT INTO `flm_db`.`subject`"
                    + "(`subject_id`, `subject_code`, `subject_name`, "
                    + "`subject_type_id`, `subject_is_active`, `subject_description`, `subject_group_id`) "
                    + "VALUES (?, ?, ?, ?, b?, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, id);
            pstm.setString(2, code);
            pstm.setString(3, name);
            pstm.setString(4, typeid);
            pstm.setString(5, active);
            pstm.setString(6, description);
            pstm.setString(7, groupid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addSubjectNoneParent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addSubjectNoneGroup(String id, String code, String name, String typeid, String active, String description, String parentid) {
        try {
            String strIns = "INSERT INTO `flm_db`.`subject`"
                    + "(`subject_id`, `subject_code`, `subject_name`, "
                    + "`subject_type_id`, `subject_is_active`, `subject_description`, `subject_parent_id`) "
                    + "VALUES (?, ?, ?, ?, b?, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, id);
            pstm.setString(2, code);
            pstm.setString(3, name);
            pstm.setString(4, typeid);
            pstm.setString(5, active);
            pstm.setString(6, description);
            pstm.setString(7, parentid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addSubjectNoneGroup: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addSubjectNoneParentAndNoneGroup(String id, String code, String name, String typeid, String active, String description) {
        try {
            String strIns = "INSERT INTO `flm_db`.`subject`"
                    + "(`subject_id`, `subject_code`, `subject_name`, "
                    + "`subject_type_id`, `subject_is_active`, `subject_description`) "
                    + "VALUES (?, ?, ?, ?, b?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, id);
            pstm.setString(2, code);
            pstm.setString(3, name);
            pstm.setString(4, typeid);
            pstm.setString(5, active);
            pstm.setString(6, description);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addSubjectNoneParentAndNoneGroup: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

//    public static void main(String[] args) {
//        SubjectDAO sd = new SubjectDAO();
//        ArrayList<Subject> listParentIDSubject = sd.getListOfSubjectForHead();
//        for (Subject subject : listParentIDSubject) {
//            System.out.println(subject.getId());
//        }
//    }
    public ArrayList<Subject> getListOfSubjectForCuriculum(String curriculumId) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect = "select s.subject_id, s.subject_code, s.subject_name from flm_db.subject s\n"
                    + "where s.subject_id not in (select s.subject_id from flm_db.curriculum_subject cs\n"
                    + "join flm_db.subject s  on s.subject_id = cs.subject_id\n"
                    + "where cs.curriculum_id = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getString(1));
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println("error getListOfSubjectForCuriculum " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListByPage(ArrayList<Subject> listSubject, int start, int end) {
        ArrayList<Subject> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(listSubject.get(i));
        }
        return arr;
    }

    public ArrayList<Subject> getListSubjectContentGroup(String contentgroupid, String curriculumid) {
        ArrayList<Subject> listSubjectContentGroup = new ArrayList<>();
        try {
            String strSelect = "SELECT `subject`.subject_id, `subject`.subject_code, `subject`.subject_name, `subject`.subject_type_id,\n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id, `subject`.subject_group_id\n"
                    + " FROM flm_db.`subject` join flm_db.curriculum_subject\n"
                    + "on `subject`.subject_id = `curriculum_subject`.subject_id\n"
                    + "WHERE `subject`.subject_group_id= ? and `curriculum_subject`.curriculum_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, contentgroupid);
            pstm.setString(2, curriculumid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listSubjectContentGroup.add(new Subject(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8)));
            }
            return listSubjectContentGroup;
        } catch (Exception e) {
            System.out.println("error getListSubjectContentGroup " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public boolean checkSubjectCode(String subject_code) {
        try {
            String strSelect = "SELECT * FROM flm_db.subject WHERE subject_code= ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subject_code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("error checkSubjectCode: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public Subject getSubjectCodeBySubjectId(String subject_id) {
        try {
            String strSelect = "SELECT `subject`.subject_id, `subject`.subject_code, \n"
                    + "`subject`.subject_name, `subject`.subject_type_id, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, \n"
                    + "`subject`.subject_parent_id, `subject`.subject_group_id \n"
                    + "FROM flm_db.`subject` WHERE subject_id = ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subject_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch (Exception e) {
            System.out.println("error getSubjectCodeBySubjectId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Subject getSubjectIdbySubjectCode(String code) {
        try {
            String strSelect = "SELECT `subject`.subject_id, `subject`.subject_code, "
                    + "`subject`.subject_name, `subject`.subject_type_id,\n"
                    + "`subject`.subject_is_active, `subject`.subject_description, "
                    + "`subject`.subject_parent_id, `subject`.subject_group_id\n"
                    + "FROM flm_db.`subject` \n"
                    + "WHERE subject_code = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getString(6), rs.getString(7), rs.getString(8));
            }
        } catch (Exception e) {
            System.out.println("error getSubjectCodeBySubjectId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListOfSubjectForStaff(String staffId) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect = "select distinct s.subject_id, s.subject_code, s.subject_name from flm_db.subject s \n"
                    + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "where cs.curriculum_id in (select distinct c.curriculum_id from flm_db.curriculum c where owner_id = ? or create_id = ?) \n"
                    + "and s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = 'default');";

            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, staffId);
            pstm.setString(2, staffId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getString(1));
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println(" getListOfSubjectForStaff " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListOfSubjectForHead() {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect = "select distinct s.subject_id, s.subject_code, s.subject_name from flm_db.subject s \n"
                    + "left join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "left join flm_db.group_curriculum gc on gc.group_id = cs.group_id and cs.curriculum_id = gc.curriculum_id\n"
                    + "left join flm_db.group g on g.group_id = gc.group_id\n"
                    + "where s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = 'default');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getString(1));
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println(" getListOfSubjectForHead " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListOfSubjectByCode(String search, String start, String end) {
        ArrayList<Subject> listOfSubject = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `subject`.subject_id, `subject`.subject_code,  `subject`.subject_name, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id, \n"
                    + "`curriculum_subject`.curriculum_id\n"
                    + "FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_code LIKE ?\n"
                    + " LIMIT ?,?;	";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(2, startInt);
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listOfSubject.add(new Subject(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4).equals("1") ? "TRUE" : "FALSE",
                        rs.getString(5) == null || rs.getString(5).isEmpty() ? "NONE" : rs.getString(5),
                        rs.getString(6), rs.getString(7)));
            }
            return listOfSubject;
        } catch (Exception e) {
            System.out.println("error getListOfSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListOfAllSubject(String start, String end) {
        ArrayList<Subject> listOfSubject = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `subject`.subject_id, `subject`.subject_code,  `subject`.subject_name, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id, \n"
                    + "`curriculum_subject`.curriculum_id\n"
                    + "FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_code LIKE '%%'\n"
                    + " LIMIT ?,?;	";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(1, startInt);
            pstm.setInt(2, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listOfSubject.add(new Subject(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4).equals("1") ? "TRUE" : "FALSE",
                        rs.getString(5) == null || rs.getString(5).isEmpty() ? "NONE" : rs.getString(5),
                        rs.getString(6), rs.getString(7)));
            }
            return listOfSubject;
        } catch (Exception e) {
            System.out.println("error getListOfAllSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListSubjectContentsByName(String search, String start, String end) {
        ArrayList<Subject> listOfSubject = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `subject`.subject_id, `subject`.subject_code,  `subject`.subject_name, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id, \n"
                    + "`curriculum_subject`.curriculum_id\n"
                    + "FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_name LIKE ?\n"
                    + " LIMIT ?,?;	";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(2, startInt);
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listOfSubject.add(new Subject(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4).equals("1") ? "TRUE" : "FALSE",
                        rs.getString(5) == null || rs.getString(5).isEmpty() ? "NONE" : rs.getString(5),
                        rs.getString(6), rs.getString(7)));
            }
            return listOfSubject;
        } catch (Exception e) {
            System.out.println("error getListSubjectContentsByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ResultSet getResultSetOfSubjectForStaff(String userId) {
        try {
            String strSelect = "select distinct s.subject_id, s.subject_code, s.subject_name from flm_db.subject s \n"
                    + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "where cs.curriculum_id in (select distinct c.curriculum_id from flm_db.curriculum c where owner_id = ? or create_id = ?) \n"
                    + "and s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = 'default');";

            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, userId);
            pstm.setString(2, userId);
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println(" getResultSetOfSubjectForStaff " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public boolean checkSubjectInCurriculum(String cId, String sId) {
        try {
            String strSelect = "SELECT `subject`.subject_id, `subject`.subjecT_code, `subject`.subject_name, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id \n"
                    + "FROM flm_db.subject join `curriculum_subject` on `subject`.subject_id = `curriculum_subject`.subject_id\n"
                    + "WHERE `subject`.subject_id = ? and `curriculum_subject`.curriculum_id = ?;	";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, sId);
            pstm.setString(2, cId);
            ResultSet rs = pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("error checkSubjectInCurriculum: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public boolean checkContentNone(String cId, String sId) {
        try {
            String strSelect = "SELECT `subject`.subject_id, `subject`.subjecT_code, `subject`.subject_name, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id \n"
                    + "FROM flm_db.subject join `curriculum_subject` on `subject`.subject_id = `curriculum_subject`.subject_id\n"
                    + "WHERE `subject`.subject_id = ? and `curriculum_subject`.curriculum_id = ?     \n"
                    + "AND `curriculum_subject`.content_id IS NULL \n"
                    + "AND `curriculum_subject`.group_id IS NULL;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, sId);
            pstm.setString(2, cId);
            ResultSet rs = pstm.executeQuery();
            return true;
        } catch (Exception e) {
            System.out.println("error checkComboAndContentNone: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public ResultSet getResultSetOfSubjectForHead() {

        try {
            String strSelect = "select distinct s.subject_id, s.subject_code, s.subject_name from flm_db.subject s \n"
                    + "left join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "left join flm_db.group_curriculum gc on gc.group_id = cs.group_id and cs.curriculum_id = gc.curriculum_id\n"
                    + "left join flm_db.group g on g.group_id = gc.group_id\n"
                    + "where s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = 'default');";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println(" getResultSetOfSubjectForHead " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public boolean checkSubjectId(String userId, String subjectId) {
        try {
            String strSelect = "select distinct s.subject_id, s.subject_code, s.subject_name from flm_db.subject s \n"
                    + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "where cs.curriculum_id in (select distinct c.curriculum_id from flm_db.curriculum c where owner_id = ? or create_id = ?) \n"
                    + "and s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = 'default') and s.subject_id = ?;";

            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, userId);
            pstm.setString(2, userId);
            pstm.setString(3, subjectId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(" checkSubjectId " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public boolean checkSubjectIdForHead(String subjectId) {
        try {
            String strSelect = "select distinct s.subject_id, s.subject_code, s.subject_name from flm_db.subject s \n"
                    + "join flm_db.curriculum_subject cs on cs.subject_id = s.subject_id \n"
                    + "where s.subject_type_id = (select st.setting_id from flm_db.setting st where st.setting_name = 'default') and s.subject_id = ?;";

            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subjectId);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println(" checkSubjectId " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public String getNumberOfAllSubject() {
        try {
            String strSelect
                    = "SELECT count(`subject`.subject_id) FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_code LIKE '%%' ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfAllSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfSubjectByCode(String search) {
        try {
            String strSelect
                    = "SELECT count(`subject`.subject_id) FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_code LIKE ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfSubjectByName(String search) {
        try {
            String strSelect
                    = "SELECT count(`subject`.subject_id) FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_name LIKE ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfSubjectByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListActiveSubjectContents(String start, String end) {
        ArrayList<Subject> listOfSubject = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `subject`.subject_id, `subject`.subject_code,  `subject`.subject_name, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id, \n"
                    + "`curriculum_subject`.curriculum_id\n"
                    + "FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_is_active = '1' \n"
                    + " LIMIT ?,?;	";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(1, startInt);
            pstm.setInt(2, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listOfSubject.add(new Subject(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4).equals("1") ? "TRUE" : "FALSE",
                        rs.getString(5) == null || rs.getString(5).isEmpty() ? "NONE" : rs.getString(5),
                        rs.getString(6), rs.getString(7)));
            }
            return listOfSubject;
        } catch (Exception e) {
            System.out.println("error getListActiveSubjectContents: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListDeactiveSubjectContents(String start, String end) {
        ArrayList<Subject> listOfSubject = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `subject`.subject_id, `subject`.subject_code,  `subject`.subject_name, \n"
                    + "`subject`.subject_is_active, `subject`.subject_description, `subject`.subject_parent_id, \n"
                    + "`curriculum_subject`.curriculum_id\n"
                    + "FROM flm_db.subject \n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_is_active = '0' \n"
                    + " LIMIT ?,?;	";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(1, startInt);
            pstm.setInt(2, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listOfSubject.add(new Subject(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4).equals("1") ? "TRUE" : "FALSE",
                        rs.getString(5) == null || rs.getString(5).isEmpty() ? "NONE" : rs.getString(5),
                        rs.getString(6), rs.getString(7)));
            }
            return listOfSubject;
        } catch (Exception e) {
            System.out.println("error getListDeactiveSubjectContents: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfActiveSubjectContent() {
        try {
            String strSelect
                    = "SELECT count(`subject`.subject_id) FROM flm_db.subject\n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_is_active = '1';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfActiveSubjectContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfDeactiveSubjectContent() {
        try {
            String strSelect
                    = "SELECT count(`subject`.subject_id) FROM flm_db.subject\n"
                    + "JOIN `curriculum_subject` ON `subject`.subject_id = `curriculum_subject`.subject_id \n"
                    + "WHERE `subject_type_id` = '4' \n"
                    + " AND `subject`.subject_is_active = '0';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfDeactiveSubjectContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Subject getSubjectElectiveBySubjectId(String subjectid) {
        try {
            String strSelect = "SELECT subject_id,subject_code,subject_name,subject_is_active FROM flm_db.subject where subject_id=?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, subjectid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Subject(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("loi getSubjectElectiveBySubjectId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updateSubjectElectiveById(Subject s) {
        try {
            String strSelect = "UPDATE `flm_db`.`subject` "
                    + "SET "
                    + "`subject_code` = ?,"
                    + "`subject_name` = ?,"
                    + "`subject_is_active` = b? WHERE (`subject_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, s.getCode());
            pstm.setString(2, s.getName());
            pstm.setString(3, s.getIsActive());
            pstm.setString(4, s.getId());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateSubjectElectiveById: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public ArrayList<Subject> getAllSubject() {
        ArrayList<Subject> listOfSubject = new ArrayList<>();
        try {
            String strSelect = "SELECT subject_id,subject_code,subject_name,subject_is_active FROM flm_db.subject where subject_type_id = 6;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listOfSubject.add(new Subject(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return listOfSubject;
        } catch (Exception e) {
            System.out.println("error getAllSubject: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
}
