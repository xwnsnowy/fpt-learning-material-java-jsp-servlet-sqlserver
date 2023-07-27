/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Po;

/**
 *
 * @author Admin
 */
public class PoDAO extends BaseDAO {

    public ArrayList<Po> getListPoByCode(String Code) {
        ArrayList<Po> list = new ArrayList<>();
        try {
            String strSelect
                    = "select p.po_id,p.po_name,p.po_description,p.is_active from po as p join flm_db.curriculum as c\n"
                    + "on p.curriculum_id = c.curriculum_id\n"
                    + "where c.code = ?";

            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Code);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String po_id = rs.getString(1);
                String po_name = rs.getString(2);
                String po_description = rs.getString(3);
                String is_active = rs.getString(4);
                list.add(new Po(po_id, po_name, po_description, is_active));
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListPoByCode: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Po getPObyPoID(String po_id) {
        try {
            String strSelect = "select p.po_id,p.po_name,p.po_description,c.code,c.name, p.is_active from po as p join curriculum as c\n"
                    + "on p.curriculum_id = c.curriculum_id\n"
                    + "where p.po_id = ? ";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, po_id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Po(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("getPObyNamePoAndCodeCurriculum " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public void updatePOs(Po p) {
        try {
            String StrUpdate = "update flm_db.po \n"
                    + "	SET flm_db.po.po_description =?, flm_db.po.po_name =?, flm_db.po.is_active = b?\n"
                    + "	where flm_db.po.po_id=?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(StrUpdate);
            pstm.setString(1, p.getPo_description());
            pstm.setString(2, p.getPo_name());
            pstm.setString(3, p.getIs_Active());
            pstm.setString(4, p.getPo_id());
            pstm.execute();
        } catch (Exception e) {
            System.out.println("updatePOs " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void deletePoById(String idPo) {
        try {
            String strSelect
                    = "delete from flm_db.Po\n"
                    + "where flm_db.Po.Po_id = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, idPo);
            pstm.execute();

        } catch (Exception e) {
            System.out.println("deletePoById: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public void addPoByCcode(String po_id, String po_name, String po_description, String Ccode, String is_Active) {
        try {
            String strAdd = "INSERT INTO flm_db.`po` (po_id, po_name, po_description, curriculum_id, is_active)\n"
                    + "SELECT ?, ?, ?, c.curriculum_id,b'1'\n"
                    + "FROM flm_db.curriculum c\n"
                    + "WHERE c.code = ?;";
            Po p = new Po();
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strAdd);
            pstm.setString(1, po_id);
            pstm.setString(2, po_name);
            pstm.setString(3, po_description);
            pstm.setString(4, Ccode);
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("addPoByCcode: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
    }

    public boolean checkIdPo(String PoID) {
        try {
            String strSelect = "select flm_db.Po.Po_id from flm_db.Po \n"
                    + "where flm_db.Po.Po_id =?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, PoID);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("checkIdPo: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return false;
    }

    public static void main(String[] args) {
        PoDAO pld = new PoDAO();
        ArrayList<Po> list = pld.getListPoByNameForCrddStaff("BBA_MKT_K16D17A", "po1");
        ArrayList<Po> list2 = pld.getListPoByPage(list, 2, 2);
        for (Po po : list2) {
            System.out.println(po.getPo_id());
            System.out.println(po.getPo_name());
            System.out.println(po.getPo_description());
        }
    }

    public int getMaxPoId() {
        int maxId = Integer.MIN_VALUE;
        try {
            String sql = "SELECT MAX(po_id) AS max_id FROM po;";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt(1);
            }
            rs.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }
        return maxId;
    }

    public ArrayList<Po> getListPoByNameForTeacher(String Ccode, String search) {
        ArrayList<Po> list = new ArrayList<>();
        try {
            String strSelect
                    = "select p.po_id,p.po_name,p.po_description from flm_db.po p join curriculum c on p.curriculum_id=c.curriculum_id   where c.code = ? and  p.po_name like '%" + search + "%'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Po p = new Po();
                p.setPo_id(rs.getString(1));
                p.setPo_name(rs.getString(2));
                p.setPo_description(rs.getString(3));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListPoByNameForTeacher: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Po> getListPoByPage(ArrayList<Po> ListPo, int start, int end) {
        ArrayList<Po> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(ListPo.get(i));
        }
        return arr;
    }

    public ArrayList<Po> getListPoByDescriptionForCrddStaff(String Ccode, String search) {
        ArrayList<Po> list = new ArrayList<>();
        try {
            String strSelect
                    = "select p.po_id,p.po_name,p.po_description,p.is_active from flm_db.po p join curriculum c on p.curriculum_id=c.curriculum_id   where c.code = ? and  p.po_description like '%" + search + "%'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Po p = new Po();
                p.setPo_id(rs.getString(1));
                p.setPo_name(rs.getString(2));
                p.setPo_description(rs.getString(3));
                p.setIs_Active(rs.getString(4));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListPoByDescriptionForCrddStaff: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Po> getListPoByDescriptionForTeacher(String Ccode, String search) {
        ArrayList<Po> list = new ArrayList<>();
        try {
            String strSelect
                    = "select p.po_id,p.po_name,p.po_description from flm_db.po p join curriculum c on p.curriculum_id=c.curriculum_id   where c.code = ? and  p.po_description like '%" + search + "%'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Po p = new Po();
                p.setPo_id(rs.getString(1));
                p.setPo_name(rs.getString(2));
                p.setPo_description(rs.getString(3));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListPoByDescriptionForTeacher: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;

    }

    public ArrayList<Po> getListPoByNameForCrddStaff(String Ccode, String search) {
        ArrayList<Po> list = new ArrayList<>();
        try {
            String strSelect
                    = "select p.po_id,p.po_name,p.po_description,p.is_active from flm_db.po p join curriculum c on p.curriculum_id=c.curriculum_id   where c.code = ? and  p.po_name like '%" + search + "%'";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, Ccode);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Po p = new Po();
                p.setPo_id(rs.getString(1));
                p.setPo_name(rs.getString(2));
                p.setPo_description(rs.getString(3));
                p.setIs_Active(rs.getString(4));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListPoByNameForCrddStaff: " + e.getMessage());
        } finally {
            closeConnection(connection);
        }
        return null;
    }
}
