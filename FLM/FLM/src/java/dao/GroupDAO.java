/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Elective;
import model.Group;
import model.Subject;

/**
 *
 * @author user
 */
public class GroupDAO extends BaseDAO {

    public void addElective(Elective e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Group> getListByPage(ArrayList<Group> listCombo, int start, int end) {
        ArrayList<Group> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(listCombo.get(i));
        }
        return arr;
    }

    public ArrayList<Group> getListComboByCurriculumId(String id) {
        ArrayList<Group> listComboByCurriculumId = new ArrayList<>();
        try {
            String strSelect = ""
                    + "SELECT `group`.group_id, `group`.group_name, group_curriculum.curriculum_id, "
                    + "`group`.combo_subject FROM flm_db.group \n"
                    + "join flm_db.group_curriculum ON `group`.group_id = group_curriculum.group_id \n"
                    + "WHERE group_curriculum.curriculum_id = ? and `group`.combo_subject='1'; ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listComboByCurriculumId.add(new Group(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return listComboByCurriculumId;
        } catch (Exception e) {
            System.out.println("error getListComboByCurriculumId " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getFullListCombo() {
        ArrayList<Group> listFullListCombo = new ArrayList<>();
        try {
            String strSelect = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id,\n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + " `group`.created_by, `group`.updated_at, `group`.updated_by FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='1';   ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listFullListCombo.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }
            return listFullListCombo;
        } catch (Exception e) {
            System.out.println("error getFullListCombo " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getFullListComboByName(String search) {
        ArrayList<Group> listFullListCombo = new ArrayList<>();
        try {
            String strSelect = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + " `group`.created_by, `group`.updated_at, `group`.updated_by FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='1' and `group`.group_name like ?;   ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listFullListCombo.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }
            return listFullListCombo;
        } catch (Exception e) {
            System.out.println("error getFullListCombo " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListContentGroup(String id) {
        ArrayList<Group> listContentGroup = new ArrayList<>();
        try {
            String strSelect = "SELECT DISTINCT `group`.group_id, `group`.group_name, group_curriculum.curriculum_id,\n"
                    + "`group`.combo_subject FROM flm_db.group \n"
                    + "join flm_db.group_curriculum ON `group`.group_id = group_curriculum.group_id \n"
                    + "join flm_db.`subject` ON `group`.group_id = `subject`.subject_group_id \n"
                    + "WHERE group_curriculum.curriculum_id = ? and `group`.combo_subject='0';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listContentGroup.add(new Group(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
            }
            return listContentGroup;
        } catch (Exception e) {
            System.out.println("error getListContentGroup " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Group getComboByComboId(String comboid) {
        try {
            String strSelect = "SELECT * FROM flm_db.group WHERE combo_subject = '1' and group_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, comboid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9));
            }
        } catch (Exception e) {
            System.out.println("loi getComboByComboId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Group checkDuplicateGroupId(String groupid) {
        try {
            String strSelect = "SELECT * FROM flm_db.group where group_id=?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, groupid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("error checkDuplicateGroupId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Group checkDuplicateComboName(String comboname) {
        try {
            String strSelect = "SELECT * FROM flm_db.group "
                    + "where group_name= ? "
                    + "and combo_subject = 1;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, comboname);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("error checkDuplicateGroupId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Group checkDuplicateContentName(String contentname) {
        try {
            String strSelect = "SELECT * FROM flm_db.group "
                    + "where group_name= ? "
                    + "and combo_subject = 0;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, contentname);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("error checkDuplicateContentName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Group getComboIdMax() {
        try {
            String strSelect = "SELECT MAX(group_id) from flm_db.group;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1));
            }
        } catch (Exception e) {
            System.out.println("error getComboIdMax: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void addCombo(String id, String groupname, String createAt, String createBy) {
        try {
            String strIns = "INSERT INTO `flm_db`.`group` (`group_id`, `group_name`, `combo_subject`,`is_active`, `created_at`, `created_by`)"
                    + " VALUES (?, ?, 1, 1, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, id);
            pstm.setString(2, groupname);
            pstm.setString(3, createAt);
            pstm.setString(4, createBy);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addCombo: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addComboToGroupCurriculum(String id, String curriculumidcombo, String createAt, String createBy) {
        try {
            String strIns = "INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`, `created_at`, `created_by`) "
                    + "VALUES (?, ? , 1, ? , ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, id);
            pstm.setString(2, curriculumidcombo);
            pstm.setString(3, createAt);
            pstm.setString(4, createBy);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addComboToGroupCurriculum: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public Group getComboByComboIdAdmin(String comboid) {
        try {
            String strSelect = "SELECT `group`.group_id, `group`.group_name, group_curriculum.curriculum_id, "
                    + "`group`.combo_subject FROM flm_db.group \n"
                    + "join flm_db.group_curriculum ON `group`.group_id = group_curriculum.group_id \n"
                    + "WHERE `group`.group_id = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, comboid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("loi getComboByComboId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updateCombo(String groupname, String active, String gId, String curriculumIdNew) {
        try {
            String strUpdateCombo = ""
                    + "UPDATE `flm_db`.`group` "
                    + "SET `group_name` = ?, "
                    + "`combo_subject` = b? "
                    + "WHERE (`group_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdateCombo);
            pstm.setString(1, groupname);
            pstm.setString(2, active);
            pstm.setString(3, gId);
            pstm.executeUpdate();
            String strUpdateComboToCurriculum = ""
                    + "UPDATE `flm_db`.`group_curriculum` "
                    + "SET `curriculum_id` = ? "
                    + "WHERE (`group_id` = ?);";
            pstm = connection.prepareStatement(strUpdateComboToCurriculum);
            pstm.setString(1, curriculumIdNew);
            pstm.setString(2, gId);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error updateCombo: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public ArrayList<Group> getListGroup() {
        ArrayList<Group> listGroup = new ArrayList<>();
        try {
            String strSelect = "SELECT * FROM flm_db.group;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listGroup.add(new Group(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            return listGroup;
        } catch (Exception e) {
            System.out.println("error getListGroup " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Group getGroupNameByGroupId(String groupid) {
        try {
            String strSelect = "SELECT * FROM flm_db.group WHERE group_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, groupid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("error getGroupNameByGroupId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Group getContentGroupById(String contentgroupid) {
        try {
            String strSelect = "SELECT * FROM flm_db.group WHERE group_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, contentgroupid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Group(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } catch (Exception e) {
            System.out.println("error getContentGroupById: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfCombosForAdmin(String search) {
        try {
            String strSelect
                    = "SELECT count(`group`.group_id) FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='1' and `group`.group_name like ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            String nunber = "0";
            while (rs.next()) {
                nunber = rs.getString(1);
            }
            return nunber;
        } catch (Exception e) {
            System.out.println("error getNumberOfCombosForAdmin: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListCombosByName(String search, String start, String end) {
        ArrayList<Group> listCombosByName = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + "`group`.created_by, `group`.updated_at, `group`.updated_by FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='1' and `group`.group_name like ? limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, "%" + search + "%");
            pstm.setInt(2, Integer.parseInt(start));
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listCombosByName.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9)));
            }
            return listCombosByName;
        } catch (Exception e) {
            System.out.println("error getListCombosByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfContentForAdmin(String cId, String search) {
        try {
            String strSelect
                    = "SELECT count(`group`.group_id) FROM flm_db.group \n"
                    + "join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group_curriculum`.curriculum_id=? and `group`.group_name like ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfContent(String cId, String search) {
        try {
            String strSelect
                    = "SELECT count(`group`.group_id) FROM flm_db.group \n"
                    + "join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' "
                    + "and `group_curriculum`.curriculum_id=? and"
                    + "`group`.is_active = '1' and `group`.group_name like ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfAllContent(String cId) {
        try {
            String strSelect
                    = "SELECT count(`group`.group_id) FROM flm_db.group \n"
                    + "join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group`.is_active ='1'  "
                    + "and `group_curriculum`.curriculum_id=?"
                    + " and `group`.is_active = '1' and `group`.group_name like '%%';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfAllContentForAdmin(String cId) {
        try {
            String strSelect
                    = "SELECT count(`group`.group_id) FROM flm_db.group \n"
                    + "join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group_curriculum`.curriculum_id=? and `group`.group_name like '%%';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfSubjectContent(String contentid) {
        try {
            String strSelect
                    = "SELECT count(cs.subject_id) FROM curriculum_subject as cs join `subject` as s ON \n"
                    + "cs.subject_id = s.subject_id\n"
                    + "WHERE content_id = ? and cs.curriculum_subject_is_active = '1' and curriculum_subject_is_active='1';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, contentid);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfSubjectContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfSubjectContentForAdmin(String contentid) {
        try {
            String strSelect
                    = "SELECT count(cs.subject_id) FROM curriculum_subject as cs join `subject` as s ON \n"
                    + "cs.subject_id = s.subject_id\n"
                    + "WHERE content_id = ? and curriculum_subject_is_active='1';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, contentid);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfSubjectContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListContentsByName(String cId, String search, String start, String end) {
        ArrayList<Group> listContentsByName = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + "`group`.created_by, `group`.updated_at, `group`.updated_by, `group_curriculum`.is_active FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group_curriculum`.curriculum_id=?"
                    + " and `group`.is_active = '1' and `group`.group_name like ? limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, "%" + search + "%");
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(3, startInt);
            pstm.setInt(4, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listContentsByName.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10).equals("1") ? "TRUE" : "FALSE"));
            }
            return listContentsByName;
        } catch (Exception e) {
            System.out.println("error getListContentsByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListContentsByNameForAdmin(String cId, String search, String start, String end) {
        ArrayList<Group> listContentsByName = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + "`group`.created_by, `group`.updated_at, `group`.updated_by, `group_curriculum`.is_active FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group_curriculum`.curriculum_id=? and `group`.group_name like ? limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            pstm.setString(2, "%" + search + "%");
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(3, startInt);
            pstm.setInt(4, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listContentsByName.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10).equals("1") ? "TRUE" : "FALSE"));
            }
            return listContentsByName;
        } catch (Exception e) {
            System.out.println("error getListContentsByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListAllContentsForAdmin(String cId, String start, String end) {
        ArrayList<Group> listContentsByName = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + "`group`.created_by, `group`.updated_at, `group`.updated_by, `group_curriculum`.is_active FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group_curriculum`.curriculum_id=? "
                    + "and `group`.group_name like '%%' limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(2, startInt);
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listContentsByName.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10).equals("1") ? "TRUE" : "FALSE"));
            }
            return listContentsByName;
        } catch (Exception e) {
            System.out.println("error getListContentsByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListAllContents(String cId, String start, String end) {
        ArrayList<Group> listContentsByName = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + "`group`.created_by, `group`.updated_at, `group`.updated_by, `group_curriculum`.is_active FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group`.is_active = '1'"
                    + "  and `group_curriculum`.curriculum_id=? "
                    + "and `group`.group_name like '%%' limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, cId);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(2, startInt);
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listContentsByName.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10).equals("1") ? "TRUE" : "FALSE"));
            }
            return listContentsByName;
        } catch (Exception e) {
            System.out.println("error getListContentsByName: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfActiveContent(String curriculumid) {
        try {
            String strSelect
                    = "SELECT count(`group`.group_id) FROM flm_db.group \n"
                    + "join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group_curriculum`.curriculum_id=? "
                    + "and  `group_curriculum`.is_active = '1';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfActiveContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public String getNumberOfDeactiveContent(String curriculumid) {
        try {
            String strSelect
                    = "SELECT count(`group`.group_id) FROM flm_db.group \n"
                    + "join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' and `group_curriculum`.curriculum_id=? "
                    + "and  `group_curriculum`.is_active = '0';";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            ResultSet rs = pstm.executeQuery();
            String number = "0";
            while (rs.next()) {
                number = rs.getString(1);
            }
            return number;
        } catch (Exception e) {
            System.out.println("error getNumberOfActiveContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListActiveContents(String curriculumid, String start, String end) {
        ArrayList<Group> listContentsByName = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + "`group`.created_by, `group`.updated_at, `group`.updated_by,`group_curriculum`.is_active FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' \n"
                    + " and `group_curriculum`.curriculum_id=?\n"
                    + " and `group_curriculum`.is_active = '1' limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(2, startInt);
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listContentsByName.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10).equals("1") ? "TRUE" : "FALSE"));
            }
            return listContentsByName;
        } catch (Exception e) {
            System.out.println("error getListActiveContents: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Group> getListDeactiveContents(String curriculumid, String start, String end) {
        ArrayList<Group> listContentsByName = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT `group`.group_id, `group`.group_name, `group_curriculum`.curriculum_id, \n"
                    + "`group`.combo_subject,`group`.is_active, `group`.created_at,\n"
                    + "`group`.created_by, `group`.updated_at, `group`.updated_by,`group_curriculum`.is_active FROM flm_db.group \n"
                    + " join group_curriculum on `group`.group_id = `group_curriculum`.group_id\n"
                    + " WHERE `group`.combo_subject='0' \n"
                    + " and `group_curriculum`.curriculum_id=?\n"
                    + " and `group_curriculum`.is_active = '0' limit ?,?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, curriculumid);
            int startInt = Integer.parseInt(start);
            if (startInt < 0) {
                startInt = 0;
            }
            pstm.setInt(2, startInt);
            pstm.setInt(3, Integer.parseInt(end));
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                listContentsByName.add(new Group(
                        rs.getString(1), rs.getString(2),
                        rs.getString(3), rs.getString(4),
                        rs.getString(5).equals("1") ? "TRUE" : "FALSE", rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9), rs.getString(10).equals("1") ? "TRUE" : "FALSE"));
            }
            return listContentsByName;
        } catch (Exception e) {
            System.out.println("error getListActiveContents: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updateIsactiveContent(String contentgroupid, String updateAt, String updateBy) {
        try {
            String strUpdate = "UPDATE `flm_db`.`group_curriculum` \n"
                    + "SET `is_active` = b'1',\n"
                    + " `updated_at` = ?, \n"
                    + " `updated_by` = ?\n"
                    + " WHERE (`group_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, updateAt);
            pstm.setString(2, updateBy);
            pstm.setString(3, contentgroupid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error updateIsactiveContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void updateDeactiveContent(String contentgroupid, String updateAt, String updateBy) {
        try {
            String strUpdate = "UPDATE `flm_db`.`group_curriculum` \n"
                    + "SET `is_active` = b'0',\n"
                    + " `updated_at` = ?, \n"
                    + " `updated_by` = ?\n"
                    + " WHERE (`group_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, updateAt);
            pstm.setString(2, updateBy);
            pstm.setString(3, contentgroupid);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error updateDeactiveContent: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addContentGroupInCurriculum(String idContentGroup, String groupname, String curriculumid, String createAt, String createBy) {
        try {
            String strIns = "INSERT INTO `flm_db`.`group` (`group_id`, `group_name`,"
                    + " `combo_subject`,`is_active`, `created_at`, `created_by`)"
                    + " VALUES (?, ?, 0, 1, ?, ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strIns);
            pstm.setString(1, idContentGroup);
            pstm.setString(2, groupname);
            pstm.setString(3, createAt);
            pstm.setString(4, createBy);
            pstm.executeUpdate();
            String strIns2 = "INSERT INTO `flm_db`.`group_curriculum` (`group_id`, `curriculum_id`, `is_active`, `created_at`, `created_by`) "
                    + "VALUES (?, ? , 1, ? , ?);";
            pstm = connection.prepareStatement(strIns2);
            pstm.setString(1, idContentGroup);
            pstm.setString(2, curriculumid);
            pstm.setString(3, createAt);
            pstm.setString(4, createBy);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error addContentGroupInCurriculum: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void updateContentGroup(String name, String id) {
        try {
            String strUpdate = "UPDATE `flm_db`.`group` SET `group_name` "
                    + "= ? WHERE (`group_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strUpdate);
            pstm.setString(1, name);
            pstm.setString(2, id);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("error updateContentGroup: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public ArrayList<Elective> getListElectiveByCode(String Ccode) {
        ArrayList<Elective> list = new ArrayList<>();
        try {
            String strSelect = "SELECT distinct g.group_id,g.group_name,g.is_active FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where s.subject_type_id=6 and c.code=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String is_active = rs.getString(3);
                list.add(new Elective(id, name, is_active));
            }
            return list;
        } catch (Exception e) {
            System.out.println("error getListElectiveByCode " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Elective> getListElectiveByPage(ArrayList<Elective> ListElective, int start, int end) {
        ArrayList<Elective> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(ListElective.get(i));
        }
        return arr;
    }

    public ArrayList<Subject> getListSubjectElectiveByPage(ArrayList<Subject> ListSubject, int start, int end) {
        ArrayList<Subject> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(ListSubject.get(i));
        }
        return arr;
    }

    public ArrayList<Subject> getSubjectOfElectiveByElectiveId(String Electiveid) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect = "SELECT distinct s.subject_id,s.subject_code,s.subject_name,s.subject_is_active FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where s.subject_type_id=6 and g.group_id= ? ;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Electiveid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String code = rs.getString(2);
                String name = rs.getString(3);
                String is_active = rs.getString(4);
                list.add(new Subject(id, code, name, is_active));
            }
            return list;
        } catch (Exception e) {
            System.out.println("error getSubjectOfElectiveByElectiveId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;

    }

    public Elective getElectiveByElectiveId(String Electiveid) {
        try {
            String strSelect = "select * from flm_db.group where group_id = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Electiveid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Elective(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (Exception e) {
            System.out.println("loi getElectiveByElectiveId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updateElective(Elective e) {
        try {
            String StrUpdate = "UPDATE flm_db.group "
                    + "SET "
                    + "`group_name` = ?, "
                    + "`is_active` = b? "
                    + "WHERE (`group_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(StrUpdate);
            pstm.setString(1, e.getName());
            pstm.setString(2, e.getIs_active());
            pstm.setInt(3, e.getId());
            pstm.execute();
        } catch (Exception ex) {
            System.out.println("updateElective " + ex.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public ArrayList<Elective> getListElectiveByNameForTeacher(String Ccode, String search) {
        ArrayList<Elective> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct g.group_id,g.group_name,g.group_name FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where  s.subject_type_id=6 and c.code= ? and g.group_name like '%" + search + "%' ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Elective el = new Elective();
                el.setId(rs.getInt(1));
                el.setName(rs.getString(2));
                el.setSubject(rs.getString(3));
                list.add(el);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListElectiveByNameForTeacher: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Elective> getListElectiveByNameForCrddStaff(String Ccode, String search) {
        ArrayList<Elective> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct g.group_id,g.group_name,g.group_name,g.is_active FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where  s.subject_type_id=6 and c.code= ? and g.group_name like '%" + search + "%'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Elective el = new Elective();
                el.setId(rs.getInt(1));
                el.setName(rs.getString(2));
                el.setSubject(rs.getString(3));
                el.setIs_active(rs.getString(4));
                list.add(el);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListElectiveByNameForCrddStaff: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Elective> getListElectiveByIdForTeacher(String Ccode, String search) {
        ArrayList<Elective> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct g.group_id,g.group_name,g.group_name FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where  s.subject_type_id=6 and c.code= ? and g.group_id like '%" + search + "%'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Elective el = new Elective();
                el.setId(rs.getInt(1));
                el.setName(rs.getString(2));
                el.setSubject(rs.getString(3));
                list.add(el);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListElectiveByIdForTeacher: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Elective> getListElectiveByIdForCrddStaff(String Ccode, String search) {
        ArrayList<Elective> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct g.group_id,g.group_name,g.group_name,g.is_active FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where  s.subject_type_id=6 and c.code= ? and g.group_id like '%" + search + "%' ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Elective el = new Elective();
                el.setId(rs.getInt(1));
                el.setName(rs.getString(2));
                el.setSubject(rs.getString(3));
                el.setIs_active(rs.getString(4));
                list.add(el);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListElectiveByIdForCrddStaff: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListSubjectElectiveByNameForTeacher(int ElectiveId, String search) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct s.subject_id,s.subject_code,s.subject_name FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where s.subject_type_id=6 and g.group_id= ? and s.subject_name like '%" + search + "%' ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setInt(1, ElectiveId);
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
            System.out.println("getListElectiveByNameForTeacher: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListSubjectElectiveByNameForCrddStaff(int ElectiveId, String search) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct s.subject_id,s.subject_code,s.subject_name,s.subject_is_active FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where s.subject_type_id=6 and g.group_id= ? and s.subject_name like '%" + search + "%' ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setInt(1, ElectiveId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getString(1));
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                s.setIsActive(rs.getString(4));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSubjectElectiveByNameForCrddStaff: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListSubjectElectiveByIdForTeacher(String ElectiveId, String search) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct s.subject_id,s.subject_code,s.subject_name FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where s.subject_type_id=6 and g.group_id= ? and s.subject_id like '%" + search + "%' ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, ElectiveId);
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
            System.out.println("getListSubjectElectiveByIdForTeacher: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Subject> getListSubjectElectiveByIdForCrddStaff(String ElectiveId, String search) {
        ArrayList<Subject> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT distinct s.subject_id,s.subject_code,s.subject_name,s.subject_is_active FROM flm_db.group g \n"
                    + "inner join flm_db.group_curriculum gc on g.group_id=gc.group_id \n"
                    + "inner join flm_db.curriculum c on gc.curriculum_id=c.curriculum_id\n"
                    + "inner join flm_db.curriculum_subject cs on g.group_id=cs.group_id\n"
                    + "inner join flm_db.subject s on cs.subject_id=s.subject_id\n"
                    + "where s.subject_type_id=6 and g.group_id= ? and s.subject_id like '%" + search + "%' ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, ElectiveId);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Subject s = new Subject();
                s.setId(rs.getString(1));
                s.setCode(rs.getString(2));
                s.setName(rs.getString(3));
                s.setIsActive(rs.getString(4));
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSubjectElectiveByIdForCrddStaff: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
}
