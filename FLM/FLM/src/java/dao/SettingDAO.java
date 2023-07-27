/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Setting;

/**
 *
 * @author Admin
 */
public class SettingDAO extends BaseDAO {

    // list of role
    public ArrayList<Setting> getListRole() {
        ArrayList<Setting> list = new ArrayList<>();
        try {
            String strSelect
                    = "select setting_id, setting_name from setting where setting_type ='User Role';";

            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting(rs.getString(1), rs.getString(2).toLowerCase());
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListRole: " + e.getMessage());
        }
        finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Setting> getListSetting() {
        ArrayList<Setting> data = new ArrayList<Setting>();
        try {
            String strSelect = "SELECT setting_id,setting_name,setting_type,setting_value,display_order,is_active \n"
                    + "FROM flm_db.setting";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String type = rs.getString(3);
                String value = rs.getString(4);
                String displayOrder = rs.getString(5);
                String is_active = rs.getString(6);
                data.add(new Setting(id, name, type, value, displayOrder, is_active));
            }
        } catch (Exception e) {
            System.out.println("getListSetting:" + e.getMessage());
        }
        finally {
            closeConnection(connection);
        }
        return data;
    }

    public ArrayList<Setting> getAllType() {
        ArrayList<Setting> settings = new ArrayList<>();
        try {
            String strSelect = "SELECT DISTINCT setting_type FROM flm_db.setting;";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Setting setting = new Setting(rs.getString("setting_type"));
                settings.add(setting);
            }
        } catch (Exception e) {
            System.out.println("loi getAllType: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return settings;
    }

    public Setting getSettingBySettingID(String id) {
        Setting s = new Setting();
        try {
            String strSelect
                    = "select * from setting where setting_id =?";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                s.setId(rs.getString(1));
                s.setName(rs.getString(2));
                s.setType(rs.getString(3));
                s.setValue(rs.getString(4));
                s.setDisplayOrder(rs.getString(5));
                s.setDescription(rs.getString(6));
                s.setIs_active(rs.getString(7));
            }
        } catch (Exception e) {
            System.out.println("getSettingBySettingID: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return s;
    }

    public void UpdateSetting(Setting s) {
        try {
            String sql = "UPDATE `flm_db`.`setting` "
                    + "SET  `setting_name` = ?, `setting_type` = ?, `setting_value` = ?, `display_order` = ?, `setting_description` = ?, `is_active` = b? "
                    + "WHERE (`setting_id` = ?);";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, s.getName().toUpperCase());
            pstm.setString(2, s.getType());
            pstm.setString(3, s.getValue());
            pstm.setString(4, s.getDisplayOrder());
            pstm.setString(5, s.getDescription());
            pstm.setString(6, s.getIs_active());
            pstm.setString(7, s.getId());
            pstm.executeUpdate();
        } catch (Exception e) {
            System.out.println("UpdateSetting: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public int getMaxIdSetting() {
        int maxId = Integer.MIN_VALUE;
        try {
            String sql = "SELECT MAX(setting_id) AS maxId FROM setting";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                maxId = rs.getInt("maxId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection(connection);
        }
        return maxId;
    }

    public boolean addNewSetting(Setting setting) {
        boolean success = false;

        try {
            // Tạo câu lệnh SQL để thêm cài đặt mới
            String addSettingQuery = "INSERT INTO setting (setting_id, setting_name, setting_type, setting_value, display_Order, setting_description) VALUES (?, ?, ?, ?, ?, ?)";

            // Tạo PreparedStatement để thực thi câu lệnh SQL
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(addSettingQuery);
            int maxId = getMaxIdSetting();
            pstm.setInt(1, maxId + 1); // Gán giá trị id mới
            pstm.setString(2, setting.getName().toUpperCase());
            pstm.setString(3, setting.getType());
            pstm.setString(4, setting.getValue());
            pstm.setString(5, setting.getDisplayOrder());
            pstm.setString(6, setting.getDescription());
            // Thực thi câu lệnh SQL
            int rowsAffected = pstm.executeUpdate();
            // Kiểm tra xem có thành công không
            if (rowsAffected > 0) {
                success = true;
            }
        } catch (Exception e) {
            System.out.println("addNewSetting: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }

        return success;
    }

    public void deleteSettingById(String id) {
        try {
            String strSelect
                    = "delete from user_role \n"
                    + "where role_id =?;";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
            strSelect
                    = "delete from setting \n"
                    + "where setting_id = ?;";
            
            pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, id);
            pstm.execute();
        } catch (Exception e) {
            System.out.println("deleteSettingById: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
    }

    public boolean checkSettingNameByName(String name) {
        ResultSet rs;
        try {
            String query = "SELECT COUNT(*) FROM setting WHERE setting_name = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, name);
            rs = pstm.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (Exception e) {
            System.out.println("checkSettingNameByName: " + e.getMessage());
            return false;
        }finally {
            closeConnection(connection);
        }

    }

    public boolean checkSettingTypeByType(String type) {
        ResultSet rs;
        try {
            String query = "SELECT COUNT(*) FROM setting WHERE setting_type = ?";
            connection = getConnection();
            PreparedStatement pstm = connection.prepareStatement(query);
            pstm.setString(1, type);
            rs = pstm.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            return count > 0;
        } catch (Exception e) {
            System.out.println("checkSettingTypeByType: " + e.getMessage());
            return false;
        }finally {
            closeConnection(connection);
        }
    }

    public Setting getRoleNameIdUserGoogle() {
        try {
            String strSelect
                    = "SELECT setting_id, setting_name FROM setting where setting_name = 'STUDENT';";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Setting(rs.getString(1), rs.getString(2));
            }
        } catch (Exception e) {
            System.out.println("loi getRoleNameUser: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Setting> getListSubjectType() {
        ArrayList<Setting> list = new ArrayList<>();
        try {
            String strSelect
                    = "select setting_id, setting_name from setting where setting_type ='subject type';";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting(rs.getString(1), rs.getString(2).toLowerCase());
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSubjectType: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public Setting getSettingNameBySettingId(String settingid) {
        try {
            String strSelect = "SELECT * FROM flm_db.setting WHERE setting_id = ?;";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, settingid);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                return new Setting(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            }
        } catch (Exception e) {
            System.out.println("error getGroupNameByGroupId: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public ArrayList<Setting> getListSettingByType(String type) {
        ArrayList<Setting> list = new ArrayList<>();
        try {
            String strSelect
                    = "select setting_id, setting_name from setting where setting_type =?;";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, type);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Setting s = new Setting(rs.getString(1), rs.getString(2).toLowerCase());
                list.add(s);
            }
            return list;
        } catch (Exception e) {
            System.out.println("getListSettingByType: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;

    }

    public ResultSet getResultSetSettingByType(String type) {
        try {
            String strSelect
                    = "select setting_id, setting_name from setting where setting_type =?;";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, type);
            ResultSet rs = pstm.executeQuery();

            return rs;
        } catch (Exception e) {
            System.out.println("getResultSetSettingByType: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return null;
    }

    public boolean checkDegreeId(String degree) {
        String type = "Degree Level";
        try {
            String strSelect
                    = "select setting_id, setting_name from setting where setting_type =? and setting_id = ?;";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            pstm.setString(1, type);
            pstm.setString(2, degree);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("getResultSetSettingByType: " + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return false;
    }

    public ArrayList<Setting> getListSettingForSearch(String search) {
        ArrayList<Setting> data = new ArrayList<Setting>();
        try {
            String strSelect = "SELECT setting_id,setting_name,setting_type,setting_value,display_order,is_active \n"
                    + "FROM flm_db.setting where setting_name like '%" + search + "%' or setting_type like '%" + search + "%'";
            connection = getConnection();            PreparedStatement pstm = connection.prepareStatement(strSelect);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                String id = rs.getString(1);
                String name = rs.getString(2);
                String type = rs.getString(3);
                String value = rs.getString(4);
                String displayOrder = rs.getString(5);
                String is_active = rs.getString(6);
                data.add(new Setting(id, name, type, value, displayOrder, is_active));
            }
        } catch (Exception e) {
            System.out.println("getListSettingForSearch:" + e.getMessage());
        }finally {
            closeConnection(connection);
        }
        return data;
    }

    public ArrayList<Setting> getListSettingByPage(ArrayList<Setting> listSetting, int start, int end) {
        ArrayList<Setting> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(listSetting.get(i));
        }
        return arr;
    }
}
