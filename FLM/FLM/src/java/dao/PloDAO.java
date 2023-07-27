/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import model.Plo;
import model.User;

/**
 *
 * @author Admin
 */
public class PloDAO extends BaseDAO {

    PreparedStatement ps;
    ResultSet rs;

    public ArrayList<Plo> getListPloByCode(String Code) {
        ArrayList<Plo> list = new ArrayList<>();
        try {
            String strSelect
                    = "select p.plo_id,p.plo_name,p.plo_description,p.is_active, s.setting_name from plo as p join flm_db.curriculum as c\n"
                    + "                    on p.curriculum_id = c.curriculum_id join user u on u.user_id  = p.created_by join user_role ur on ur.user_id = u.user_id\n"
                    + "                    join setting s on s.setting_id = ur.role_id\n"
                    + "                    where c.code = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String plo_id = rs.getString(1);
                String plo_name = rs.getString(2);
                String plo_description = rs.getString(3);
                String is_active = rs.getString(4);
                String created_by = rs.getString(5);
                list.add(new Plo(plo_id, plo_name, plo_description, is_active, created_by));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListPloByCode: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Plo getPLObyPloID(String plo_id) {
        try {
            String strSelect = "select p.plo_id,p.plo_name,p.plo_description,c.code,c.name, p.is_active from plo as p join curriculum as c\n"
                    + "on p.curriculum_id = c.curriculum_id\n"
                    + "where p.plo_id = ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, plo_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Plo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));

            }
        } catch (Exception e) {
            System.out.println("getPLObyNamePloAndCodeCurriculum " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return null;
    }

    public void updatePLOs(Plo p) {
        try {
            String StrUpdate = "update flm_db.plo \n"
                    + "	SET flm_db.plo.plo_description =?, flm_db.plo.plo_name =?, flm_db.plo.is_active = b?\n"
                    + "	where flm_db.plo.plo_id=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(StrUpdate);
            pstm.setString(1, p.getPlo_description());
            pstm.setString(2, p.getPlo_name());
            pstm.setString(3, p.getIs_Active());
            pstm.setString(4, p.getPlo_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updatePLOs " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public void deletePloById(String idPlo) {
        try {
            String strSelect
                    = "delete from flm_db.plo\n"
                    + "where flm_db.plo.plo_id = ?";
            
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, idPlo);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("deletePloById: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public void addPloByCcode(String plo_id, String plo_name, String plo_description, String Ccode, String is_Active, String created_at, String created_by) {
        try {
            String strAdd = "INSERT INTO flm_db.`plo` (plo_id, plo_name, plo_description, is_active,created_at,created_by,curriculum_id )\n"
                    + "                    SELECT ?, ?, ?,b?,?,?,c.curriculum_id\n"
                    + "                    FROM flm_db.curriculum c\n"
                    + "                    WHERE c.code = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strAdd);
            pstm.setString(1, plo_id);
            pstm.setString(2, plo_name);
            pstm.setString(3, plo_description);
            pstm.setString(4, is_Active);
            pstm.setString(5, created_at);
            pstm.setString(6, created_by);
            pstm.setString(7, Ccode);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addPloByCcode: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public boolean checkIdPlo(String ploID) {
        try {
            String strSelect = "select flm_db.plo.plo_id from flm_db.plo \n"
                    + "where flm_db.plo.plo_id =?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, ploID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkIdPlo: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return false;
    }

//    public static void main(String[] args) {
//        PloDAO pld = new PloDAO();
//        pld.UpdateStatusPlo("1", "50");
//        ArrayList<Plo> list = pld.getListPloByCode("BBA_MKT_K16D17A");
//        System.out.println(list);
//
//    }

    public ArrayList<Plo> getListByPage(ArrayList<Plo> listPlo, int start, int end) {
        ArrayList<Plo> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(listPlo.get(i));
        }
        return arr;
    }

    public int getlastPloId() {
        try {
            String strSelect
                    = "select plo_id  from plo order by plo_id desc limit 1";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);

            }
        } catch (Exception e) {
            System.out.println("getlastPloId: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return -1;

    }

    public boolean checkNameExist(String Name, String Cid) {
        String sql = "SELECT p.plo_name FROM flm_db.plo p WHERE p.plo_name = ? and p.curriculum_id = ?";

        try {
            connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Name);
            ps.setString(2, Cid);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception ex) {
            System.out.println("checkNameExist" + ex.getMessage());
        }finally {
            closeConnection(connection);
        }
        return false;
    }

    public void UpdateStatusPlo(String active, String plo_id) {
        try {
            String StrUpdate = "UPDATE `flm_db`.`plo` SET `is_active` = b? WHERE (`plo_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(StrUpdate);
            pstm.setString(1, active);
            pstm.setString(2, plo_id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("UpdateStatusClo " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public ArrayList<Plo> getListPLOBySearch(String search, String Cid) {
        ArrayList<Plo> list = new ArrayList<>();
        try {
            String strSelect
                    = "SELECT p.plo_id,p.plo_name,p.plo_description,p.curriculum_id,p.is_active,p.created_at,s.setting_name FROM flm_db.plo p join\n"
                    + "user u on u.user_id  = p.created_by join user_role ur on ur.user_id = u.user_id\n"
                    + "join setting s on s.setting_id = ur.role_id\n"
                    + "where p.curriculum_id= ?  and p.plo_name like ?;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Cid);
            pstm.setString(2, "%" + search + "%");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String plo_id = rs.getString(1);
                String plo_name = rs.getString(2);
                String plo_discription = rs.getString(3);
                Cid = rs.getString(4);
                String is_active = rs.getString(5);
                String create_at = rs.getString(6);
                String create_by = rs.getString(7);
                list.add(new Plo(plo_id, plo_name, plo_discription, Cid, is_active, create_at, create_by));
            }
        } catch (Exception e) {
            System.out.println("getListPLOBySearch:" + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return list;
    }

    public void addListPlo(ArrayList<Plo> myList) {
        try {
            String sql = "insert into plo (plo_id,plo_name,plo_description,curriculum_id,is_active,created_at,created_by) values (?,?,?,?,b?,?,?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            for (Plo plo : myList) {
//                Row nextRow = rowIterator.next();
//                Iterator<Cell> cellIterator = nextRow.cellIterator();
                pstm.setString(1, plo.getPlo_id());
                pstm.setString(2, plo.getPlo_name());
                pstm.setString(3, plo.getPlo_description());
                pstm.setString(4, plo.getCurriculum_id());
                pstm.setString(5, plo.getIs_Active());
                pstm.setString(6, plo.getCreated_at());
                pstm.setString(7, plo.getCreated_by());
                pstm.addBatch();
            }
            // execute the remaining queries
            pstm.executeBatch();
        } catch (Exception ex) {
            System.out.println("addListPlo" + ex.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

}
