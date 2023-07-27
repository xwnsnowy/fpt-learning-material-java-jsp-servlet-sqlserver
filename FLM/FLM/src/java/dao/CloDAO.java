/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Clo;
/**
 *
 * @author Admin
 */
public class CloDAO extends BaseDAO {

    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Clo> getListCloBySyllabusID(String syllabus_id) {
        ArrayList<Clo> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT c.clo_id,c.clo_name,c.clo_description,c.syllabus_id,c.is_active,c.created_at,s.setting_name FROM flm_db.clo c join\n"
                    + "user u on u.user_id  = c.created_by join user_role ur on ur.user_id = u.user_id\n"
                    + "                    join setting s on s.setting_id = ur.role_id\n"
                    + "where c.syllabus_id= ?"
                    + ";";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, syllabus_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = rs.getString(1);
                String clo_name = rs.getString(2);
                String clo_discription = rs.getString(3);
                syllabus_id = rs.getString(4);
                String is_active = rs.getString(5);
                String create_at = rs.getString(6);
                String create_by = rs.getString(7);
                list.add(new Clo(clo_id, clo_name, clo_discription, syllabus_id, is_active, create_at, create_by));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListCloBySyllabusID: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void addNewCLOs(String Newclo_id, String clo_name, String clo_description, String syllabus_id, String is_active, String created_at, String created_by) {
        try {
            String strAdd = "insert into clo (clo_id,clo_name,clo_description,syllabus_id,is_active,created_at,created_by) "
                    + "values (?,?,?,?,b?,?,?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strAdd);
            pstm.setString(1, Newclo_id);
            pstm.setString(2, clo_name);
            pstm.setString(3, clo_description);
            pstm.setString(4, syllabus_id);
            pstm.setString(5, is_active);
            pstm.setString(6, created_at);
            pstm.setString(7, created_by);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addNewCLOs: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public int getlastCloId() {
        try {
            String strSelect
                    = "select clo_id  from clo order by clo_id desc limit 1";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("getlastUserId: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return -1;
    }

    public Clo getCLObyCloID(String clo_id) {
        try {
            String strSelect = "SELECT * FROM flm_db.clo c where c.clo_id =?  ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, clo_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Clo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
                        rs.getString(5), rs.getString(6), rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println("getPLObyNamePloAndCodeCurriculum " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updateCLOs(Clo c) {
        try {
            String StrUpdate = "update flm_db.clo \n"
                    + "SET flm_db.clo.clo_description =?, flm_db.clo.clo_name =?, flm_db.clo.is_active = b?\n"
                    + "where flm_db.clo.clo_id=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(StrUpdate);
            pstm.setString(1, c.getClo_description());
            pstm.setString(2, c.getClo_name());
            pstm.setString(3, c.getIs_active());
            pstm.setString(4, c.getClo_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updateCLOs " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public ArrayList<Clo> getListCLOBySearch(String search, String SylId, String is_active) {
        ArrayList<Clo> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT c.clo_id,c.clo_name,c.clo_description,c.syllabus_id,c.is_active,c.created_at,s.setting_name FROM flm_db.clo c join\n"
                    + "                    user u on u.user_id  = c.created_by join user_role ur on ur.user_id = u.user_id\n"
                    + "                    join setting s on s.setting_id = ur.role_id\n"
                    + "                    where c.syllabus_id= ?  and c.clo_name like ? and c.is_active = ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, SylId);
            pstm.setString(2, "%" + search + "%");
            pstm.setString(3, is_active);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = rs.getString(1);
                String clo_name = rs.getString(2);
                String clo_discription = rs.getString(3);
                SylId = rs.getString(4);
                is_active = rs.getString(5);
                String create_at = rs.getString(6);
                String create_by = rs.getString(7);
                list.add(new Clo(clo_id, clo_name, clo_discription, SylId, is_active, create_at, create_by));
            }
        } catch (Exception e) {
            System.out.println("getListCLOBySearch:" + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return list;
    }

    public ArrayList<Clo> getListByPage(ArrayList<Clo> listClo, int start, int end) {
        ArrayList<Clo> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(listClo.get(i));
        }
        return arr;
    }

    public boolean checkSyllabusIdExist(String SyllabusID) {

        String sql = "SELECT syllabus_id FROM flm_db.syllabus WHERE syllabus_id = ?";

        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, SyllabusID);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Nếu tồn tại curriculumId trong cơ sở dữ liệu
                return true;
            }
        } catch (Exception ex) {
            System.out.println("checkSyllabusIdExist" + ex.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public void importClo(Clo clo) {
        String sql = ""
                + "INSERT INTO `flm_db`.`clo` (`clo_id`, `clo_name`, `clo_description`, `syllabus_id`, `is_active`) VALUES (?, ?, ?, ?, b?);";

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            ps = connection.prepareStatement(sql);
            ps.setString(1, clo.getClo_id());
            ps.setString(2, clo.getClo_name());
            ps.setString(3, clo.getClo_description());
            ps.setString(4, clo.getSyllabus_id());
            ps.executeUpdate();

            connection.commit();
            System.out.println("Clo added successfully.");
        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (Exception rollbackEx) {
                rollbackEx.printStackTrace();
            }
            System.out.println("Failed to add Clo. Transaction rolled back.");
        } finally {
            closeConnection(connection);
        }
    }

    public void addListClo(ArrayList<Clo> list) {
        try {
            String sql = "insert into clo (clo_id,clo_name,clo_description,syllabus_id,is_active,created_at,created_by) values (?,?,?,?,b'1',?,?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            for (Clo clo : list) {

//                Row nextRow = rowIterator.next();
//                Iterator<Cell> cellIterator = nextRow.cellIterator();
                pstm.setString(1, clo.getClo_id());
                pstm.setString(2, clo.getClo_name());
                pstm.setString(3, clo.getClo_description());
                pstm.setString(4, clo.getSyllabus_id());
                pstm.setString(5, clo.getCreated_at());
                pstm.setString(6, clo.getCreated_by());

                pstm.addBatch();

            }

            // execute the remaining queries
            pstm.executeBatch();

        } catch (Exception ex2) {
            System.out.println("addListClo" + ex2.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public boolean checkNameExist(String Name) {
        String sql = "SELECT c.clo_name FROM flm_db.clo c WHERE c.clo_name = ?";

        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("checkNameExist" + ex.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;

    }

    public ArrayList<Clo> getListStatus() {
        ArrayList<Clo> list = new ArrayList<>();
        try {
            String strSelect
                    = "select distinct c.is_active from flm_db.clo c;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {

                list.add(new Clo(rs.getString(1)));

            }
            return list;
        } catch (Exception e) {
            System.out.println("getListRole: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }


    public ArrayList<Clo> getListCLOBySearchNotActive(String search, String sylId) {
        ArrayList<Clo> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT c.clo_id,c.clo_name,c.clo_description,c.syllabus_id,c.is_active,c.created_at,s.setting_name FROM flm_db.clo c join\n"
                    + "                    user u on u.user_id  = c.created_by join user_role ur on ur.user_id = u.user_id\n"
                    + "                    join setting s on s.setting_id = ur.role_id\n"
                    + "                    where c.syllabus_id= ?  and c.clo_name like ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, sylId);
            pstm.setString(2, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String clo_id = rs.getString(1);
                String clo_name = rs.getString(2);
                String clo_discription = rs.getString(3);
                sylId = rs.getString(4);
                String is_active = rs.getString(5);
                String create_at = rs.getString(6);
                String create_by = rs.getString(7);
                list.add(new Clo(clo_id, clo_name, clo_discription, sylId, is_active, create_at, create_by));
            }
        } catch (Exception e) {
            System.out.println("getListCLOBySearch:" + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return list;
    }

    public void UpdateStatusClo(String active, String clo_id) {
        try {
            String StrUpdate = "UPDATE `flm_db`.`clo` SET `is_active` = b? WHERE (`clo_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(StrUpdate);
            pstm.setString(1, active);
            pstm.setString(2, clo_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("UpdateStatusClo " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }
    
}
